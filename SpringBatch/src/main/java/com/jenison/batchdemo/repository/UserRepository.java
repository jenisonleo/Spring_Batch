package com.jenison.batchdemo.repository;

import com.jenison.batchdemo.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<Users,Integer> {
}
