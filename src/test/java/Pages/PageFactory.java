package Pages;

import org.openqa.selenium.WebDriver;

public class PageFactory {
    WebDriver driver;

    private BooksPage booksPage;

    public PageFactory(WebDriver driver) {
        this.driver = driver;
    }

    public BooksPage getBooksPage()
    {
        if(booksPage == null)
        {
            booksPage = new BooksPage(driver);
        }
        return booksPage;
    }
}