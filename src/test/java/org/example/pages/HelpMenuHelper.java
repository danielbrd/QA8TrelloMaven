package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HelpMenuHelper extends PageBase {
    @FindBy(xpath = "//h1[contains(text(), 'Get help with Trello')]")
    WebElement helpTitle;
    @FindBy(xpath = "//span[contains(text(), 'Help')]")
    WebElement helpMenu;
    @FindBy(css = ".phenom-desc")
    WebElement listOfActivities;

    public HelpMenuHelper(WebDriver driver) {
        super(driver);

    }
    public void waitUntilHelpTitleIsVisible(){
        waitUntilElementIsVisible(helpTitle, 10);
    }

    public String getHelpMenuTitle() {
       return helpTitle.getText();
    }

    public void helpMenuClick() {
        waitUntilElementIsClickable(helpMenu, 10);
        helpMenu.click();
    }

    public void waitUntillActivityPageIsLoaded() {
        waitUntilElementIsVisible(listOfActivities, 10);
    }
}
//
//        WebElement firstActivity = listOfLastActivities.get(0);
//        if (getListsQuantity() == 0) return "Have no activities";
//        return firstActivity.getText();
//    }

