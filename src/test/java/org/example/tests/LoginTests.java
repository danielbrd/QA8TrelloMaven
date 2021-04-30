package org.example.tests;

import org.example.pages.BoardsPageHelper;
import org.example.pages.LoginPageHelper;
import org.example.util.DataProviders;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;

    @BeforeMethod(alwaysRun = true)
    public void initTests() {
        // ------- Press login button  --------
//        loginPage = new LoginPageHelper(driver);
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
//        boardsPage = new BoardsPageHelper(driver);
        boardsPage = PageFactory.initElements(driver, BoardsPageHelper.class);
        loginPage.openLoginPage()
                .waitUntilPageIsLoaded();
    }

    // Мой вариант
//    @Test(dataProviderClass = DataProviders.class, dataProvider = "loginNegative")
//    public void loginNegativeLoginIncorrect(String login, String password) {
//        log4j.startTestCase("loginNegativeLoginIncorrect");
//        log4j.info("====== Parameter login: " + login);
//        log4j.info("====== Parameter password: " + password);
//
//        loginPage.enterLoginPassNotAttl(login, password);
//
//        log4j.info("Not attl. Login, password was entered for" +
//                "("+login+")" + "("+password+")");
//
//        Assert.assertTrue(loginPage.getErrorNotAttlMessage()
//                .contains("Missing email")
//                | (loginPage.getErrorNotAttlMessage()
//                .contains("There isn't an account for this username"))
//                | (loginPage.getPasswordErrorNotAttlMessage()
//                .contains("Incorrect email address and / or password. Do you need help")));
//        log4j.endTestCase2();
//    }
    // Вариант в классе
    @Test(dataProviderClass = DataProviders.class, dataProvider = "loginNegative2")
    public void loginNegativeLoginIncorrect2(String login, String password, String message) {
        log4j.startTestCase("loginNegativeLoginIncorrect2");
        log4j.info("      Parameter login: " + login);
        log4j.info("      Parameter password: " + password);

        loginPage.enterLoginPassNotAttl(login, password);

        log4j.info("Not attl. Login: "+"( "+login+" )"+" and Password: " +"( "+password+" )"+ " was entered");


        Assert.assertTrue(loginPage.getErrorNotAttlMessage()
                .equals(message), "Different error message");
        log4j.endTestCase("loginNegativeLoginIncorrect2");
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "dataProviderSecond")
    public void loginNegativeParametricSecond(String login, String password) {
        log4j.startTestCase("loginNegativeParametricSecond");
        log4j.info("      Parameter login: " + login);
        log4j.info("      Parameter password: " + password);

        loginPage.enterLoginPassNotAttl(login, password);

        log4j.info("Not attl. Login: "+"( "+login+" )"+" and Password: " +"( "+password+" )"+ " was entered");


        Assert.assertTrue(loginPage.getErrorNotAttlMessage()
                        .equals("There isn't an account for this username")
                , "Different error message");
        log4j.endTestCase("loginNegativeParametricSecond");
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "dataProviderThird")
    public void loginNegativeParametricThird(String login, String password) {
        log4j.startTestCase("loginNegativeParametricThird");
        log4j.info("      Parameter login: " + login);
        log4j.info("      Parameter password: " + password);

        loginPage.enterLoginPassNotAttl(login, password);
        log4j.info("Not attl. Login: "+"( "+login+" )"+" and Password: " +"( "+password+" )"+ " was entered");


        Assert.assertTrue(loginPage.getErrorNotAttlMessage()
                        .equals("There isn't an account for this email")
                , "Different error message");
        log4j.endTestCase("loginNegativeParametricThird");
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "loginEmpty")
    public void loginNegativeLoginEmpty(String login, String password) {
        log4j.startTestCase("loginNegativeLoginEmpty");
        log4j.info("      Parameter login: (empty)");
        log4j.info("      Parameter password: " + password);
        loginPage.enterLoginPassNotAttl(login, password);

        log4j.info("Not attl. Login: ( -EMPTY- ) and Password: " +"( "+password+" )"+ " was entered");

        Assert.assertTrue(loginPage.getErrorNotAttlMessage()
                .contains("Missing"), "The error-message isn't correct");
        log4j.info("----- Error message: " + loginPage.getErrorNotAttlMessage());

        log4j.endTestCase("loginNegativeLoginEmpty");
    }

    //@Test BAG
    @Test(groups = {"smoke", "regression"}, dataProviderClass = DataProviders.class, dataProvider = "loginPositive")
    //public void loginPositive() {
    public void loginPositive(String login, String password) {
        log4j.startTestCase("loginPositive");
        log4j.info("      Parameter login: " + login);
        log4j.info("      Parameter password: " + password);

        //loginPage.enterLoginPasswordAttl(LOGIN, PASSWORD);
        loginPage.enterLoginPasswordAttl(login, password);
        boardsPage.waitUntillOpenBoardsButtonIsClickable();

        log4j.info("Attl. Login: "+"( "+login+" )"+" and Password: " +"( "+password+" )"+ " was entered");

        Assert.assertEquals("Boards", boardsPage.getNameBoardsButton());

        log4j.endTestCase("loginPositive");
    }

    @Test
    public void negativePasswordIncorrect() {
//        //---- Fill in login-field and press "login with Attlassian"----
        log4j.startTestCase("negativePasswordIncorrect");
        log4j.info("      Parameter login: " + LOGIN);
        log4j.info("      Parameter password: '123'");

        loginPage.enterLoginPasswordAttl(LOGIN, "123")
                //----- Fill in password field and press login-submit button-----------
                .getErrorAttlMessage();
        log4j.info("Attl. Login: "+"( "+LOGIN+" )"+" and Password: " +"( 123 )"+ " was entered");

        log4j.info("     Error message: " + loginPage.getErrorAttlMessage());

        Assert.assertTrue(loginPage.getErrorAttlMessage().contains("email"));

        log4j.endTestCase("negativePasswordIncorrect");
    }


    @Test(groups = {"regression"}, dataProviderClass = DataProviders.class, dataProvider = "dataProviderRandomDataInsert") // sel-21
    public void loginNegative(String login, String password){
        log4j.startTestCase("loginNegative");
        log4j.info("       Parameter login: " + login);
        log4j.info("       Parameter password: " + password);

        loginPage.enterLoginPassNotAttl(login, password);
        log4j.info("Not attl. Login: "+"( "+login+" )"+" and Password: " +"( "+password+" )"+ " was entered");

        Assert.assertTrue(loginPage.getErrorNotAttlMessage()
                        .equals("There isn't an account for this username")
                , "Different error message");
        log4j.info("----- Error message: " + loginPage.getErrorNotAttlMessage());

        log4j.endTestCase("loginNegative");
    }

}