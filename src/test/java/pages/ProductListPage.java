package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductListPage extends BaseClass {

    public ProductListPage(WebDriver driver){
        super(driver);
    }

    @FindAll(@FindBy(xpath ="//li[@class='productListContent-item']//a[contains(@class,'moria-ProductCard')]"))
    public List<WebElement> productCard;

    @FindAll(@FindBy(xpath = "//h3[@data-test-id='product-card-name']"))
    public List<WebElement> productCardName;

    public List<WebElement> productCardName() {
        return productCardName;
    }

    public WebElement getProductCard(int index){
        return productCard.get(index);
    }
}
