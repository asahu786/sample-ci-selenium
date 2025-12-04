// tests/src/test/java/com/galaxe/sampleci/ui/HelloPageTest.java
package com.galaxe.sampleci.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URL;

import static org.testng.Assert.assertTrue;

public class HelloPageTest {
  private WebDriver driver;

  @BeforeClass
  public void setUp() throws Exception {
    ChromeOptions options = new ChromeOptions();
    driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
  }

  @Test
  public void helloEndpointLoads() {
    driver.get("http://localhost:8080/hello");
    assertTrue(driver.getPageSource().contains("Hello from Spring Boot App!"));
  }

  @AfterClass
  public void tearDown() {
    if (driver != null) driver.quit();
  }
}
