package com.wyq.jiruby.ssm.dao;

import com.wyq.jiruby.ssm.domain.Role;
import com.wyq.jiruby.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserDao {

    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property ="username",column = "username"),
            @Result(property ="email",column = "email"),
            @Result(property ="password",column = "password"),
            @Result(property ="phoneNum",column = "phoneNum"),
            @Result(property ="status",column = "status"),
            @Result(property ="roles",column = "id",
                    javaType = List.class,
                    many = @Many(select = "com.wyq.jiruby.ssm.dao.IRoleDao.findById"))
    })
    public UserInfo findByUsername(String username);

    @Select("select * from users")
    List<UserInfo> findAll();


    /**
     * //todo
     */
    @Select("select * from users where id=#{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property ="username",column = "username"),
            @Result(property ="email",column = "email"),
            @Result(property ="password",column = "password"),
            @Result(property ="phoneNum",column = "phoneNum"),
            @Result(property ="status",column = "status"),
            @Result(property ="roles",column = "id",
                    javaType = List.class,
                    many = @Many(select = "com.wyq.jiruby.ssm.dao.IRoleDao.findById"))
    })
    UserInfo findById(String id);

    @Insert("insert into users (username,email,password,phoneNum,status) values(#{username},#{email},#{password},#{phoneNum},#{status})")
    void save(UserInfo user);

    @Select("select * from role where id not in(select roleid  from users_role where userid=#{id})")
    List<Role> findOtherRoles(String id);

    @Insert("insert into users_role(userId,roleId) values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId);
}
