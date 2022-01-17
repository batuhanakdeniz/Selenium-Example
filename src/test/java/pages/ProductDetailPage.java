package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductDetailPage extends BaseClass{

    public ProductDetailPage(WebDriver driver){
        super(driver);
    }

    @FindAll
    (@FindBy(xpath = "//div[@class='marketplace-list']//button[./span[text()='Sepete Ekle']]"))
    public List<WebElement> addToCart;

    @FindBy(css = "h1#product-name")
    public WebElement productNameTitle;

    @FindBy(xpath = "//div[@id='notification']/div[@id='pcwrapper']/div[@class='popup']//a[@class='deny-text']")
    public WebElement denyText;

    @FindBy(xpath = "//div[contains(@id,'AddToCart')]//div[contains(@class,'checkoutui-AddToCart')]//a[contains(@class,'checkoutui-Modal')]")
    public WebElement denyPopUpClose;

    @FindBy(xpath = "//a[./span[@id='shoppingCart']]")
    public WebElement sepetimButton;

    @FindBy(css = "span#cartItemCount")
    public WebElement cartItemCount;

    @FindBy(xpath = "//div[contains(@class,'SalesFrontCash')]/button[normalize-space(text())='Sepete git']")
    public WebElement goToCartButton;



    public List<WebElement> addToCart() {
        return addToCart;
    }
    public WebElement productNameTitle() {
        return productNameTitle;
    }
    public WebElement denyText() {
        return denyText;
    }
    public WebElement denyPopUpClose() {
        return denyPopUpClose;
    }
    public WebElement sepetimButton() {
        return sepetimButton;
    }
    public WebElement cartItemCount() {
        return cartItemCount;
    }
    public WebElement goToCartButton() {
        return goToCartButton;
    }
}
