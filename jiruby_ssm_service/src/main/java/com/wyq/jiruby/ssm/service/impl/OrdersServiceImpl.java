package com.wyq.jiruby.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.wyq.jiruby.ssm.dao.IOrdersDao;
import com.wyq.jiruby.ssm.domain.Orders;
import com.wyq.jiruby.ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements IOrdersService {

    @Autowired
    private IOrdersDao ordersDao;

    @Override
    public List<Orders> findAll(int page, int size) throws Exception{
        PageHelper.startPage(page,size);
        return ordersDao.findAll();
    }

    @Override
    public Orders findById(String id) {

        return ordersDao.findById(id);
    }
}
