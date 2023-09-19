package com.example.case6.repository;

import com.example.case6.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IMessageRepo extends JpaRepository<Message, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM message " +
            " WHERE (sender_id = :id1 AND receiver_id = :id2) " +
            " OR (sender_id = :id2 AND receiver_id = :id1);")
    List<Message> getAllMessageBySenderIdAndReceiverId(@Param("id1") long id1, @Param("id2") long id2);
}
