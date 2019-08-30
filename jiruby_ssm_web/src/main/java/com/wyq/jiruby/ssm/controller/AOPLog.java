package com.wyq.jiruby.ssm.controller;

import com.wyq.jiruby.ssm.domain.SysLog;
import com.wyq.jiruby.ssm.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class AOPLog {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISysLogService sysLogService;

    private Date visitTime;
    private Class excecutionClass;   //执行的类对象
    private Method executionMethod;  // 执行的方法对象

    @Before("execution(* com.wyq.jiruby.ssm.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        //获取访问时间
        visitTime = new Date();
        //获取访问的方法名称
        String methodName = jp.getSignature().getName();
        //获取访问的类对象
        excecutionClass = jp.getTarget().getClass();
        //获取方法对象
        //0.获取参数数组
        Object[] args = jp.getArgs();
        //1.参数表为空, 获取无参方法
        if (args.length == 0 || args == null) {
            executionMethod = excecutionClass.getMethod(methodName);
        } else {
            //2.获取有参方法
            //转换Class数组对象
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            executionMethod = excecutionClass.getMethod(methodName, classArgs);
        }
    }


    @After("execution(* com.wyq.jiruby.ssm.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
        if (excecutionClass !=SysLogController.class) {
            //获取类上的注解
            RequestMapping excecutionClassAnnotation = (RequestMapping) excecutionClass.getAnnotation(RequestMapping.class);
            if(excecutionClassAnnotation!=null){
                //获取方法上的注解
                RequestMapping executionMethodAnnotation = executionMethod.getAnnotation(RequestMapping.class);
                if(executionMethodAnnotation!=null){
                    //组装url地址
                    String url="";
                    url=excecutionClassAnnotation.value()[0]+executionMethodAnnotation.value()[0];
                    //访问者IP地址
                    String ip = request.getRemoteAddr();
                    //计算操作时间
                    Long executionTime=new Date().getTime()-visitTime.getTime();
                    //获取访问者姓名
                        //1.获取权限上下文对象
                    SecurityContext context = SecurityContextHolder.getContext();
                        //2.获取权限框架里的用户
                    User user = (User) context.getAuthentication().getPrincipal();
                        //3.获取用户名
                    String username = user.getUsername();
                    /*
                        封装数据到SysLog对象
                     */
                    SysLog sysLog=new SysLog();
                    sysLog.setExecutionTime(executionTime);
                    sysLog.setIp(ip);
                    sysLog.setMethod("类名"+excecutionClass.getName()+"方法名"+executionMethod.getName());
                    sysLog.setVisitTime(visitTime);
                    sysLog.setUrl(url);
                    sysLog.setUsername(username);
                    //存储到数据库
                    sysLogService.save(sysLog);
                }
            }
        }
    }
}
