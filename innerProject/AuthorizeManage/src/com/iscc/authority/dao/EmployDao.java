package com.iscc.authority.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.iscc.authority.entity.Employee;

public class EmployDao extends BaseDao{
	
	/**
	 * ���ݲ��ű�Ż�ȡ�����µ�����Ա��
	 * @param deptNo
	 * @return
	 * @throws Exception
	 */
	public List<Employee> getSubEmploy(String deptNo) throws Exception{
		String sql = "select *from temploy where deptno='"+deptNo+"'";
		this.OpenConncetion();
		PreparedStatement ps=connection.prepareStatement(sql);
		List<Employee> emList = new ArrayList<Employee>();
		try {
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Employee employee=new Employee();
				employee.setEno(rs.getString("eno"));
				employee.setDeptNo(rs.getString("deptno"));
				employee.setEname(rs.getString("ename"));
				employee.setSex(rs.getString("sex"));
				emList.add(employee);
			}
		} catch (Exception e) {
			throw e;
		}
		return emList;
	}

	/*public Employee getEmp(String empNo){
		return null;
		
	}*/
	
	/**
	 * ɾ���û�-��ɫ���¼
	 */
	public void delEmpTur(Employee emp) throws Exception{
		String sql = "delete from tuserrole tur where tur.eno='"+emp.getEno()+"'";
		this.OpenConncetion();
		PreparedStatement ps = connection.prepareStatement(sql);
		try {
			ps.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * ɾ��Ա��-Ȩ�ޱ�ļ�¼
	 * @param emp
	 */
	public void delEmpTea(Employee emp) throws Exception{
		String sql = "delete from temployauth tea where tea.eno='"+emp.getEno()+"'";
		this.OpenConncetion();
		PreparedStatement ps = connection.prepareStatement(sql);
		try {
			ps.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * ɾ���û����¼
	 * @param emp
	 */
	public void delTuser(Employee emp)throws Exception{
		String sql = "delete from tuser where tuser.eno='"+emp.getEno()+"'";
		this.OpenConncetion();
		PreparedStatement ps = connection.prepareStatement(sql);
		try {
			ps.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * ɾ����Ա���¼
	 * @param emp
	 */
	public void delTem(Employee emp) throws Exception{
		String sql = "delete from temploy tem where tem.eno='"+emp.getEno()+"'";
		this.OpenConncetion();
		PreparedStatement ps = connection.prepareStatement(sql);
		try {
			ps.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * ��ӹ�Ա��Ϣ
	 * @param eNo
	 * @param deptNo
	 * @param eName
	 * @param sex
	 */
	public void addEmploy(Employee employee) throws Exception{
		String sql = "insert into temploy values('"+employee.getEno()+"','"+employee.getDeptNo()+"','"+employee.getEname()+"','"+employee.getSex()+"')";
		this.OpenConncetion();
		PreparedStatement ps = connection.prepareStatement(sql);
		try {		
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * ���¹�Ա��Ϣ
	 * @param employee
	 */
	public void updateEmploy(Employee employee) throws Exception{
		String sql = "update temploy set ename='"+employee.getEname()+"',sex='"+employee.getSex()+"' where eno='"+employee.getEno()+"'";
		this.OpenConncetion();
		PreparedStatement ps = connection.prepareStatement(sql);
		try {
			ps.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
		
		
	}
}
