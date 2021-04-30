package org.example.tests;

import org.example.pages.BoardsPageHelper;
import org.example.pages.HelpMenuHelper;
import org.example.pages.LoginPageHelper;
import org.example.pages.MemberMenuHelper;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HelpPageTests extends TestBase {
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    MemberMenuHelper memberMenu;
    HelpMenuHelper helpMenu;

    @BeforeMethod
    public void initTests(){
//        loginPage = new LoginPageHelper(driver);
//        boardsPage = new BoardsPageHelper(driver);
//        memberMenu = new MemberMenuHelper(driver);
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver, BoardsPageHelper.class);
        memberMenu = PageFactory.initElements(driver, MemberMenuHelper.class);
        helpMenu = PageFactory.initElements(driver,HelpMenuHelper.class);

        loginPage.openLoginPage();
        loginPage.waitUntilPageIsLoaded();
        loginPage.enterLoginPasswordAttl(LOGIN, PASSWORD);
        boardsPage.waitUntillOpenBoardsButtonIsClickable();
    }

    @Test //sel-22
    public void memberMenuHelpPanel() throws InterruptedException {
        boardsPage.memberMenuClick();
        helpMenu.helpMenuClick();

        String firstWindow = driver.getWindowHandle();
        String secondWindow = "";
        for (String wCode : driver.getWindowHandles()) {
            if (!wCode.equals(firstWindow)) secondWindow = wCode; }

        driver.switchTo().window(secondWindow);
        helpMenu.waitUntilHelpTitleIsVisible();
        String s = helpMenu.getHelpMenuTitle();
        System.out.println(helpMenu.getHelpMenuTitle());

        driver.close();
        Thread.sleep(1500);
        driver.switchTo().window(firstWindow);
        Assert.assertEquals(s, "Get help with Trello", "Messages don't match");
        Assert.assertEquals("Boards", boardsPage.getNameBoardsButton());
    }
}
