package com.wyq.jiruby.ssm.service.impl;

import com.wyq.jiruby.ssm.dao.IPermissionDao;
import com.wyq.jiruby.ssm.domain.Permission;
import com.wyq.jiruby.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private IPermissionDao permissionDao;


    @Override
    public List<Permission> finAll() {
        return permissionDao.findAll();
    }

    @Override
    public void save(Permission permission) {
        permissionDao.save(permission);
    }
}
