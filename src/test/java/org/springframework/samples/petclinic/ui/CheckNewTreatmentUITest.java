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


public class CheckNewTreatmentUITest {
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
  public void testCheckNewTreatment() throws Exception {
	as("vet1")
	.whenIamLoggedIntheSystem()
	.thenICanCreateATreatment();;
  }
  private void thenICanCreateATreatment() {
	  try {
	     assertEquals("limpieza de coronavirus", driver.findElement(By.xpath("//td")).getText());
	    			} catch (Error e) {
	   verificationErrors.append(e.toString());
	    			}
	    	try {
	      
	    			
	    			
	    			
	    			assertEquals("bird", driver.findElement(By.xpath("//tr[2]/td")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    try {
	      assertEquals("132.0", driver.findElement(By.xpath("//tr[3]/td")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    try {
	      assertEquals("AntiCoronavirus", driver.findElement(By.xpath("//tr[4]/td")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
  }
  
	
  private CheckNewTreatmentUITest whenIamLoggedIntheSystem() {	
	return this;
  }
	
  private CheckNewTreatmentUITest as(String username)throws Exception {
		
    driver.get("http://localhost:8080/");
    driver.findElement(By.xpath("//div[@id='main-navbar']/ul[2]/li/a")).click();
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys(username);
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys(passwordOfVet());
    driver.findElement(By.id("password")).sendKeys(Keys.ENTER);
    driver.findElement(By.linkText("Tratamientos")).click();
    driver.findElement(By.linkText("Crear nuevo tratamiento")).click();
    driver.findElement(By.id("type")).click();
    driver.findElement(By.id("type")).clear();
    driver.findElement(By.id("type")).sendKeys("limpieza de coronavirus");
    driver.findElement(By.id("price")).click();
    driver.findElement(By.id("price")).clear();
    driver.findElement(By.id("price")).sendKeys("132");
    driver.findElement(By.id("description")).click();
    driver.findElement(By.id("description")).clear();
    driver.findElement(By.id("description")).sendKeys("AntiCoronavirus");
    new Select(driver.findElement(By.id("petType"))).selectByVisibleText("bird");
    driver.findElement(By.xpath("//option[@value='bird']")).click();
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.linkText("Volver")).click();
    driver.findElement(By.linkText("limpieza de coronavirus")).click();
    
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
