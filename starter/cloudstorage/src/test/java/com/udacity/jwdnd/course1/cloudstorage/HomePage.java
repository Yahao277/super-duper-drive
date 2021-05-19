package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
  @FindBy(id = "nav-notes-tab")
  private WebElement notesTab;

  @FindBy(id = "add-note-btn")
  private WebElement addNoteBtn;

  @FindBy(id = "note-title")
  private WebElement noteTitle;

  @FindBy(id = "note-description")
  private WebElement noteDescription;

  @FindBy(id = "note-save")
  private WebElement noteSave;

  @FindBy(xpath = "//*[@id=\"userTable\"]/tbody/tr/td[1]/button")
  private WebElement noteEditBtn;

  @FindBy(xpath = "//*[@id=\"userTable\"]/tbody/tr/td[1]/a")
  private WebElement noteDelBtn;

  @FindBy(id = "nav-credentials-tab")
  private WebElement credentialsTab;

  @FindBy(id = "credential-add-btn")
  private WebElement credentialAddBtn;

  @FindBy(id = "credential-url")
  private WebElement credentialUrl;

  @FindBy(id = "credential-username")
  private WebElement credentialUsername;

  @FindBy(id = "credential-password")
  private WebElement credentialPassword;

  @FindBy(id = "credential-save")
  private WebElement credentialSave;

  @FindBy(xpath = "//*[@id=\"credentialTable\"]/tbody/tr/td[1]/a")
  private WebElement deleteCredentialBtn;

  @FindBy(xpath = "//*[@id=\"credentialTable\"]/tbody/tr/td[1]/button")
  private WebElement editCredentialBtn;

  @FindBy(id = "logout-btn")
  public WebElement logoutBtn;

  @FindBy(id = "submit-button")
  private WebElement submitButton;

  private WebDriver driver;
  private final WebDriverWait driverWait;
  private final JavascriptExecutor js;

  public HomePage(WebDriver webDriver,WebDriverWait webDriverWait) {
    PageFactory.initElements(webDriver, this);
    this.driver = webDriver;
    this.driverWait = webDriverWait;
    this.js = (JavascriptExecutor) this.driver;
  }

  public void logoutClick(){
    this.js.executeScript("arguments[0].click()",this.logoutBtn);
  }

  public void createCredential(String url,String user,String password){
    this.js.executeScript("arguments[0].click()",this.credentialsTab);
    this.driverWait.until(ExpectedConditions.elementToBeClickable(this.credentialAddBtn));
    this.credentialAddBtn.click();
    this.driverWait.until(ExpectedConditions.elementToBeClickable(this.credentialUrl));
    this.credentialUrl.sendKeys(url);
    this.credentialUsername.sendKeys(user);
    this.credentialPassword.sendKeys(password);
    this.credentialSave.click();
  }

  public void editCredential(String username){
    this.js.executeScript("arguments[0].click()",this.credentialsTab);
    this.driverWait.until(ExpectedConditions.elementToBeClickable(this.editCredentialBtn));
    this.editCredentialBtn.click();
    this.driverWait.until(ExpectedConditions.elementToBeClickable(this.credentialUsername));
    this.credentialUsername.clear();
    this.credentialUsername.sendKeys(username);
    this.credentialSave.click();

  }

  public void deleteCredential(){
    this.js.executeScript("arguments[0].click()",this.credentialsTab);
    this.driverWait.until(ExpectedConditions.elementToBeClickable(this.deleteCredentialBtn));
    this.deleteCredentialBtn.click();
  }


  public void createNote(String title, String description){
    this.js.executeScript("arguments[0].click()",this.notesTab);
    this.driverWait.until(ExpectedConditions.elementToBeClickable(this.addNoteBtn));
    this.addNoteBtn.click();
    this.driverWait.until(ExpectedConditions.elementToBeClickable(this.noteTitle));
    this.noteTitle.sendKeys(title);
    this.noteDescription.sendKeys(description);
    this.noteSave.click();
  }

  public void editNote(String description){
    this.js.executeScript("arguments[0].click()",this.notesTab);
    this.driverWait.until(ExpectedConditions.elementToBeClickable(this.noteEditBtn));
    this.noteEditBtn.click();
    this.driverWait.until(ExpectedConditions.elementToBeClickable(this.noteDescription));
    this.noteDescription.clear();
    this.noteDescription.sendKeys(description);
    this.noteSave.click();
  }

  public void deleteNote(){
    this.js.executeScript("arguments[0].click()",this.notesTab);
    this.driverWait.until(ExpectedConditions.elementToBeClickable(this.noteDelBtn));
    this.noteDelBtn.click();
  }




/*
  public Note getFirstNote() {
    String title = wait.until(ExpectedConditions.elementToBeClickable(tableNoteTitle)).getText();
    String description = tableNoteDescription.getText();

    return new Note(title, description);
  }

  public Credential getFirstCredential() {
    String url = wait.until(ExpectedConditions.elementToBeClickable(tblCredentialUrl)).getText();
    String username = tblCredentialUsername.getText();
    String password = tblCredentialPassword.getText();

    return new Credential(url, username, password);
  }

 */
}
