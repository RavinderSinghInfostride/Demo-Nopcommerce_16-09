package Pages;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

public class BooksPage {
    WebDriver driver;
    WebDriverWait wait;

    By registerLink = By.xpath("//a[contains(text(),'Register')]");
    By firstNameInput = By.xpath("//input[@name='FirstName']");
    By lastNameInput = By.xpath("//input[@name='LastName']");
    By emailInput = By.xpath("//input[@name='Email']");
    By passwordInput = By.xpath("//input[@name='Password']");
    By confirmPasswordInput = By.xpath("//input[@name='ConfirmPassword']");
    By registerUserButton = By.xpath("//button[contains(@class,'register-next-step-button')]");
    By continueButton = By.xpath("//a[contains(@class,'register-continue-button')]");
    By bookAddToCart = By.xpath("(//button[contains(@class,'product-box-add-to-cart-button')])[1]");
    By notificationCrossBtn = By.xpath("//div[contains(@class,'success')]//span[@class='close']");
    By shoppingCartLink = By.xpath("//span[contains(text(),'Shopping')]");
    By productAddedVerify = By.xpath("//a[@class='product-name']");
    By termsOfServiceCheckbox = By.xpath("//input[@name='termsofservice']");
    By checkoutFromCartButton = By.xpath("//button[contains(@class,'checkout-button')]");
    By countryDropdown = By.xpath("//select[@name='BillingNewAddress.CountryId']");
    By countryOption = By.xpath("//*[contains(text(),'India')][@value='133']");
    By cityInput = By.xpath("//input[@name='BillingNewAddress.City']");
    By address1Input = By.xpath("//input[@name='BillingNewAddress.Address1']");
    By zipInput = By.xpath("//input[@name='BillingNewAddress.ZipPostalCode']");
    By phoneNumberInput = By.xpath("//input[@name='BillingNewAddress.PhoneNumber']");
    By continueAfterAddressDetailsButton = By.xpath("//button[contains(@class,'new-address-next-step-button')][@name='save']");
    By continueButton2 = By.xpath("//button[contains(@class,'shipping-method-next-step-button')]");
    By continueButton3  = By.xpath("//button[contains(@class,'payment-method-next-step-button')]");
    By continueButton4 = By.xpath("//button[contains(@class,'payment-info-next-step-button')]");
    By logoutLink = By.xpath("//a[contains(text(),'Log out')]");

    int random = (int) (Math.random() * (99 - 11 + 1) + 11);

    public BooksPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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

    public void addBookToCart() throws IOException {
        String path = System.getProperty("user.dir") + "//src//test//java//TestData//TestDataFile.xlsx";
        FileInputStream prop1 = null;
        try {
            prop1 = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        XSSFWorkbook wb = new XSSFWorkbook(prop1);
        XSSFSheet sheet = wb.getSheet("Sheet1");
        String navMenuOption = sheet.getRow(1).getCell(2).getStringCellValue();
        driver.findElement(By.xpath(String.format("//div[@class='header-menu']//ul[contains(@class,'notmobile')]//a[contains(text(),'%s')]", navMenuOption))).click();
        driver.findElement(bookAddToCart).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(notificationCrossBtn));
        driver.findElement(shoppingCartLink).click();
        String isProductAddedRight = driver.findElement(productAddedVerify).getText();
        Assert.assertEquals("Fahrenheit 451 by Ray Bradbury",isProductAddedRight);
    }

    public void checkout() throws IOException {
        String path = System.getProperty("user.dir") + "//src//test//java//TestData//TestDataFile.xlsx";
        FileInputStream prop1 = null;
        try {
            prop1 = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        XSSFWorkbook wb = new XSSFWorkbook(prop1);
        XSSFSheet sheet = wb.getSheet("Sheet1");
        String city = sheet.getRow(1).getCell(3).getStringCellValue();
        String address1 = sheet.getRow(1).getCell(4).getStringCellValue();
        driver.findElement(termsOfServiceCheckbox).click();
        driver.findElement(checkoutFromCartButton).click();
        driver.findElement(countryDropdown).click();
        driver.findElement(countryOption).click();
        driver.findElement(cityInput).sendKeys(city);
        driver.findElement(address1Input).sendKeys(address1);
        driver.findElement(zipInput).sendKeys("130071");
        driver.findElement(phoneNumberInput).sendKeys("9999999999");
        driver.findElement(continueAfterAddressDetailsButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(continueButton2));
        driver.findElement(continueButton2).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(continueButton3));
        driver.findElement(continueButton3).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(continueButton4));
        driver.findElement(continueButton4).click();
    }
}