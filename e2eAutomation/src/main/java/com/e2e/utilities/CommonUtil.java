package com.e2e.utilities;

import lombok.experimental.UtilityClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

@UtilityClass
public class CommonUtil {

    /**
     * Clicks on a WebElement from a list of WebElements which contains a specific text
     *
     * @param list List of WebElements to be iterated
     * @param txt  Text contained by the desired webElement to be clicked
     */
    public static void clkElementFromListByTxt(List<WebElement> list, String txt) {
        for (WebElement element : list) {
            if (element.getText().equals(txt)) {
                element.click();
                break;
            }
        }
    }

    /**
     * Formats xpath with parameters
     * @param xpath
     * @param param
     * @return Xpath as String with applied param values
     */
    public static String formatXpath(String xpath, Object... param){
        return String.format(xpath, param);
    }

    /**
     * Builds a WebElement by providing non-formatted xpath and preferred params to it
     * @param driver
     * @param xpath
     * @param param
     * @return The WebElement found by the driver
     */
    public static WebElement buildWebElement(WebDriver driver, String xpath, Object... param){
        return driver.findElement(By.xpath(formatXpath(xpath,param)));
    }
}
