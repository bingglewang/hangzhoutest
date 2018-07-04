/**  
 * @Title: UserDaoImp.java  
 * @Package com.xhgjky.dao.imp  
 * @Description: TODO(dao层接口的实现类)  
 * @author 王炳贵  
 * @date 2018年6月27日  
 * @version V1.0  
 */ 
package com.xhgjky.dao.imp;

import java.util.List;

import com.xhgjky.bean.Order;
import com.xhgjky.bean.User;
import com.xhgjky.bean.UserOrder;
import com.xhgjky.dao.DAO;
import com.xhgjky.dao.UserDao;

/**  
 * @ClassName: UserDaoImp  
 * @Description: TODO(dao层接口的实现类)  
 * @author 王炳贵  
 * @date 2018年6月27日     
 */
public class UserDaoImp extends DAO implements UserDao {

	
	@Override
	public List<UserOrder> getAllUser(Long id) {
		// TODO Auto-generated method stub
		String hql = "select new com.xhgjky.bean.UserOrder(usr.userId,usr.userName,usr.orderId,odr.orderName) from User as usr , Order  as odr where usr.orderId=odr.orderId and usr.userId="+id;
		return (List<UserOrder>)super.findObjs(hql);
	}

	@Override
	public List<User> getAllUserOrderId() {
		String hql = "select user from User as user";
		if(super.findObjs(hql).size() > 0){
			return (List<User>)super.findObjs(hql);
		}
		return null;
	}

	@Override
	public Long getTotalRecord(String value) {
		String hql = "select count(*) from User where userName like '%"+value+"%'";
		return (Long) super.getTotalRecord(hql);
	}

	@Override
	public List<User> getAllUserList(int page, int pageSize,String value) {
		String hql = "select user from User as user where user.userName like '%"+value+"%' order by user.userId desc";
		//from User as user where user.id like '%"+id+"%'"
		List<User> list=(List<User>)super.queryByPage(hql,page,pageSize);
		return list;
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		super.add(user);
	}

	@Override
	public void delUser(User user) {
		// TODO Auto-generated method stub
		super.del(user);
	}
	
	@Override
	public void updUser(User user) {
		// TODO Auto-generated method stub
		super.upd(user);
	}
	
	@Override
	public User getById(Long id) {
		// TODO Auto-generated method stub
		String sql = "select * from User where userId="+id;
		return (User)super.executeSQL(sql, User.class);
	}

}
