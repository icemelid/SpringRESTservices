package com.gecko.service;

import com.gecko.bean.TestProduct;

import java.io.File;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface IProductService {
    public TestProduct findById(Long id);
    public int saveFile(MultipartFile filename);
}