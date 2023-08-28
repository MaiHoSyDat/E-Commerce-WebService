package com.example.case6.repository;

import com.example.case6.model.Review;
import com.example.case6.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepo extends JpaRepository<Role, Long> {
}
