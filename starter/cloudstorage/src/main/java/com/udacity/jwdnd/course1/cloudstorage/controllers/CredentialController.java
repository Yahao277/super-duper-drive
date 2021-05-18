package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.*;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.Base64;

@Controller
@RequestMapping("/credential")
public class CredentialController {
  private final FileService fileService;
  private final UserService userService;
  private final NoteService noteService;
  private final CredentialService credentialService;
  private final EncryptionService encryptionService;

  public CredentialController(
      FileService fileService, UserService userService, NoteService noteService,
      CredentialService credentialService, EncryptionService encryptionService) {
    this.fileService = fileService;
    this.userService = userService;
    this.noteService = noteService;
    this.credentialService = credentialService;
    this.encryptionService = encryptionService;
  }


  private Integer getUserId(Authentication authentication){
    String username = authentication.getName();
    User user = this.userService.getUser(username);
    return user.getUserId();
  }

  @GetMapping
  public String getHomePage(
      Authentication authentication, @ModelAttribute("newFile") FileForm newFile,
      @ModelAttribute("newCredential") CredentialForm newCredential,
      @ModelAttribute("newNote") NoteForm newNote, Model model) {
    Integer userId = getUserId(authentication);
    model.addAttribute("files", this.fileService.getFilesList(userId));
    model.addAttribute("notes", noteService.getNotesByUser(userId));
    model.addAttribute("credentials", credentialService.getCredentialListings(userId));
    model.addAttribute("encryptionService", encryptionService);

    return "home";
  }

  @PostMapping("")
  public String newCredential(
      Authentication authentication, @ModelAttribute("newFile") FileForm newFile,
      @ModelAttribute("newCredential") CredentialForm newCredential,
      @ModelAttribute("newNote") NoteForm newNote, Model model) {
    String userName = authentication.getName();
    String newUrl = newCredential.getUrl();
    String credentialIdStr = newCredential.getCredentialId();
    String password = newCredential.getPassword();

    SecureRandom random = new SecureRandom();
    byte[] key = new byte[16];
    random.nextBytes(key);
    String encodedKey = Base64.getEncoder().encodeToString(key);
    String encryptedPassword = encryptionService.encryptValue(password, encodedKey);

    if (credentialIdStr.isEmpty()) {
      credentialService.addCredential(newUrl, userName, newCredential.getUserName(), encodedKey, encryptedPassword);
    } else {
      Credential existingCredential = getCredential(Integer.parseInt(credentialIdStr));
      credentialService.updateCredential(existingCredential.getCredentialId(), newCredential.getUserName(), newUrl, encodedKey, encryptedPassword);
    }
    User user = userService.getUser(userName);
    model.addAttribute("credentials", credentialService.getCredentialListings(user.getUserId()));
    model.addAttribute("encryptionService", encryptionService);
    model.addAttribute("result", "success");

    return "result";
  }

  @GetMapping(value = "/{credentialId}")
  public Credential getCredential(@PathVariable Integer credentialId) {
    return credentialService.getCredential(credentialId);
  }

  @GetMapping(value = "/delete/{credentialId}")
  public String deleteCredential(
      Authentication authentication, @PathVariable Integer credentialId,
      @ModelAttribute("newCredential") CredentialForm newCredential,
      @ModelAttribute("newFile") FileForm newFile,
      @ModelAttribute("newNote") NoteForm newNote, Model model) {
    credentialService.deleteCredential(credentialId);
    String userName = authentication.getName();
    User user = userService.getUser(userName);
    model.addAttribute("credentials", credentialService.getCredentialListings(user.getUserId()));
    model.addAttribute("encryptionService", encryptionService);
    model.addAttribute("result", "success");

    return "result";
  }
}
