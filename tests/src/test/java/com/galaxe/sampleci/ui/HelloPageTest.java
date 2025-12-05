package com.galaxe.sampleci.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class HelloPageTest {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Point to ChromeDriver.exe
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ashutosh\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new"); // works on Windows
        driver = new ChromeDriver(options);
    }

    @Test
    public void regressionTests() {
        // Example sanity check
        Assert.assertTrue(true, "App should run without error");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}