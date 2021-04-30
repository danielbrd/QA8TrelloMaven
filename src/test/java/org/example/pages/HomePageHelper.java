package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePageHelper extends PageBase{
    @FindBy(xpath = "//a[contains(text(),'Log in')]")
    WebElement loginIcon;


    public HomePageHelper(WebDriver driver) {
        super(driver);
    }

//    public void waitUntilPageIsLoaded() {
//        waitUntilElementIsClickable(loginIcon, 40);
//    }
    public HomePageHelper waitUntilPageIsLoaded() {
    waitUntilElementIsClickable(loginIcon, 40);
    return this;
    }
//----- Метод получения титульного логотипа с гл. стр.-----
    public String getPageTitle(){
       return driver.getTitle();
    }

}
