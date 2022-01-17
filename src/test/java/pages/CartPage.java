package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BaseClass{

    public  CartPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//section[@id='onboarding_item_list']//ul[contains(@class,'item_list')]/li")
    public List<WebElement> productsInCart;

    @FindBy(xpath = "//div[contains(@class,'product_properties')]/div[contains(@class,'product_name')]/a")
    public List<WebElement> productsName;

    @FindBy(xpath = "//div[contains(@class,'product_detail')]/div[contains(@class,'merchant_name')]/span/a")
    public List<WebElement> productsMerchantName;


    public List<WebElement> productsInCart() {
        return productsInCart;
    }
    public List<WebElement> productsName() {
        return productsName;
    }
    public List<WebElement> productsMerchantName() {
        return productsMerchantName;
    }

}
