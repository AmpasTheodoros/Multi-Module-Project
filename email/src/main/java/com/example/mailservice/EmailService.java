package com.example.mailservice;

import org.springframework.stereotype.Service;

//@AllArgsConstructor
@Service
public class EmailService {


    public void sendEMail(){
        System.out.println("Email service working !!");
    }
}