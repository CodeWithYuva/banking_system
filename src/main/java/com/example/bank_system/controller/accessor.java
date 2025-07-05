package com.example.bank_system.controller;

import com.example.bank_system.repo.userRepo;
import com.example.bank_system.model.User;
import com.example.bank_system.service.emailService;
import com.example.bank_system.service.operations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
public class accessor {


    private userRepo repo;
    private operations ser;
    private emailService email;

    public accessor(userRepo repo,operations ser, emailService email){
        this.repo=repo;
        this.ser=ser;
        this.email=email;
    }

    @GetMapping("/register/{mail}")
    public ResponseEntity<String> mailling(@PathVariable String mail){
        email.sendEmail(mail);
        return  ResponseEntity.ok("String");
    }

    @PostMapping("/login")
    public Object login(@RequestBody User user ){

        if(user.getUsername().equals("admin")&&user.getPin()==5555){
            return repo.findAll();
        }

        if(ser.isLogin(user)){
            return ResponseEntity.ok("success");
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user ){
        if(!ser.isLogin(user)) {
            repo.save(user);
            return ResponseEntity.ok("success");
        }
        return ResponseEntity.ok("already existing user");
    }

    @GetMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestParam String username, int amount){
        if(ser.deposit(username,amount)){
         return ResponseEntity.ok("success");
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/withraw")
    public ResponseEntity<String> withraw(@RequestParam String username, int amount){
        if(ser.withraw(username,amount)){
            return ResponseEntity.ok("success");
        }
        return ResponseEntity.ok("insufficient balance");
    }

    @GetMapping("/bal/{username}")
    public int getbal(@PathVariable String username){
        User u=repo.findByUsername(username).getFirst();
        return u.getBal();
    }

    @GetMapping("/transaction/{from}")
    public ResponseEntity<String> transaction(@PathVariable String from , @RequestParam int amount , String to){

        if(ser.withraw(from,amount)){

            if(ser.deposit(to,amount)){
                return  ResponseEntity.ok("success");
            }
            return  ResponseEntity.ok("failed");
        }
        return ResponseEntity.noContent().build();
    }




}
