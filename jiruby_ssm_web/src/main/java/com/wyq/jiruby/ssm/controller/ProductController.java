package com.wyq.jiruby.ssm.controller;

import com.wyq.jiruby.ssm.domain.Product;
import com.wyq.jiruby.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @RolesAllowed("ADMIN")
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        List<Product> productList = productService.findAll();
        ModelAndView mv=new ModelAndView();
        mv.addObject("productList",productList);
        mv.setViewName("product-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String Save(Product product) throws Exception{
        productService.save(product);
        return  "redirect:findAll.do";
    }
}
