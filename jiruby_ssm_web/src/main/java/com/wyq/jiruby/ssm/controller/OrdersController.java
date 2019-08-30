package com.wyq.jiruby.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.wyq.jiruby.ssm.domain.Orders;
import com.wyq.jiruby.ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@SuppressWarnings("all")
@RequestMapping("/orders")
@Controller
public class OrdersController {

    @Autowired
    private IOrdersService ordersService;

    //未分页的数据
   /* @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv=new ModelAndView();
        List<Orders> ordersList = ordersService.findAll();
        mv.addObject("ordersList",ordersList);
        mv.setViewName("orders-list");
        return mv;
    }*/

    //使用PageHelper处理过的分页数据
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page" ,required = true,defaultValue = "1") Integer page,
                                @RequestParam(name = "size" ,required = true,defaultValue = "4") Integer size
    ) throws Exception {
        ModelAndView mv=new ModelAndView();
        List<Orders> ordersList = ordersService.findAll(page,size);
        PageInfo pageInfo=new PageInfo(ordersList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-page-list");
        return mv;
    }

    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name = "id",required = true,defaultValue = "1") String id){
        ModelAndView mv=new ModelAndView();
        Orders orders=ordersService.findById(id);
        mv.addObject("orders",orders);
        mv.setViewName("orders-show");
        return mv;
    }
}
