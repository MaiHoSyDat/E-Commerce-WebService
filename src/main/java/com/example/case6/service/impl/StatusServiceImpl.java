package com.example.case6.service.impl;

import com.example.case6.model.Status;
import com.example.case6.repository.IStatusRepo;
import com.example.case6.service.IStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StatusServiceImpl implements IStatusService {
    @Autowired
    private IStatusRepo iStatusRepo;
    @Override
    public List<Status> getAllStatusOrder() {
        return iStatusRepo.getAllStatusOrder();
    }
    @Override
    public List<Status> getAllStatus() {
        return iStatusRepo.getAllStatus();
    }

    @Override
    public List<Status> getCustomerStatus() {
        return iStatusRepo.getCustomerStatus();
    }

    @Override
    public List<Status> getShopStatus() {
        return iStatusRepo.getShopStatus();
    }
}
