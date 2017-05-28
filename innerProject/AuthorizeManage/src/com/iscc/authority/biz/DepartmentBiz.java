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
	 * 获取整个树的根部门
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
	 * 递归查询结点的子结点
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
			listDept = deptBiz.getSubDept(dept.getDeptNo());     // 根据部门编号查找出该部门下的子部门List
			listEmploy = employBiz.getSubEmploy(dept.getDeptNo());// 根据部门编号查询该部门下的员工list

			if (listDept != null) {
				for (Department d : listDept) {// 遍历获取的子结点list
					DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(d);// 一个个的创建部门node
					node.add(childNode);// 将获取到的子结点，加到相应的父结点下
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
	 * 根据某个部门的部门号获取其子部门（包含子的子）
	 * @param deptNo
	 * @return
	 * @throws Exception
	 */
	public List<Department> getAllSubDept(String deptNo) throws Exception {
		DepartmentDao deptDao = new DepartmentDao();
		List<Department> list = new ArrayList<Department>();
		try {
			list = deptDao.getAllSubDept(deptNo);// 获取结点下的子部门集合
		} catch (Exception e) {
			throw e;
		} finally {
			deptDao.closeConnection();
		}

		return list;
		
	}
	/**
	 * 根据某个部门的部门号获取其子部门（不含子的子）
	 * 
	 * @param deptNo
	 * @return
	 * @throws Exception
	 */
	public List<Department> getSubDept(String deptNo) throws Exception {

		DepartmentDao deptDao = new DepartmentDao();
		List<Department> list = new ArrayList<Department>();
		try {
			list = deptDao.getSubDept(deptNo);// 获取结点下的子部门集合
		} catch (Exception e) {
			throw e;
		} finally {
			deptDao.closeConnection();
		}

		return list;
	}
	
	/**
	 * 添加子部门
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
	 * 根据部门编号查询部门
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
	 * 修改部门信息
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
	 * 删除部门
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
