package api.requests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class BaseRequest {
    public String contentType = "application/json;charset=utf-8";
    public String host = "https://cloud-api.yandex.net";
    public String token = "AgAAAAAol0y6AAaGfbNNMk57uE-bvUUFdSFvU_w";

    public RequestSpecification givenWithAuth(){
        return given()
                .headers("Authorization","OAuth " + token);
    }

    public RequestSpecBuilder getRequestBuilder(String baseUrl){
        return new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setAccept("*/*");

    }
}
