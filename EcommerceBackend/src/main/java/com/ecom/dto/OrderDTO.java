package com.ecom.dto;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.ecom.enums.OrderStatus;

public class OrderDTO {

	private long id;
	private String description;
	private Date  date;
	private double amount;
	private String payment;
	private OrderStatus orderStatus;
	private double totalAmount;
	private double discount;
	private UUID trackingId;
	private String couponCode;
	private String userName;
	private String address;
	private double delivery;
	private long cardNo;
	private String upiId;
	private List<CartItemsDTO> cartItems;

	
	
	public long getCardNo() {
		return cardNo;
	}

	public void setCardNo(long cardNo) {
		this.cardNo = cardNo;
	}

	public String getUpiId() {
		return upiId;
	}

	public void setUpiId(String upiId) {
		this.upiId = upiId;
	}

	public double getDelivery() {
		return delivery;
	}

	public void setDelivery(double delivery) {
		this.delivery = delivery;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public UUID getTrackingId() {
		return trackingId;
	}

	public void setTrackingId(UUID trackingId) {
		this.trackingId = trackingId;
	}

	public String getUser() {
		return userName;
	}

	public void setUser(String userName) {
		this.userName = userName;
	}

	public List<CartItemsDTO> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItemsDTO> cartItems) {
		this.cartItems = cartItems;
	}

	

	public OrderDTO(long id, String description, Date date, double amount, String payment, OrderStatus orderStatus,
			double totalAmount, double discount, UUID trackingId, String couponCode, String userName, String address,
			double delivery, long cardNo, String upiId, List<CartItemsDTO> cartItems) {
		super();
		this.id = id;
		this.description = description;
		this.date = date;
		this.amount = amount;
		this.payment = payment;
		this.orderStatus = orderStatus;
		this.totalAmount = totalAmount;
		this.discount = discount;
		this.trackingId = trackingId;
		this.couponCode = couponCode;
		this.userName = userName;
		this.address = address;
		this.delivery = delivery;
		this.cardNo = cardNo;
		this.upiId = upiId;
		this.cartItems = cartItems;
	}

	public OrderDTO() {
	}

}
