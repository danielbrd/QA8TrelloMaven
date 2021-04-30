package org.example.tests;


import org.example.pages.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MemberMenuTests extends TestBase{
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    MemberMenuHelper memberMenu;
    HelpMenuHelper helpMenu;

    @BeforeMethod(alwaysRun = true)
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

    @Test //sel-14
    public void memberMenuFindEmail(){
        boardsPage.memberMenuClick();
        memberMenu.waitUntillMemberEmailIsVisible();
        memberMenu.getMemberEmail();
        System.out.println(memberMenu.getMemberEmail());
        Assert.assertTrue(memberMenu.getMemberEmail().contains("@"));
    }

    @Test //sel-18
    public void comparisonOfUsernames() throws InterruptedException {
        boardsPage.memberMenuClick();
        memberMenu.settingsMenuClick();
        memberMenu.getMemberMenuButtonTooltipUsername();
        memberMenu.getMemberMenuTitleUsername();
        boardsPage.memberMenuClick();
        memberMenu.getMemberMenuButtonUsername();

        Assert.assertTrue(memberMenu.getMemberMenuButtonTooltipUsername()
                .contains(memberMenu.getMemberMenuButtonUsername()));
        Assert.assertTrue(memberMenu.getMemberMenuButtonTooltipUsername()
        .contains(memberMenu.getMemberMenuTitleUsername()));
    }

}
