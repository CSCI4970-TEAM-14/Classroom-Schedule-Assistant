package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class RegisterTest {
   public static void main(String[] args) {
      System.setProperty("webdriver.chrome.driver","C:\\CapstoneProject\\WorkSpace-17\\Scheduler\\src\\main\\webapp\\WEB-INF\\chromedriver.exe");
   	
      WebDriver g = new ChromeDriver();
         
      g.get("http://localhost:8080/Scheduler/register.jsp");
       	
      g.findElement(By.name("username")).sendKeys("username13"); 
      g.findElement(By.name("firstName")).sendKeys("firstName13");
      g.findElement(By.name("lastName")).sendKeys("lastName13"); 
      g.findElement(By.name("email")).sendKeys("username11@gmail.com");
      g.findElement(By.name("password")).sendKeys("password13"); 
       	
      g.findElement(By.id("subBtn")).click();
       	
      String u = g.getCurrentUrl();
       	
      if(u.equalsIgnoreCase("http://localhost:8080/Scheduler/userHome.jsp?%20namefirstName13"))
      {
         System.out.println("Test case passed");
      }
      else
      {
         System.out.println("Test case failed");
      }
      //Closing the browser session
      //g.close();

   }
}

