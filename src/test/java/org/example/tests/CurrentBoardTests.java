package org.example.tests;


import org.example.pages.*;
import org.example.util.DataProviders;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CurrentBoardTests extends TestBase {
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    CurrentBoardHelper qa8haifaBoard;


    @BeforeMethod(alwaysRun = true)
    public void initTests(){
//        loginPage = new LoginPageHelper(driver);
//        boardsPage = new BoardsPageHelper(driver);
        qa8haifaBoard = new CurrentBoardHelper(driver, "QA8 Haifa");
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver, BoardsPageHelper.class);

        loginPage.openLoginPage()
                .waitUntilPageIsLoaded()
                .enterLoginPasswordAttl(LOGIN, PASSWORD);
        boardsPage.waitUntillOpenBoardsButtonIsClickable();
        qa8haifaBoard.openCurrentBoardPage();
    }

    @Test(groups = {"regression", "special for QA8"}, dataProviderClass = DataProviders.class, dataProvider = "addList")
    public void createNewList(String name){
        qa8haifaBoard.waitUntilBoardsButtonIsClickable();
        int firstListCount = driver.findElements(By.cssSelector(".list-header")).size();
        qa8haifaBoard.addAnotherListButtonClick();
        //qa8haifaBoard.enterListTitleField("This is the New List");
        qa8haifaBoard.enterListTitleField(name);
        qa8haifaBoard.saveNewListAddListButtonClick();
        int lastListCount = driver.findElements(By.cssSelector(".list-header")).size();
        Assert.assertEquals(firstListCount+1, lastListCount,
                "The quantity of lists after adding is not the quantity before adding plus one");
    }

//    @Test //SEL-08
    @Test(dataProviderClass = DataProviders.class, dataProvider = "addCard")
    //public void addCardToLastList() {
    public void addCardToLastList(String name) {
        qa8haifaBoard.ifHaveNoListsAddNew("And Another NewList");
        qa8haifaBoard.waitUntilAllElementsArePresent(By.cssSelector(".list-header"),15);
        int cardsBefore = driver.findElements(By.cssSelector(".js-card-details")).size();
        qa8haifaBoard.addCardToLastList(name);
        int cardsAfter = driver.findElements(By.cssSelector(".js-card-details")).size();

        Assert.assertEquals(cardsBefore+1,cardsAfter,
                "The quantity of cards after adding is not equal to cards before adding plus one");
    }

    @Test (groups = {"regression"}) // SEL-09
    public void deleteLastList() {
        qa8haifaBoard.ifHaveNoListsAddNew("What? Again?");
        qa8haifaBoard.waitUntilAllElementsArePresent(By.cssSelector(".list-header"),15);
        int firstListCount = driver.findElements(By.xpath("//div[@class='list-header-extras']")).size();
        qa8haifaBoard.findAndDeleteList();
        int lastListCount = driver.findElements(By.cssSelector(".list-header")).size();
        Assert.assertEquals(firstListCount-1, lastListCount);
    }
}


