package api.requests;

import io.restassured.response.ValidatableResponse;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class DiskManagementRequest extends BaseRequest{
    public String path = "/v1/disk";

    public ValidatableResponse getDiskMetaInfo(){
        return givenWithAuth()
                .spec(getRequestBuilder(host + path).build())
                .when()
                .get(host + path)
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("getMetaInfo.json"));
    }
}
