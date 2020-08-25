package com.gecko.repository;

import com.gecko.model.Product;

// import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, PagingAndSortingRepository<Product, Long> {

	@Query(value = "select p from Product p where "
			+ "FORMATDATETIME(updatedTime,'YYYY-MM-DD') >= FORMATDATETIME(:startDate,'YYYY-MM-DD') AND "
			+ "FORMATDATETIME(updatedTime,'YYYY-MM-DD') <= FORMATDATETIME(:endDate,'YYYY-MM-DD')")
	public List<Product> getAllBetweenDatesList(
			@Param("startDate") String startDate,@Param("endDate") String endDate, Pageable pageable);
	
	@Query(value = "select p from Product p where "
			+ "FORMATDATETIME(updatedTime,'YYYY-MM-DD') >= FORMATDATETIME(:startDate,'YYYY-MM-DD') AND "
			+ "FORMATDATETIME(updatedTime,'YYYY-MM-DD') <= FORMATDATETIME(:endDate,'YYYY-MM-DD')")
	Page<Product> getAllBetweenDatesPage(
			@Param("startDate") String startDate, @Param("endDate") String endDate,	Pageable pageRequest);		
}
