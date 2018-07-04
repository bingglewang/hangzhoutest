/**  
 * @Title: OrderService.java  
 * @Package com.xhgjky.service  
 * @Description: TODO(order业务层)  
 * @author 王炳贵  
 * @date 2018年6月27日  
 * @version V1.0  
 */ 
package com.xhgjky.service;

import java.util.List;

import com.xhgjky.bean.Order;
import com.xhgjky.bean.User;
import com.xhgjky.dao.OrderDao;
import com.xhgjky.dao.UserDao;

/**  
 * @ClassName: OrderService  
 * @Description: TODO(order业务层)  
 * @author 王炳贵  
 * @date 2018年6月27日     
 */
public class OrderService {
	
    private  OrderDao orderDao;
	
	
	/**
	 * @return the orderDao
	 */
	public OrderDao getOrderDao() {
		return orderDao;
	}
	/**
	 * @param orderDao the orderDao to set
	 */
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	/**
	 * @Title: getAllOrder  
	 * @Description: TODO(查询所有的订单)  
	 * @param @return    参数  
	 * @return List<Order>    返回类型  
	 * @throws
	 */
	public List<Order> getAllOrder(){
		return orderDao.getAllOrder();
	}

	/**
	 * 查询所有的班组人员
	 * @param page
	 * @param pageSize
	 * @param value
	 * @return
	 */
	public List<Order> getQueryPageOrderList(int page,int pageSize,String value){
		return (List<Order>)orderDao.getQueryPageOrder(page,pageSize,value);
	}

	/**
	 * 根据搜索查询到的记录数
	 * @param value
	 * @return
	 */
	public Long getOrderCount(String value){
		return (Long)orderDao.getOrderCount(value);
	}

	/**
	 * 得到所有的订单
	 * @return
	 */
	public Long getTotalRecord(){
		return orderDao.getTotalRecord();
	}
	/**
	 * @Title: addOrder  
	 * @Description: TODO(添加订单)  
	 * @param @param order    参数  
	 * @return void    返回类型  
	 * @throws
	 */
	public void addOrder(Order order){
		orderDao.addOrder(order);
	}
	/**
	 * @Title: delOrdder  
	 * @Description: TODO(删除订单)  
	 * @param @param order    参数  
	 * @return void    返回类型  
	 * @throws
	 */
	public void delOrdder(Order order){
		orderDao.delOrder(order);
	}
	/**
	 * @Title: updOrder  
	 * @Description: TODO(更新订单)  
	 * @param @param order    参数  
	 * @return void    返回类型  
	 * @throws
	 */
	public void updOrder(Order order){
		orderDao.updOrder(order);
	}
	/**
	 * @Title: getById  
	 * @Description: TODO(根据订单id查询订单)  
	 * @param @param id
	 * @param @return    参数  
	 * @return Order    返回类型  
	 * @throws
	 */
	public Order getById(String id){
		return orderDao.getById(id);
	}
}
