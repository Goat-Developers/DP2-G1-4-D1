package org.springframework.samples.petclinic.ui;

import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.test.annotation.DirtiesContext;

@DirtiesContext
public class AttendAppointmentUITest {
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
	public void testAppointmentUI() throws Exception {
		as("owner1")
		.whenIamLoggedIntheSystem()
		.attendAppointment();
	}
	
	private void attendAppointment() {
		  driver.findElement(By.xpath("//button[@type='submit']")).click();
	}
	
	private AttendAppointmentUITest whenIamLoggedIntheSystem() {	
		return this;
	}
	

	private AttendAppointmentUITest as(String username) throws Exception {
  
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
    driver.findElement(By.id("reason")).sendKeys("prueba");
    new Select(driver.findElement(By.id("vaccine"))).selectByVisibleText("Vacuna de la rabia");
    driver.findElement(By.xpath("//option[@value='Vacuna de la rabia']")).click();
    new Select(driver.findElement(By.id("treatment"))).selectByVisibleText("Corte de pelo");
    driver.findElement(By.xpath("//option[@value='Corte de pelo']")).click();
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.xpath("//div[@id='main-navbar']/ul[2]/li/a")).click();
    driver.findElement(By.linkText("Cerrar Sesión")).click();
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.xpath("//div[@id='main-navbar']/ul[2]/li/a")).click();
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("vet1");
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("v3t");
    driver.findElement(By.id("password")).sendKeys(Keys.ENTER);
    driver.findElement(By.linkText("Horario")).click();
    driver.findElement(By.linkText("30")).click();
    driver.findElement(By.linkText("Atender cita")).click();
    driver.findElement(By.id("observations")).click();
    driver.findElement(By.id("observations")).clear();
    driver.findElement(By.id("observations")).sendKeys("se ha atendido la cita");
  
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
