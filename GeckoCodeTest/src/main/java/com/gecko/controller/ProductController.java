package com.gecko.controller;

import com.gecko.bean.Product;
import com.gecko.exception.ResourceNotFoundException;
import com.gecko.repository.ProductRepository;
import com.gecko.service.IProductService;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
    
    @Autowired
    IProductService productService;

    @RequestMapping("/api/{productId}")
    public Product findProduct(@PathVariable Long productId) {
        Product product = productService.findById(productId);
        return product;
    }
    
    @PostMapping("/api")
	public Product createProduct(@Valid @RequestBody Product product) {
		return productRepository.save(product);
	} 
    
    @RequestMapping(value="/apifile", method=RequestMethod.POST)
    public int saveFile(@RequestParam(name="file") MultipartFile filename) {
    	return productService.saveFile(filename);
    }
    
	@DeleteMapping("/api/{id}")
	public Map<String, Boolean> delete(@PathVariable(value = "id") Long productId) 
			throws ResourceNotFoundException {
				Product product = productService.findById(productId);
				//.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + productId));
		productRepository.delete(product);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}    
}
