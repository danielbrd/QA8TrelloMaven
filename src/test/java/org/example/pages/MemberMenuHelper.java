package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MemberMenuHelper extends PageBase {
    @FindBy(xpath = "//span[contains(text(),'@')]")
    WebElement memberEmail;
    @FindBy(xpath = "//span[contains(text(),'Activity')]/..") //взять родительский элемент обьекта
    List<WebElement> activityMenu;
    @FindBy(xpath = "//a[@data-test-id='header-member-menu-settings']")
    WebElement settingsMenu;
    @FindBy(xpath = "//span[contains(text(), '@')]/../div") // опускание на уровень вниз, находим элемент '@', и взять родительский элемент
    WebElement memberMenuButtonUsername;
    @FindBy(xpath = "//*[@aria-label='Open member menu']")
    WebElement tooltipUsername;
    @FindBy(css = "._32mB-ZO8fxjtUy")
    WebElement titleUsername;

    public MemberMenuHelper(WebDriver driver) {
        super(driver);
    }

    public MemberMenuHelper waitUntillMemberEmailIsVisible(){
        waitUntilElementIsVisible(memberEmail, 10);
        return this;
    }

    public String getMemberEmail() {
        return memberEmail.getText();
    }

    public void activityMenuClick() {
        int last = activityMenu.size()-1;
        waitUntilElementIsClickable(By.xpath("//span[contains(text(),'Activity')]/.."), 10);
        activityMenu.get(last).click();
    }

    public MemberMenuHelper settingsMenuClick() {
        waitUntilElementIsClickable(settingsMenu, 10);
        settingsMenu.click();
        return this;
    }

    public String getMemberMenuButtonUsername() {
        waitUntilElementIsVisible(memberMenuButtonUsername, 10);
        return memberMenuButtonUsername.getText();
    }

    public String getMemberMenuButtonTooltipUsername() {
        waitUntilElementIsVisible(tooltipUsername, 10);
        return tooltipUsername.getAttribute("title");
    }

    public String getMemberMenuTitleUsername(){
        waitUntilElementIsVisible(titleUsername, 10);
        return titleUsername.getText();
    }

}

