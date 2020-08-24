package com.gecko.service;

import com.gecko.bean.Product;
import com.gecko.repository.ProductRepository;
import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductService implements IProductService {

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
        // FileInputStream fiStream = null;
    	InputStream iStream = null;
        try {       
            // fiStream = new FileInputStream(filename);
    		iStream = filename.getInputStream();
            CSVReader reader = new CSVReader(new InputStreamReader(iStream));
            String[] nextLine;
            reader.readNext();
            
            while ((nextLine = reader.readNext()) != null) {
            	Product newProduct = new Product(nextLine[0], nextLine[1], nextLine[2]);
                products.add(newProduct);
                System.out.println("Name : " + nextLine[1]);
            }
            repository.save(products);
            return 9;
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
		return 0;
	}
}