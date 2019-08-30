package com.wyq.jiruby.ssm.dao;

import com.wyq.jiruby.ssm.domain.Permission;
import com.wyq.jiruby.ssm.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRoleDao {

    /**
     *  查询该用户下的所有role
     * @param id   userId
     * @return
     */
    @Select("select * from role where id in (select roleId from users_role where userId= #{id})")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",
                    javaType = List.class,many = @Many(select = "com.wyq.jiruby.ssm.dao.IPermissionDao.findById")
            )
    })
    public List<Role> findById(String id);

    @Select("select * from role")
    public List<Role> findAll();


    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role);

    /**
     * 查询单个Role
     * @param id   roleId
     * @return
     */
    @Select("select * from role where Id=#{id}")
    Role findByRoleId(String id);


    /**
     * 根据roleId查询可以添加的PermissionList
     * @param id
     * @return
     */
    @Select("select * from permission where id not in (select permissionId from  role_permission where roleId = #{id})")
    List<Permission> findOtherPermissions(String id);


    /**
     * 为role角色添加权限
     * 向中间表插入数据即可
     * @param roleId
     * @param permissionId
     */
    @Insert("insert into role_permission(permissionId,roleId) values(#{permissionId},#{roleId})")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId);
}
