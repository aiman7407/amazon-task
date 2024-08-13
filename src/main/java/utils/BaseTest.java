package utils;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;


public class BaseTest {




    public BaseTest() {
     PageFactory.initElements(driver, BaseTest.this);
    }


    public static ExtentReports extentReports;
    public static ExtentTest logger;

    protected static WebDriver driver;




 @AfterTest
 public void afterTest()
 {
     if (driver != null) {
         driver.quit();
     }

 }



    @BeforeSuite
    public void  setup(){
        reportHandler();
    }









    private void reportHandler() {
        String timeNow = TimeFormatter.getTimeNow();
        System.out.println(timeNow);
        extentReports = new ExtentReports("reports/index/report " + timeNow + ".html");
        extentReports.addSystemInfo("FrameWork Type","Selenium Web Driver");
        extentReports.addSystemInfo("env","Windows");
        extentReports.addSystemInfo("App","Task Amazon");
    }





    @AfterSuite
    public void afterSuite()
    {
        extentReports.flush();
        driver.quit();
    }


    @Parameters("browser")
    @BeforeMethod
    public void beforeMethod(Method method, @Optional String browser)
    {
        if (browser==null){
            ChromeOptions options = new ChromeOptions();
            driver = new ChromeDriver(options);





        }




        driver.get("https://www.amazon.eg/");
        driver.manage().window().maximize();
        logger=extentReports.startTest(method.getName());
    }




    @AfterMethod
    public void afterMethod(Method method, ITestResult result) throws InterruptedException {
        TakesScreenshot screenshotDriver = (TakesScreenshot) driver;
        File screenshot = screenshotDriver.getScreenshotAs(OutputType.FILE);

        Thread.sleep(2000);
//        File image  =driver.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot,new File("screenshots/"+method.getName()+".jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String fullImagePath=System.getProperty("user.dir")+File.separator+"screenshots/"+method.getName()+".jpg";
        if(result.getStatus()==ITestResult.SUCCESS){
            logger.log(LogStatus.PASS,"Test Is Passed");
            logger.log(LogStatus.PASS,logger.addScreenCapture(fullImagePath));

        }
        else if(result.getStatus()==ITestResult.FAILURE)
        {
            logger.log(LogStatus.ERROR,result.getThrowable());
            logger.log(LogStatus.ERROR,logger.addScreenCapture(fullImagePath));
        }
        else {
            logger.log(LogStatus.SKIP,"Test Escaped because no error ");
        }

        driver.quit();
    }
}
