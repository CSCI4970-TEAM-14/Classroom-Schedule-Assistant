package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RegisterTest {
	
	public static void main(String[] args) {
    	System.setProperty("webdriver.chrome.driver","C:\\CapstoneProject\\WorkSpace-11\\Scheduler\\src\\main\\webapp\\WEB-INF\\chromedriver.exe");
    	
    	WebDriver g = new ChromeDriver();
    	
    	//g.get("http://ec2-3-129-194-150.us-east-2.compute.amazonaws.com:8080/Scheduler/login.jsp");
    	g.get("http://localhost:8080/Scheduler/register.jsp");
    	
    	g.findElement(By.name("Username")).sendKeys("43156061"); 
    	g.findElement(By.name("fname")).sendKeys("testFname");
    	g.findElement(By.name("lname")).sendKeys("testLname"); 
    	g.findElement(By.name("email")).sendKeys("testEmail");
    	g.findElement(By.name("pwd")).sendKeys("char@gmail.com"); 
    	
    	g.findElement(By.id("regBtn")).click();
    	
    	String u = g.getCurrentUrl();
    	
    	if(u.equalsIgnoreCase("http://ec2-3-129-194-150.us-east-2.compute.amazonaws.com:8080/Scheduler/login.jsp"))
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

