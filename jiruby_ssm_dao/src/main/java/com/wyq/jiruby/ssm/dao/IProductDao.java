package com.wyq.jiruby.ssm.dao;

import com.wyq.jiruby.ssm.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductDao {


    @Select("select * from product")
    public List<Product> findAll() throws Exception;

    @Select("select * from product where id=#{id}")
    public Product findByProductId(String id) throws Exception;

    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);
}
