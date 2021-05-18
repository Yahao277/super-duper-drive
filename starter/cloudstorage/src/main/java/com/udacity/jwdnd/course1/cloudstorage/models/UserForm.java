package com.udacity.jwdnd.course1.cloudstorage.models;

public class UserForm {
  private String username;
  private String password;
  private String firstName;
  private String lastName;

  public UserForm(String username, String password,String firstName, String lastName) {
    this.username = username;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public String getFirstName(){
    return this.firstName;
  }

  public void setFirstName(String firstName){ this.firstName = firstName;}

  public String getLastName() { return this.lastName;}

  public void setLastName(){ this.lastName = lastName;}

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
