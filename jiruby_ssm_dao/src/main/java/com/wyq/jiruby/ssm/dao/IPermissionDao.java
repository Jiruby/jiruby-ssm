package com.wyq.jiruby.ssm.dao;

import com.wyq.jiruby.ssm.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPermissionDao {

    @Select("select * from permission")
    public List<Permission> findAll();

    @Insert("insert into permission (permissionname,url) values(#{permissionName},#{url})")
    void save(Permission permission);

    @Select("select * from permission where id in (select permissionid from role_permission where roleid=#{id})")
    public List<Permission> findById(String id);
}
