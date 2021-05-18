package com.udacity.jwdnd.course1.cloudstorage.models;

public class File {
  private Integer fileId;

  private String filename;
  private String contentType;
  private String fileSize;
  private Integer userId;
  private byte[] fileData;

  public File(int fileId, String fileName, String contentType, String fileSize, Integer userId, byte[] fileData) {
    this.fileId = fileId;
    this.filename = fileName;
    this.contentType = contentType;
    this.fileSize = fileSize;
    this.userId = userId;
    this.fileData = fileData;
  }

  public Integer getFileId() {
    return fileId;
  }

  public void setFileId(Integer fileId) {
    this.fileId = fileId;
  }

  public String getFilename() {
    return filename;
  }

  public void setFilename(String filename) {
    this.filename = filename;
  }

  public String getContentType() {
    return contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  public String getFileSize(){ return this.fileSize; }
  public void setFileSize(String fileSize){  this.fileSize = fileSize;}

  public Integer getUserId() { return this.userId;}
  public void setUserId(Integer userId) { this.userId = userId;}

  public byte[] getFileData() { return this.fileData;}
  public void setFileData(byte[] fileData) { this.fileData = fileData;}




}
