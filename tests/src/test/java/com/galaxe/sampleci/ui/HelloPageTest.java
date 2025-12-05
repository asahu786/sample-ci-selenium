package com.galaxe.sampleci.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import java.net.MalformedURLException;
import java.net.URL;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class HelloPageTest {
    private WebDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new RemoteWebDriver(new URL("http://selenium-hub:4444/wd/hub"), options);
    }

    @Test
    public void testOpenHomePage() {
        driver.get("http://springboot-app:8081/");
        WebElement body = driver.findElement(By.tagName("body"));
        String bodyText = body.getText();
        System.out.println("Page body text: " + bodyText);
        Assert.assertTrue(bodyText.contains("Hello World Sir"), "Page should contain Hello World");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
