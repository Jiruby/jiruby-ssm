package com.wyq.jiruby.ssm.service;

import com.wyq.jiruby.ssm.domain.Product;

import java.util.List;

public interface IProductService {

    List<Product> findAll() throws Exception;

    void save(Product product);
}
