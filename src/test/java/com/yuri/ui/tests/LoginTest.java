package com.yuri.ui.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import com.yuri.ui.pages.LoginPage;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.selenide.videorecorder.junit5.VideoRecorderExtension;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@Epic("SauceDemo UI")
@ExtendWith(VideoRecorderExtension.class)
public class LoginTest {

    private final LoginPage loginPage = new LoginPage();

    @BeforeAll
    public static void setUpAllure() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)         // сохранять скриншоты
                .savePageSource(true)     // не сохранять HTML страницы (можно true, если нужно)
        );
    }

    @Step("Подготовка тестового окружения: открытие страницы и удаление cookies и local storage")
    @BeforeEach
    public void setUp() {
        loginPage.openPage();
        cookies().clear();
        localStorage().clear();
        loginPage.openPage();
    }

    @ParameterizedTest
    @CsvSource({
            "standard_user, secret_sauce, true",
            "locked_out_user, secret_sauce, false",
            "problem_user, secret_sauce, true",
            "error_user, secret_sauce, true",
            "visual_user, secret_sauce, true",
            "invalid_user, secret_sauce, false"
    })
    @DisplayName("Вход с разными пользователями")
    @Feature("Авторизация")
    @Story("Вход в систему")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Параметризованный тест проверяет успешный и неуспешный вход для разных пользователей")
    void loginTest(String username, String password, boolean shouldSucceed) {
        loginPage.login(username, password);
        if (shouldSucceed) {
            $(".title").shouldHave(text("Products"));
        } else {
            $("[data-test='error']").shouldBe(visible);
        }
    }

    @Test
    @DisplayName("Вход с задержкой (performance_glitch_user)")
    @Feature("Авторизация")
    @Story("Вход в систему")
    @Severity(SeverityLevel.NORMAL)
    @Description("Проверка входа для пользователя performance_glitch_user с ожиданием до 7 секунд")
    void performanceGlitchUserLogin() {
        loginPage.login("performance_glitch_user", "secret_sauce");
        $(".title").shouldHave(text("Products"), Duration.ofSeconds(7));
    }

    @Step("Очистка после теста: удаление cookies и local storage")
    @AfterEach
    public void tearDown() {
        cookies().clear();
        localStorage().clear();
    }
}