package com.yuri.ui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class ProductsPage {
    private final SelenideElement productLink = $("[data-test='item-5-title-link']");
    private final SelenideElement addToCartButton = $("[data-test='add-to-cart-sauce-labs-fleece-jacket']");

    @Step("Добавить продукт в корзину")
    public void addProductToCart() {
        addToCartButton.click();
    }

    @Step("Просмотреть продукт")
    public void viewProduct() {
        productLink.click();
    }
}
