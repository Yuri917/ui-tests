package com.yuri.ui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class CartPage {
    private final SelenideElement removeProduct = $("#remove-sauce-labs-fleece-jacket");
    private final SelenideElement checkoutButton = $("[data-test='checkout']");

    @Step("Удалить продукт из корзины")
    public void removeProductFromCart() {
        removeProduct.click();
    }

    @Step("Перейти к оформлению заказа")
    public void checkout() {
        checkoutButton.click();
    }
}
