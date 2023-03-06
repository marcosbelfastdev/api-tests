package trainingXyz;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class ReqResTests {

    @Test
    public void getUsers() {
        String endpoint = "http://reqres.in/api/users";
        var response = given().get(endpoint)
                .then().log().body();
    }
}
