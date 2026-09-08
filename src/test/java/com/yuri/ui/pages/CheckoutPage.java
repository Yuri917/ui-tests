package com.yuri.ui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class CheckoutPage {
    private final SelenideElement firstName = $("[data-test='firstName']");
    private final SelenideElement lastName = $("[data-test='lastName']");
    private final SelenideElement zipPostalCode = $("[data-test='postalCode']");
    private final SelenideElement continueButton = $("[data-test='continue']");

    @Step("Ввести персональные данные для заказа")
    public CheckoutPage fillCheckoutInformation(String firstName, String lastName, String zip) {
        this.firstName.setValue(firstName);
        this.lastName.setValue(lastName);
        this.zipPostalCode.setValue(zip);
        return this;
    }

    @Step("Продолжить оформление заказа")
    public void clickContinue() {
        continueButton.click();
    }
}
