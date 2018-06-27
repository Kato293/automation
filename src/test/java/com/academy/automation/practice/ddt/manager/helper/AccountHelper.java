package com.academy.automation.practice.ddt.manager.helper;

import com.academy.automation.practice.ddt.manager.page.MyAccountPage;
import org.openqa.selenium.WebDriver;

public class AccountHelper {

    private WebDriver driver;

    public AccountHelper(WebDriver driver) {
        this.driver = driver;
    }

    public String getUserName() {
        return new MyAccountPage(driver).getUserNameCapture();
    }
}
