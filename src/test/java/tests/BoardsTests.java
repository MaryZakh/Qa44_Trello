package tests;

import dataproviders.DataProviderBoards;
import dto.BoardDTO;

import manager.ApplicationManager;
import manager.TestNGListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.BoardsPage;
import pages.HomePage;
import pages.PersonalBoardPage;

import java.lang.reflect.Method;

import java.util.List;
import java.util.Random;

import static pages.BoardsPage.pause;

@Listeners(TestNGListener.class)

public class BoardsTests extends ApplicationManager {

//    UserDTO user = UserDTO.builder()
//            .email("z0559882272@gmail.com")
//            .password("Mmar123456$")
//            .build();

    BoardsPage boardsPage = new BoardsPage(getDriver());


    @BeforeMethod
    public void loginBeforeBoards() {
        HomePage homePage = new HomePage(getDriver());
        boardsPage = homePage.clickBtnLogin()
                .typeEmail(user)
                .typePassword(user);
    }


    @Test
    public void createBoardPositive(Method method) {
        //int i = (int) ((System.currentTimeMillis()/1000)%3600);
        int i = new Random().nextInt(1000) + 1000;

        BoardDTO board = BoardDTO.builder()
                .boardTitle("QA44-" + i)
                .build();

        logger.info(method.getName() + " starts with board title--> " + board.getBoardTitle());
        //HomePage homePage = new HomePage(getDriver());
        Assert.assertTrue(
//               homePage.clickBtnLogin()
//               .typeEmail(user)
//               .typePassword(user)
                boardsPage.typeBoardTitle(board)
                        .clickBtnCreateSubmitPositive()
                        .isTextInElementPresent_nameBoard(board.getBoardTitle()));

    }

    @Test
    public void createBoardNegative() {

        BoardDTO board = BoardDTO.builder()
                .boardTitle("   ")
                .build();

        Assert.assertFalse(boardsPage
                .typeBoardTitle(board)
                .clickBtnCreateSubmitNegative()
                .isElementClickable_btnCreateSubmit(), "element is clickable");

    }

    @Test(dataProvider = "DPFile_deleteBoardPositiveTest", dataProviderClass = DataProviderBoards.class)
    public void deleteBoardPositiveTest(BoardDTO board) {
//        int i = new Random().nextInt(1000) + 1000;
//
//        BoardDTO board = BoardDTO.builder()
//                .boardTitle("QA44-" + i)
//                .build();

        PersonalBoardPage personalBoardPage = boardsPage
                .typeBoardTitle(board)
                .clickBtnCreateSubmitPositive();

        if (personalBoardPage.isTextInElementPresent_nameBoard(board.getBoardTitle())) {
            Assert.assertTrue(personalBoardPage.deleteBoard(board)
                    .isTextPopUpPresent());
        } else {
            Assert.fail("board isn`t create");
        }

    }


    @Test
    public void deleteAllBoard() {
        pause(3000);
        List<WebElement> listBoars = getDriver().findElements(
                By.xpath("//li[@class='boards-page-board-section-list-item']"));
        System.out.println("size list --> " + listBoars.size());
        //boardsPage.clickElement2ListBoards().deleteBoard();
        for (int i = 0; i < listBoars.size()-2; i++) {
            boardsPage.clickElement2ListBoards().deleteBoard();
            pause(5000);
        }
    }
}

