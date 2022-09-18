package Test;

import org.testng.annotations.Test;
import java.io.IOException;

public class AddCameraToWishlist extends BaseClass {
    @Test
    public void navigateToCameraPage() throws IOException {
        pageFactory.getCameraPage().navigateToCameraPage();
    }

    @Test(dependsOnMethods = "navigateToCameraPage")
    public void addCameraToWishlist() {
        pageFactory.getCameraPage().addCameraToWishlist();
    }
}