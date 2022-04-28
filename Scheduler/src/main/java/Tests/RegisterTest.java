package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.remote.DesiredCapabilities;

public class RegisterTest {
   public static void main(String[] args) {
      System.setProperty("webdriver.chrome.driver","C:\\CapstoneProject\\WorkSpace-14\\Scheduler\\src\\main\\webapp\\WEB-INF\\chromedriver.exe");
   	
      WebDriver g = new ChromeDriver();
       	//g.get("http://ec2-3-129-194-150.us-east-2.compute.amazonaws.com:8080/Scheduler/login.jsp");
         
      g.get("http://localhost:8080/Scheduler/register.jsp");
       	
      g.findElement(By.name("username")).sendKeys("43156061"); 
      g.findElement(By.name("firstName")).sendKeys("testFname");
      g.findElement(By.name("lastName")).sendKeys("testLname"); 
      g.findElement(By.name("email")).sendKeys("testEmail");
      g.findElement(By.name("password")).sendKeys("char@gmail.com"); 
       	
      g.findElement(By.id("regBtn")).click();
       	
      String u = g.getCurrentUrl();
       	
      if(u.equalsIgnoreCase("http://localhost:8080/Scheduler/userHome.jsp"))
      {
         System.out.println("Test case passed");
      }
      else
      {
         System.out.println("Test case failed");
      }
       	
      g.close();
   
   }
}

