package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.junit.AfterClass;
import  org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class LoginTest {
   private static WebDriver driver;
   
   private static String chromePath = "/ClassScheduler/WebContent/chromedriver.exe";
   private static String systemPath = LoginTest.class.getProtectionDomain().getCodeSource().getLocation().toExternalForm().replace("file:/", "").replace("ClassScheduler/build/classes/", "").replace("%20", " ");
    
   @BeforeClass
   public static void setUp() {	
      System.setProperty("webdriver.chrome.driver", systemPath + chromePath);
      driver=new ChromeDriver(); 
   }
   @Test 
   public void login() throws Exception  { 
      driver.get("http://localhost:8080/ClassScheduler/login.jsp");
      //driver.findElement(By.xpath("//input[@name='UserId']")).sendKeys("testUI"); 
      driver.findElement(By.id("UserId")).sendKeys("testUser"); 
      //driver.findElement(By.xpath("//input[@name='Password']")).sendKeys("testP"); 
      driver.findElement(By.id("pwd")).sendKeys("testPass");
   
      //driver.findElement(By.xpath("/html/body/table/tbody/tr[46]/td[2]")).click();
      ///html/body/table/tbody/tr[46]/td[2]
      
      String actualUrl="http://localhost:8080/ClassScheduler/homepage.html";
 
      String expectedUrl= driver.getCurrentUrl(); 
      Assert.assertEquals(expectedUrl,actualUrl); 
   }   
   @AfterClass
    public static void closeBrowser() {
      driver.close();
   }
}