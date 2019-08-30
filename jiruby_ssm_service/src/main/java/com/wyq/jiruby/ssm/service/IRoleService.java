package com.wyq.jiruby.ssm.service;

import com.wyq.jiruby.ssm.domain.Permission;
import com.wyq.jiruby.ssm.domain.Role;

import java.util.List;

public interface IRoleService {

    List<Role> findAll();

    void save(Role role);

    Role findById(String id) throws Exception;

    List<Permission> findOtherPermissions(String id) throws Exception;

    void addPermissionToRole(String roleId, String[] ids);
}
