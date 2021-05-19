package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.models.FileForm;
import com.udacity.jwdnd.course1.cloudstorage.models.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/file")
public class FileController {
  private final FileService fileService;
  private final UserService userService;

  public FileController(FileService fileService,UserService userService){
    this.fileService = fileService;
    this.userService = userService;
  }

  private Integer getUserId(Authentication authentication){
    String username = authentication.getName();
    User user = this.userService.getUser(username);
    return user.getUserId();
  }

  @PostMapping
  public String createFile (Authentication authentication, @ModelAttribute("newFile") FileForm newFile,
                            @ModelAttribute("newNote") NoteForm newNote,
                            @ModelAttribute("newCredential") CredentialForm newCredential,
                            Model model) throws IOException {
    String userName = authentication.getName();
    User user = userService.getUser(userName);
    Integer userId = user.getUserId();
    String[] fileListings = fileService.getFilesList(userId);
    MultipartFile multipartFile = newFile.getFile();
    String fileName = multipartFile.getOriginalFilename();
    boolean fileIsDuplicate = false;
    for (String fileListing: fileListings) {
      if (fileListing.equals(fileName)) {
        fileIsDuplicate = true;
        break;
      }
    }
    if (!fileIsDuplicate) {
      fileService.addFile(multipartFile, userName);
      model.addAttribute("result", "success");
    } else {
      model.addAttribute("result", "error");
      model.addAttribute("message", "File is duplicated");
    }
    model.addAttribute("files", fileService.getFilesList(userId));

    return "result";
  }

  @GetMapping(value = "/{fileName}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
  public @ResponseBody()
  byte[] viewFile(@PathVariable String fileName){
    return fileService.getFile(fileName).getFileData();
  }

  @GetMapping("/delete/{fileName}")
  public String deleteFile(Authentication authentication, @PathVariable String fileName,
                 @ModelAttribute("newFile") FileForm newFile,
                 @ModelAttribute("newNote") NoteForm newNote, @ModelAttribute("newCredential") CredentialForm newCredential,
                 Model model){
    fileService.deleteFile(fileName);
    Integer userId = getUserId(authentication);
    model.addAttribute("files", fileService.getFilesList(userId));
    model.addAttribute("result", "success");

    return "result";
  }

}
