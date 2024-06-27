package com.ecom.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.ecom.dto.OrderDTO;
import com.ecom.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String description;
	private Date  date;
	private double amount;
	private String payment;
	private String address;
	private OrderStatus orderStatus;
	private double totalAmount;
	private double discount;
	private UUID trackingId;
	private double delivery;
	private String upiId;
	private long cardNo;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "coupon_id", referencedColumnName = "id")
	private Coupon coupon;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL)
	private List<CartItems> cartItems;
	
	public double getDelivery() {
		return delivery;
	}

	public void setDelivery(double delivery) {
		this.delivery = delivery;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	public long getId() {
		return id;
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

	public void setDate(Date date2) {
		this.date = date2;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<CartItems> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItems> cartItems) {
		this.cartItems = cartItems;
	}

	public Order(long id, String description, Date date, double amount, String payment, String address,
			OrderStatus orderStatus, double totalAmount, double discount, UUID trackingId, double delivery,
			String upiId, long cardNo, User user, Coupon coupon, List<CartItems> cartItems) {
		super();
		this.id = id;
		this.description = description;
		this.date = date;
		this.amount = amount;
		this.payment = payment;
		this.address = address;
		this.orderStatus = orderStatus;
		this.totalAmount = totalAmount;
		this.discount = discount;
		this.trackingId = trackingId;
		this.delivery = delivery;
		this.upiId = upiId;
		this.cardNo = cardNo;
		this.user = user;
		this.coupon = coupon;
		this.cartItems = cartItems;
	}

	public Order() {
	}
	
	public OrderDTO getOrderDto() {
		OrderDTO orderDTO=new OrderDTO();
		orderDTO.setId(id);
		orderDTO.setDescription(description);
		orderDTO.setTrackingId(trackingId);
		orderDTO.setTotalAmount(amount);
		orderDTO.setDate(date);
		orderDTO.setOrderStatus(orderStatus);
		orderDTO.setUserName(user.getUsername());
		orderDTO.setAddress(address);
		orderDTO.setDelivery(delivery);
		if(coupon!=null) {
			orderDTO.setCouponCode(coupon.getCode());
		}
		
		return orderDTO;
	}
}
