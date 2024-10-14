package tests;

import dto.UserDTO;
import manager.ApplicationManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProfileAndVisibilityPage;

public class ProfileTests extends ApplicationManager {

    UserDTO user = UserDTO.builder()
            .email("z0559882272@gmail.com")
            .password("Mmar123456$")
            .build();

    ProfileAndVisibilityPage profileAndVisibilityPage;

    @BeforeMethod
    public void loginBeforeProfile() {
        HomePage homePage = new HomePage(getDriver());
        profileAndVisibilityPage = homePage.clickBtnLogin()
                .typeEmail(user)
                .typePassword(user)
                .goToProfileAndVisibility();
    }


    @Test
    public void changeProfilePhotoPositiveTest() {
        profileAndVisibilityPage.changeAvatar("cat3.jpeg");
    }
}
