import org.openqa.selenium.By;
//import necessary Selenium WebDriver classes
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class NewTest {
  //declare Selenium WebDriver
  private WebDriver webDriver;		
  
  @Test
  public void checkPages() {
	  //Load website as a new page
	  webDriver.navigate().to("http://localhost:8090/NotePad2/NoteServlet/dashboard");
	  
	  //Assert the title to check that we are indeed in the correct website
	  Assert.assertEquals(webDriver.getTitle(), "Home");
	  
	  System.out.println("title: "+webDriver.getTitle());
	  
	  //Retrieve link using it's class name and click on it
	  webDriver.findElement(By.className("link")).click();

	  //Assert the new title to check that the title contain Wikipedia and the button had successfully bring us to the new page
	  Assert.assertTrue(webDriver.getTitle().contains("User Management Application"));
	  System.out.println("new title: "+webDriver.getTitle());
  }
  
  @Test
  public void checkCreate() {
	  webDriver.navigate().to("http://localhost:8090/NotePad2/create.jsp");
	  
	  webDriver.findElement(By.name("userName")).sendKeys("testngusername");
	  webDriver.findElement(By.name("title")).sendKeys("testngtitle");
	  webDriver.findElement(By.name("description")).sendKeys("testngdescription");
	  
	  webDriver.findElement(By.className("button")).submit();
	  
	  Assert.assertTrue(webDriver.getTitle().contains("Home"));
  }
  
  @Test
  public void checkUpdate() {
	  webDriver.navigate().to("http://localhost:8090/NotePad2/NoteServlet/edit?user=testngusername");
	  
	  webDriver.findElement(By.name("title")).sendKeys("UPDATED");
	  webDriver.findElement(By.name("details")).sendKeys("UPDATED");
	  
	  webDriver.findElement(By.name("submit")).submit();
	  
	  Assert.assertEquals(webDriver.getTitle(), "Home");
  }
  
  @Test
  public void checkDelete() {
	  webDriver.navigate().to("http://localhost:8090/NotePad2/NoteServlet/delete?user=testngusername");
	  Assert.assertTrue(webDriver.getTitle().contains("Home"));
  }
  
  @BeforeTest
  public void beforeTest() {
	  //Setting system properties of ChromeDriver
	  //to amend directory path base on your local file path
	  String chromeDriverDir = "C:\\Program Files\\Google\\Chrome\\chromedriver.exe";

	  System.setProperty("webdriver.chrome.driver", chromeDriverDir);

	  //initialize FirefoxDriver at the start of test
	  webDriver = new ChromeDriver();  
  }

//  @AfterTest
//  public void afterTest() {
//	  //Quit the ChromeDriver and close all associated window at the end of test
//	  webDriver.quit();			
//  }

}