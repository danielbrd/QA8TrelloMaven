package org.example.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTests extends TestBase {

    @Test(groups = {"smoke"})
//----- Получение тайтла через метод getPageTitle() -----
    public void applicationTest(){
        log4j.info("Test - ApplicationTest was started");
        Assert.assertEquals(homePage.getPageTitle(),
                "Trello", "Appl. is not Trello");
        log4j.info("Test - ApplicationTest was finished");
    }
}
