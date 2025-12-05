package com.galaxe.sampleci.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class HelloPageTest {

    private WebDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        // Configure Chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // run headless in CI

        // Connect to Selenium Grid (hub must be running in Docker Compose)
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
    }

    @Test
    public void testOpenHomePage() {
        driver.get("http://localhost:8081"); // your Spring Boot app URL
        String title = driver.getTitle();
        System.out.println("Page title is: " + title);

        // Simple assertion
        assert title != null && !title.isEmpty() : "Page title should not be empty";
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
