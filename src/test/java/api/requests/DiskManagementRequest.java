package api.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.io.IOException;

public class DiskManagementRequest extends BaseRequest{
    public String path = "/v1/disk/resources";

    public DiskManagementRequest() throws IOException {
    }

    @Step("GET Запрос: Получение метаинформации о диске пользователя")
    public Response getDiskMetaInfo(){
        return givenWithAuth()
                .spec(getRequestBuilder(host + "/v1/disk").build())
                .when()
                .get()
                .then()
                .extract()
                .response();
    }

    @Step("GET Запрос: Получение метаинформации о диске пользователя")
    public Response getFileOrFolderMetaInfo(String pathToFileOrFolder){
        return givenWithAuth()
                .spec(getRequestBuilder(host + path).build())
                .when()
                .get(host + path + "?path=" + pathToFileOrFolder)
                .then()
                .extract()
                .response();
    }

    @Step("PUT Запрос: Создание новой папки")
    public Response putCreateNewFolder(String folderName){
        return givenWithAuth()
                .spec(getRequestBuilder(host + path).build())
                .when()
                .put(host + path + "?path=" + folderName)
                .then()
                .statusCode(201)
                .extract()
                .response();
    }

    @Step("DELETE Запрос: Удаление файла или папки")
    public void deleteFileOrFolder(String folderName){
        givenWithAuth()
                .spec(getRequestBuilder(host + path).build())
                .when()
                .delete(host + path + "?path=" + folderName)
                .then()
                .statusCode(204);
    }

    @Step("GET Запрос: Получить сслыку на скачивание файла")
    public Response getDownloadLink(String folderName){
        return givenWithAuth()
                .spec(getRequestBuilder(host + path).build())
                .when()
                .get(host + path + "/download/?path=" + folderName)
                .then()
                .statusCode(200)
                .extract()
                .response();
    }

}
