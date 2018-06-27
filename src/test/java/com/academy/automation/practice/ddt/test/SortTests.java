package com.academy.automation.practice.ddt.test;

import com.academy.automation.practice.ddt.manager.page.HomePage;
import com.academy.automation.practice.ddt.manager.page.DressPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SortTests extends BaseTest {

    @Test
    public void testSortDress() throws InterruptedException {
        manager.goTo().home();
        DressPage dressPage = new HomePage(manager.getDriver())
                .clickDressLink()
                .selectByText("Price: Lowest first");

        //Thread.sleep(3000);
        //System.out.println(dressPage.getFirstPrice());
        dressPage.waitForJSandJQueryToLoad();
        Assert.assertEquals(dressPage.getFirstPrice(), "$16.40");
    }
}
