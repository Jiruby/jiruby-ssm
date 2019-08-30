package com.wyq.jiruby.ssm.dao;

import com.wyq.jiruby.ssm.domain.Member;
import com.wyq.jiruby.ssm.domain.Orders;
import com.wyq.jiruby.ssm.domain.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrdersDao {

    @Select("select * from orders")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "product" ,column = "productId",
                    javaType = Product.class,
                    one=@One( select = "com.wyq.jiruby.ssm.dao.IProductDao.findByProductId"))
    })
    public List<Orders> findAll() throws Exception;

    @Select("select * from orders where id=#{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "product" ,column = "productId",
                    javaType = Product.class,
                    one=@One( select = "com.wyq.jiruby.ssm.dao.IProductDao.findByProductId")),
            @Result(property = "member" ,column = "memberId",
                    javaType = Member.class,
                    one=@One( select = "com.wyq.jiruby.ssm.dao.IMemberDao.findById")),
            @Result(property = "travellers" ,column = "id",
                    javaType = List.class,
                    many=@Many( select = "com.wyq.jiruby.ssm.dao.ITravellerDao.findByOrderId")),
    })
    public Orders findById(String id);
}
