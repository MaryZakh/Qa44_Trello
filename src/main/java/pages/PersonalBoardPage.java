package pages;

import dto.BoardDTO;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class PersonalBoardPage extends BasePage {
    public PersonalBoardPage(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }


    @FindBy(xpath = "//h1[@data-testid='board-name-display']")
    WebElement nameBoard;
    @FindBy(xpath = "//button[@aria-label='Show menu']")
    WebElement btnDots;
    @FindBy(xpath = "//*[text()='Close board']")
    WebElement btnCloseBoard;
    @FindBy(xpath = "//input[@value='Close']")
    WebElement btnCloseConfirm;
    @FindBy(xpath = "//button[@data-testid='close-board-delete-board-button']")
    WebElement btnDeleteBoard;
    @FindBy(xpath = "//button[@data-testid='close-board-delete-board-confirm-button']")
    WebElement btnDeleteBoardConfirm;
    @FindBy(xpath = "//div[@class='qsCZSrobO7JoSv']")
    WebElement logo;




    public boolean isTextInElementPresent_nameBoard(String text) {
        return isTextInElementPresent(nameBoard, text, 10);
    }


    public BoardsPage deleteBoard(BoardDTO board) {
        btnDots.click();
        btnCloseBoard.click();
        btnCloseConfirm.click();
        btnDeleteBoard.click();
        btnDeleteBoardConfirm.click();
        return new BoardsPage(driver);
    }
    public BoardsPage deleteBoard() {
        btnDots.click();
        btnCloseBoard.click();
        btnCloseConfirm.click();
        btnDeleteBoard.click();
        btnDeleteBoardConfirm.click();
        logo.click();
        pause(3000);
        return new BoardsPage(driver);
    }
}
