package com.yuri.ui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class HeaderPage {
    private final SelenideElement cartIcon = $("[data-test='shopping-cart-link']");
    private final SelenideElement burgerMenu = $("#react-burger-menu-btn");
    private final SelenideElement logoutButton = $("[data-test='logout-sidebar-link']");

    @Step("Открыть корзину заказов")
    public void openCart() {
        cartIcon.click();
    }

    @Step("Открыть бургер меню")
    public HeaderPage openBurgerMenu() {
        burgerMenu.click();
        return this;
    }

    @Step("Выйти из личного кабинета")
    public void logout() {
        logoutButton.click();
    }
}