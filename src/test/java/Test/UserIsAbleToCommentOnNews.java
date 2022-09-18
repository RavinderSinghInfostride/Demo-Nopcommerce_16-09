package Test;

import org.testng.annotations.Test;
import java.io.IOException;

public class UserIsAbleToCommentOnNews extends BaseClass {
    @Test
    public void registerUser() throws IOException {
        pageFactory.getHomePage().registerUser();
    }

    @Test(dependsOnMethods = "registerUser")
    public void commentOnNews() throws IOException {
        pageFactory.getHomePage().commentOnNews();
    }
}