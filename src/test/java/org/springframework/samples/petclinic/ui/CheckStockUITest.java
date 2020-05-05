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

public class CheckStockUITest {
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
	public void testCheckStock() throws Exception {
		as("vet1","owner3")
		.whenIamLoggedIntheSystem()
		.decreaseStock();
	}
	
	private void decreaseStock() {
	    assertEquals("2", driver.findElement(By.xpath("//tr[6]/td")).getText());
	}
	
	private CheckStockUITest whenIamLoggedIntheSystem() {	
		return this;
	}
	

	private CheckStockUITest as(String username, String username2)  {
    driver.get("http://localhost:8080/");
    driver.findElement(By.xpath("//div[@id='main-navbar']/ul[2]/li/a")).click();
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys(username);
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys(passwordOfVet());
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.xpath("//div[@id='main-navbar']/ul/li[6]/a/span[2]")).click();
    driver.findElement(By.linkText("vacuna perros 2")).click();
    assertEquals("3", driver.findElement(By.xpath("//tr[6]/td")).getText());
    driver.findElement(By.xpath("//div[@id='main-navbar']/ul[2]/li/a")).click();
    driver.findElement(By.linkText("Cerrar Sesión")).click();
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.xpath("//div[@id='main-navbar']/ul[2]/li/a")).click();
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys(username2);
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys(passwordOfOwner());
    driver.findElement(By.id("password")).sendKeys(Keys.ENTER);
    driver.findElement(By.linkText("Mi Información")).click();
    driver.findElement(By.linkText("Crear cita")).click();
    driver.findElement(By.id("appointmentDate")).click();
    driver.findElement(By.linkText("29")).click();
    driver.findElement(By.id("reason")).click();
    driver.findElement(By.id("reason")).clear();
    driver.findElement(By.id("reason")).sendKeys("hola");
    driver.findElement(By.id("vaccine")).click();
    new Select(driver.findElement(By.id("vaccine"))).selectByVisibleText("vacuna perros 2");
    driver.findElement(By.xpath("//option[@value='vacuna perros 2']")).click();
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.xpath("//div[@id='main-navbar']/ul[2]/li/a")).click();
    driver.findElement(By.linkText("Cerrar Sesión")).click();
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.xpath("//div[@id='main-navbar']/ul[2]/li/a")).click();
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys(username);
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys(passwordOfVet());
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.xpath("//div[@id='main-navbar']/ul/li[6]/a/span[2]")).click();
    driver.findElement(By.linkText("vacuna perros 2")).click();
    return this;
  }
	
	private CharSequence passwordOfVet() {
		return "v3t";
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
