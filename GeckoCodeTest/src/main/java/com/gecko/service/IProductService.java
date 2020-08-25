package com.gecko.service;

import com.gecko.model.Product;
import org.springframework.web.multipart.MultipartFile;

public interface IProductService {
    public Product findById(Long id);
    public int saveFile(MultipartFile filename);
}