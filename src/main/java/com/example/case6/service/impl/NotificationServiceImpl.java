package com.example.case6.service.impl;

import com.example.case6.model.Notification;
import com.example.case6.model.Shop;
import com.example.case6.repository.INotificationRepo;
import com.example.case6.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Service
public class NotificationServiceImpl implements INotificationService {
    @Autowired
    private INotificationRepo iNotificationRepo;
    @PersistenceContext
    EntityManager entityManager;
    @Override
    public List<Notification> getAllNotificationsByReceiverCustomer(long idCustomer) {
        String hql = "SELECT n " +
                " FROM Notification n " +
                " JOIN n.receiver r " +
                " JOIN Customer c ON c.account.id = r.id " +
                " WHERE c.id = :idCustomer " +
                " ORDER BY n.id DESC ";
        List<Notification> result = entityManager.createQuery(hql, Notification.class)
                .setParameter("idCustomer", idCustomer)
                .getResultList();
        return result;
    }

    @Override
    public List<Notification> getAllNotificationsByReceiverShop(long idShop) {
        String hql = "SELECT n " +
                " FROM Notification n " +
                " JOIN n.receiver r " +
                " JOIN Shop s ON s.account.id = r.id " +
                " WHERE s.id = :idShop " +
                " ORDER BY n.id DESC ";
        List<Notification> result = entityManager.createQuery(hql, Notification.class)
                .setParameter("idShop", idShop)
                .getResultList();
        return result;
    }

    @Override
    public void save(Notification notification) {
        iNotificationRepo.save(notification);
    }
}
