package com.ecom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.entity.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long>{
	Optional<Coupon> findByCode(String code);
}
