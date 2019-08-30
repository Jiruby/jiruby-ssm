package com.wyq.jiruby.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.wyq.jiruby.ssm.dao.IUserDao;
import com.wyq.jiruby.ssm.domain.Role;
import com.wyq.jiruby.ssm.domain.UserInfo;
import com.wyq.jiruby.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo userInfo = userDao.findByUsername(username);

//        User user=new User(userInfo.getUsername(), "{noop}"+userInfo.getPassword(),getAuth(userInfo.getRoles()));
        User user=new User(userInfo.getUsername(), userInfo.getPassword(),userInfo.getStatus()==1,true,true,true,getAuth(userInfo.getRoles()));
        return user;
    }

    private List<SimpleGrantedAuthority> getAuth(List<Role> roleList){
        List<SimpleGrantedAuthority> list=new ArrayList<>();
        for (Role role : roleList) {
            SimpleGrantedAuthority role_user = new SimpleGrantedAuthority("ROLE_"+role.getRoleName());
            list.add(role_user);
        }
        return list;
    }

    @Override
    public List<UserInfo> findAll(int page, int size) throws Exception {
        PageHelper.startPage(page,size);
        return userDao.findAll();
    }

    @Override
    public UserInfo findById(String id) throws Exception{
        return userDao.findById(id) ;
    }

    @Override
    public void save(UserInfo userInfo) throws Exception {
        //加密
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userDao.save(userInfo);
    }

    @Override
    public List<Role> findOtherRoles(String id) throws Exception {

        return userDao.findOtherRoles(id);
    }

    @Override
    public void addRoleToUser(String userId, String[] ids) {
        for (String roleId : ids) {
            userDao.addRoleToUser(userId,roleId);
        }
    }
}
