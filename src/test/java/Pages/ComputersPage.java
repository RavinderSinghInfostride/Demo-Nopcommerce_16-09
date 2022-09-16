package Pages;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class ComputersPage {
    WebDriver driver;
    WebDriverWait wait;
    List<WebElement> allElementDisplay ;

    By computersSectionLink = By.xpath("(//ul[contains(@class,'top-menu')]//a[contains(text(),'Computers')])[1]");
    By notebooksLink = By.linkText("Notebooks");
    By verifySixItems = By.xpath("//div[@class='item-box']");
    By appleMacBookProduct = By.partialLinkText("MacBook");
    By addToCartButton = By.xpath("//div[@class='add-to-cart']//button[contains(@class,'add-to-cart-button')]");
    By crossButtonNotification = By.xpath("//div[contains(@class,'success')]//span[@class='close']");
    By shoppingCartLink = By.xpath("//a[@class='ico-cart']");
    By productAddedVerify = By.partialLinkText("MacBook");
    By termsOfServiceCheckbox = By.xpath("//input[@name='termsofservice']");
    By checkoutFromCartButton = By.xpath("//button[contains(@class,'checkout-button')]");
    By registerButton = By.xpath("//button[contains(@class,'register-button')]");
    By firstNameInput = By.xpath("//input[@name='FirstName']");
    By lastNameInput = By.xpath("//input[@name='LastName']");
    By emailInput = By.xpath("//input[@name='Email']");
    By passwordInput = By.xpath("//input[@name='Password']");
    By confirmPasswordInput = By.xpath("//input[@name='ConfirmPassword']");
    By registerNextPageButton = By.xpath("//button[contains(@class,'register-next-step-button')]");
    By continueButton = By.xpath("//a[contains(@class,'register-continue-button')]");
    By countryDropdown = By.xpath("//select[@name='BillingNewAddress.CountryId']");
    By countryOption = By.xpath("//*[contains(text(),'India')][@value='133']");
    By cityInput = By.xpath("//input[@name='BillingNewAddress.City']");
    By address1Input = By.xpath("//input[@name='BillingNewAddress.Address1']");
    By zipInput = By.xpath("//input[@name='BillingNewAddress.ZipPostalCode']");
    By phoneNumberInput = By.xpath("//input[@name='BillingNewAddress.PhoneNumber']");
    By continueAfterAddressDetailsButton = By.xpath("//button[contains(@class,'new-address-next-step-button')][@name='save']");

    public ComputersPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void addItemToCart()
    {
        driver.findElement(computersSectionLink).click();
        driver.findElement(notebooksLink).click();
        allElementDisplay = driver.findElements(verifySixItems);
        int xpathCount = allElementDisplay.size();
        Assert.assertEquals(xpathCount,6); //To verify 6 products are present
        driver.findElement(appleMacBookProduct).click();
        driver.findElement(addToCartButton).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(crossButtonNotification));
        driver.findElement(shoppingCartLink).click();
        boolean isAdminDisplayed = driver.findElement(productAddedVerify).isDisplayed();
        Assert.assertTrue(isAdminDisplayed, "Wrong product in cart");
    }

    public void checkoutFromCart() throws IOException {
        driver.findElement(termsOfServiceCheckbox).click();
        driver.findElement(checkoutFromCartButton).click();
        driver.findElement(registerButton).click();
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
        String email = sheet.getRow(1).getCell(2).getStringCellValue();
        String password = sheet.getRow(1).getCell(3).getStringCellValue();
        String confirmPassword = sheet.getRow(1).getCell(4).getStringCellValue();
        String city = sheet.getRow(1).getCell(5).getStringCellValue();
        String address1 = sheet.getRow(1).getCell(6).getStringCellValue();
        driver.findElement(firstNameInput).sendKeys(firstName);
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(emailInput).sendKeys("ssjsjsdnl@email.com");
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(confirmPasswordInput).sendKeys(confirmPassword);
        driver.findElement(registerNextPageButton).click();
        driver.findElement(continueButton).click();
        driver.findElement(termsOfServiceCheckbox).click();
        driver.findElement(checkoutFromCartButton).click();
        driver.findElement(countryDropdown).click();
        driver.findElement(countryOption).click();
        driver.findElement(cityInput).sendKeys(city);
        driver.findElement(address1Input).sendKeys(address1);
        driver.findElement(zipInput).sendKeys("130071");
        driver.findElement(phoneNumberInput).sendKeys("9999999999");
        driver.findElement(continueAfterAddressDetailsButton).click();
    }
}
