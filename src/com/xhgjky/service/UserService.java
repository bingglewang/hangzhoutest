/**  
 * @Title: UserService.java  
 * @Package com.xhgjky.service  
 * @Description: TODO(user业务层)  
 * @author 王炳贵  
 * @date 2018年6月27日  
 * @version V1.0  
 */ 
package com.xhgjky.service;

import java.util.List;

import com.xhgjky.bean.User;
import com.xhgjky.bean.UserOrder;
import com.xhgjky.dao.UserDao;

/**  
 * @ClassName: UserService  
 * @Description: TODO(user业务层)  
 * @author 王炳贵  
 * @date 2018年6月27日     
 */
public class UserService {
	
	private  UserDao userDao;
	
	/**
	 * @return the userDao
	 */
	public UserDao getUserDao() {
		return userDao;
	}
	/**
	 * @param userDao the userDao to set
	 */
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	/**
	 * @Title: getAllUser  
	 * @Description: TODO(查询所有的用户)  
	 * @param @return    参数  
	 * @return List<User>    返回类型  
	 * @throws
	 */
	public List<UserOrder> getAllUser(Long id){
		return userDao.getAllUser(id);
	}

	public List<User> getAllUserOrderId(){
		return userDao.getAllUserOrderId();
	}
	/**
	 * 得到用户表的所有记录数
	 * @return
	 */
	public Long getTotalRecord(String value){
		return userDao.getTotalRecord(value);
	}
	/**
	 * @Title: getAllUserList
	 * @Description: TODO(查询用户列表)
	 * @param @return    参数
	 * @return List<User>    返回类型
	 * @throws
	 */
	public List<User> getAllUserList(int page,int pageSize,String value){

		return (List<User>)userDao.getAllUserList(page,pageSize,value);
	}
	/**
	 * @Title: addUser  
	 * @Description: TODO(添加用户)  
	 * @param @param user    参数  
	 * @return void    返回类型  
	 * @throws
	 */
	public void addUser(User user){
		userDao.addUser(user);
	}
	/**
	 * @Title: delUser  
	 * @Description: TODO(删除用户)  
	 * @param @param user    参数  
	 * @return void    返回类型  
	 * @throws
	 */
	public void delUser(User user){
		userDao.delUser(user);
	}
	/**
	 * @Title: updUser  
	 * @Description: TODO(更新用户)  
	 * @param @param user    参数  
	 * @return void    返回类型  
	 * @throws
	 */
	public void updUser(User user){
		userDao.updUser(user);
	}
	/**
	 * @Title: getById  
	 * @Description: TODO(根据id查询用户)  
	 * @param @param id
	 * @param @return    参数  
	 * @return User    返回类型  
	 * @throws
	 */
	public User getById(Long id){
		return userDao.getById(id);
	}
	
}
