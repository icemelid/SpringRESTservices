package com.gecko.service;

import com.gecko.bean.TestProduct;
import com.gecko.repository.ProductRepository;
import com.opencsv.CSVReader;

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
    public TestProduct findById(Long id) {
    	TestProduct product = repository.findOne(id);
        return product;
    }  

	@Override
	public int saveFile(MultipartFile filename) {
    	List<TestProduct> products = new ArrayList();
        // FileInputStream fiStream = null;
    	InputStream iStream = null;
        try {       
            // fiStream = new FileInputStream(filename);
    		iStream = filename.getInputStream();
            CSVReader reader = new CSVReader(new InputStreamReader(iStream));
            String[] nextLine;
            reader.readNext();
            
            while ((nextLine = reader.readNext()) != null) {
            	String inputDatetime = nextLine[2]; // "2007-11-11 12:13:14" ;
            	java.sql.Timestamp ts = java.sql.Timestamp.valueOf(inputDatetime) ;
            	TestProduct newProduct = new TestProduct(nextLine[0], nextLine[1], ts);
                products.add(newProduct);
                System.out.println("Name : " + nextLine[1] + " - " + nextLine[2]);
            }
            repository.save(products);
            return 9;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TestProduct.class.getName()).log(Level.SEVERE, null, ex);            
        } catch (IOException ex) {
            Logger.getLogger(TestProduct.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (iStream != null) {
                    iStream.close();
                }
            } catch (IOException ex) {}
        }
		return 0;
	}
	
	/*
    @Override
    public List<Product> findPaginated(int pageNo, int pageSize) { 
        Pageable paging =  new PageRequest(pageNo, pageSize, Sort.Direction.ASC, "id");
        
        Page<Product> pagedResult = repository.findAll(paging);

        return pagedResult.iterator();
    }
    */
}