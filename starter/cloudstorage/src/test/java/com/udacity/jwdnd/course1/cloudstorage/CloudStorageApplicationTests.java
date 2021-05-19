package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
	@Order(1)
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	@Order(2)
	public void testHomeWithoutLoggin(){
		String homeURI = "http://localhost:" + this.port + "/home";
		Assertions.assertNotEquals("Home",driver.getTitle());
	}

	@Test
	@Order(3)
	public void testSignupLoginFlow(){

		driver.get("http://localhost:" + this.port + "/signup");
		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup("Jiahao","Ye","hello","alt12345");
		driver.get("http://localhost:" + this.port + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login("hello","alt12345");

		Assertions.assertEquals("Home", driver.getTitle());

		HomePage homePage = new HomePage(driver,driverWait);
		homePage.logoutClick();
		Assertions.assertEquals("Login",driver.getTitle());
	}

	@Test
	@Order(4)
	public void testCredentialCreation(){
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
	}

	@Test
	@Order(5)
	public void testCredentialEdit(){
		String baseURI = "http://localhost:" + this.port;
		HomePage homePage = new HomePage(driver,driverWait);

		//login
		driver.get("http://localhost:" + this.port + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login("hello","alt12345");


		driver.get(baseURI + "/home#nav-credentials-tab");
		homePage.editCredential("username2");

	}

	@Test
	@Order(6)
	public void testCredentialDelete(){
		String baseURI = "http://localhost:" + this.port;
		HomePage homePage = new HomePage(driver,driverWait);

		//login
		driver.get("http://localhost:" + this.port + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login("hello","alt12345");


		driver.get(baseURI + "/home#nav-credentials-tab");
		homePage.deleteCredential();
	}

	@Test
	@Order(7)
	public void testNotesCreation(){
		String baseURI = "http://localhost:" + this.port;
		HomePage homePage = new HomePage(driver,driverWait);

		//login
		driver.get("http://localhost:" + this.port + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login("hello","alt12345");

		driver.get(baseURI + "/home#nav-notes-tab");
		homePage.createNote("TFG","descripcion corta");
	}

	@Test
	@Order(8)
	public void testNotesEdit(){
		String baseURI = "http://localhost:" + this.port;
		HomePage homePage = new HomePage(driver,driverWait);
		//login
		driver.get("http://localhost:" + this.port + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login("hello","alt12345");

		driver.get(baseURI + "/home#nav-notes-tab");
		homePage.editNote("second description");
	}

	@Test
	@Order(9)
	public void testNotesDelete(){
		String baseURI = "http://localhost:" + this.port;
		HomePage homePage = new HomePage(driver,driverWait);
		//login
		driver.get("http://localhost:" + this.port + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login("hello","alt12345");

		driver.get(baseURI + "/home#nav-notes-tab");
		homePage.deleteNote();
	}


}
