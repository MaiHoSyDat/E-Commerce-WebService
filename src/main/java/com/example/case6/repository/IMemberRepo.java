package com.example.case6.repository;

import com.example.case6.model.Image;
import com.example.case6.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMemberRepo extends JpaRepository<Member, Long> {
}
