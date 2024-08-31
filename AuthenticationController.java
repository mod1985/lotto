package com.example.lottery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public RedirectView signup(@RequestParam String username, @RequestParam String password) {
        userService.registerUser(username, password);
        return new RedirectView("/");  // Redirect to the home page after successful signup
    }
}