package com.example.case6.service.impl;

import com.example.case6.model.Status;
import com.example.case6.repository.IStatusRepo;
import com.example.case6.service.IStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StatusService implements IStatusService {
    @Autowired
    IStatusRepo iStatusRepo;
    @Override
    public List<Status> getAllStatus() {
        return iStatusRepo.getAllStatus();
    }
}
