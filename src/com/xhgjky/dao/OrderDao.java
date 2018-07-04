/**  
 * @Title: OrderDao.java  
 * @Package com.xhgjky.dao  
 * @Description: TODO(订单表dao层的接口定义)  
 * @author 王炳贵  
 * @date 2018年6月27日  
 * @version V1.0  
 */ 
package com.xhgjky.dao;

import java.util.List;

import com.xhgjky.bean.Order;

/**  
 * @ClassName: OrderDao  
 * @Description: TODO(订单表dao层的接口定义)  
 * @author 王炳贵  
 * @date 2018年6月27日     
 */
public interface OrderDao {
	/**
	 * @Title: getAllOrder  
	 * @Description: TODO(查询订单表中的所有订单)  
	 * @param @return    参数  
	 * @return List<Order>    返回类型  
	 * @throws
	 */
	public List<Order> getAllOrder();

	/**
	 * 查询所有的班组人员包括分页
	 * @param page
	 * @param pageSize
	 * @param value
	 * @return
	 */
	public List<Order> getQueryPageOrder(int page,int pageSize,String value);

	/**
	 * 根据搜索得到所有的班组人员数量
	 * @param value
	 * @return
	 */
	public Long getOrderCount(String value);
	/**
	 * 得到所有的记录数
	 * @return
	 */
	public Long getTotalRecord();
	/**
	 * @Title: addOrder  
	 * @Description: TODO(向订单表中添加订单)  
	 * @param @param order    参数  
	 * @return void    返回类型  
	 * @throws
	 */
	public void addOrder(Order order);
	/**
	 * @Title: delOrder  
	 * @Description: TODO(向订单表中删除订单)  
	 * @param @param order    参数  
	 * @return void    返回类型  
	 * @throws
	 */
	public void delOrder(Order order);
	/**
	 * @Title: updOrder  
	 * @Description: TODO(更新订单表中的数据)  
	 * @param @param order    参数  
	 * @return void    返回类型  
	 * @throws
	 */
	public void updOrder(Order order);
	/**
	 * @Title: getById  
	 * @Description: TODO(更具订单id得到订单)  
	 * @param @param id
	 * @param @return    参数  
	 * @return Order    返回类型  
	 * @throws
	 */
	public Order getById(String id);
}
