package com.example.case6.service;

import com.example.case6.model.Image;

import java.util.List;

public interface IImageService {
    List<Image> getAllImageByIdProduct(long id);
}
