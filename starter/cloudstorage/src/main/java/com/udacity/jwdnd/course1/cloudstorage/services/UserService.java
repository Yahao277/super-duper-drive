package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.models.UserForm;
import org.springframework.stereotype.Service;

import java.util.Base64;

import java.security.SecureRandom;

@Service
public class UserService {
  private final UserMapper userMapper;
  private final HashService hashService;

  public UserService(UserMapper userMapper, HashService hashService){
    this.userMapper = userMapper;
    this.hashService = hashService;

  }

  public boolean isUsernameAvailable(String username){
    return this.userMapper.getUser(username) == null;
  }

  public int createUser(UserForm userForm){
    SecureRandom random = new SecureRandom();
    byte[] salt = new byte[16];
    String encodedSalt = Base64.getEncoder().encodeToString(salt);
    String hashedPassword = this.hashService.getHashedValue(userForm.getPassword(),encodedSalt);
    return userMapper.insert(new User(null,userForm.getUsername(),encodedSalt,
        hashedPassword,userForm.getFirstName(),userForm.getLastName()));
  }

  public User getUser(String username) {
    return userMapper.getUser(username);
  }

  public User getUser(Integer userId) {
    return userMapper.getUserById(userId);
  }
}
