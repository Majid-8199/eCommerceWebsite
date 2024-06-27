package com.ecom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.entity.Coupon;
import com.ecom.repository.CouponRepository;

@Service
public class CouponService {

	@Autowired
	private CouponRepository couponRepository;
	
	public Coupon addCoupon(Coupon coupon) {
		return couponRepository.save(coupon);
	}
	
	public List<Coupon> getAllCoupon(){
		return couponRepository.findAll();
	}
	
	public void deleteCoupon(long id) {
		couponRepository.deleteById(id);
	}
}
