package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.MobileWebDriverConfig;
import io.appium.java_client.android.AndroidDriver;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.annotation.Nonnull;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class MobileWebDriver implements WebDriverProvider {

    private final String apkProjectRelativePath = "src/test/resources/app-alpha-universal-release.apk";

    private static URL getAppiumServerUrl() {
        MobileWebDriverConfig config = ConfigFactory.create(MobileWebDriverConfig.class);
        try {
            String appiumUrl = config.appiumUrl();
            if (appiumUrl == null) throw new IllegalArgumentException("Parameter ${appium.url} is not defined!");
            return new URL(appiumUrl);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Nonnull
    @Override
    public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("deviceName", "RF8N71SMG0H");
        desiredCapabilities.setCapability("version", "11.0");
        desiredCapabilities.setCapability("locale", "en");
        desiredCapabilities.setCapability("language", "en");
        desiredCapabilities.setCapability("appPackage", "org.wikipedia.alpha");
        desiredCapabilities.setCapability("appActivity", "org.wikipedia.main.MainActivity");
        desiredCapabilities.setCapability("app", getAbsolutePathOfMobileApk());

        return new AndroidDriver(getAppiumServerUrl(), desiredCapabilities);
    }

    private String getAbsolutePathOfMobileApk() {
        File file = new File(apkProjectRelativePath);
        if (!file.exists()) throw new IllegalArgumentException(apkProjectRelativePath + " not found");
        return file.getAbsolutePath();
    }
}
