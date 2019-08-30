package com.wyq.jiruby.ssm.service;

import com.wyq.jiruby.ssm.domain.Role;
import com.wyq.jiruby.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {

    List<UserInfo> findAll(int page, int size) throws Exception;

    UserInfo findById(String id) throws Exception;

    void save(UserInfo userInfo) throws Exception;

    List<Role> findOtherRoles(String id) throws Exception;

    void addRoleToUser(String userId, String[] ids);
}
