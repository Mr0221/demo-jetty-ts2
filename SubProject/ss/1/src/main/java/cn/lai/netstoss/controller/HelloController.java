package cn.lai.netstoss.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.lai.netstoss.Entity.Admin;
import cn.lai.netstoss.service.AdminService;



@Controller
@RequestMapping("/demo")
public class HelloController {

    @Resource(name="adminService")
    private AdminService service;

    @RequestMapping("/testAdmin.do")
    public String testAdmin(final HttpServletRequest request){
        final Admin a = service.findByCode("caocao");
        request.setAttribute("name", a.getName());
        request.setAttribute("phone", a.getTelephone());
        return "testAdmin";
    }
    public HelloController() {

    }

    @RequestMapping("/hello.do")
    public ModelAndView toHello(){
        System.out.println("HelloController::toHello()");
        return new ModelAndView("hello");
    }
}
