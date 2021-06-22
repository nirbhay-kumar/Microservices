package com.crescent.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crescent.entities.Coupon;

public interface CouponRepo extends JpaRepository<Coupon, Long> {

	Coupon findByCode(String code);

}
