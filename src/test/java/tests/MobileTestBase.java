package tests;

import com.codeborne.selenide.Configuration;
import drivers.MobileDriverProvider;
import reporting.AttachmentHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static reporting.AttachmentHelper.getSessionId;

abstract public class MobileTestBase {

    @BeforeAll
    public static void setup() {
        addListener("AllureSelenide", new AllureSelenide());

        Configuration.browser = MobileDriverProvider.getProviderClass().getName();
        Configuration.startMaximized = false;
        Configuration.browserSize = null;
        Configuration.timeout = 10000;
    }

    @BeforeEach
    public void startDriver() {
        open();
    }

    @AfterEach
    public void afterEach() {
        String sessionId = getSessionId();

        AttachmentHelper.screenshotAs("Last screenshot");
        AttachmentHelper.pageSource();

        closeWebDriver();

        AttachmentHelper.attachVideo(sessionId);
    }
}
