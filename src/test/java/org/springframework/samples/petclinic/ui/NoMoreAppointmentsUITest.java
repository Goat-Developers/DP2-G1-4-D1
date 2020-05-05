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
import org.openqa.selenium.support.ui.Select;

public class NoMoreAppointmentsUITest {
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
	public void testNoMoreAppointmentsUI() throws Exception {
		as("owner1")
		.whenIamLoggedIntheSystem()
		.checkNoMoreAppointmentsAvailable();
	}
	
	private void checkNoMoreAppointmentsAvailable() {
		  assertEquals("required no hay cita disponible a esa hora y ese día", driver.findElement(By.xpath("//form[@id='add-appointment-form']/div[2]/div/span[2]")).getText());
	}
	
	private NoMoreAppointmentsUITest whenIamLoggedIntheSystem() {	
		return this;
	}
	

	private NoMoreAppointmentsUITest as(String username) throws Exception {
 
    driver.get("http://localhost:8080/");
    driver.findElement(By.xpath("//div[@id='main-navbar']/ul[2]/li/a")).click();
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys(username);
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys(passwordOfOwner());
    driver.findElement(By.id("password")).sendKeys(Keys.ENTER);
    driver.findElement(By.linkText("Mi Información")).click();
    driver.findElement(By.linkText("Añadir nueva mascota")).click();
    driver.findElement(By.id("name")).click();
    driver.findElement(By.id("name")).clear();
    driver.findElement(By.id("name")).sendKeys("buddy");
    driver.findElement(By.id("birthDate")).click();
    driver.findElement(By.linkText("28")).click();
    new Select(driver.findElement(By.id("type"))).selectByVisibleText("bird");
    driver.findElement(By.xpath("//option[@value='bird']")).click();
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.linkText("Crear cita")).click();
    driver.findElement(By.id("appointmentDate")).click();
    driver.findElement(By.linkText("30")).click();
    driver.findElement(By.id("reason")).click();
    driver.findElement(By.id("reason")).clear();
    driver.findElement(By.id("reason")).sendKeys("primer");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.xpath("(//a[contains(text(),'Crear cita')])[2]")).click();
    driver.findElement(By.id("appointmentDate")).click();
    driver.findElement(By.linkText("30")).click();
    driver.findElement(By.id("reason")).click();
    driver.findElement(By.id("reason")).clear();
    driver.findElement(By.id("reason")).sendKeys("second");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.linkText("Añadir nueva mascota")).click();
    driver.findElement(By.id("name")).click();
    driver.findElement(By.id("name")).clear();
    driver.findElement(By.id("name")).sendKeys("teddy");
    driver.findElement(By.id("birthDate")).click();
    driver.findElement(By.linkText("19")).click();
    new Select(driver.findElement(By.id("type"))).selectByVisibleText("cat");
    driver.findElement(By.xpath("//option[@value='cat']")).click();
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.xpath("(//a[contains(text(),'Crear cita')])[3]")).click();
    driver.findElement(By.id("appointmentDate")).click();
    driver.findElement(By.linkText("30")).click();
    driver.findElement(By.id("reason")).click();
    driver.findElement(By.id("reason")).clear();
    driver.findElement(By.id("reason")).sendKeys("third");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.linkText("Añadir nueva mascota")).click();
    driver.findElement(By.id("name")).click();
    driver.findElement(By.id("name")).clear();
    driver.findElement(By.id("name")).sendKeys("rocky");
    driver.findElement(By.id("birthDate")).click();
    driver.findElement(By.xpath("//div[@id='ui-datepicker-div']/div")).click();
    driver.findElement(By.xpath("//div[@id='ui-datepicker-div']/div/a/span")).click();
    driver.findElement(By.linkText("16")).click();
    new Select(driver.findElement(By.id("type"))).selectByVisibleText("dog");
    driver.findElement(By.xpath("//option[@value='dog']")).click();
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.xpath("(//a[contains(text(),'Crear cita')])[3]")).click();
    driver.findElement(By.id("appointmentDate")).click();
    driver.findElement(By.linkText("30")).click();
    driver.findElement(By.id("reason")).click();
    driver.findElement(By.id("reason")).clear();
    driver.findElement(By.id("reason")).sendKeys("forth");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
  
    return this;
	}
	private CharSequence passwordOfOwner() {
		return "0wn3r";
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
