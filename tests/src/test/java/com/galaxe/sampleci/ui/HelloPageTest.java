package com.galaxe.sampleci.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;



import java.net.MalformedURLException;
import java.net.URL;

public class HelloPageTest {

    private WebDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        // Configure Chrome options
    	ChromeOptions options = new ChromeOptions();
    	options.addArguments("--headless=new");
    	options.addArguments("--no-sandbox");
    	options.addArguments("--disable-dev-shm-usage");

        // Connect to Selenium Grid (hub must be running in Docker Compose)
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);

    }

    @Test
    public void testOpenHomePage() {
    	driver.get("http://springboot-app:8081/");
        String title = driver.getTitle();
        System.out.println("Page title is: " + title);
        String bodyText = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue(bodyText.contains("Hello World"), "Page should contain Hello World");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
