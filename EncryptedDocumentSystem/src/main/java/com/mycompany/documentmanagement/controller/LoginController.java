package com.mycompany.documentmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {
    // This method handles the GET request for the login page
    @GetMapping(value = "/Login")
    public String loginPage() {
        return "Login"; // This returns the name of the view to render
    }

    // This method handles the POST request for the login page
    @PostMapping(value = "/Login")
    public String loginProcess(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               Model model) {
        // You can use the AuthenticationManager to authenticate the user credentials
        // You can also use the SecurityContextHolder to get the authentication result
        // You can use the model to pass any data to the view
        // You can use the RedirectView to redirect to another URL based on the authentication result
        // You can also handle any exceptions that may occur during the authentication process
        // For more details, you can refer to the web search results [^1^][1] [^2^][2] [^3^][3] [^4^][4]
        return "redirect:/hello"; // This returns the URL to redirect to
    }
}

