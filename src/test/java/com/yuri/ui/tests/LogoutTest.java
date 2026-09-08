package com.yuri.ui.tests;

import com.yuri.ui.pages.HeaderPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.selenide.videorecorder.core.Video;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

@Epic("SauceDemo UI")
public class LogoutTest extends BaseTest {
    private final HeaderPage headerPage = new HeaderPage();

    @Test
    @Video
    @DisplayName("Выход пользователя")
    @Feature("Выход из системы")
    @Severity(SeverityLevel.NORMAL)
    @Description("Проверка, что после выхода отображается кнопка логина")
    public void logout() {
        headerPage.openBurgerMenu().logout();

        $("[data-test='login-button']").shouldBe(visible);
    }
}
