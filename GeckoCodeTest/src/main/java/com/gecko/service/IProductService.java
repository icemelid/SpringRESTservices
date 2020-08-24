package com.gecko.service;

import com.gecko.bean.Product;

import java.io.File;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface IProductService {
    public Product findById(Long id);
    public int saveFile(MultipartFile filename);
}