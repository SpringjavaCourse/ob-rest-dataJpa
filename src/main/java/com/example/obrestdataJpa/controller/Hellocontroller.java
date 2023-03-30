package com.example.obrestdataJpa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hellocontroller {

    @GetMapping("/hola")
    public String holaMundo(){
        return "Hola Mundo que tal vamos mundo, como va todo?";
    }
}
