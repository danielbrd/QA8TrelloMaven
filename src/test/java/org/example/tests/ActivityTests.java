package org.example.tests;

import org.example.pages.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ActivityTests extends TestBase{
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    CurrentBoardHelper qa8HaifaBoard;
    MemberMenuHelper memberMenu;
    ActivityPageHelper activityPage;
    HelpMenuHelper helpMenu;

    @BeforeMethod
    public void initTests()  {
        qa8HaifaBoard = new CurrentBoardHelper(driver,"QA8 Haifa");

        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver,BoardsPageHelper.class);
        memberMenu = PageFactory.initElements(driver, MemberMenuHelper.class);
        activityPage = PageFactory.initElements(driver,ActivityPageHelper.class);
        helpMenu = PageFactory.initElements(driver,HelpMenuHelper.class);

        loginPage.openLoginPage()
                .waitUntilPageIsLoaded()
                .enterLoginPasswordAttl(LOGIN,PASSWORD);
        boardsPage.waitUntillOpenBoardsButtonIsClickable();}



    @Test //sel-17
    public void newCardInActivity(){
        String cardName = "test_card";
        boardsPage.waitUntillOpenBoardsButtonIsClickable();
        qa8HaifaBoard.openCurrentBoardPage();
        qa8HaifaBoard.ifHaveNoListsAddNew("And Another NewList");
        qa8HaifaBoard.waitUntilAllElementsArePresent(By.cssSelector(".list-header"),15);
        qa8HaifaBoard.addCardToLastList(cardName);

        boardsPage.memberMenuClick();
        memberMenu.activityMenuClick();
        helpMenu.waitUntillActivityPageIsLoaded();
        Assert.assertEquals(cardName, activityPage.getLastActionCardNameInHistory());

    }
}