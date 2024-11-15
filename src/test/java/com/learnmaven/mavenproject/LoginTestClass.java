package com.learnmaven.mavenproject;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

class LoginTestClass {
	WebDriver driver = null;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		WebDriverManager.chromedriver().setup(); 
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		
	}

	@BeforeEach
	void setUp() throws Exception {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@AfterEach
	void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	void testSuccessfulLogin() {
		try {
            String url = "https://practicetestautomation.com/practice-test-login/";  
            driver.get(url);
            WebElement usernameField = driver.findElement(By.id("username"));  
            usernameField.sendKeys("student");  
            WebElement passwordField = driver.findElement(By.id("password"));  
            passwordField.sendKeys("Password123");  

            WebElement loginButton = driver.findElement(By.id("submit"));  
            loginButton.click();
            
            
            String currentUrl = driver.getCurrentUrl();
            assertEquals("https://practicetestautomation.com/logged-in-successfully/", currentUrl, "URL did not match. Login failed.");
            String congratulationsMessage = driver.findElement(By.xpath("//div[@id='loop-container']/div/article/div[2]/p/strong")).getText();
            assertEquals("Congratulations student. You successfully logged in!", congratulationsMessage, "Congratulations message is not displayed as expected.");

            WebElement logoutButton = driver.findElement(By.xpath("//div[@id='loop-container']/div/article/div[2]/div/div/div/a"));
            assertTrue(logoutButton.isDisplayed(),"Logout button is not displayed");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	@Test
	void testWrongUsername()
	{
		try {
            String url = "https://practicetestautomation.com/practice-test-login/";  
            driver.get(url);

            WebElement usernameField = driver.findElement(By.id("username"));  
            usernameField.sendKeys("incorrectUser");  
            WebElement passwordField = driver.findElement(By.id("password"));  
            passwordField.sendKeys("Password123");  

            WebElement loginButton = driver.findElement(By.id("submit"));  
            loginButton.click();
            
            
            WebElement errorMessage = driver.findElement(By.id("error"));
            assertTrue(errorMessage.isDisplayed(),"Error Message is not displayed");
            assertEquals("Your username is invalid!", errorMessage.getText(), "Error message did not match");

            
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	@Test
	void testWrongPassword()
	{
		try {
            String url = "https://practicetestautomation.com/practice-test-login/";  
            driver.get(url);

            WebElement usernameField = driver.findElement(By.id("username"));  
            usernameField.sendKeys("student");  
            WebElement passwordField = driver.findElement(By.id("password"));  
            passwordField.sendKeys("incorrectPassword");  

            WebElement loginButton = driver.findElement(By.id("submit"));  
            loginButton.click();
            
            
            WebElement errorMessage = driver.findElement(By.id("error"));
            assertTrue(errorMessage.isDisplayed(),"Error Message is not displayed");
            assertEquals("Your password is invalid!", errorMessage.getText(), "Error message did not match");


        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
