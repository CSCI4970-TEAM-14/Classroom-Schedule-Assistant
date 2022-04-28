package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.remote.DesiredCapabilities;

public class LoginTest {
   public static void main(String[] args) {
      System.setProperty("webdriver.chrome.driver","C:\\CapstoneProject\\WorkSpace-14\\Scheduler\\src\\main\\webapp\\WEB-INF\\chromedriver.exe");
      //capability.setCapability("chrome.binary", "C:\\CapstoneProject\\WorkSpace-13\\Scheduler\\src\\main\\webapp\\WEB-INF\\chromedriver.exe");
      //System.setProperty("webdriver.chrome.whitelistedIps", "C:\\CapstoneProject\\WorkSpace-13\\Scheduler\\src\\main\\webapp\\WEB-INF\\chromedriver.exe");
      
      //System.setProperty("webdriver.chrome.driver", "C:\\CapstoneProject\\WorkSpace-13\\Scheduler\\src\\main\\webapp\\WEB-INF\\chromedriver.exe");
      // DesiredCapabilities capability = new DesiredCapabilities();
       
       //capability.setCapability("binary", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
       
       //capability.setCapability("binary", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome_proxy.exe");
       //:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe
       //WebDriver driver = new ChromeDriver(capability);
      
      
      
      WebDriver g = new ChromeDriver();
    //g.get("http://localhost:8080/Scheduler/login.jsp");
    	//g.get("http://ec2-3-129-194-150.us-east-2.compute.amazonaws.com:8080/Scheduler/login.jsp");
      g.get("http://localhost:8080/Scheduler/login.jsp");
   
      g.findElement(By.name("username")).sendKeys("43156061"); 
      g.findElement(By.name("password")).sendKeys("char@gmail.com");
      g.findElement(By.id("subBtn")).click();
    	
        //g.findElement(By.xpath("/html/body/div[3]/form/div[2]/input[1]"));//.sendKeys("43156061");
        //g.findElement(By.xpath("/html/body/div[3]/form/div[2]/input[2]"));//.sendKeys("char@gmail.com");
        
   
       // g.findElement(By.id("subBtn")).click();
        //g.findElement(By.xpath("/html/body/div[3]/form/div[2]/button"));//.click();
    	
      String u = g.getCurrentUrl();
    	
      //if(u.equalsIgnoreCase("http://ec2-3-129-194-150.us-east-2.compute.amazonaws.com:8080/Scheduler/userHome.jsp"))
      if(u.equalsIgnoreCase("http://localhost:8080/Scheduler/userHome.jsp?name43156061"))
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

