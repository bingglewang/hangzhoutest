/**  
 * @Title: UserDao.java  
 * @Package com.xhgjky.dao  
 * @Description: TODO(用户表dao层的接口定义)  
 * @author 王炳贵  
 * @date 2018年6月27日  
 * @version V1.0  
 */ 
package com.xhgjky.dao;

import java.util.List;

import com.xhgjky.bean.User;
import com.xhgjky.bean.UserOrder;


/**  
 * @ClassName: UserDao  
 * @Description: TODO(用户表dao层的接口定义)  
 * @author 王炳贵  
 * @date 2018年6月27日    
 */
public interface UserDao {
	/**	 
	 * @Title: getAllUser  
	 * @Description: TODO(得到用户表的所有用户)  
	 * @param @return    参数  
	 * @return List<User>    返回类型  
	 * @throws
	 */
	public List<UserOrder> getAllUser(Long id);

	public List<User> getAllUserOrderId();
	/**
	 * 得到总共的记录数
	 * @return
	 */
	public Long getTotalRecord(String value);
	/**
	 * @Title: getAllUserList
	 * @Description: TODO(查询用户列表)
	 * @param @return   page ，pageSize参数
	 * @return List<User>    返回类型
	 * @throws
	 */
	public  List<User> getAllUserList(int page,int pageSize,String value);
	/**	 
	 * @Title: addUser  
	 * @Description: TODO(向用户表中添加数据)  
	 * @param @param user    参数  
	 * @return void    返回类型  
	 * @throws
	 */
	public void addUser(User user);
	/**
	 * @Title: delUser  
	 * @Description: TODO(向用户表中删除数据)  
	 * @param @param user    参数  
	 * @return void    返回类型  
	 * @throws
	 */
	public void delUser(User user);
	/**
	 * @Title: updUser  
	 * @Description: TODO(更新用户表)  
	 * @param @param user    参数  
	 * @return void    返回类型  
	 * @throws
	 */
	public void updUser(User user);
	/**
	 * @Title: getById  
	 * @Description: TODO(根据用户的id得到用户)  
	 * @param @param id
	 * @param @return    参数  
	 * @return User    返回类型  
	 * @throws
	 */
	public User getById(Long id);
}
