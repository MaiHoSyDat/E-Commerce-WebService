package com.example.case6.repository;

import com.example.case6.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFeedbackRepo extends JpaRepository<Feedback, Long> {
}
