package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/real.properties"
})
public interface SelenideConfig extends Config {
    @Config.Key("url")
    String url();
    @Config.Key("apkUrl")
    String apkUrl();
}
