package com.wyq.jiruby.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.wyq.jiruby.ssm.domain.Role;
import com.wyq.jiruby.ssm.domain.UserInfo;
import com.wyq.jiruby.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@SuppressWarnings("all")
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/addRoleToUser.do")
    public String  addRoleToUser(String userId,String[] ids) throws Exception{
        userService.addRoleToUser(userId,ids);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(String id) throws Exception {
        ModelAndView mv=new ModelAndView();
        //查找被操作的user对象
        UserInfo userInfo = userService.findById(id);
        //查找可以被添加的role数组
        List<Role> otherRoles= userService.findOtherRoles(id);
        mv.addObject("user",userInfo);
        mv.addObject("roleList",otherRoles);
        mv.setViewName("user-role-add");
        return mv;
    }

    @RequestMapping("/findAll.do")
    @Secured("ROLE_ADMIN")
    public ModelAndView findAll(@RequestParam(name = "page" ,required = true,defaultValue = "1") Integer page,
                                @RequestParam(name = "size" ,required = true,defaultValue = "4") Integer size) throws Exception {
        ModelAndView mv=new ModelAndView();
        List<UserInfo> userList= userService.findAll(page,size);
        PageInfo pageInfo=new PageInfo(userList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("user-list");
        return mv;
    }


    @RequestMapping("/save.do")
    public String save(UserInfo userInfo) throws Exception{
        userService.save(userInfo);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception {
        ModelAndView mv=new ModelAndView();
        UserInfo user=userService.findById(id);
        mv.addObject("user",user);
        mv.setViewName("user-show");
        return mv;
    }
}
