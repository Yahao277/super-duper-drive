package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.*;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/note")
public class NoteController {
  private final NoteService noteService;
  private final UserService userService;

  public NoteController(NoteService noteService, UserService userService) {
    this.noteService = noteService;
    this.userService = userService;
  }

  @GetMapping
  public String getHomePage(
      Authentication authentication, @ModelAttribute("newFile") FileForm newFile, @ModelAttribute("newNote") NoteForm newNote,
      @ModelAttribute("newCredential") CredentialForm newCredential, Model model) {
    Integer userId = getUserId(authentication);
    model.addAttribute("notes", this.noteService.getNotesByUser(userId));

    return "home";
  }

  private Integer getUserId(Authentication authentication) {
    String userName = authentication.getName();
    User user = userService.getUser(userName);
    return user.getUserId();
  }

  @GetMapping(value = "/{noteId}")
  public Note getNote(@PathVariable Integer noteId) {
    return noteService.getNote(noteId);
  }

  @PostMapping
  public String createNote(Authentication authentication, @ModelAttribute("newFile") FileForm newFile,
                           @ModelAttribute("newNote") NoteForm newNote, @ModelAttribute("newCredential") CredentialForm newCredential,
                           Model model){
    String username = authentication.getName();
    String title = newNote.getTitle();
    Integer noteId = newNote.getNoteId();
    String description = newNote.getDescription();

    if (noteId == null) {
      noteService.addNote(title, description, username);
    } else {
      Note existingNote = this.getNote(noteId);
      noteService.updateNote(existingNote.getNoteId(), title, description);
    }
    Integer userId = getUserId(authentication);
    model.addAttribute("notes", noteService.getNotesByUser(userId));
    model.addAttribute("result", "success");
    return "result";
  }

  @GetMapping("/delete/{noteId}")
  public String deleteNote(Authentication authentication, @PathVariable Integer noteId, @ModelAttribute("newNote") NoteForm newNote,
                           @ModelAttribute("newFile") FileForm newFile, @ModelAttribute("newCredential") CredentialForm newCredential,
                           Model model){
    noteService.deleteNote(noteId);
    Integer userId = getUserId(authentication);
    model.addAttribute("notes", noteService.getNotesByUser(userId));
    model.addAttribute("result", "success");

    return "result";

  }
}
