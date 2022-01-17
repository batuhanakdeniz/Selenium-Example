package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BaseClass{

    public LoginPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//div[@role='presentation']/div[normalize-space(text())='Giriş yap']")
    public WebElement signInPresentation;

    @FindBy(xpath = "//div[@role='presentation']/div[normalize-space(text())='Üye ol']")
    public WebElement signUpPresentation;

    @FindBy(css = "input#txtUserName")
    public WebElement inputUserName;

    @FindBy(css = "button#btnLogin")
    public WebElement signInUserNameButton;

    @FindBy(css = "input#txtPassword")
    public WebElement inputPassword;

    @FindBy(css = "button#btnEmailSelect")
    public WebElement signInPasswordButton;


    public WebElement signInPresentation(){
        return signInPresentation;
    }
    public WebElement signUpPresentation(){
        return signUpPresentation;
    }
    public WebElement inputUserName(){
        return inputUserName;
    }
    public WebElement signInUserNameButton(){
        return signInUserNameButton;
    }
    public WebElement inputPassword(){
        return inputPassword;
    }
    public WebElement signInPasswordButton(){
        return signInPasswordButton;
    }
}
