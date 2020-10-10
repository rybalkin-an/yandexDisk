package api.requests;

import helpers.ParametersProvider;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import java.io.IOException;
import static io.restassured.RestAssured.given;

public class BaseRequest {
    public String host;
    public String token = "AgAAAAAol0y6AAaGfbNNMk57uE-bvUUFdSFvU_w";

    public BaseRequest() throws IOException {
        host = ParametersProvider.getProperty("url");
    }

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
