package com.iscc.authority.biz;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import com.iscc.authority.dao.DepartmentDao;
import com.iscc.authority.entity.Department;
import com.iscc.authority.entity.Employee;
import com.iscc.authority.entity.User;
import com.iscc.util.DelException;

public class DepartmentBiz {

	/**
	 * ��ȡ�������ĸ�����
	 * @return
	 * @throws Exception
	 */
	public Department obtainRootDepart() throws Exception {
		DepartmentDao deptDao = new DepartmentDao();
		Department rootDept = null;
		try {
			rootDept = deptDao.obtainRootDepartment();
		} catch (Exception e) {
			throw e;
		} finally {
			deptDao.closeConnection();
		}

		return rootDept;
	}

	/**
	 * �ݹ��ѯ�����ӽ��
	 * 
	 * @param node
	 * @throws Exception
	 */
	public void getSubDept(DefaultMutableTreeNode node) throws Exception {
		Object obj = node.getUserObject();
		List<Department> listDept = null;
		List<Employee> listEmploy = null;
		if (obj instanceof Department) {
			Department dept = (Department) obj;
			DepartmentBiz deptBiz = new DepartmentBiz();
			EmployBiz employBiz = new EmployBiz();
			listDept = deptBiz.getSubDept(dept.getDeptNo());     // ���ݲ��ű�Ų��ҳ��ò����µ��Ӳ���List
			listEmploy = employBiz.getSubEmploy(dept.getDeptNo());// ���ݲ��ű�Ų�ѯ�ò����µ�Ա��list

			if (listDept != null) {
				for (Department d : listDept) {// ������ȡ���ӽ��list
					DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(d);// һ�����Ĵ�������node
					node.add(childNode);// ����ȡ�����ӽ�㣬�ӵ���Ӧ�ĸ������
					getSubDept(childNode);
				}
			}

			if (listEmploy != null) {
				for (Employee em : listEmploy) {
					DefaultMutableTreeNode emChildNode2 = new DefaultMutableTreeNode(em);
					node.add(emChildNode2);
				}
			}

		}
	}

	/**
	 * ����ĳ�����ŵĲ��źŻ�ȡ���Ӳ��ţ������ӵ��ӣ�
	 * @param deptNo
	 * @return
	 * @throws Exception
	 */
	public List<Department> getAllSubDept(String deptNo) throws Exception {
		DepartmentDao deptDao = new DepartmentDao();
		List<Department> list = new ArrayList<Department>();
		try {
			list = deptDao.getAllSubDept(deptNo);// ��ȡ����µ��Ӳ��ż���
		} catch (Exception e) {
			throw e;
		} finally {
			deptDao.closeConnection();
		}

		return list;
		
	}
	/**
	 * ����ĳ�����ŵĲ��źŻ�ȡ���Ӳ��ţ������ӵ��ӣ�
	 * 
	 * @param deptNo
	 * @return
	 * @throws Exception
	 */
	public List<Department> getSubDept(String deptNo) throws Exception {

		DepartmentDao deptDao = new DepartmentDao();
		List<Department> list = new ArrayList<Department>();
		try {
			list = deptDao.getSubDept(deptNo);// ��ȡ����µ��Ӳ��ż���
		} catch (Exception e) {
			throw e;
		} finally {
			deptDao.closeConnection();
		}

		return list;
	}
	
	/**
	 * ����Ӳ���
	 * @param deptNo
	 * @param deptName
	 * @param parentDept
	 * @throws Exception 
	 */
	public void addDepartment(String deptNo,String deptName,String parentDept,String deptDesc) throws Exception{
		DepartmentDao deptDao = new DepartmentDao();
		try {
			deptDao.addDepartment(deptNo, deptName, parentDept,deptDesc); 
		} catch (Exception e) {
			throw e;
		}finally{
			deptDao.closeConnection();
		}
	}
	
	/**
	 * ���ݲ��ű�Ų�ѯ����
	 * @param deptNo
	 * @return
	 */
	public Department getDept(String deptNo) throws Exception{
		DepartmentDao deptDao = new DepartmentDao();
		Department department;
		try {
			department = deptDao.getDept(deptNo);
		} catch (Exception e) {
			throw e;
		}finally{
			deptDao.closeConnection();
		}
		
		return department;
	}
	
	/**
	 * �޸Ĳ�����Ϣ
	 * @param dept
	 * @throws Exception
	 */
	public void updateDept(Department dept) throws Exception{
		DepartmentDao deptDao = new DepartmentDao();
		try {
			deptDao.updateDept(dept);
		} catch (Exception e) {
			throw e;
		}finally{
			deptDao.closeConnection();
		}
		
	}
	
	/**
	 * ɾ������
	 * @param dept
	 * @throws DelException
	 * @throws Exception
	 */
	public void delDept(Department dept) throws DelException,Exception{
		DepartmentDao deptDao = new DepartmentDao();
		try {
			deptDao.delDept(dept);
		} catch (DelException e) {
			throw new DelException();
		}catch (Exception e) {
			throw e;
		}finally{
			deptDao.closeConnection();
		}
	}
}
