package Tests;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class AddScheduleTest {
   public static void main(String[] args) {
      System.setProperty("webdriver.chrome.driver","C:\\CapstoneProject\\WorkSpace-17\\Scheduler\\src\\main\\webapp\\WEB-INF\\chromedriver.exe");
      	
      WebDriver driver = new ChromeDriver();

            
      driver.get("http://localhost:8080/Scheduler/addSchedule.jsp");

      driver.findElement(By.name("course")).sendKeys("CSCI 4500"); 
      driver.findElement(By.name("sec")).sendKeys("3"); 
      driver.findElement(By.name("meth")).sendKeys("Online"); 
      driver.findElement(By.name("enr")).sendKeys("pi");
      driver.findElement(By.name("in")).sendKeys("Pei-Chi Huang"); 
      driver.findElement(By.name("day")).sendKeys(" ");
      driver.findElement(By.name("start")).sendKeys("12:00pm"); 
      driver.findElement(By.name("end")).sendKeys("1:15pm");
      driver.findElement(By.name("room")).sendKeys("Online");
    
          	
      driver.findElement(By.id("adjustBtn")).click();
          	
         String u = driver.getCurrentUrl();
          	
         if(u.equalsIgnoreCase("http://localhost:8080/Scheduler/addSchedule.jsp"))
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
