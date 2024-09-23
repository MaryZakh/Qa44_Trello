package tests;

import dto.UserDTO;
import manager.ApplicationManager;
import org.testng.annotations.Test;
import pages.HomePage;

public class LoginTests extends ApplicationManager {

    UserDTO user = UserDTO.builder()
            .email("z0559882272@gmail.com")
            .password("Mmar123456$")
            .build();

    @Test
    public void loginTest() {
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin()
                .typeEmail(user)
                .typePassword(user);


    }

}
