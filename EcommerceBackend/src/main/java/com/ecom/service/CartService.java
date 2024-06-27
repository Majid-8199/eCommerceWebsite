package com.ecom.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ecom.dto.AddProductToCartDTO;
import com.ecom.dto.CartItemsDTO;
import com.ecom.dto.OrderDTO;
import com.ecom.dto.PlaceOrderDTO;
import com.ecom.entity.CartItems;
import com.ecom.entity.Coupon;
import com.ecom.entity.Order;
import com.ecom.entity.Product;
import com.ecom.entity.User;
import com.ecom.enums.OrderStatus;
import com.ecom.repository.CartItemsRepository;
import com.ecom.repository.CouponRepository;
import com.ecom.repository.OrderRepository;
import com.ecom.repository.ProductRepository;
import com.ecom.repository.UserRepository;

@Service
public class CartService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CartItemsRepository cartItemsRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CouponRepository couponRepository;
	
	
	public ResponseEntity<?> AddProductToCart(AddProductToCartDTO addProductToCartDTO){
		Order activeOrder=orderRepository.findByUserIdAndOrderStatus(addProductToCartDTO.getUserId(),OrderStatus.PENDING);
		Optional<CartItems> optionalCartItem=cartItemsRepository.findByProductIdAndOrderIdAndUserId(addProductToCartDTO.getProductId(),activeOrder.getId(),addProductToCartDTO.getUserId());
		CartItems cartItem;
		if(optionalCartItem.isPresent()) {
			cartItem = optionalCartItem.get();
	        cartItem.setQuantity(cartItem.getQuantity() + 1);
	        cartItemsRepository.save(cartItem);

		}else {
			Optional<Product> optionalProduct=productRepository.findById(addProductToCartDTO.getProductId());
			Optional<User> optionalUser=userRepository.findById(addProductToCartDTO.getUserId());
			
			if(optionalProduct.isPresent()&& optionalUser.isPresent()) {
				CartItems cart=new CartItems();
				cart.setProduct(optionalProduct.get());
				cart.setPrice(optionalProduct.get().getPrice());
				cart.setQuantity(1);
				cart.setUser(optionalUser.get());
				cart.setOrder(activeOrder);
				
				cartItemsRepository.save(cart);
				
				activeOrder.setTotalAmount(activeOrder.getTotalAmount() + cart.getPrice());
				activeOrder.setAmount(activeOrder.getAmount() + cart.getPrice());
				activeOrder.getCartItems().add(cart);
				
				orderRepository.save(activeOrder);
				
				return ResponseEntity.status(HttpStatus.CREATED).body(cart);
				
			}else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or Product not Found");
			}

		}
		activeOrder.setTotalAmount(activeOrder.getTotalAmount() + cartItem.getPrice());
	    activeOrder.setAmount(activeOrder.getAmount() + cartItem.getPrice());
	    orderRepository.save(activeOrder);

	    CartItemsDTO cartItemsDTO = new CartItemsDTO();
	    cartItemsDTO.setProductId(cartItem.getProduct().getId());
	    cartItemsDTO.setProductName(cartItem.getProduct().getName());
	    cartItemsDTO.setPrice(cartItem.getPrice());
	    cartItemsDTO.setQuantity(cartItem.getQuantity());
	    cartItemsDTO.setProductDescription(cartItem.getProduct().getDescription());

	    return ResponseEntity.status(HttpStatus.CREATED).body(cartItemsDTO);
	}
	
	public OrderDTO getCartByUserID(long userId) {
		Order activeOrder=orderRepository.findByUserIdAndOrderStatus(userId,OrderStatus.PENDING);
		List<CartItemsDTO> cartItemsDTO=activeOrder.getCartItems().stream().map(CartItems::getCartDto).collect(Collectors.toList());
		OrderDTO orderDTO=new OrderDTO();
		orderDTO.setAmount(activeOrder.getAmount());
		orderDTO.setId(activeOrder.getId());
		orderDTO.setOrderStatus(activeOrder.getOrderStatus());
		orderDTO.setDiscount(activeOrder.getDiscount());
		orderDTO.setTotalAmount(activeOrder.getTotalAmount());
		orderDTO.setCartItems(cartItemsDTO);
		if(activeOrder.getCoupon() != null) {
			orderDTO.setCouponCode(activeOrder.getCoupon().getCode());
		}
		return orderDTO;

	}
	
	public OrderDTO applyCoupon(long userId, String code) {
		Order activeOrder=orderRepository.findByUserIdAndOrderStatus(userId,OrderStatus.PENDING);
		Coupon coupon=couponRepository.findByCode(code).orElseThrow();
		
		double discountPercentage = coupon.getDiscount();
        double discountAmount = (discountPercentage / 100.0) * activeOrder.getAmount();
        double netAmount = activeOrder.getAmount() - discountAmount;
		
		System.out.println("Original Total Amount: " + activeOrder.getAmount());
        System.out.println("Discount Amount: " + discountAmount);
        System.out.println("Net Amount: " + netAmount);
		activeOrder.setTotalAmount(netAmount);
		activeOrder.setDiscount(discountAmount);
		activeOrder.setCoupon(coupon);
		orderRepository.save(activeOrder);
		return activeOrder.getOrderDto();
	}
	
	public OrderDTO increaseProductQuantity(AddProductToCartDTO addProductToCartDTO) {
		Order activeOrder=orderRepository.findByUserIdAndOrderStatus(addProductToCartDTO.getUserId(), OrderStatus.PENDING);
		Optional<Product> optionalProduct=productRepository.findById(addProductToCartDTO.getProductId());
		
		Optional<CartItems> optionalCartItems=cartItemsRepository.findByProductIdAndOrderIdAndUserId(addProductToCartDTO.getProductId(), activeOrder.getId(), addProductToCartDTO.getUserId());
		
		if(optionalProduct.isPresent() && optionalCartItems.isPresent()) {
			CartItems cartItems=optionalCartItems.get();
			Product product=optionalProduct.get();
			
			activeOrder.setAmount(activeOrder.getAmount()+product.getPrice());;
			activeOrder.setTotalAmount(activeOrder.getTotalAmount()+product.getPrice());;
			
			cartItems.setQuantity(cartItems.getQuantity()+1);
			System.out.println(cartItems);
			
			if(activeOrder.getCoupon()!=null) {
				double discountAmount=((activeOrder.getCoupon().getDiscount() / 100.0)*activeOrder.getTotalAmount());
				double netAmount=activeOrder.getTotalAmount()-discountAmount;
				System.out.println(netAmount+"Net Amount");
				System.out.println(discountAmount+"Discount Amount");
				activeOrder.setAmount(netAmount);
				activeOrder.setDiscount(discountAmount);	
			}
		
			cartItemsRepository.save(cartItems);
			orderRepository.save(activeOrder);
			return activeOrder.getOrderDto();
		}
		return null;
	}
	
	public OrderDTO decreaseProductQuantity(AddProductToCartDTO addProductToCartDTO) {
		Order activeOrder=orderRepository.findByUserIdAndOrderStatus(addProductToCartDTO.getUserId(), OrderStatus.PENDING);
		Optional<Product> optionalProduct=productRepository.findById(addProductToCartDTO.getProductId());
		
		Optional<CartItems> optionalCartItems=cartItemsRepository.findByProductIdAndOrderIdAndUserId(addProductToCartDTO.getProductId(), activeOrder.getId(), addProductToCartDTO.getUserId());
		
		if(optionalProduct.isPresent() && optionalCartItems.isPresent()) {
			CartItems cartItems=optionalCartItems.get();
			Product product=optionalProduct.get();
			
			activeOrder.setAmount(activeOrder.getAmount()-product.getPrice());;
			activeOrder.setTotalAmount(activeOrder.getTotalAmount()-product.getPrice());;
			
			cartItems.setQuantity(cartItems.getQuantity()-1);
			System.out.println(cartItems);
			
			if(activeOrder.getCoupon()!=null) {
				double discountAmount=((activeOrder.getCoupon().getDiscount() / 100.0)*activeOrder.getTotalAmount());
				double netAmount=activeOrder.getTotalAmount()-discountAmount;
				System.out.println(netAmount+"Net Amount");
				System.out.println(discountAmount+"Discount Amount");
				activeOrder.setAmount(netAmount);
				activeOrder.setDiscount(discountAmount);	
			}
		
			cartItemsRepository.save(cartItems);
			orderRepository.save(activeOrder);
			return activeOrder.getOrderDto();
		}
		return null;
	}
	
	public OrderDTO placeOrder(PlaceOrderDTO placeOrderDTO) {
		Order activeOrder=orderRepository.findByUserIdAndOrderStatus(placeOrderDTO.getUserId(), OrderStatus.PENDING);
		Optional<User> optionalUser=userRepository.findById(placeOrderDTO.getUserId());
		
		if(optionalUser.isPresent()) {
			activeOrder.setAmount(placeOrderDTO.getAmount());
			activeOrder.setDelivery(placeOrderDTO.getDelivery());
			activeOrder.setDiscount(placeOrderDTO.getDiscount());
			activeOrder.setTotalAmount(placeOrderDTO.getTotalAmount());
			activeOrder.setDescription(placeOrderDTO.getOrderDescription());
			activeOrder.setAddress(placeOrderDTO.getAddress()+", "+placeOrderDTO.getCity()+", "+placeOrderDTO.getState()+", "+placeOrderDTO.getZipcode());
			activeOrder.setPayment(placeOrderDTO.getPayment());
			activeOrder.setDate(new Date());
			activeOrder.setOrderStatus(OrderStatus.PLACED);
			activeOrder.setTrackingId(UUID.randomUUID());
			activeOrder.setCardNo(placeOrderDTO.getCardNo());
			activeOrder.setUpiId(placeOrderDTO.getUpiId());
			
			orderRepository.save(activeOrder);
			
			Order order=new Order();
			order.setAmount(0);
			order.setTotalAmount(0);
			order.setDiscount(0);
			order.setUser(optionalUser.get());
			order.setOrderStatus(OrderStatus.PENDING);
			orderRepository.save(order);
			
			return activeOrder.getOrderDto();
		}
		return null;
	}
}
