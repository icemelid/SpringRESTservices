package com.gecko.service;

import com.gecko.model.Product;
import com.gecko.repository.ProductRepository;
import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductService implements IProductService {

	private static Validator validator;
	
    @Autowired
    private ProductRepository repository;

    @Override
    public Product findById(Long id) {
    	Product product = repository.findOne(id);
        return product;
    }  

	@Override
	public int saveFile(MultipartFile filename) {
    	List<Product> products = new ArrayList();
    	InputStream iStream = null;
        try {       
    		iStream = filename.getInputStream();
            CSVReader reader = new CSVReader(new InputStreamReader(iStream));
            String[] nextLine;
            reader.readNext();
            
            while ((nextLine = reader.readNext()) != null) {
            	/* timestamp Format sample "2007-11-11 12:13:14"
            	 */
            	String inputDatetime = nextLine[2];
            	java.sql.Timestamp ts = java.sql.Timestamp.valueOf(inputDatetime) ;
            	Product newProduct = new Product(nextLine[0], nextLine[1], ts);
            	/* Validate fields of product
            	 */
                ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
                validator = factory.getValidator();
                validate(newProduct);
            	
                products.add(newProduct);
                System.out.println("Name : " + nextLine[1] + " - " + nextLine[2]);
            }
            repository.save(products);
            return 0;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);            
        } catch (IOException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (iStream != null) {
                    iStream.close();
                }
            } catch (IOException ex) {}
        }
		return 1;
	}
	
    public static void validate(Product product) {
        Set<ConstraintViolation<Product>> cvs = validator.validate(product);

        for (ConstraintViolation<Product> cv : cvs) {
            System.out.println(cv.getPropertyPath() + ": " + cv.getMessage());
        }        
    }
}