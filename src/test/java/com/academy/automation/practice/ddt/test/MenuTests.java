package com.academy.automation.practice.ddt.test;

import com.academy.automation.practice.ddt.manager.page.HomePage;
import com.academy.automation.practice.ddt.manager.page.WomenPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MenuTests extends BaseTest {

    @Test
    public void testWomenMenu() {

        // 1 ШАГ - перейти на страницу
        // TODO
        manager.goTo().home();

        // 2 ШАГ - клик по меню "Women"
        HomePage homePage = new HomePage(manager.getDriver());
        WomenPage womenPage = homePage.clickWomenLink();

        // 3 ШАГ - считываю заголовок "Women"
        String womenCaptureText = womenPage.getWomenCapture();

        // 4 ШАГ - проверяем соответствие заголовока тексту "Women"
        Assert.assertEquals(womenCaptureText, "Women");
    }
}
