package drivers;

public class MobileDriverProvider {
    public static Class getProviderClass() {
        String deviceHost = System.getProperty("deviceHost");
        if ("browserstack".equals(deviceHost)) {
            return BrowserstackMobileDriver.class;
        } else if ("selenoid".equals(deviceHost)) {
            return SelenoidMobileDriver.class;
        } else if ("emulation".equals(deviceHost)) {
            return EmulatorMobileDriver.class;
        } else if ("real".equals(deviceHost)) {
            return RealMobileDriver.class;
        } else {
            return EmulatorMobileDriver.class;
        }
    }
}
