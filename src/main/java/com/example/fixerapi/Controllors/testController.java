package com.example.fixerapi.Controllors;

import com.example.fixerapi.Services.Mail.mailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@CrossOrigin("*")
public class testController {

    @Autowired
    mailService ms;


    @GetMapping("")
    public String testRequest(){
        //ms.sendEmail("sabbaghala@gmail.com","test","hello world");
        return "hello world";
    }

}
