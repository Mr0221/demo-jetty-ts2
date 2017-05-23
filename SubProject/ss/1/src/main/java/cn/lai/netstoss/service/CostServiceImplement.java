package cn.lai.netstoss.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.lai.netstoss.dao.CostDao;

@Service("costService")
public class CostServiceImplement implements CostService {
	@Resource(name="costDao")
	private CostDao dao;
}
