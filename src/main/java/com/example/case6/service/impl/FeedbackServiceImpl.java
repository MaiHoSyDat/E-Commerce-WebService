package com.example.case6.service.impl;

import com.example.case6.model.Feedback;
import com.example.case6.model.Product;
import com.example.case6.repository.IFeedbackRepo;
import com.example.case6.service.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Service
public class FeedbackServiceImpl implements IFeedbackService {
    @Autowired
    private IFeedbackRepo iFeedbackRepo;

    @PersistenceContext
    EntityManager entityManager;
    @Override
    public List<Feedback> getAllFeedBackByProductId(long id) {
        List<Feedback> result = entityManager.createQuery("SELECT f " +
                        " FROM Feedback f " +
                        " JOIN f.reviewMap r " +
                        " JOIN r.product p " +
                        " WHERE p.id = :idProduct ", Feedback.class)
                .setParameter("idProduct", id)
                .getResultList();
        return result;
    }

    @Override
    public void save(Feedback feedback) {
        iFeedbackRepo.save(feedback);
    }
}
