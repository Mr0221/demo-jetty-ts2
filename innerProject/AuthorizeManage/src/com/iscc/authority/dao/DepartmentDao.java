package com.iscc.authority.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.iscc.authority.entity.Department;
import com.iscc.authority.my.tree1;
import com.iscc.util.DelException;

public class DepartmentDao extends BaseDao{

	/**
	 * ��ȡ�������ĸ�Department
	 * @return
	 * @throws Exception
	 */
	public Department obtainRootDepartment() throws Exception{
		Department department = new Department();
		String sql = "select * from tdepartment where deptno='dept0'";
		this.OpenConncetion();
		PreparedStatement ps = connection.prepareStatement(sql);
		try {
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				department.setDeptNo("dept0");
				department.setDeptName(rs.getString("deptName"));
				department.setParentDept(rs.getString("parentdept"));	
				department.setDeptDesc(rs.getString("deptdesc"));
			}
		} catch (Exception e) {
		    throw e;
		}
		
		return department;
	}
	
	/**
	 * ����ĳ�����ŵĲ��źŻ�ȡ���Ӳ��ţ������ӵ��ӣ�
	 * @param deptNo
	 * @return
	 * @throws Exception
	 */
	public List<Department> getAllSubDept(String deptNo) throws Exception {
		String sql = "select * from tdepartment where deptno like '" + deptNo + "%' order by deptno";
		this.OpenConncetion();
		PreparedStatement ps = connection.prepareStatement(sql);
		
		List<Department> listDept=new ArrayList<Department>();
		try {
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Department department = new Department();
				department.setDeptNo(rs.getString("deptno"));
				department.setDeptName(rs.getString("deptname"));
				department.setParentDept(rs.getString("parentdept"));
				department.setDeptDesc(rs.getString("deptdesc"));
				listDept.add(department);
			}
		} catch (Exception e) {
			throw e;
		}
		
		return listDept;
	}
	/**
	 * ��ȡ�����µ��Ӳ���
	 * @param deptNo
	 * @return
	 * @throws Exception
	 */
	public List<Department> getSubDept(String deptNo) throws Exception{
		String sql = "select * from tdepartment where parentdept='" + deptNo + "' ";
		this.OpenConncetion();
		PreparedStatement ps = connection.prepareStatement(sql);
		
		List<Department> listDept=new ArrayList<Department>();
		try {
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Department department = new Department();
				department.setDeptNo(rs.getString("deptno"));
				department.setDeptName(rs.getString("deptname"));
				department.setParentDept(rs.getString("parentdept"));
				department.setDeptDesc(rs.getString("deptdesc"));
				listDept.add(department);
			}
		} catch (Exception e) {
			throw e;
		}
		return listDept;
	}
	
	/**
	 * ����Ӳ���
	 * @param deptNo
	 * @param deptName
	 * @param parentDept
	 * @param deptDesc
	 * @throws Exception
	 */
	public void addDepartment(String deptNo,String deptName,String parentDept,String deptDesc) throws Exception{
		String sql = "insert into tdepartment(deptno,deptname,parentdept,deptdesc) values('"+deptNo+"','"+deptName+"','"+parentDept+"','"+deptDesc+"')";
		this.OpenConncetion();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.executeUpdate();
	}
	
	/**
	 * ����ѡ�еĲ��ţ���ѯ������Ϣ
	 * @param deptNo
	 * @return
	 * @throws Exception
	 */
	public Department getDept(String deptNo) throws Exception{
		String sql ="select *from tdepartment where deptno='"+deptNo+"'";
		this.OpenConncetion();
		PreparedStatement ps = connection.prepareStatement(sql);
		Department department = new Department();
		try {
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				department.setDeptNo(rs.getString("deptno"));
				department.setDeptName(rs.getString("deptname"));
				department.setDeptDesc(rs.getString("deptdesc"));
			}
		} catch (Exception e) {
			throw e;
		}
		
		return department;
	}
	
	/**
	 * ���²���
	 * @param dept
	 * @throws Exception
	 */
	public void updateDept(Department dept) throws Exception{
		String sql = "update tdepartment t set deptno = '"+dept.getDeptNo()+"',deptname = '"+dept.getDeptName()+"',deptdesc = '"+dept.getDeptDesc()+"' where t.deptno='"+dept.getDeptNo()+"'";
		this.OpenConncetion();
		PreparedStatement ps = connection.prepareStatement(sql);
		try {
			ps.executeUpdate(sql);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * ɾ������
	 * @param dept
	 * @throws DelException
	 * @throws Exception
	 */
	public void delDept(Department dept) throws DelException,Exception{
		String sql = "delete from tdepartment t where t.deptno='"+dept.getDeptNo()+"'";
		this.OpenConncetion();
		PreparedStatement ps = connection.prepareStatement(sql);
		try {
			ps.executeUpdate();
		}catch (Exception e) {
		    throw new DelException();
		}
	}
}
