package com.ecom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.dto.AddProductToCartDTO;
import com.ecom.dto.OrderDTO;
import com.ecom.dto.PlaceOrderDTO;
import com.ecom.entity.Product;
import com.ecom.service.CartService;
import com.ecom.service.OrderService;
import com.ecom.service.ProductService;

@RestController
@RequestMapping("/customer")
@CrossOrigin("http://localhost:4200")
public class CustomerController {
	
	@Autowired
	private ProductService productService;

	@Autowired
	private CartService cartService;
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/allproducts")
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}
	
	@PostMapping("/cart")
	public ResponseEntity<?> AddProductToCart(@RequestBody AddProductToCartDTO addProductToCartDTO){
		return cartService.AddProductToCart(addProductToCartDTO);
	}
	
	@GetMapping("/cart/{userId}")
	public ResponseEntity<?> getgetCartByUserID(@PathVariable long userId){
		OrderDTO orderDTO=cartService.getCartByUserID(userId);
		return ResponseEntity.status(HttpStatus.OK).body(orderDTO);
	}
	
	@GetMapping("/coupon/{userId}/{code}")
	public ResponseEntity<?> applyCoupon(@PathVariable long userId, @PathVariable String code){
		OrderDTO orderDTO=cartService.applyCoupon(userId, code);
		return ResponseEntity.ok(orderDTO);
	}
	
	@PostMapping("/addition")
	public ResponseEntity<OrderDTO> increaseProductQuantity(@RequestBody AddProductToCartDTO addProductToCartDTO){
		return ResponseEntity.status(HttpStatus.CREATED).body(cartService.increaseProductQuantity(addProductToCartDTO));
	}
	
	@PostMapping("/subtraction")
	public ResponseEntity<OrderDTO> decreaseProductQuantity(@RequestBody AddProductToCartDTO addProductToCartDTO){
		return ResponseEntity.status(HttpStatus.CREATED).body(cartService.decreaseProductQuantity(addProductToCartDTO));
	}
	
	@PostMapping("/placeorder")
	public ResponseEntity<OrderDTO> placeOrder(@RequestBody PlaceOrderDTO placeOrderDTO){
		return ResponseEntity.status(HttpStatus.CREATED).body(cartService.placeOrder(placeOrderDTO));
	}
	
	@GetMapping("/orders/{userId}")
	public List<OrderDTO> viewOrdersbyUserId(@PathVariable long userId) {
		return orderService.viewOrdersbyUserId(userId);
	}
}
