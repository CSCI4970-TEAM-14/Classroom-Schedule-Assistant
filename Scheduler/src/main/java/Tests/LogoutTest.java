package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;


public class LogoutTest {
   public static void main(String[] args) {
      System.setProperty("webdriver.chrome.driver","C:\\CapstoneProject\\WorkSpace-17\\Scheduler\\src\\main\\webapp\\WEB-INF\\chromedriver.exe");
	
      WebDriver driver = new ChromeDriver();

      driver.get("http://localhost:8080/Scheduler/login.jsp");
   
      driver.findElement(By.name("username")).sendKeys("username7"); 
      driver.findElement(By.name("password")).sendKeys("password7");
      
      driver.findElement(By.id("subBtn")).click();
    	
      String u = driver.getCurrentUrl();
      
      if(u.equalsIgnoreCase("http://localhost:8080/Scheduler/userHome.jsp?nameusername7"))
      {
         System.out.println("Login Test Case Passed . . .!");
      }
      else
      {
         System.out.println("Login Test Case Failed . . .!");
      }
    //Closing the browser session
      //driver.close();
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		try {
			
			WebElement logoutBtn = driver.findElement(By.id("logOutBtn"));
			
			if(logoutBtn.isDisplayed()){

				logoutBtn.click();
				System.out.println("Logout Test Case Passed . . .!");
			}
		} 
		catch (Exception e) {
			System.out.println("Logout Test Case Failed ....!");
		}
		//Closing the browser session
		//driver.quit();

    	
   }
}