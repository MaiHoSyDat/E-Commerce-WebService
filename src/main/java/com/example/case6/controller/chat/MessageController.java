package com.example.case6.controller.chat;

import com.example.case6.model.Message;
import com.example.case6.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/messages")
public class MessageController {
    @Autowired
    private IMessageService messageService;
    @PostMapping("/save")
    public void save(@RequestBody Message message) {
        messageService.save(message);
    }
    @GetMapping("/{id1}/{id2}")
    public ResponseEntity<List<Message>> getAllMessageBySenderIdAndReceiverId(@PathVariable long id1, @PathVariable long id2) {
        return new ResponseEntity<>(messageService.getAllMessageBySenderIdAndReceiverId(id1, id2), HttpStatus.OK);
    }
}
