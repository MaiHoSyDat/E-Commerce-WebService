package com.example.case6.service.impl;

import com.example.case6.model.Notification;
import com.example.case6.model.Shop;
import com.example.case6.service.INotificationService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Service
public class NotificationServiceImpl implements INotificationService {
    @PersistenceContext
    EntityManager entityManager;
    @Override
    public List<Notification> getAllNotificationsByReceiverCustomer(long idCustomer) {
        String hql = "SELECT n " +
                " FROM Notification n " +
                " JOIN n.receiver r " +
                " JOIN Customer c ON c.account.id = r.id " +
                " WHERE c.id = :idCustomer ";
        List<Notification> result = entityManager.createQuery(hql, Notification.class)
                .setParameter("idCustomer", idCustomer)
                .getResultList();
        return result;
    }
}
