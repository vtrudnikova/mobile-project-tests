package reporting;

import static io.restassured.RestAssured.given;

public class BrowserstackApiClient {

    public String videoUrl(String sessionId) {
        return given()
                .auth().basic("stanislavvasenko_VbIEGO", "gzMbXq5ts4MJPnZs7jKK")
                .when()
                .get("https://api-cloud.browserstack.com/app-automate/sessions/" + sessionId + ".json")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .path("automation_session.video_url");
    }

}