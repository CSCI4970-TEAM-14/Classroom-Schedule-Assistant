package Tests;

//import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class HomepageTest {
   private static WebDriver driver;

   private static String chromePath = "/ClassScheduler/WebContent/chromedriver.exe";
 
   private static String systemPath = HomepageTest.class.getProtectionDomain().getCodeSource().getLocation().toExternalForm().replace("file:/", "").replace("ClassScheduler/build/classes/", "").replace("%20", " ");
	
   @BeforeClass
   public static void setup() {
      System.setProperty("webdriver.chrome.driver", systemPath + chromePath);
      driver = new ChromeDriver();
   }

   @Before
   public void navigate()
   {
   	
	   //driver.get("http://localhost:8080/ClassScheduler/WEB-INF/classes/Tests/HomepageTest.java");
	   driver.get("http://localhost:8080/ClassScheduler/homepage.html");
	   
   }
	
   @Test
   public void testScreen()
   {
      Assert.assertEquals("Incorrect Homepage Title", "Classroom Scheduler ", driver.findElement(By.xpath("//header/h1")).getText());
   }

   @Test
   public void testLinks()
   {
      driver.findElement(By.linkText("Login")).click();
      Assert.assertEquals("Incorrect Login Title", "Login", driver.findElement(By.xpath("//header/h1")).getText());
      driver.findElement(By.linkText("Home")).click();
      testScreen();
   	
   }


   @AfterClass
    public static void closeBrowser() {
      driver.close();
   }
}