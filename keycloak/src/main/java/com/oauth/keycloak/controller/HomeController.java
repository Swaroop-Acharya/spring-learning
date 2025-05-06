package com.oauth.keycloak.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
   @GetMapping("/home")
   public String getHOmeString() {
       return "home";
   }  
}
