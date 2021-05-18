package com.udacity.jwdnd.course1.cloudstorage.models;

public class Note {
  private Integer noteId;
  private String noteTitle;
  private String noteDescription;
  private Integer userId;

  public Note(Integer noteId, String title, String description, Integer userId){
    this.noteId = noteId;
    this.noteTitle = title;
    this.noteDescription = description;
    this.userId = userId;
  }

  public Integer getUserId(){ return this.userId;}
  public void setUserId() { this.userId = userId;}

  public Integer getNoteId(){return this.noteId;}
  public void setNoteId(Integer noteId) { this.noteId = noteId;}

  public String getNoteTitle(){ return this.noteTitle;}
  public void setNoteTitle(String noteTitle) { this.noteTitle = noteTitle;}

  public String getNoteDescription(){return this.noteDescription;}
  public void setNoteDescription(String noteDescription) { this.noteDescription = noteDescription;}

}
