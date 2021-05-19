package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.models.UserForm;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/signup")
public class SignupController {
  private final UserService userService;

  public SignupController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping()
  public String signupPage(@ModelAttribute("newUser") UserForm userForm, Model model) {

    return "signup";
  }

  @PostMapping()
  public String signupUser(@ModelAttribute("newUser") UserForm userForm,
                           Model model) {
    String signupError = null;

    if (!userService.isUsernameAvailable(userForm.getUsername())) {
      signupError = "The username already exists.";
    }

    if (signupError == null) {
      int rowsAdded = userService.createUser(userForm);
      if (rowsAdded < 0) {
        signupError = "There was an error signing you up.";
      }
    }

    if (signupError == null) {
      model.addAttribute("signupSuccess", true);
      return "redirect:/login";
    } else {
      model.addAttribute("signupError", signupError);
      return "signup";
    }

  }
}
