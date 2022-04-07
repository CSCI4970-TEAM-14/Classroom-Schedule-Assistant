package Tests;



import org.junit.AfterClass;
import  org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {
   private static WebDriver driver;

   private static String chromePath = "/ClassScheduler/WebContent/chromedriver.exe";
   private static String systemPath = HomepageTest.class.getProtectionDomain().getCodeSource().getLocation().toExternalForm().replace("file:/", "").replace("ClassScheduler/build/classes/", "").replace("%20", " ");
    
   @BeforeClass
   public static void setUp() {	
      System.setProperty("webdriver.chrome.driver", systemPath + chromePath);
      driver=new ChromeDriver(); 
   }
   @Test 
   public void login() throws Exception  { 

      driver.get("http://localhost:8080/ClassScheduler/login.jsp");

   	
      driver.findElement(By.xpath("//input[@name='UserId']")).sendKeys("testUI"); 
      driver.findElement(By.xpath("//input[@name='Password']")).sendKeys("testP"); 
      driver.findElement(By.xpath("//input[@value='Login']")).click();

      String actualUrl="http://localhost:8080/ClassScheduler/homepage.html";
 
      String expectedUrl= driver.getCurrentUrl(); 
      Assert.assertEquals(expectedUrl,actualUrl); 
   }   

   @AfterClass
    public static void closeBrowser() {
      driver.close();
   }

}