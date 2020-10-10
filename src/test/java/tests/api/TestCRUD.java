package tests.api;

import api.requests.DiskManagementRequest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

public class TestCRUD {
    DiskManagementRequest request;

    public TestCRUD(){
        request = new DiskManagementRequest();
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Получить метаинформацию о диске пользователя")
    public void testGetMetaInfoAboutUsersDisk() {
        request.getDiskMetaInfo();
    }
}
