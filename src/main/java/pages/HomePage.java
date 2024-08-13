package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.AbstractSeleniumActions;

import java.util.List;

public class MainPage extends AbstractSeleniumActions {

    private final By loginScreenButton=By.id("nav-link-accountList");
    private final By emailTextField=By.id("ap_email");
    private final By emailValidationErrorMessage=By.id("auth-error-message-box");
    private final By submitEmailButton=By.id("continue");


    private final By todayDealsButton=By.linkText("عروض اليوم");

    private final By selectedCategory= By.cssSelector(".a-carousel-card:nth-child(4) button");

    private final By selectedItem= By.xpath("/html[1]/body[1]/div[1]/div[1]/div[21]/div[1]/div[1]/div[1]/div[2]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/a[1]/div[1]/div[1]/div[1]");

    private final By itemCountDropdown= By.id("a-autoid-0");
    private final By itemCount= By.cssSelector("#quantity_1");

    private  final By addToCartButton= By.id("submit.add-to-cart");
    private  final By confirmAddToCart= By.linkText(" لا، شكراً ");

    private final By priceText=By.className("a-price-whole");
    private final By priceInCartScreen=By.className("a-price-whole");

    public void openLoginScreen() throws Exception {
        clickOnElement(loginScreenButton,20);
    }


    public void writeEmail(String email) throws Exception {
        typeText(emailTextField,email,20);
    }


    public void clickSubmitEmail() throws Exception {
        clickOnElement(submitEmailButton,20);
    }

    public String  getEmailValidationErrorMessage() throws Exception {
       return getTextFromElement(emailValidationErrorMessage,20);
    }



    public void selectTodayDeals() throws Exception {
        clickOnElement(todayDealsButton,20);
    }

    public void openCategory() throws Exception
    {
       clickOnElement(selectedCategory,20);

    }

    public void selectItem() throws Exception {

        clickOnElement(selectedItem,20);
    }

    public void openCountDropDown()throws Exception{
        clickOnElement(itemCountDropdown,20);
    }
    public void selectCount() throws Exception {
        clickOnElement(itemCount,20);
    }

    public void clickAddToCartButton()throws Exception{

        clickOnElement(addToCartButton,20);
    }


    public void clickConfirmAddToCart()throws Exception{

        clickOnElement(confirmAddToCart,20);
    }

    public String getPrice() throws Exception {
     return   getTextFromElement(priceText,20);
    }

    public String getPriceFromCartScreen() throws Exception {
        return   getTextFromElement(priceInCartScreen,20);
    }
}

