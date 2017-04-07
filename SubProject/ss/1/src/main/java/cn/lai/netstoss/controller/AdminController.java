package cn.lai.netstoss.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.lai.netstoss.Entity.Admin;
import cn.lai.netstoss.service.AdminService;

@Controller("ad")
public class AdminController {
    @Resource(name="adminService")
    private AdminService service;

    @RequestMapping("/testAdmin.do")
    public String testAdmin(final HttpServletRequest request){
        final Admin a = service.findByCode("1");
        request.setAttribute("name", a.getName());
        request.setAttribute("phone", a.getTelephone());
        return "testAdmin";
    }
}
