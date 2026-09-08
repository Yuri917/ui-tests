package com.yuri.ui.tests;

import com.yuri.ui.pages.*;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.selenide.videorecorder.core.Video;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

@Epic("SauceDemo UI")
public class CartTest extends BaseTest {
    private final CartPage cartPage = new CartPage();
    private final ProductsPage productsPage = new ProductsPage();
    private final HeaderPage headerPage = new HeaderPage();

    @Test
    @Video
    @DisplayName("Добавление товара в корзину")
    @Feature("Корзина")
    @Story("Управление товарами")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка, что товар добавляется в корзину и кнопка меняется на Remove")
    public void addToCartTest() {
        productsPage.addProductToCart();
        $("[data-test='remove-sauce-labs-fleece-jacket']").shouldHave(text("Remove"));
    }

    @Test
    @Video
    @DisplayName("Удаление товара из корзины")
    @Feature("Корзина")
    @Story("Управление товарами")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка, что товар удаляется из корзины")
    public void removeFromCartTest() {
        productsPage.addProductToCart();
        headerPage.openCart();
        cartPage.removeProductFromCart();
        $("[data-test=inventory-item-name]").shouldNot(exist);
    }
}
