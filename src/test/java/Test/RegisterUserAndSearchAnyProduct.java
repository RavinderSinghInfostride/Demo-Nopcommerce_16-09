package Test;

import org.testng.annotations.Test;
import java.io.IOException;

public class RegisterUserAndSearchAnyProduct extends BaseClass {
    @Test
    public void registerUser() throws IOException {
        pageFactory.getHomePage().registerUser();
    }

    @Test(dependsOnMethods = "registerUser")
    public void searchProduct() throws IOException {
        pageFactory.getHomePage().searchProduct();
    }
}