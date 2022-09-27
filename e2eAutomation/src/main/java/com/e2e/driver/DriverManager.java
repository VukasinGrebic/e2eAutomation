package com.e2e.driver;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class DriverManager {

    public static WebDriver createInstance(String browserName){
        WebDriver driver = null;
        DriverType browser= DriverType.getDriverByName(browserName);

        switch (browser){
            case CHROME:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case EDGE:
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case IE:
                /* SKIPPED */
                break;
        }

        return driver;
    }

    public static void dockBrowserWindow(String dockSide){
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_WINDOWS);
            if(dockSide=="right"){ robot.keyPress(KeyEvent.VK_RIGHT); } else { robot.keyPress(KeyEvent.VK_LEFT); }
            robot.keyRelease(KeyEvent.VK_WINDOWS);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

}

