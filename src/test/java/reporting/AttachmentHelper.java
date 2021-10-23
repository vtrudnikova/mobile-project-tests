package reporting;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class AttachmentHelper {

    private static final BrowserstackApiClient browserstackApiClient = new BrowserstackApiClient();

    @Attachment(value = "{attachName}", type = "text/plain")
    public static String attachAsText(String attachName, String message) {
        return message;
    }

    @Attachment(value = "Page source", type = "text/plain")
    public static byte[] pageSource() {
        return getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
    }

    @Attachment(value = "{attachName}", type = "image/png")
    public static byte[] screenshotAs(String attachName) {
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Video", type = "text/html", fileExtension = ".html")
    public static String attachVideo(String sessionId) {
        return "<html><body><video width='100%' height='100%' controls autoplay><source src='"
                + getVideoUrl(sessionId)
                + "' type='video/mp4'></video></body></html>";
    }

    private static String getVideoUrl(String sessionId) {
        String deviceFarm = System.getProperty("deviceFarm");

        if ("browserstack".equals(deviceFarm)) {
            return browserstackApiClient.videoUrl(sessionId);
        } else if ("selenoid".equals(deviceFarm)) {
            return getSelenoidVideoUrl(sessionId);
        }
        return null;
    }

    private static String getSelenoidVideoUrl(String sessionId) {
        try {
            return new URL("https://selenoid.autotests.cloud/video/" + sessionId + ".mp4") + "";
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getSessionId() {
        return ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
    }

}
