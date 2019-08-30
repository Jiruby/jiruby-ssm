package com.wyq.jiruby.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.wyq.jiruby.ssm.dao.ISysLogDao;
import com.wyq.jiruby.ssm.domain.SysLog;
import com.wyq.jiruby.ssm.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysLogServiceImpl implements ISysLogService {

    @Autowired
    private ISysLogDao sysLogDao;

    @Override
    public void save(SysLog sysLog) throws Exception{
        sysLogDao.save(sysLog);
    }

    @Override
    public List<SysLog> findAll(Integer page, Integer size) throws Exception {
        PageHelper.startPage(page,size);
        return sysLogDao.findAll();
    }
}
