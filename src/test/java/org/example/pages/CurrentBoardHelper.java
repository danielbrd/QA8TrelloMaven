package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CurrentBoardHelper extends PageBase {
    String boardName;


    @FindBy(css = ".list-header")
    List<WebElement> listQuantity;
    @FindBy(css = ".js-list-name-input")
    List<WebElement> lastNameList;
    @FindBy(css = ".js-list-name-input")
    WebElement lastNameWebElement;
    @FindBy(css = ".open-card-composer")
    WebElement cardComposerWebElement;
    @FindBy(css = ".open-card-composer")
    List<WebElement> cardComposerList;
    @FindBy(xpath = "//span[@class='placeholder']/..")
    WebElement boardsButton;
    @FindBy(xpath = "//input[@name='name']")
    WebElement listTitleField;
    @FindBy(css = ".js-save-edit")
    WebElement addListButton;
    @FindBy(xpath = "//span[@class='placeholder']")
    WebElement addList;
    @FindBy(css = ".js-cancel-edit")
    WebElement cancelButton;
    @FindBy(xpath = "//div[@class='list-header-extras']")
    WebElement listActionsMenu;
    @FindBy(xpath = "//a[@class='js-close-list']")
    WebElement dropMenuArchiveList;
    @FindBy(css = ".js-card-title")
    WebElement cardName;
    @FindBy(css = ".js-add-card")
    WebElement addCard;
    @FindBy(css = ".js-cancel")
    WebElement closeCardMenu;

    public CurrentBoardHelper(WebDriver driver, String boardName) {
        super(driver);
        this.boardName = boardName;
        PageFactory.initElements(driver, this);
    }

    public CurrentBoardHelper openCurrentBoardPage(){
        WebElement qaHaifa8Board = driver.findElement(By
                .xpath("//a[@class = 'board-tile'][.//@title='" + boardName + "']"));
        qaHaifa8Board.click();
        return this;
    }

    public int getListsQuantity(){
        //return driver.findElements(By.cssSelector(".list-header")).size();
        return listQuantity.size();
    }

    public CurrentBoardHelper waitUntilBoardsButtonIsClickable() {
        waitUntilElementIsClickable(boardsButton, 7);
        return this;
    }

    public CurrentBoardHelper addAnotherListButtonClick() {
        boardsButton.click();
        return this;
    }

    public CurrentBoardHelper enterListTitleField(String title) {
        waitUntilElementIsClickable(listTitleField, 7);
        fillField(listTitleField, title);
        return this;
    }

    public CurrentBoardHelper saveNewListAddListButtonClick() {
        waitUntilElementIsClickable(addListButton, 7);
        addListButton.click();
        return this;
    }

    public CurrentBoardHelper ifHaveNoListsAddNew(String nameList) {
        waitUntilElementIsClickable(addList, 7);
        if(addList.getText().equals("Add a list")) {
            addList.click();
            waitUntilElementIsClickable(boardsButton,10);
            fillField(listTitleField, nameList);
            addListButton.click();
            waitUntilElementIsClickable(cancelButton,10);
            cancelButton.click();
        }
        return this;
    }

    public CurrentBoardHelper findAndRenameList(String name) {
//      int quantity = getListsQuantity()-1;
        int quantity = listQuantity.size()-1;

//      WebElement lastHeader = driver.findElements(By.cssSelector(".list-header")).get(quantity);
        WebElement lastHeader = listQuantity.get(quantity);
        lastHeader.click();

        waitUntilElementIsClickable(lastNameWebElement,10);
        WebElement lastNameObject = lastNameList.get(quantity);
        lastNameObject.sendKeys(name);
        lastNameObject.sendKeys(Keys.ENTER);
        driver.navigate().refresh();
        waitUntilAllElementsArePresent
                (By.cssSelector(".list-header"),15);
        return this;
    }

    public String getLastListName() {

        WebElement lastName = lastNameList.get(getListsQuantity()-1);
        if (getListsQuantity() == 0) return "Have no lists";
        return lastName.getText();
    }

    public CurrentBoardHelper addCardToLastList(String card) {
        int quantity = listQuantity.size()-1;
        waitUntilElementIsClickable(cardComposerWebElement,10);

        WebElement addNewCard = cardComposerList.get(quantity);
        addNewCard.click();

        //----- Fill New Card Name Field -----
        waitUntilElementIsClickable(cardName,10);
        fillField(cardName,card);

        //----- Define 'Add Card' button and click it -----------
        addCard.click();

        //------ Click X-button -----------
        waitUntilElementIsClickable(closeCardMenu,10);
        closeCardMenu.click();
        return this;
    }

    public CurrentBoardHelper findAndDeleteList() {
        waitUntilElementIsClickable(listActionsMenu, 7);
        listActionsMenu.click();
        //------- Wait 'Archive List' option disappears ---------
        waitUntilElementIsClickable(dropMenuArchiveList, 7);
        dropMenuArchiveList.click();
        return this;
    }

//    public void waitUntilPageIsLoaded() {
//        waitUntilElementIsClickable(By.cssSelector(".mod-show-menu"), 10);
//    }

//    public void addNewList(String nameList) {
//        WebElement addListButton = driver.findElement(By.xpath("//span[@class='placeholder']/.."));
//        addListButton.click();
//        waitUntilElementIsClickable(By.xpath("//input[@name = 'name']"),10);
//        WebElement titleListField = driver.findElement(By.xpath("//input[@name = 'name']"));
//        fillField(titleListField,nameList);
//        WebElement submitButton = driver.findElement(By.cssSelector(".js-save-edit"));
//        submitButton.click();
//        WebElement cancelEditList = driver.findElement(By.cssSelector(".js-cancel-edit"));
//        cancelEditList.click();
//        waitUntilAllElementsArePresent(By.cssSelector(".list-header"),15);
//    }
//
//    public int getCardsQuantity(){
//        return driver.findElements(By.cssSelector(".js-card-details")).size();
//    }
//
//    public String getAddListButtonName(){
//        return driver.findElement(By.xpath("//span[@class='placeholder']")).getText();
//    }

//    public void findLastList() {
//        waitUntilAllElementsArePresent(By.cssSelector(".list-header"), 15);
//        int findLastList = driver.findElements(By.cssSelector(".open-card-composer")).size() - 1;
//        WebElement addCard = driver.findElements(By.cssSelector(".open-card-composer")).get(findLastList);
//        waitUntilElementIsClickable(By.xpath(".open-card-composer"), 7);
//        addCard.click();
//    }
//
//    public void enterNewCardToSelectedList(String title) {
//        waitUntilElementIsClickable(By.xpath("//textarea[@placeholder='Enter a title for this card…']"), 7);
//        WebElement addCardMenu = driver.findElement(By.xpath
//                ("//textarea[@placeholder='Enter a title for this card…']"));
//        addCardMenu.click();
//            waitUntilElementIsClickable(By.xpath
//                    ("//input[@name='name']"), 7);
//            WebElement enterListTitleField = driver.findElement(By.xpath
//                    ("//input[@name='name']"));
//            fillField(enterListTitleField, title);
//    }

}
