package com.wyq.jiruby.ssm.dao;

import com.wyq.jiruby.ssm.domain.Traveller;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITravellerDao {

    @Select("select * from traveller where id in ( select travellerid from order_traveller where orderid=#{id})")
    public List<Traveller> findByOrderId(String id);
}
