package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageHelper extends PageBase{
    @FindBy(xpath = "//a[contains(text(),'Log in')]")
    WebElement loginIcon;
    @FindBy(id = "user")
    WebElement loginField;
    @FindBy(id = "login")
    WebElement loginButton;
    @FindBy(id = "password")
    WebElement passwordField;
    @FindBy(css = "#error >.error-message")
    WebElement errorMessageNotAttl;
    @FindBy(css = "#password-error >.error-message")
    WebElement passwordErrorMessageNotAttl;
    @FindBy(xpath = "//input[@value = 'Log in with Atlassian']")
    WebElement loginAsAttlButton;

    public LoginPageHelper(WebDriver driver) {
        super(driver);
    }

    public LoginPageHelper openLoginPage() {
//        WebElement loginIcon = driver.findElement
//                (By.xpath("//a[contains(text(),'Log in')]"));
        loginIcon.click();
        return this;
    }

//    public void waitUntilPageIsLoaded() {
        public LoginPageHelper waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(loginField,10);
        waitUntilElementIsClickable(loginButton,20);
        return this;
    }

    public LoginPageHelper enterLoginPassNotAttl(String login, String password) {
        log4j.info("[ METHOD:  'enterLoginPassNotAttl'  IS  STARTED ]");
        enterLoginNotAttl(login); //without 'this.'
        log4j.info("------- Login value /"+login+"/ was entered");
        enterPasswordNotAttl(password);
        log4j.info("------- Password value /"+password+"/ was entered");
        clickLoginInButtonNotAttl();
        log4j.info("------> Click on LOGIN button (not Attl.)");
        log4j.info("");
        return this;
    }

    public LoginPageHelper enterLoginPasswordAttl(String login, String password) {
        log4j.info("[ METHOD:  'enterLoginPasswordAttl'  IS  STARTED ]");
        enterLoginNotAttl(login);
        log4j.info("------- Login value /"+login+"/ was entered");
        clickLoginAttl();
        log4j.info("------> Click on LOGIN button (Attl)");
        enterPasswordAttl(password);
        log4j.info("------- Password value /"+password+"/ was entered");
        submitAttl();
        log4j.info("");
        return this;
    }

    public LoginPageHelper enterLoginNotAttl(String value) {
        log4j.info("[ METHOD:  'enterLoginNotAttl'  IS  STARTED ]");
//        WebElement loginField = driver.findElement(By.id("user"));
        fillField(loginField,value);
        log4j.info("------- Login value /"+value+"/ was entered");
        log4j.info("");
        return this;
    }

    public LoginPageHelper enterPasswordNotAttl(String value) {
        log4j.info("[ METHOD:  'enterPasswordNotAttl'  IS  STARTED ]");
        waitUntilElementIsClickable(passwordField,10);
//      WebElement passwordField = driver.findElement(By.id("password"));
        fillField(passwordField,value);
        log4j.info("------- Password value /"+value+"/ was entered");
        log4j.info("");
// ----- To be sure that loginField and passwordField are already filled in -----
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public LoginPageHelper clickLoginInButtonNotAttl() {
        log4j.info("[ METHOD:  'clickLoginInButtonNotAttl'  IS  STARTED ]");
//        waitUntilElementIsClickable(By.id("login"),20);
//        driver.findElement(By.id("login")).click();
        waitUntilElementIsClickable(loginButton,20);
        loginButton.click();
        log4j.info("------> Click on LOGIN button (not Attl.)");
        log4j.info("");
        return this;
    }

    public String getErrorNotAttlMessage(){
        log4j.info("[ METHOD:  'clickLoginInButtonNotAttl'  IS  STARTED ]");
        waitUntilElementIsVisible(errorMessageNotAttl,20);
        log4j.error("-!-!-!-!- Error message of the method 'getErrorNotAttlMessage' - "+errorMessageNotAttl.getText());
        log4j.info("");
        return errorMessageNotAttl.getText();
    }

    public String getErrorNotAttlMessageWrongLogin(){
        log4j.info("[ METHOD:  'getErrorNotAttlMessageWrongLogin'  IS  STARTED ]");
        waitUntilElementIsVisible(errorMessageNotAttl,20);
        log4j.error("-!-!-!-!- Error message of the method 'getErrorNotAttlMessageWrongLogin' - "+errorMessageNotAttl.getText());
        log4j.info("");
        return errorMessageNotAttl.getText();
    }

    public String getPasswordErrorNotAttlMessage(){
        log4j.info("[ METHOD:  'getPasswordErrorNotAttlMessage'  IS  STARTED ]");
        waitUntilElementIsVisible(passwordErrorMessageNotAttl,20);
        log4j.error("-!-!-!-!- Error message of the method 'passwordErrorMessageNotAttl' - "+passwordErrorMessageNotAttl.getText());
        log4j.info("");
        return passwordErrorMessageNotAttl.getText();
    }

    public LoginPageHelper clickLoginAttl() {
        log4j.info("[ METHOD:  'clickLoginAttl'  IS  STARTED ]");
//        waitUntilElementIsClickable(By.xpath("//input[@value = 'Log in with Atlassian']"),10);
//        driver.findElement(By.id("login")).click();
        waitUntilElementIsClickable(loginAsAttlButton,10);
        loginAsAttlButton.click();
        log4j.info("------> Click on LOGIN button (Attl.)");
        log4j.info("");
        return this;
    }

    public LoginPageHelper enterPasswordAttl(String value) {
        log4j.info("[ METHOD:  'clickLoginAttl'  IS  STARTED ]");
        waitUntilElementIsClickable(By.id("password"),10);
        WebElement passwordField = driver.findElement(By.id("password"));
        fillField(passwordField,value);
        log4j.info("------- Password value /"+value+"/ was entered");
        log4j.info("");
        return this;
    }

    public LoginPageHelper submitAttl() {
        log4j.info("[ METHOD:  'submitAttl'  IS  STARTED ]");
        waitUntilElementIsClickable(By.id("login-submit"),10);
        driver.findElement(By.id("login-submit")).click();
        log4j.info("------> Click on LOGIN SUBMIT button");
        return this;
    }

    public String getErrorAttlMessage() {
        log4j.info("[ METHOD:  'getErrorAttlMessage'  IS  STARTED ]");
        waitUntilElementIsVisible(By.id("login-error"),10);
        log4j.error("-!-!-!-!- Error message of the method 'passwordErrorMessageNotAttl' - "+driver.findElement(By.id("login-error")).getText());
        return driver.findElement(By.id("login-error")).getText();

    }
}
