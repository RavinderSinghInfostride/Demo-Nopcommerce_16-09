package Test;

import org.testng.annotations.Test;
import java.io.IOException;

public class RegisterUserAndAddBooksToCartAndCheckout extends BaseClass {
    @Test
    public void registerUser() throws IOException {
        pageFactory.getBooksPage().registerUser();
    }

    @Test(dependsOnMethods = "registerUser")
    public void searchProduct() throws IOException {
        pageFactory.getBooksPage().addBookToCart();
    }

    @Test(dependsOnMethods = "searchProduct")
    public void checkout() throws IOException {
        pageFactory.getBooksPage().checkout();
    }
}