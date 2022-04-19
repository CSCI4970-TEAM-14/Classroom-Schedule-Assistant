
package Tests;
//import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class LoginTest {

    public static void main(String[] args) {
    System.setProperty("webdriver.chrome.driver","\\Scheduler\\WebContent\\chromedriver.exe"); 
    WebDriver g = new ChromeDriver();
    g.get("http://localhost:8080/Scheduler/login.jsp");
    
    g.findElement(By.name("Username")).sendKeys("username");
    g.findElement(By.name("pwd")).sendKeys("password");

    g.findElement(By.id("subBtn")).click();
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
