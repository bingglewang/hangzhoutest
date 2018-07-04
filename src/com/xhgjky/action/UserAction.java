/**  
 * @Title: UserAction.java  
 * @Package com.xhgjky.action  
 * @Description: TODO(用户action层)  
 * @author 王炳贵  
 * @date 2018年6月27日  
 * @version V1.0  
 */ 
package com.xhgjky.action;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.xhgjky.bean.User;
import com.xhgjky.bean.UserOrder;
import com.xhgjky.service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @ClassName: UserAction  
 * @Description: TODO(用户action层)  
 * @author 王炳贵  
 * @date 2018年6月27日     
 */
public class UserAction extends ActionSupport implements ServletRequestAware, ServletResponseAware, ServletContextAware {
	
	private UserService userService;
	
	private User user;	
	private Long userId;
	private String userName;
	private String orderId;

	// 接收前端发送来的分页参数
	private int page = 1;
	private int limit = 2;

	//获取request response 等对象
	private HttpServletRequest request;

	private ServletContext servletContext;

	private HttpServletResponse response;

	//接受前端发来的搜索参数
	private String value="";

	private List<UserOrder> list;
	/**
	 * @Title: getAllUser  
	 * @Description: TODO(用户action层查询所有的用户并跳转到list页面)  
	 * @param @return    参数  
	 * @return String    返回类型  
	 * @throws
	 */
	public String getAllUser() {
		System.out.println("哈哈哈"+userId);
		List<UserOrder> list1 = userService.getAllUser(userId);
		Map<String, Object> result = new HashMap<String, Object>();
		JSONArray array = JSONArray.fromObject(list1);
		System.out.println("##################"+array);
		result.put("data", array);
		// 将其转换为JSON数据，并压入值栈返回
		ActionContext.getContext().getValueStack().set("jsonData", JSONObject.fromObject(result));
		return "success";
	}

	public String getAllUserOrderId(){
		List<User> userOrderIds = userService.getAllUserOrderId();
		Map<String, Object> result = new HashMap<String, Object>();
		JSONArray array = JSONArray.fromObject(userOrderIds);
		result.put("data", array);
		ActionContext.getContext().getValueStack().set("jsonData", JSONObject.fromObject(result));
		return "success";
	}
	public String findAll(){
		try{
			value = URLDecoder.decode(value , "utf-8");//编码解码
		}catch(Exception e){
			e.printStackTrace();
		}
		List<User> allUser = userService.getAllUserList(page,limit,value);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("code",0);
		result.put("msg","");
        //System.out.println("***********");
		result.put("count",userService.getTotalRecord(value));
		//System.out.println("***********"+userService.getTotalRecord(value));
		JSONArray array = JSONArray.fromObject(allUser);
        //System.out.println("##################"+array);
		result.put("data",array);
		// 将其转换为JSON数据，并压入值栈返回
		ActionContext.getContext().getValueStack().set("jsonData", JSONObject.fromObject(result));
		return "success";
	}
	public String add(){
		//System.out.println(userName+"!!!!!!!!!!!"+orderId);
		User addUser = new User();
		addUser.setUserName(userName);
		addUser.setOrderId(orderId);
		userService.addUser(addUser);
		return "add";
	}
	
	public String addUI(){
		return "addUI";
	} 
	
	public String del(){
		User delUser = new User();
		delUser.setUserId(userId);
		userService.delUser(delUser);
		return "del";
	}

	public String delAll(){
		List<Integer> list =  JSONArray.fromObject(request.getParameter("userIds"));
		User delAll = new User();
		for(Integer o : list){
			delAll.setUserId(o.longValue());
			userService.delUser(delAll);
		}
		/*System.out.println("+++++++++"+o);*/
		return "delAll";
	}
	public String upd(){
		System.out.println(orderId+"----------"+userName+"------"+userId);
		User updUser = new User();
		updUser.setUserName(userName);
		updUser.setOrderId(orderId);
		updUser.setUserId(userId);
		userService.updUser(updUser);
		return "upd";
	}
	
	public String updUI(){		
		System.out.println("*****"+userId);
		/*user=userService.getById(userId);*/		
		return "updUI";
	}
	/**
	 * @return the userService
	 */
	public UserService getUserService() {
		return userService;
	}
	/**
	 * @param userService the userService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	/**
	 * @return the list
	 */
	public List<UserOrder> getList() {
		return list;
	}
	/**
	 * @param list the list to set
	 */
	public void setList(List<UserOrder> list) {
		this.list = list;
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	
	
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the orderId
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setPage(int page) {
		this.page = page;
	}
	public int getPage() {
		return page;
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
