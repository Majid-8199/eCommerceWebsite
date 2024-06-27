package com.ecom.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.dto.OrderDTO;
import com.ecom.dto.ProductDTO;
import com.ecom.entity.Category;
import com.ecom.entity.Coupon;
import com.ecom.entity.Product;
import com.ecom.enums.OrderStatus;
import com.ecom.service.CategoryService;
import com.ecom.service.CouponService;
import com.ecom.service.OrderService;
import com.ecom.service.ProductService;

@RestController
@RequestMapping("/admin")
@CrossOrigin("http://localhost:4200")
public class AdminController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CouponService couponService;
	
	@Autowired
	private OrderService orderService;

	
	@PostMapping("/category")
	public Category addCategory(@RequestBody Category category){
		return categoryService.addCategory(category);
	}
	
	@GetMapping("/allcategories")
	public List<Category> getAllCategories(){
		return categoryService.getAllCategories();
	}
	
	@PostMapping("/product")
	public ResponseEntity<ProductDTO> addProduct(@ModelAttribute ProductDTO product) throws IOException {
		ProductDTO productDTO=productService.addProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(productDTO);
	}
	
	@GetMapping("/allproducts")
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}
	
	@PostMapping("/coupon")
	public Coupon addCoupon(@RequestBody Coupon coupon) {
		return couponService.addCoupon(coupon);
	}
	
	@GetMapping("/allcoupons")
	public List<Coupon> getAllCoupons(){
		return couponService.getAllCoupon();
	}
	
	@DeleteMapping("/deletecoupon/{id}")
	public void deleteXCoupon(@PathVariable long id) {
		couponService.deleteCoupon(id);
	}
	
	@GetMapping("/orders")
	public List<OrderDTO> viewAllOrders() {
		return orderService.viewAllOrders();
	}
	
	@PostMapping("status/{orderId}/{orderStatus}")
	public OrderDTO updateOrderStatus(@PathVariable Long orderId, @PathVariable OrderStatus orderStatus) throws Exception {
	    if (orderId == null || orderStatus == null) {
	        throw new IllegalArgumentException("OrderId and OrderStatus must not be null");
	    }
	    System.out.println("Received OrderId: " + orderId + ", OrderStatus: " + orderStatus);
	    return orderService.updateOrderStatus(orderId, orderStatus);
	}
	
}
