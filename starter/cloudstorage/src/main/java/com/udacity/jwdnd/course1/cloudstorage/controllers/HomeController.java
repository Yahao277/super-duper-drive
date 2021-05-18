package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.models.FileForm;
import com.udacity.jwdnd.course1.cloudstorage.models.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("home")
public class HomeController {

  private final FileService fileService;
  private final UserService userService;
  private final NoteService noteService;
  private final CredentialService credentialService;
  private final EncryptionService encryptionService;

  public HomeController(
      FileService fileService, UserService userService, NoteService noteService,
      CredentialService credentialService, EncryptionService encryptionService) {
    this.fileService = fileService;
    this.userService = userService;
    this.noteService = noteService;
    this.credentialService = credentialService;
    this.encryptionService = encryptionService;
  }

  @GetMapping
  public String getHomePage(Authentication authentication, @ModelAttribute("newFile") FileForm newFile,
                            @ModelAttribute("newNote") NoteForm newNote,
                            @ModelAttribute("newCredential") CredentialForm newCredential,
                            Model model){
    Integer userId = getUserId(authentication);
    model.addAttribute("files", this.fileService.getFilesList(userId));
    model.addAttribute("notes", noteService.getNotesByUser(userId));
    model.addAttribute("credentials", credentialService.getCredentialListings(userId));
    model.addAttribute("encryptionService", encryptionService);

    return "home";
  }

  private Integer getUserId(Authentication authentication){
    String username = authentication.getName();
    User user = this.userService.getUser(username);
    return user.getUserId();
  }





}
