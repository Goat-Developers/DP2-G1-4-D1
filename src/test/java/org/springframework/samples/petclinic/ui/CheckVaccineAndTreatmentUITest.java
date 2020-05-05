package org.springframework.samples.petclinic.ui;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.test.annotation.DirtiesContext;

@DirtiesContext
public class CheckVaccineAndTreatmentUITest {
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
 	public void testCheckVaccineAndTreatment() throws Exception {
 		as("vet1")
 		.whenIamLoggedIntheSystem()
 		.checkAll();
 		
 	}
  
  private void checkAll() {
	  try {
	      assertEquals("Vacuna de la rabia", driver.findElement(By.xpath("//td")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    try {
	      assertEquals("cat", driver.findElement(By.xpath("//tr[2]/td")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    try {
	      assertEquals("Para ratas", driver.findElement(By.xpath("//tr[3]/td")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    try {
	      assertEquals("32.3", driver.findElement(By.xpath("//tr[4]/td")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    try {
	      assertEquals("Chema", driver.findElement(By.xpath("//tr[5]/td")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    try {
	      assertEquals("30", driver.findElement(By.xpath("//tr[6]/td")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    try {
	      assertEquals("produce ardores", driver.findElement(By.xpath("//tr[7]/td")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    try {
	      assertEquals("2022-06-08", driver.findElement(By.xpath("//tr[8]/td")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    driver.findElement(By.xpath("//div[@id='main-navbar']/ul/li[7]/a/span[2]")).click();
	    driver.findElement(By.linkText("Dientes serpiente")).click();
	    try {
	      assertEquals("Dientes serpiente", driver.findElement(By.xpath("//td")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    try {
	      assertEquals("snake", driver.findElement(By.xpath("//tr[2]/td")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    try {
	      assertEquals("65.7", driver.findElement(By.xpath("//tr[3]/td")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    try {
	      assertEquals("Limpieza de dientes", driver.findElement(By.xpath("//tr[4]/td")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    
	}
 	
 	
 	
 	private CheckVaccineAndTreatmentUITest whenIamLoggedIntheSystem() {	
 		return this;
 	}

  
 	private CheckVaccineAndTreatmentUITest as(String username) throws Exception {
    driver.get("http://localhost:8080/");
    driver.findElement(By.xpath("//div[@id='main-navbar']/ul[2]/li/a")).click();
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys(username);
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys(passwordOfVet());
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.xpath("//div[@id='main-navbar']/ul/li[6]/a/span[2]")).click();
    driver.findElement(By.xpath("//table[@id='vaccineTable']/tbody/tr/td")).click();
    driver.findElement(By.linkText("Vacuna de la rabia")).click();
    
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
