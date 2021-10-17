package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/emulator.properties"
})
public interface EmulatorConfig extends Config {
    @Key("url")
    String url();
}

