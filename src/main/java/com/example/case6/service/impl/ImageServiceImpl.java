package com.example.case6.service.impl;

import com.example.case6.model.Image;
import com.example.case6.repository.IImageRepo;
import com.example.case6.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ImageServiceImpl implements IImageService {
    @Autowired
    IImageRepo iImageRepo;
    @Override
    public List<Image> getAllImageByIdProduct(long id) {
        return iImageRepo.findAllByProductId(id);
    }
}
