package Pages;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

public class CameraPage {
    WebDriver driver;
    WebDriverWait wait;

    By cameraSubMenuOption = By.xpath("(//ul[contains(@class,'sublist')]//a[contains(text(),'Camera & photo')])[1]");
    By addToCartBtn = By.xpath("(//button[contains(@class,'product-box-add-to-cart-button')])[1]");
    By redCameraAddToWishlistBtn = By.xpath("//div[contains(text(),'Red')]//following::div[@class='add-to-wishlist']//button[contains(@class,'add-to-wishlist-button')]");
    By notificationCrossBtn = By.xpath("//div[contains(@class,'success')]//span[@class='close']");
    By wishlistLink = By.xpath("//a[@class='ico-wishlist']");
    By wishlistProductVerify = By.xpath("//span[contains(text(),'N5500DS_R')]");

    public CameraPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateToCameraPage() throws IOException {
        String path = System.getProperty("user.dir") + "//src//test//java//TestData//TestDataFile.xlsx";
        FileInputStream prop1 = null;
        try {
            prop1 = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        XSSFWorkbook wb = new XSSFWorkbook(prop1);
        XSSFSheet sheet = wb.getSheet("Sheet1");
        String navMenuOption = sheet.getRow(1).getCell(0).getStringCellValue();
        WebElement topMenu = driver.findElement(By.xpath(String.format("//div[@class='header-menu']//ul[contains(@class,'notmobile')]//a[contains(text(),'%s')]", navMenuOption)));
        Actions action = new Actions(driver);
        action.moveToElement(topMenu).perform();
        driver.findElement(cameraSubMenuOption).click();
    }

    public void addCameraToWishlist() {
        driver.findElement(addToCartBtn).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(redCameraAddToWishlistBtn));
        driver.findElement(redCameraAddToWishlistBtn).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(notificationCrossBtn));
        driver.findElement(wishlistLink).click();
        String actual = driver.findElement(wishlistProductVerify).getText();
        Assert.assertEquals("N5500DS_R", actual);
    }
}