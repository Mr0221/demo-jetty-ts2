package cn.lai.netstoss.dao;

import cn.lai.netstoss.Entity.Admin;

public interface AdminDao {
	public Admin findByCode(String code);
}
