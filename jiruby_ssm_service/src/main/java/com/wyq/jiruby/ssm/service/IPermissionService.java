package com.wyq.jiruby.ssm.service;

import com.wyq.jiruby.ssm.domain.Permission;

import java.util.List;

public interface IPermissionService {
    List<Permission> finAll();

    void save(Permission permission);
}
