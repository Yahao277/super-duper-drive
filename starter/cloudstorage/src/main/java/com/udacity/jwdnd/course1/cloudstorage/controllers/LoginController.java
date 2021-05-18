package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.UserForm;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

  private final UserService userService;
  private final EncryptionService encryptionService;

  public LoginController(UserService userService, EncryptionService encryptionService) {
    this.userService = userService;
    this.encryptionService = encryptionService;
  }
  @GetMapping
  public String getLoginPage(@ModelAttribute("user") UserForm userForm, Model model){
    return "login";
  }
}
