package org.example.pages;

import org.example.util.LogLog4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class PageBase {

    public static LogLog4j log4j = new LogLog4j();
    WebDriver driver;


    public PageBase(WebDriver driver) {
        this.driver = driver;
    }

    public PageBase waitUntilElementIsClickable(By locator, int time) {
        try {
            new WebDriverWait(driver, time). until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public PageBase waitUntilElementIsClickable(WebElement element, int time) {
        try {
            new WebDriverWait(driver, time). until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }
    public PageBase waitUntilElementIsPresent(By locator, int time) {

        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public PageBase waitUntilElementDisappears(By locator, int time) {

        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public PageBase waitUntilAllElementsArePresent(By locator, int time) {

        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public PageBase waitUntilElementIsVisible(By locator, int time) {

        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public PageBase waitUntilElementIsVisible(WebElement element, int time) {

        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    void waitUntilAllElementsVisible(List<WebElement> elements, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOfAllElements(elements));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PageBase fillField(WebElement element, String value) {
        element.clear();
        element.click();
        element.sendKeys(value);
        return this;
    }
}