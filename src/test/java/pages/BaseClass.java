package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BaseClass {
    public static WebDriver driver;
    public BaseClass(WebDriver driver){
        BaseClass.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
