
package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class AddRoomTest {
   public static void main(String[] args) {
      System.setProperty("webdriver.chrome.driver","C:\\CapstoneProject\\WorkSpace-17\\Scheduler\\src\\main\\webapp\\WEB-INF\\chromedriver.exe");
   	
      WebDriver driver = new ChromeDriver();
         
      driver.get("http://localhost:8080/Scheduler/addClass.jsp");
       	
      driver.findElement(By.name("room")).sendKeys("PKI 250"); 
      driver.findElement(By.name("type")).sendKeys("Lecture");
      driver.findElement(By.name("seat")).sendKeys("30");
      	
      driver.findElement(By.id("adjustBtn")).click();
       	
      String u = driver.getCurrentUrl();
       	
      if(u.equalsIgnoreCase("http://localhost:8080/Scheduler/classView.jsp"))
      {
         System.out.println("Test case passed . . .!");
      }
      else
      {
         System.out.println("Test case failed . . .!");
      }
      
      //Closing the browser session
      //g.close();
   
   }
}