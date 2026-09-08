package com.yuri.ui.tests;

import com.yuri.ui.pages.ProductsPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.selenide.videorecorder.core.Video;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

@Epic("SauceDemo UI")
public class ProductsTest extends BaseTest {
    private final ProductsPage productsPage = new ProductsPage();

    @Test
    @Video
    @DisplayName("Просмотр продукта")
    @Feature("Продукт")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка просмотра продукта")
    public void viewProductTest() {
        productsPage.viewProduct();
        $("[data-test='inventory-item-name']").shouldHave(text("Sauce Labs Fleece Jacket"));
        // Тестирование скриншотов
//        $("[data-test='inventory-item-name']").shouldHave(text("НЕВЕРНЫЙ ТОВАР"));
        $("[data-test='add-to-cart']").shouldBe(visible);
        webdriver().shouldHave(urlContaining("/inventory-item.html?id=5"));
    }
}
