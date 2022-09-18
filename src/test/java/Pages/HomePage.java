package Pages;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HomePage {
    WebDriver driver;

    By registerLink = By.xpath("//a[contains(text(),'Register')]");
    By firstNameInput = By.xpath("//input[@name='FirstName']");
    By lastNameInput = By.xpath("//input[@name='LastName']");
    By emailInput = By.xpath("//input[@name='Email']");
    By passwordInput = By.xpath("//input[@name='Password']");
    By confirmPasswordInput = By.xpath("//input[@name='ConfirmPassword']");
    By registerUserButton = By.xpath("//button[contains(@class,'register-next-step-button')]");
    By continueButton = By.xpath("//a[contains(@class,'register-continue-button')]");
    By newsDetailButton = By.xpath("(//div[@class='news-head']//a[contains(text(),'nopCommerce new release!')]//following::div[@class='buttons']//a[contains(text(),'details')])[1]");
    By commentTitleInput = By.xpath("//input[@class='enter-comment-title']");
    By commentInput = By.xpath("//textarea[@class='enter-comment-text']");
    By newCommentSubmitButton = By.xpath("//button[contains(@class,'news-item-add-comment-button')]");
    By commentAddedNotification = By.xpath("//div[@class='result']");
    By logoutLink = By.xpath("//a[contains(text(),'Log out')]");

    int random = (int) (Math.random() * (99 - 11 + 1) + 11);

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void registerUser() throws IOException {
        driver.findElement(registerLink).click();
        String path = System.getProperty("user.dir") + "//src//test//java//TestData//TestDataFile.xlsx";
        FileInputStream prop1 = null;
        try {
            prop1 = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        XSSFWorkbook wb = new XSSFWorkbook(prop1);
        XSSFSheet sheet = wb.getSheet("Sheet1");
        String firstName = sheet.getRow(1).getCell(0).getStringCellValue();
        String lastName = sheet.getRow(1).getCell(1).getStringCellValue();
        String email = firstName + lastName + random + "@email.com";
        driver.findElement(firstNameInput).sendKeys(firstName);
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys("123456");
        driver.findElement(confirmPasswordInput).sendKeys("123456");
        driver.findElement(registerUserButton).click();
        driver.findElement(continueButton).click();
    }

    public void commentOnNews() throws IOException {
        driver.findElement(newsDetailButton).click();
        String path = System.getProperty("user.dir") + "//src//test//java//TestData//TestDataFile.xlsx";
        FileInputStream prop1 = null;
        try {
            prop1 = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        XSSFWorkbook wb = new XSSFWorkbook(prop1);
        XSSFSheet sheet = wb.getSheet("Sheet1");
        String commentTitle = sheet.getRow(1).getCell(2).getStringCellValue();
        String comment = sheet.getRow(1).getCell(3).getStringCellValue();
        driver.findElement(commentTitleInput).sendKeys(commentTitle);
        driver.findElement(commentInput).sendKeys(comment);
        driver.findElement(newCommentSubmitButton).click();
        String isCommentAddedNotification = driver.findElement(commentAddedNotification).getText();
        Assert.assertEquals("News comment is successfully added.", isCommentAddedNotification);
        driver.findElement(logoutLink).click();
    }
}