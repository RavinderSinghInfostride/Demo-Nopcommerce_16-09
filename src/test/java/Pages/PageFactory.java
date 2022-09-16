package Pages;

import org.openqa.selenium.WebDriver;

public class PageFactory {
    WebDriver driver;

    private ComputersPage computersPage;

    public PageFactory(WebDriver driver) {
        this.driver = driver;
    }

    public ComputersPage getComputersPage() {
        if (computersPage == null) {
            computersPage = new ComputersPage(driver);
        }
        return computersPage;
    }
}