package com.yuri.ui.tests;

import com.yuri.ui.pages.*;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.selenide.videorecorder.core.Video;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

@Epic("SauceDemo UI")
public class CheckoutTest extends BaseTest {
    private final CartPage cartPage = new CartPage();
    private final ProductsPage productsPage = new ProductsPage();
    private final HeaderPage headerPage = new HeaderPage();
    private final CheckoutPage checkoutPage = new CheckoutPage();
    private final CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage();

    @Test
    @Video
    @DisplayName("Оформление заказа")
    @Feature("Оформление заказа")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Проверка оформления заказа: добавление в корзину, заполнение данных, окончательное оформление")
    public void checkoutTest() {
        productsPage.addProductToCart();
        headerPage.openCart();
        cartPage.checkout();
        checkoutPage
                .fillCheckoutInformation("Nick", "Sauce", "400777")
                .clickContinue();
        checkoutOverviewPage.clickFinish();
        $("[data-test='complete-header']").shouldHave(text("Thank you for your order!"));
    }
}
