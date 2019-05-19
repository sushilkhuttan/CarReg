package OnlineCarRegTest;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;



import org.apache.logging.log4j.*;
//import com.sun.tools.sjavac.Log;

import io.github.bonigarcia.wdm.WebDriverManager;


public class ReadExcelData {
	String ExcelData;
	double fileSize;
	String Extension;
	ReadExcelxls obj;
	fileProperties fpo;
	String reg;
	String color;
	String make;
	int rowCount = 0;
	// For logs
	private static Logger log =  LogManager.getLogger(ReadExcelData.class.getName());
	
	
	public void readData(int a,int b, int c) throws IOException {
		//To read Excel data 
		obj = new ReadExcelxls("./CarDetails.xls");
		ExcelData = obj.getData(a, b, c);
		//rowCount = obj.getRowCount();
		
	}
	@Test
	public void readExcelProperties() throws IOException
	{
		fpo = new fileProperties("C:\\TestData"); //file properties
		log.debug("Calling to print File name, size of the file, extension");
		fpo.getFileSize();	//Call to print File name, size and extension
		
	}

	
	@Test
	public void dvla() throws IOException, InterruptedException
	{
	log.debug("Setting up browser driver");	
	WebDriverManager.chromedriver().setup();
	WebDriver driver = new ChromeDriver();
	//System.out.println(obj.getRowCount());
	int s;
	for(int f=0;f<3;f++)
		{
		for(s =0; s<3; s++)
		{
			readData(0,f,s);
			if (s == 0) {	
			reg = ExcelData.toUpperCase();
			}else if(s==1){
			make = ExcelData.toUpperCase();
			}else if (s==2) {
			color = ExcelData.toUpperCase();
	log.debug("launching the website");			
	driver.get("https://www.gov.uk/get-vehicle-information-from-dvla");
	log.info("Website is launched");
	driver.findElement(By.xpath("//a[contains(@href,'vehicleenquiry.service')]")).click();
	
	//adding wait
	WebDriverWait Explicitwait = new WebDriverWait(driver,10); 
	Explicitwait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Vrm")));
	
	log.debug("Inspecting the car Registration box");
	driver.findElement(By.id("Vrm")).sendKeys(reg);
	driver.findElement(By.cssSelector("button[type='Submit']")).click();
	
//	

	driver.findElement(By.id("Correct_True")).click();
	driver.findElement(By.cssSelector("button[type='Submit']")).click();
//	
	int count = driver.findElements(By.cssSelector("li[class=list-summary-item]")).size();
//	

	for (int i=0;i<count;i++)
	{
		if(driver.findElements(By.xpath("//div[@class='column-two-thirds']/div/ul/li")).get(i).getText().contains("Vehicle make:"))
				{
//			
				String a= driver.findElements(By.xpath("//div[@class='column-two-thirds']/div/ul/li/span/strong")).get(i).getText();
				System.out.println(a);
				log.info("Asserting make of the "+ reg +" car");
				Assert.assertEquals(make,a);
//				
				}
		if(driver.findElements(By.xpath("//div[@class='column-two-thirds']/div/ul/li")).get(i).getText().contains("Vehicle colour:"))
				{
//			
				String b= driver.findElements(By.xpath("//div[@class='column-two-thirds']/div/ul/li/span/strong")).get(i).getText();
				System.out.println(b);
				log.info("Asserting color of the "+ reg  +"car");
				Assert.assertEquals(color,b);
				
				}
//		
	}
	Thread.sleep(1000L);
	File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

	log.info("Saving screenshot of car "+ reg);
	FileUtils.copyFile(src,new File("C:\\Padai\\screenshot"+ " for "+ reg +System.currentTimeMillis() +".png"));
		}
	}
		}
	}
}
	
	
	

