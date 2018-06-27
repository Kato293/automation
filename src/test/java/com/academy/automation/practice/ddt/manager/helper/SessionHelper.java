package com.academy.automation.practice.ddt.manager.helper;

import com.academy.automation.practice.ddt.manager.page.HomePage;
import com.academy.automation.practice.ddt.manager.page.LoginPage;
import com.academy.automation.practice.ddt.manager.page.MyAccountPage;
import org.openqa.selenium.WebDriver;

public class SessionHelper {

    private WebDriver driver;
    private String login;
    private String password;

    public SessionHelper(WebDriver driver, String login, String password) {
        this.driver = driver;
        this.login = login;
        this.password = password;
    }

    public void login() {
        new HomePage(driver)
                .clickSignInLink()
                .inputLogin(login)
                .inputPassword(password)
                .clickSignIn(false);
    }

    public void tryLogin(String login, String password) {
        LoginPage loginPage =
                (LoginPage) new HomePage(driver)
                        .clickSignInLink()
                        .inputLogin(login)
                        .inputPassword(password)
                        .clickSignIn(false);
    }

    public String getErrMessage() {
        return new LoginPage(driver).getErrorMessage();
    }

    public void logout() {
        new MyAccountPage(driver).clickLogout();
    }
}
