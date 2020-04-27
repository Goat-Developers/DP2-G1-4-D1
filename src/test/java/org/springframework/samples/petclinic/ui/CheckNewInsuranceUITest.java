package org.springframework.samples.petclinic.ui;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class CheckNewInsuranceUITest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeEach
  public void setUp() throws Exception {
	String pathToGeckoDriver="./target/classes/static/resources/";
	System.setProperty("webdriver.gecko.driver", pathToGeckoDriver + "geckodriver.exe");
    driver = new FirefoxDriver();
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testCheckNewInsuranceUI() throws Exception {
    driver.get("http://localhost:8080/");
    driver.findElement(By.xpath("//div[@id='main-navbar']/ul[2]/li/a")).click();
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("owner1");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("0wn3r");
    driver.findElement(By.id("password")).sendKeys(Keys.ENTER);
    driver.findElement(By.linkText("Mi Información")).click();
    driver.findElement(By.linkText("Añadir Seguro")).click();
    new Select(driver.findElement(By.id("insuranceBase"))).selectByVisibleText("Seguro Base Felino");
    driver.findElement(By.xpath("//option[@value='Seguro Base Felino']")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [addSelection | id=vaccines | label=Vacuna de la peste]]
    driver.findElement(By.xpath("//option[@value='Vacuna de la peste']")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [addSelection | id=treatments | label=Limpieza de dientes]]
    driver.findElement(By.xpath("//option[@value='Limpieza de dientes']")).click();
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.linkText("1")).click();
    try {
        assertEquals("Vacuna de la peste (32.3 Euros)\n Vacuna de la rabia (32.3 Euros)", driver.findElement(By.xpath("//tr[2]/td")).getText());
      } catch (Error e) {
        verificationErrors.append(e.toString());
      }
      try {
        assertEquals("Limpieza de dientes (65.7 Euros)\n Corte de pelo (10.0 Euros)", driver.findElement(By.xpath("//tr[3]/td")).getText());
      } catch (Error e) {
        verificationErrors.append(e.toString());
      }
  }

  @AfterEach
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
