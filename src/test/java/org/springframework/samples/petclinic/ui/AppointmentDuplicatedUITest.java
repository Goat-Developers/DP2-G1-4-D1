package org.springframework.samples.petclinic.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AppointmentDuplicatedUITest {
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
	public void testAppointmentDuplicatedUI() throws Exception {
		as("owner1")
		.whenIamLoggedIntheSystem()
		.checkDuplicatedAppointment();
	}
	
	private void checkDuplicatedAppointment() {
		 assertEquals("required su mascota ya dispone de una cita para ese día y hora", driver.findElement(By.xpath("//form[@id='add-appointment-form']/div/div/span[2]")).getText());
	}
	
	private AppointmentDuplicatedUITest whenIamLoggedIntheSystem() {	
		return this;
	}
	

	private AppointmentDuplicatedUITest as(String username) throws Exception {

  
    driver.get("http://localhost:8080/");
    driver.findElement(By.xpath("//div[@id='main-navbar']/ul[2]/li/a")).click();
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys(username);
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("0wn3r");
    driver.findElement(By.id("password")).sendKeys(Keys.ENTER);
    driver.findElement(By.linkText("Mi Información")).click();
    driver.findElement(By.linkText("Crear cita")).click();
    driver.findElement(By.id("appointmentDate")).click();
    driver.findElement(By.linkText("30")).click();
    driver.findElement(By.id("reason")).click();
    driver.findElement(By.id("reason")).clear();
    driver.findElement(By.id("reason")).sendKeys("ninguna");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.linkText("Crear cita")).click();
    driver.findElement(By.id("appointmentDate")).click();
    driver.findElement(By.linkText("30")).click();
    driver.findElement(By.id("add-appointment-form")).click();
    driver.findElement(By.id("reason")).click();
    driver.findElement(By.id("reason")).clear();
    driver.findElement(By.id("reason")).sendKeys("otra");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.xpath("//form[@id='add-appointment-form']/div/div/span[2]")).click();
   
    return this;
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
