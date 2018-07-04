package com.xhgjky.dao.imp;

import java.util.List;

import com.xhgjky.bean.Order;
import com.xhgjky.bean.User;
import com.xhgjky.dao.DAO;
import com.xhgjky.dao.OrderDao;

public class OrderDaoImp extends DAO implements OrderDao {

	@Override
	public List<Order> getAllOrder() {
		// TODO Auto-generated method stub
		String hql = "select order from Order as order";
		List<Order> list = super.findObjs(hql);		
		return list;
	}

	@Override
	public List<Order> getQueryPageOrder(int page, int pageSize, String value) {
		String hql = "select order from Order as order where order.orderName like '%"+value+"%' order by order.orderId desc";
		//from User as user where user.id like '%"+id+"%'"
		List<Order> list=(List<Order>)super.queryByPage(hql,page,pageSize);
		return list;
	}

	@Override
	public Long getOrderCount(String value) {
		String hql = "select count(*) from Order where orderName like '%"+value+"%'";
		return (Long) super.getTotalRecord(hql);
	}

	@Override
	public Long getTotalRecord() {
		String hql = "select count(*) from Order";
		return (Long)super.getTotalRecord(hql);
	}

	@Override
	public void addOrder(Order order) {
		// TODO Auto-generated method stub
		super.add(order);
	}

	@Override
	public void delOrder(Order order) {
		// TODO Auto-generated method stub
		super.del(order);
	}

	@Override
	public void updOrder(Order order) {
		// TODO Auto-generated method stub
		super.upd(order);
	}

	@Override
	public Order getById(String id) {
		// TODO Auto-generated method stub
		String hql = "from Order where orderId=?";
		if(super.findObj(hql,id) == null)
			return null;
		return (Order)super.findObj(hql,id);
	}

}
