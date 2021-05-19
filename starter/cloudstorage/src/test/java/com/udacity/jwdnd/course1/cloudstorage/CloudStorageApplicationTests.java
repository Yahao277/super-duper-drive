package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;
	private WebDriverWait driverWait;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
		this.driverWait = new WebDriverWait (this.driver, 1000);
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	@Order(1)
	public void testSignupLoginFlow(){

		driver.get("http://localhost:" + this.port + "/signup");
		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup("Jiahao","Ye","hello","alt12345");
		driver.get("http://localhost:" + this.port + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login("hello","alt12345");

		Assertions.assertEquals("Home", driver.getTitle());

	}

	@Test
	@Order(2)
	public void testCRUDCredentialsFlow(){
		String baseURI = "http://localhost:" + this.port;

		//login
		driver.get("http://localhost:" + this.port + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login("hello","alt12345");

		Assertions.assertEquals("Home", driver.getTitle());

		//Test credentials
		HomePage homePage = new HomePage(driver,driverWait);
		driver.get(baseURI + "/home#nav-credentials-tab");
		homePage.createCredential("http://localhost:8080","user","123");
		driver.get(baseURI + "/home#nav-credentials-tab");
		homePage.editCredential("username2");
		driver.get(baseURI + "/home#nav-credentials-tab");
		homePage.deleteCredential();

	}

	@Test
	@Order(3)
	public void testCRUDNotesFlow(){
		String baseURI = "http://localhost:" + this.port;
		//login
		driver.get("http://localhost:" + this.port + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login("hello","alt12345");

		Assertions.assertEquals("Home", driver.getTitle());

		//Test notes
		HomePage homePage = new HomePage(driver,driverWait);
		driver.get(baseURI + "/home#nav-notes-tab");
		homePage.createNote("TFG","descripcion corta");
		driver.get(baseURI + "/home#nav-notes-tab");
		homePage.editNote("second description");
		driver.get(baseURI + "/home#nav-notes-tab");
		homePage.deleteNote();

	}

}
