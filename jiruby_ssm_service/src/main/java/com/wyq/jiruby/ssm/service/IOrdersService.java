package com.wyq.jiruby.ssm.service;

import com.wyq.jiruby.ssm.domain.Orders;

import java.util.List;

public interface IOrdersService {

    public List<Orders> findAll(int page, int size) throws Exception;

    Orders findById(String id);
}
