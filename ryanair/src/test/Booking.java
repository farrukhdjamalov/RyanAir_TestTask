package test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Booking {
	
	static WebDriver driver; //instance of webdriver
    static Wait<WebDriver> wait;
	static String url="https://www.bookryanair.com/SkySales/Booking.aspx?#Search"; //RYANAIR URL

	//Before creating a Class instance do create FireFox Driver object and assign it to driver (webdriver)
	//wait for 30 sec before runing a class
	
	@BeforeClass  
	public static void setUpBeforeClass() throws Exception {
        // setup selenium test
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 30);//30 sec wait
	}
    
	//After all tests close firefox and quit driver.
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.close();
		driver.quit();
	}
	
	//get the url and wait untill Search button willapear on the page
	
	@BeforeMethod
	public void setUp() throws Exception {
        driver.get(url);
    	// wait until displayed
    	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("SearchInput_ButtonSubmit")));
	}

	//Method has 8 parameters on the signature, which gets data from method calls.
	
	public void test_booking(String orig, String dest, String deptDate, String retDate, 
								String adult, String teen, String child, String infant){	
	
	//Looking for Elements By their Name & Passing Values them.
	
	driver.findElement( By.name("SearchInput$Orig")).sendKeys(orig);;
	driver.findElement( By.name("SearchInput$Dest")).sendKeys(dest);;
	
	
	//DEPARTURE and RETURN ELEMENTS, clear dates before assigning new one
	
	driver.findElement( By.name("SearchInput$DeptDate")).clear();
	driver.findElement( By.name("SearchInput$DeptDate")).sendKeys(deptDate);
	driver.findElement( By.name("SearchInput$RetDate")).clear();
	driver.findElement( By.name("SearchInput$RetDate")).sendKeys(retDate);
	
	//ADULT, TEEN, CHILD, INFANT Select Instances (ELEMENTS) (Comboboxes), Assigning values from vars
	
	Select adultCombo = new Select(driver.findElement( By.name("SearchInput$PaxTypeADT")));
	adultCombo.selectByValue(adult);
	Select teenCombo = new Select(driver.findElement( By.name("SearchInput$PaxTypeTEEN")));
	teenCombo.selectByValue(teen);
	Select childCombo = new Select(driver.findElement( By.name("SearchInput$PaxTypeCHD")));	
	childCombo.selectByValue(child);
	Select infantCombo = new Select(driver.findElement( By.name("SearchInput$PaxTypeINFANT")));
	infantCombo.selectByValue(infant);
	
	//Emulate Click() on SEARCH Button
	
	driver.findElement( By.name("SearchInput$ButtonSubmit")).click();
	driver.findElement( By.name("SearchInput$ButtonSubmit")).click();
	
	//wait untill next page shows and Edit element appear
	
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("search")));
	
	}
   //TEST CASES/DATA. Calling method with passing parameters to them. 
	
	@Test
	public void test1() {
		test_booking( "DUB", "BRE", "11/06/2015", "16/06/2015", "1","2","3","1");
	}
	
	@Test
	public void test2() {
		test_booking( "BRI", "BRE", "15/06/2015", "16/07/2015", "3","2","1","2");
	}
	
	@Test
	public void test3() {
		test_booking( "AHO", "BRE", "20/06/2015", "28/06/2015", "1","2","3","0");
	}
	
	@Test
	public void test4() {
		test_booking( "DUB", "AHO", "22/06/2015", "16/08/2015", "4","2","3","2");
	}
	@Test
	public void test5() {
		test_booking( "DUB", "BRE", "11/06/2015", "16/06/2015", "1","2","3","1");
	}
	
	@Test
	public void test6() {
		test_booking( "BRI", "BRE", "15/06/2015", "16/07/2015", "3","2","1","2");
	}
	
	@Test
	public void test7() {
		test_booking( "AHO", "BRE", "20/06/2015", "28/06/2015", "1","2","3","0");
	}
	
	@Test
	public void test8() {
		test_booking( "DUB", "AHO", "22/06/2015", "16/08/2015", "4","2","3","2");
	}
}
