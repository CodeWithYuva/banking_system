package com.example.bank_system.model;


import jakarta.persistence.*;

@Entity
//@DiscriminatorValue("User")
@Table(name = "'user'")
@DiscriminatorValue("user")
public  class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long i;
    @Column(name="'username'")
    private String username;




    public int getBal() {
        return bal;
    }


    public void setBal(int bal) {
        this.bal = bal;
    }

    @Column(name="'pin'")
    private int pin;
    @Column(name = "'bal'")
    private int bal;

    public User(){}
    public User(String username, int pin){

        this.username=username;
        this.pin=pin;
    }


    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



}
