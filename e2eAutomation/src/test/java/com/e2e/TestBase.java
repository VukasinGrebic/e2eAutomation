package com.e2e;

import com.e2e.config.ConfigurationManager;
import com.e2e.driver.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;


public abstract class TestBase {

    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd.HHmmss");

    protected static final boolean DEMO = false;
    protected static int demoWait = 2000;

    //protected String reportFolder = System.getProperty("user.dir") + "\\test-output\\SmokeSuite\\";
    private final String urlReport = System.getProperty("user.dir") + "\\test-output\\SmokeSuite\\" + "SmokeTests.html";
    boolean autoShowReport = ConfigurationManager.configuration().showReport();

    protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected int waitTime = 5;

    public static boolean dockingAllowed = ConfigurationManager.configuration().dockingAllowed();
    public static String dockSide = ConfigurationManager.configuration().dockSide();

    @BeforeSuite(alwaysRun = true)
    public void suiteSetup() {
        waitTime = ConfigurationManager.configuration().waitTime();
        String browserName = ConfigurationManager.configuration().browser();
        driver = DriverManager.createInstance(browserName);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigurationManager.configuration().waitTime()));



        if (dockingAllowed) DriverManager.dockBrowserWindow(dockSide);
    }

    @AfterSuite(alwaysRun = true)
    public void suiteTearDown() {
        if (driver != null) {
            driver.quit();
        }
        if (autoShowReport) {
            File htmlFile = new File(urlReport);
            try {
                Desktop.getDesktop().browse(htmlFile.toURI());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @AfterMethod(alwaysRun = true)
    public void methodPostConditions(ITestResult testResult) throws IOException {

        if (testResult.getStatus() == ITestResult.FAILURE) {
            takeScreenshot(testResult.getName(), Arrays.toString(testResult.getParameters()));
        }

    }

    public static void takeScreenshot(String testMethod, String testParams) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(ConfigurationManager.configuration().path2Screenshots() + testMethod + "-"
                    + testParams + sdf.format(new Timestamp(System.currentTimeMillis())) + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
