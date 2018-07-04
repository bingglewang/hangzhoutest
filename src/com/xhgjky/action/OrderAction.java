/**  
 * @Title: OrderAction.java  
 * @Package com.xhgjky.action  
 * @Description: TODO(订单action层)  
 * @author 王炳贵  
 * @date 2018年6月27日  
 * @version V1.0  
 */ 
package com.xhgjky.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.xhgjky.bean.Order;
import com.xhgjky.bean.User;
import com.xhgjky.service.OrderService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**  
 * @ClassName: OrderAction  
 * @Description: TODO(订单action层)  
 * @author 王炳贵  
 * @date 2018年6月27日     
 */
public class OrderAction extends ActionSupport implements ServletRequestAware, ServletResponseAware, ServletContextAware {
	
	private OrderService orderService;	
	
	private Order order;
	private String orderId;
	private String orderName;

	// 接收前端发送来的分页参数
	private int page = 1;
	private int limit = 2;
	//接受前端发来的搜索参数
	private String value="";

	//获取request response 等对象
	private HttpServletRequest request;

	private ServletContext servletContext;

	private HttpServletResponse response;
	
	
	public String getOrderById() {
		System.out.println("####"+orderId);
		order = orderService.getById(orderId);
		boolean code = true;
		if(order == null ){
			code = false;
		}
		Map<String,Object> result = new HashMap<String,Object>();
		//JSONArray array = JSONArray.fromObject(order);
		result.put("data",code);
		ActionContext.getContext().getValueStack().set("jsonData", JSONObject.fromObject(result));
		return "success";
	}
	public String getAllOrderList(){
		List<Order> allOrder = orderService.getAllOrder();
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("code",0);
		result.put("msg","");
		result.put("count",orderService.getTotalRecord());
		JSONArray array = JSONArray.fromObject(allOrder);
		//System.out.println("##################"+array);
		result.put("data",array);
		// 将其转换为JSON数据，并压入值栈返回
		ActionContext.getContext().getValueStack().set("jsonData", JSONObject.fromObject(result));
		return "success";
	}

	public String delCheckedOrder(){
		List<String> list =  JSONArray.fromObject(request.getParameter("orderIds"));
		Order delAll = new Order();
		for(String o : list){
			delAll.setOrderId(o);
			orderService.delOrdder(delAll);
		}
		return "success";
	}

	public String updOrder(){
		Order updOrder = new Order();
		updOrder.setOrderId(orderId);
		updOrder.setOrderName(orderName);
		orderService.updOrder(updOrder);
		return "upd";
	}

	public String delOrder(){
		System.out.println("+++"+orderId+"+++"+orderName);
		Order delOrder = new Order();
		delOrder.setOrderId(orderId);
		delOrder.setOrderName(orderName);
		orderService.delOrdder(delOrder);
		return "success";
	}
	public String addOrder(){
		//System.out.println("+++"+orderId+"+++"+orderName);
		Order addOrder = new Order();
		addOrder.setOrderId(orderId);
		addOrder.setOrderName(orderName);
		orderService.addOrder(addOrder);
		return "success";
	}

	public String findAll(){
		try{
			value = URLDecoder.decode(value , "utf-8");//编码解码
		}catch(Exception e){
			e.printStackTrace();
		}
		//System.out.println("***********"+page+"****"+limit+"****"+value);
		List<Order> allOrder = orderService.getQueryPageOrderList(page,limit,value);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("code",0);
		result.put("msg","");
		//System.out.println("***********");
		result.put("count",orderService.getOrderCount(value));
		//System.out.println("***********"+orderService.getOrderCount(value));
		JSONArray array = JSONArray.fromObject(allOrder);
		//System.out.println("##################"+array);
		result.put("data",array);
		// 将其转换为JSON数据，并压入值栈返回
		ActionContext.getContext().getValueStack().set("jsonData", JSONObject.fromObject(result));
		return "success";
	}
	
	public OrderService getOrderService() {
		return orderService;
	}
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	@Override
	public void setServletResponse(HttpServletResponse httpServletResponse) {
		this.response = httpServletResponse;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
}
