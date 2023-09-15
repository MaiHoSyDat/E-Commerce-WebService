package com.example.case6.service;

import com.example.case6.model.Feedback;

import java.util.List;

public interface IFeedbackService {
    List<Feedback> getAllFeedBackByProductId(long id);
    void save(Feedback feedback);
}
