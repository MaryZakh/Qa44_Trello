package pages;

import interfaces.Path;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProfileAndVisibilityPage extends BasePage implements Path {

    public ProfileAndVisibilityPage(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//div[@data-test-selector='profile-hover-info']")
    WebElement profilePhoto;
    @FindBy(xpath = "//button[@data-testid='change-avatar']")
    WebElement changeProfilePhoto;
    //    @FindBy(xpath = "//button[@data-testid='upload-button']")
//    WebElement uploadPhoto;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnUpload;
    @FindBy(id = "image-input")
    WebElement inputUploadPhoto;


    public ProfileAndVisibilityPage changeAvatar(String fileName) {
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        Actions actions = new Actions(driver);
        actions.moveToElement(profilePhoto).pause(2000).click().perform();
        changeProfilePhoto.click();
        pause(3000);
        File file = new File(PHOTOS_PATH + fileName);
        String filePath = file.getAbsolutePath();
        inputUploadPhoto.sendKeys(filePath);
        pause(3000);
        btnUpload.click();

        return this;
    }
}
