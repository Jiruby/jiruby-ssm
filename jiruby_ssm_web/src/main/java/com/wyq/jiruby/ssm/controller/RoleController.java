package com.wyq.jiruby.ssm.controller;

import com.wyq.jiruby.ssm.domain.Permission;
import com.wyq.jiruby.ssm.domain.Role;
import com.wyq.jiruby.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;


    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(String roleId,String[] ids){

        roleService.addPermissionToRole(roleId,ids);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(String id) throws Exception {
        ModelAndView mv=new ModelAndView();
        Role role=roleService.findById(id);
        List<Permission> permissionList= roleService.findOtherPermissions(id);
        mv.addObject("role",role);
        mv.addObject("permissionList",permissionList);
        mv.setViewName("role-permission-add");
        return mv;
    }

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv=new ModelAndView();
        List<Role> roleList=roleService.findAll();
        mv.addObject("roleList",roleList);
        mv.setViewName("role-list");
        return  mv;
    }

    @RequestMapping("/save.do")
    public String save(Role role){
        roleService.save(role);
        return "redirect:findAll.do";
    }
}
