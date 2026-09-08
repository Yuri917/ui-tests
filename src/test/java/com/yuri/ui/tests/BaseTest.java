package com.yuri.ui.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import com.yuri.ui.pages.LoginPage;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.extension.TestWatcher;
import org.selenide.videorecorder.junit5.VideoRecorderExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.*;

@ExtendWith(VideoRecorderExtension.class)
public abstract class BaseTest {
    protected final LoginPage loginPage = new LoginPage();
    private static final Logger log = LoggerFactory.getLogger(BaseTest.class);

    @BeforeAll
    public static void setUpAllure() {
        // Включаем автоматическое прикрепление скриншотов к Allure
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)         // сохранять скриншоты
                .savePageSource(true)     // не сохранять HTML страницы (можно true, если нужно)
        );
    }

    @Step("Подготовка тестового окружения: открытие страницы, удаление cookies и local storage и логин стандартным пользователем")
    @BeforeEach
    public void baseSetup() {
        loginPage.openPage();
        cookies().clear();
        localStorage().clear();
        loginPage.openPage().loginAsStandardUser();
    }

    @Step("Очистка после теста: удаление cookies и local storage")
    @AfterEach
    public void baseTearDown() {
        cookies().clear();
        localStorage().clear();
    }

    @RegisterExtension
    static TestWatcher attachVideoOnFailure = new TestWatcher() {
        @Override
        public void testFailed(ExtensionContext context, Throwable cause) {
            attachLatestVideo(context);
        }
    };

    private static void attachLatestVideo(ExtensionContext context) {
        Path reportsDir = Paths.get("build/reports/tests/");
        if (!Files.exists(reportsDir)) return;

        try (Stream<Path> files = Files.list(reportsDir)) {
            files.filter(p -> p.getFileName().toString().matches("video\\..*\\.mp4"))
                    .max(Comparator.comparing(p -> p.toFile().lastModified()))
                    .ifPresent(videoPath -> {
                        try {
                            String testName = context.getTestMethod()
                                    .map(Method::getName)
                                    .orElse("unknown");
                            Allure.addAttachment("Video for " + testName,  // ← имя с testName
                                    "video/mp4",
                                    Files.newInputStream(videoPath),
                                    ".mp4");
                        } catch (IOException e) {
                            log.error("Failed to attach video to Allure", e);
                        }
                    });
        } catch (IOException e) {
            log.error("Failed to attach video to Allure", e);
        }
    }
}