package com.ecom.dto;

public class PlaceOrderDTO {

	private long userId;
	private String address;
	private String city;
	private String state;
	private long zipcode;
	private String orderDescription;
	private String payment;
	private String upiId;
	private long cardNo;
	private double amount;
	private double totalAmount;
	private double discount;
	private double delivery;

	public PlaceOrderDTO(long userId, String address, String city, String state, long zipcode, String orderDescription,
			String payment, String upiId, long cardNo, double amount, double totalAmount, double discount,
			double delivery) {
		super();
		this.userId = userId;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.orderDescription = orderDescription;
		this.payment = payment;
		this.upiId = upiId;
		this.cardNo = cardNo;
		this.amount = amount;
		this.totalAmount = totalAmount;
		this.discount = discount;
		this.delivery = delivery;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
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

	public double getDelivery() {
		return delivery;
	}

	public void setDelivery(double delivery) {
		this.delivery = delivery;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public long getZipcode() {
		return zipcode;
	}

	public void setZipcode(long zipcode) {
		this.zipcode = zipcode;
	}

	public String getUpiId() {
		return upiId;
	}

	public void setUpiId(String upiId) {
		this.upiId = upiId;
	}



	public long getCardNo() {
		return cardNo;
	}



	public void setCardNo(long cardNo) {
		this.cardNo = cardNo;
	}



	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOrderDescription() {
		return orderDescription;
	}

	public void setOrderDescription(String orderDescription) {
		this.orderDescription = orderDescription;
	}
	
}
