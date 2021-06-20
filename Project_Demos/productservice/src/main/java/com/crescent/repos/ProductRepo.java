package com.crescent.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crescent.entities.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {

}
