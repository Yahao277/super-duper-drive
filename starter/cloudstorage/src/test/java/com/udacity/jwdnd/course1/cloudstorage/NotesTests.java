package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NotesTests {
  @LocalServerPort
  private int port;

  private WebDriver driver;

  @BeforeAll
  static void beforeAll() {
    WebDriverManager.chromedriver().setup();
  }

  @BeforeEach
  public void beforeEach() {
    this.driver = new ChromeDriver();
  }

  @AfterEach
  public void afterEach() {
    if (this.driver != null) {
      driver.quit();
    }
  }

  @Test
  public void testCRUDNotesFlow(){
    //login
    driver.get("http://localhost:" + this.port + "/signup");
    SignupPage signupPage = new SignupPage(driver);
    signupPage.signup("Jiahao","Ye","hello","alt12345");
    driver.get("http://localhost:" + this.port + "/login");
    LoginPage loginPage = new LoginPage(driver);
    loginPage.login("hello","alt12345");

    Assertions.assertEquals("Home", driver.getTitle());

    //Test notes



  }

}
