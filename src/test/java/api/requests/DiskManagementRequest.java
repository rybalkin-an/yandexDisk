package api.requests;

import io.qameta.allure.Step;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.hamcrest.Matchers.equalTo;

public class DiskManagementRequest extends BaseRequest{
    public String path = "/v1/disk/resources";

    @Step("GET Запрос на получение метаинформации о диске пользователя")
    public void getDiskMetaInfo(){
        givenWithAuth()
                .spec(getRequestBuilder(host + "/v1/disk").build())
                .when()
                .get()
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("getMetaInfo.json"));
    }

    @Step("GET Запрос на получение метаинформации о диске пользователя")
    public void getFileOrFolderMetaInfo(){
        givenWithAuth()
                .spec(getRequestBuilder(host + path + "?path=/").build())
                .when()
                .get(host + path + "?path=/")
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("getFileOrFolderMetaInfo.json"))
                .and()
                .body("_embedded.total", equalTo(10));
    }
}
