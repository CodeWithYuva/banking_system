package com.example.bank_system.repo;

import com.example.bank_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface userRepo extends JpaRepository<User,Long> {
public List<User> findByUsername(String username);
@Query(value = "select pin from User u where u.username=?1")
public int getPin(String Username);



}
