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
import org.springframework.test.annotation.DirtiesContext;

@DirtiesContext
public class CreateVaccineErrorUITest {
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
	public void testCreateVaccineError() throws Exception {
		as("vet1")
		.whenIamLoggedIntheSystem()
		.thenICanNotCreateAVaccine();
	}
	
	private void thenICanNotCreateAVaccine() {
		  assertEquals("e", driver.findElement(By.id("price")).getAttribute("value"));
	}
	
	private CreateVaccineErrorUITest whenIamLoggedIntheSystem() {	
		return this;
	}

	private CreateVaccineErrorUITest as(String username) {
    driver.get("http://localhost:8080/");
    driver.findElement(By.xpath("//div[@id='main-navbar']/ul[2]/li/a")).click();
    driver.findElement(By.id("username")).click();
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("vet1");
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys(passwordOfVet());
    driver.findElement(By.id("password")).sendKeys(Keys.ENTER);
    driver.findElement(By.linkText("Vacunas")).click();
    driver.findElement(By.linkText("Crear nueva vacuna")).click();
    driver.findElement(By.id("name")).click();
    driver.findElement(By.id("name")).clear();
    driver.findElement(By.id("name")).sendKeys("Vaccine2");
    driver.findElement(By.xpath("//form[@id='add-owner-form']/div")).click();
    driver.findElement(By.id("information")).click();
    driver.findElement(By.id("information")).clear();
    driver.findElement(By.id("information")).sendKeys("vaccine2");
    driver.findElement(By.id("price")).click();
    driver.findElement(By.id("price")).clear();
    driver.findElement(By.id("price")).sendKeys("e");
    driver.findElement(By.id("provider")).click();
    driver.findElement(By.id("provider")).clear();
    driver.findElement(By.id("provider")).sendKeys("Jose");
    driver.findElement(By.id("expiration")).click();
    driver.findElement(By.linkText("30")).click();
    driver.findElement(By.id("stock")).click();
    driver.findElement(By.id("stock")).clear();
    driver.findElement(By.id("stock")).sendKeys("5");
    new Select(driver.findElement(By.id("petType"))).selectByVisibleText("hamster");
    driver.findElement(By.xpath("//option[@value='hamster']")).click();
    driver.findElement(By.id("sideEffects")).click();
    driver.findElement(By.id("sideEffects")).clear();
    driver.findElement(By.id("sideEffects")).sendKeys("vaccine2");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
	return this;
  
  }
	
	private CharSequence passwordOfVet() {
		return "v3t";
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
