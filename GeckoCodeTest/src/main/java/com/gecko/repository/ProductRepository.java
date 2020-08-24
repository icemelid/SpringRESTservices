package com.gecko.repository;

import com.gecko.bean.Product;

// import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
// public interface ProductRepository extends CrudRepository<Product, Long> {
public interface ProductRepository extends JpaRepository<Product, Long> {	

}
