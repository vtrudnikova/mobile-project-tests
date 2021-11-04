package reporting;

import config.MobileWebDriverConfig;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.given;

public class BrowserstackApiClient {
    static MobileWebDriverConfig config = ConfigFactory.create(MobileWebDriverConfig.class);

    public String videoUrl(String sessionId) {
        return given()
                .auth().basic(config.userName(), config.password())
                .when()
                .get("https://api-cloud.browserstack.com/app-automate/sessions/" + sessionId + ".json")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .path("automation_session.video_url");
    }

}