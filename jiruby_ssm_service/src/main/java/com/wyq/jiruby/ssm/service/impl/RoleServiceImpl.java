package com.wyq.jiruby.ssm.service.impl;

import com.wyq.jiruby.ssm.dao.IRoleDao;
import com.wyq.jiruby.ssm.domain.Permission;
import com.wyq.jiruby.ssm.domain.Role;
import com.wyq.jiruby.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public Role findById(String id) throws Exception{

        return roleDao.findByRoleId(id);
    }

    @Override
    public List<Permission> findOtherPermissions(String id) throws Exception{

        return roleDao.findOtherPermissions(id);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] ids) {
        for (String permissionId : ids) {
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }
}
