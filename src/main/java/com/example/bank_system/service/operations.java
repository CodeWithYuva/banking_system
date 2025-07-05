package com.example.bank_system.service;

import com.example.bank_system.model.User;
import com.example.bank_system.repo.userRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class operations {
    userRepo repo;
    public operations(userRepo repo){
        this.repo=repo;
    }

    public boolean isLogin(User user){
        User u=repo.findByUsername(user.getUsername()).getFirst();
        int pin= u.getPin();
        if(u!=null && pin!=0){
          return true;
        }
        return false;
    }

    public boolean deposit(String username, int amount){
        User u= repo.findByUsername(username).getFirst();
        int temp_bal= u.getBal();
        temp_bal+=amount;
        u.setBal(temp_bal);
        repo.save(u);
        return true;
    }

    public boolean withraw(String username , int amount){
        User u = repo.findByUsername(username).getFirst();
        int temp_bal = u.getBal();
        if(temp_bal-amount>0){
            temp_bal-=amount;
            u.setBal(temp_bal);
            repo.save(u);
            return true;
        }
        return false;
    }



}
