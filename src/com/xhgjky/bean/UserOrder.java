package com.xhgjky.bean;

/**
 * Created by Administrator on 2018/6/28 0028.
 */
public class UserOrder {
    private Long userId;
    private String userName;
    private String orderId;
    private String orderName;

    public UserOrder() {
    }

    public UserOrder(Long userId, String userName, String orderId, String orderName) {
        this.userId = userId;
        this.userName = userName;
        this.orderId = orderId;
        this.orderName = orderName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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


}
