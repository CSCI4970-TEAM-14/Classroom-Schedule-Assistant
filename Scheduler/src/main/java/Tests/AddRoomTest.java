package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.remote.DesiredCapabilities;

public class AddRoomTest {
   public static void main(String[] args) {
      System.setProperty("webdriver.chrome.driver","C:\\CapstoneProject\\WorkSpace-14\\Scheduler\\src\\main\\webapp\\WEB-INF\\chromedriver.exe");
   	
      WebDriver g = new ChromeDriver();
       	//g.get("http://ec2-3-129-194-150.us-east-2.compute.amazonaws.com:8080/Scheduler/login.jsp");
         
      g.get("http://localhost:8080/Scheduler/addClass.jsp");
       	
      g.findElement(By.name("room")).sendKeys("roomName"); 
      g.findElement(By.name("type")).sendKeys("typeName");
      g.findElement(By.name("seat")).sendKeys("seatName"); 
 
       	
      g.findElement(By.id("addBtn")).click();
       	
      String u = g.getCurrentUrl();
       	
      if(u.equalsIgnoreCase("http://localhost:8080/Scheduler/classView.jsp"))
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

