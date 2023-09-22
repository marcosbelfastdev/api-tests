package trainingXyz;

import io.restassured.response.Response;
import model.Product;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiTests {

    @Test
    public void getCategories() {
        String endpoint = "http://localhost:8888/api_testing/category/read.php";
        var response = given()
                .when().get(endpoint)
                .then();
        response.log().body();
    }

    @Test
    public void getProduct() {
        String endpoint = "http://localhost:8888/api_testing/product/read_one.php";
        Matcher<Integer> success = oneOf(200,201);
        var response =
                given().queryParam("id", 2)
                .when().get(endpoint)
                .then().
                        //.log().body();
                        assertThat().statusCode(is(success)).
                        body("id", equalTo("2")).
                        body("name", equalTo("Cross-Back Training Tank")).
                        body("description", equalTo("The most awesome phone of 2013!")).
                        body("price",equalTo("299.00")).
                        body("category_id", equalTo("2")).
                        body("category_name", equalTo("Active Wear - Women"));

        response.log().body();
    }

    @Test
    public void getAllProducts() {
        String endpoint = "http://localhost:8888/api_testing/product/read.php";

        Response response =
                given()
                        .when().get(endpoint);
//                        .then().
                        //.log().body();
//                                assertThat().statusCode(200);

        LinkedHashMap<String,?> record = response.jsonPath().getJsonObject("records[1]");
        System.out.println(record.get("description"));

        List<LinkedHashMap<String, String>> list = response.jsonPath().getList("records");
        for (LinkedHashMap<String, String> item : list) {
            System.out.println(item.get("name"));
        }
        System.out.println(list);

        //response.log().body();

        //List<?> list = response.extract().jsonPath().get("records");
        //for (Product product : list)

    }


    @Test
    public void createProduct() {
        String endpoint = "http://localhost:8888/api_testing/product/create.php";
        String body = """
            {
            "name" : "Water Bottle",
            "description" : "Blue water bottle. Holds 64 ounces",
            "price": 12,
            "category_id": 3
            }
        """;
        var response  = given().body(body)
                .when().post(endpoint)
                .then();
        response.log().body();

    }

    @Test
    public void updateProduct() {
        String endpoint = "http://localhost:8888/api_testing/product/update.php";
        String body = """
                {
                "id" : 19,
                "name": "Water Bottle",
                "description": "Blue water bottle. Holds 64 ounces",
                "price": 15,
                "category_id": 3
                }
                """;
        var response = given().body(body)
                .when().put(endpoint)
                .then();
        response.log().body();
    }

    @Test
    public void deleteProduct() {
        String endpoint = "http://localhost:8888/api_testing/product/delete.php";
        String body = """
                {
                "id": 19
                }
                """;
        var response  = given().body(body)
                .when().delete(endpoint)
                .then();
        response.log().body();
    }

    @Test
    public void createSerializedProduct() {
        String endpoint = "http://localhost:8888/api_testing/product/create.php";
        Product product = new Product(
                "Water Bottle",
                "Blue water bottle. Holds 64 ounces",
                12,
                3
        );
        var response = given().body(product)
                .when().post(endpoint)
                .then();
        response.log().body();
    }

}
