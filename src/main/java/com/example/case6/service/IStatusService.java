package com.example.case6.service;

import com.example.case6.model.Status;

import java.util.List;

public interface IStatusService {
    List<Status> getAllStatus();
    List<Status> getCustomerStatus();
    List<Status> getShopStatus();
    List<Status> getAllStatusOrder();

}
