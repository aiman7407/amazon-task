import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;
import utils.BaseTest;

public class TestCases extends BaseTest {


    @Test(priority = 0,description = "")
    public void cantLoginWithInvalidCredentials() throws Exception {

        //actions
        MainPage mainPage=new MainPage();
        mainPage.openLoginScreen();
        mainPage.writeEmail("notexistsemail9234@email.com");
        mainPage.clickSubmitEmail();
        //assertions
        Assert.assertEquals(mainPage.getEmailValidationErrorMessage(),"حدث خطأ\n" +
                "لا يمكننا العثور على حساب بعنوان البريد الإلكتروني هذا.");
    }




    @Test(priority = 1)
    public void addItemToCart() throws Exception {
        MainPage mainPage= new MainPage();
        mainPage.selectTodayDeals();
        mainPage.openCategory();
        mainPage.selectItem();
        mainPage.openCountDropDown();
        mainPage.selectCount();
        mainPage.clickAddToCartButton();
        double expectedValue=Double.parseDouble(mainPage.getPrice())*2;
        Assert.assertEquals(mainPage.getPriceFromCartScreen(),expectedValue);


    }
}


