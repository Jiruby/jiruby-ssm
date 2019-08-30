package com.wyq.jiruby.ssm.domain;

import com.wyq.jiruby.ssm.utils.DateFormatUtils;

import java.util.Date;

public class SysLog {
    private String id;                          //主键 id
    private Date visitTime;                     //访问时间
    private String visitTimeStr;
    private String username;                    //访问者名字
    private String ip;                          //访问者IP地址
    private String url;                         //访问地址值
    private Long executionTime;                 //操作时间
    private String method;                      //访问的方法名

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    public String getVisitTimeStr() {
        visitTimeStr= DateFormatUtils.date2Str(visitTime);
        return visitTimeStr;
    }

    public void setVisitTimeStr(String visitTimeStr) {
        this.visitTimeStr = visitTimeStr;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Long executionTime) {
        this.executionTime = executionTime;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
