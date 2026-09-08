package com.yuri.ui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class CheckoutOverviewPage {
    private final SelenideElement finish = $("[data-test='finish']");

    @Step("Завершить оформление заказа")
    public void clickFinish() {
        finish.click();
    }
}
