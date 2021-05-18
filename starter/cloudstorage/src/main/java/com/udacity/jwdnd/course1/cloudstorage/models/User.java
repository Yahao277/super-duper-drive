package com.udacity.jwdnd.course1.cloudstorage.models;

public class User {
  private Integer userId;
  private String username;
  private String salt;
  private String password;
  private String firstname;
  private String lastname;

  public User(Integer userId,String username,String salt,String password,
              String firstname,String lastname){
    this.userId = userId;
    this.username = username;
    this.salt = salt;
    this.password = password;
    this.firstname = firstname;
    this.lastname = lastname;
  }

  public String getUsername(){ return this.username;}
  public void setUsername(String username){ this.username = username;}

  public Integer getUserId(){ return this.userId;}
  public void setUserId(Integer userId){ this.userId=userId;}

  public String getSalt(){ return this.salt;}
  public void setSalt(String salt){ this.salt = salt;}

  public String getPassword(){ return this.password;}
  public void setPassword(String password){ this.password = password;}

  public String getFirstName(){ return this.firstname;}
  public void setFirstname(String firstName){ this.firstname = firstName;}

  public String getLastName(){ return this.lastname;}
  public void setLastName(String lastName){ this.lastname = lastName;}
}
