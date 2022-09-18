package Pages;

import org.openqa.selenium.WebDriver;

public class PageFactory {
    WebDriver driver;

    private CameraPage cameraPage;

    public PageFactory(WebDriver driver) {
        this.driver = driver;
    }

    public CameraPage getCameraPage() {
        if (cameraPage == null) {
            cameraPage = new CameraPage(driver);
        }
        return cameraPage;
    }
}