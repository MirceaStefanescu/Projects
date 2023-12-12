package com.mycompany.documentmanagement.controller;

import com.mycompany.documentmanagement.model.Permission;
import com.mycompany.documentmanagement.model.Role;
import com.mycompany.documentmanagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

@Controller
public class SignUpController {
    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/signUp")
    public String signUpForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", mongoTemplate.findAll(Role.class, "Encryption"));
        model.addAttribute("allPermissions", mongoTemplate.findAll(Permission.class, "Encryption"));
        return "signUp";
    }

    @PostMapping("/signUp")
    public String signUpSubmit(@ModelAttribute User user) {
        // Implement your sign up logic here
        return "result";
    }
}

