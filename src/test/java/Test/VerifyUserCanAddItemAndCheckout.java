package Test;

import org.testng.annotations.Test;
import java.io.IOException;

public class VerifyUserCanAddItemAndCheckout extends BaseClass {

    @Test
    public void addItemToCart() {
        pageFactory.getComputersPage().addItemToCart();
    }

    @Test(dependsOnMethods = "addItemToCart")
    public void checkoutFromCart() throws IOException {
        pageFactory.getComputersPage().checkoutFromCart();
    }
}
