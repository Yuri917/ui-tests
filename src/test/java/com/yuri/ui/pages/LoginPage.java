package com.yuri.ui.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {
    private static final String URL = "https://www.saucedemo.com/";

    private final SelenideElement loginElement = $("#user-name");
    private final SelenideElement passwordElement = $("[data-test='password']");
    private final SelenideElement loginButton = $x("//input[@data-test='login-button']");

    @Step("Открыть главную страницу")
    public LoginPage openPage() {
        Selenide.open(URL);
        return this;
    }

    @Step("Залогиниться")
    public void login(String username, String password) {
        loginElement.setValue(username);
        passwordElement.setValue(password);
        loginButton.click();
    }

    @Step("Залогиниться как стандартный пользователь")
    public void loginAsStandardUser() {
        login("standard_user", "secret_sauce");
    }
}
