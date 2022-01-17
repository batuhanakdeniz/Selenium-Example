package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BaseClass{

    public  HomePage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//a[contains(@class,'OldHeader') and @title='Hepsiburada']")
    public WebElement Logo;

    @FindBy(css = "input[placeholder=\"Ürün, kategori veya marka ara\"]")
    public WebElement searchInputHomePage;

    @FindBy(css = "div.SearchBoxOld-buttonContainer")
    public WebElement searchButtonHomePage;

    @FindBy(css = "span[title=\"Giriş Yap\"]")
    public WebElement signInTitle;

    @FindBy(css = "div#myAccount")
    public WebElement account;

    @FindBy(css = "a#login")
    public WebElement login;

    @FindBy(css = "a[title='Hesabım'] span:nth-child(1)")
    public WebElement myAccount;

    @FindBy(css = "a[title='Hesabım'] span:nth-child(2)")
    public WebElement myName;

    @FindBy(xpath = "//a[text()='Çıkış Yap']")
    public WebElement logout;




    public WebElement logo() {
        return Logo;
    }
    public WebElement searchInputHomePage() {
        return searchInputHomePage;
    }
    public WebElement searchButtonHomePage() {
        return searchButtonHomePage;
    }
    public WebElement signInTitle() {
        return signInTitle;
    }
    public WebElement account() {
        return account;
    }
    public WebElement login() {
        return login;
    }
    public WebElement myAccount() {
        return myAccount;
    }
    public WebElement myName() {
        return myName;
    }
    public WebElement logout() {return logout; }


}
