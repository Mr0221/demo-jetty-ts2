package cn.lai.netstoss.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.lai.netstoss.Entity.Admin;
import cn.lai.netstoss.dao.AdminDao;

@Service("adminService")
public class AdminServiceImplement implements AdminService {
    @Resource(name="adminDao")
    private AdminDao dao;

    public Admin findByCode(final String code) {
        return dao.findByCode(code);
    }
}
