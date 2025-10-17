package com.example.crudSpring.projetoCRUD.CONTROLLER;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequestMapping("/")

public class MainController {
    
@GetMapping("/")
public String home(){
    return "main";
}


}