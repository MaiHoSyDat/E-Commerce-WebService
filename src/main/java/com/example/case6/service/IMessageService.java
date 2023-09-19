package com.example.case6.service;

import com.example.case6.model.Message;

import java.util.List;

public interface IMessageService {
    void save(Message message);
    List<Message> getAllMessageBySenderIdAndReceiverId(long id1, long id2);
}
