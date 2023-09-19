package com.example.case6.service.impl;

import com.example.case6.model.Message;
import com.example.case6.repository.IMessageRepo;
import com.example.case6.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements IMessageService {
    @Autowired
    private IMessageRepo iMessageRepo;
    @Override
    public void save(Message message) {
        iMessageRepo.save(message);
    }

    @Override
    public List<Message> getAllMessageBySenderIdAndReceiverId(long id1, long id2) {
        return iMessageRepo.getAllMessageBySenderIdAndReceiverId(id1, id2);
    }
}
