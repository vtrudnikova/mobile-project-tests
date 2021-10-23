package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/mobile-webdriver.properties"
})
public interface MobileWebDriverConfig extends Config {

    @Key("appium.url")
    String appiumUrl();

}
