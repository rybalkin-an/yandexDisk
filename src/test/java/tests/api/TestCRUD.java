package tests.api;

import api.requests.DiskManagementRequest;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@Epic("[Smoke] Проверка основного функицонала API Яндекс.Диска")
public class TestCRUD {

    String folderName = RandomStringUtils.randomAlphanumeric(12);
    String responsePath = "https://cloud-api.yandex.net/v1/disk/resources?path=disk%3A%2F";
    Response response;
    DiskManagementRequest request;

    public TestCRUD() throws IOException {
        request = new DiskManagementRequest();
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Получить метаинформацию о диске пользователя")
    public void testGetMetaInfoAboutUsersDisk() {
        response = request.getDiskMetaInfo();
        response.then()
                .statusCode(200)
                .assertThat()
                .body(matchesJsonSchemaInClasspath("getMetaInfo.json"));
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Получить метаинформацию корневом каталоге", dependsOnMethods = "testDeleteFolder")
    public void testGetFileOrFolderMetaInfo() {
        response = request.getFileOrFolderMetaInfo("/");
        response.then()
                .statusCode(200)
                .assertThat()
                .body(matchesJsonSchemaInClasspath("getFileOrFolderMetaInfo.json"));
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Создать новую папку {folderName}")
    public void testCreateNewFolder() {
        response = request.putCreateNewFolder(folderName);

        Assert.assertEquals(response.path("href"), responsePath + folderName);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Удалить папку {folderName}", dependsOnMethods = "testCreateNewFolder")
    public void testGetLinkForDownload() {
        response = request.getDownloadLink(folderName);
        response.then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("getLinkForDownload.json"));

        Assert.assertNotNull(response.path("href"));
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Удалить папку {folderName}", dependsOnMethods = "testGetLinkForDownload")
    public void testDeleteFolder() {
        request.deleteFileOrFolder(folderName);
        response = request.getFileOrFolderMetaInfo("/" + folderName);

        Assert.assertEquals(response.path("description"), "Resource not found.");
    }

}
