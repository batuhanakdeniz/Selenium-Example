package StepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static Util.configReader.getPassword;
import static Util.configReader.getUserName;
import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

public class LoginSteps {

    WebDriver driver = null;
    HomePage homePage;
    LoginPage loginPage;
    ProductListPage productListPage;
    ProductDetailPage productDetailPage;
    CartPage cartPage;
    WebDriverWait wait;
    String originalWindow;
    @Before
    public void initialize(){
       System.out.println("Step: browser is open");
       String projectPath = System.getProperty("user.dir");
       System.setProperty("webdriver.chrome.driver",projectPath + "/src/test/resources/Drivers/chromedriver");
       driver = new ChromeDriver();
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
       driver.manage().window().maximize();
       wait = new WebDriverWait(driver,Duration.ofSeconds(4));
       originalWindow = driver.getWindowHandle();
   }
    @After
    public void driverQuit(){
        driver.close();
        driver.quit();
    }

    @Given("Kullanıcı Hepsiburada.com sitesini ziyaret eder.")
    public void userVisitHomePage(){
        System.out.println("Step: Kullanıcı Hepsiburada.com sitesini ziyaret eder.");

        driver.get("https://www.hepsiburada.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        homePage = new HomePage(driver);

        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals("https://www.hepsiburada.com/",actualURL);
        Assert.assertTrue(homePage.logo().isDisplayed());
        Assert.assertTrue(homePage.searchInputHomePage().isDisplayed());
        Assert.assertTrue(homePage.searchButtonHomePage().isDisplayed());
        Assert.assertTrue(homePage.account().isDisplayed());
    }
    @And("Kullanıcı giriş işlemi yapılır.")
    public void userEntersUserNamePassword() throws IOException {
        System.out.println("Kullanıcı giriş işlemi yapılır.");

        Actions actionsToNavigate = new Actions(driver);
        actionsToNavigate.moveToElement(homePage.account()).build().perform();
        homePage.login().click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        loginPage = new LoginPage(driver);

        Assert.assertTrue("loginPage.signInPresentation().isDisplayed()",loginPage.signInPresentation().isDisplayed());
        Assert.assertTrue("loginPage.signUpPresentation().isDisplayed()",loginPage.signUpPresentation().isDisplayed());
        Assert.assertTrue("loginPage.inputUserName().isDisplayed()",loginPage.inputUserName().isDisplayed());
        Assert.assertTrue("loginPage.signInUserNameButton().isDisplayed()",loginPage.signInUserNameButton().isDisplayed());

        loginPage.inputUserName().sendKeys(getUserName());
        loginPage.signInUserNameButton().click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

        Assert.assertTrue("loginPage.inputPassword().isDisplayed()",loginPage.inputPassword().isDisplayed());
        Assert.assertTrue("loginPage.signInPasswordButton().isDisplayed()",loginPage.signInPasswordButton().isDisplayed());

        loginPage.inputPassword().sendKeys(getPassword());
        loginPage.signInPasswordButton().click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));

    }

    @And("Yönlendirmeden sonra anasayfada kullanıcı giriş işleminin yapıldığı doğrulanır")
    public void checkLogin(){
        System.out.println("Yönlendirmeden sonra anasayfada kullanıcı giriş işleminin yapıldığı doğrulanır");

        Assert.assertTrue("homePage.myAccount().isDisplayed()",homePage.myAccount().isDisplayed());
        Assert.assertEquals("Batuhan Akdeniz", homePage.myName().getText());
    }

    @When("Kullanıcı, burada satın almak istediği ürün için arama yapacaktır.")
    public void userSearchProduct(){
        System.out.println("Kullanıcı, burada satın almak istediği ürün için arama yapacaktır.");
        homePage.searchInputHomePage().sendKeys("Apple AirPods 2. Nesil Kulaklık");
        homePage.searchButtonHomePage().click();
   }

    @And("Kullanıcı, Arama sonucunda ekrana gelen ürün listesinden ürün seçer.")
    public void userSelectProductFromSearchedProductList(){
        System.out.println("Kullanıcı, Arama sonucunda ekrana gelen ürün listesinden ürün seçer.");
        productListPage = new ProductListPage(driver);

        List<WebElement> productCardNameList = productListPage.productCardName();
        int index = 99999;
        for(int i=0; i < productCardNameList.size();i++){
           System.out.println("CardName[" + i + "]: "+productCardNameList.get(i).getText());
            if(productCardNameList.get(i).getText().contains("Apple AirPods 2. Nesil Kulaklık")){
                index =i;
                break;
            }
        }
        Actions actionToClick = new Actions(driver);
        actionToClick.moveToElement(productListPage.getProductCard(index)).click().build().perform();
        wait.until(numberOfWindowsToBe(2));
        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
        newTab.remove(originalWindow);
        // change focus to new tab
        driver.switchTo().window(newTab.get(0));
   }

    @And("Seçilen ürün için 2 tane farklı satıcıdan ürün seçilip sepete eklenir.")
    public void userSelectTwoDifferentSupplier(){
        System.out.println("Seçilen ürün için 2 tane farklı satıcıdan ürün seçilip sepete eklenir.");
        productDetailPage = new ProductDetailPage(driver);

        productDetailPage.addToCart().get(0).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        productDetailPage.denyPopUpClose().click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));

        productDetailPage.addToCart().get(1).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        Actions actionToClick = new Actions(driver);
        actionToClick.moveToElement(productDetailPage.sepetimButton()).click().click().build().perform();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
   }
    @Then("Seçilen ürünün doğru olarak eklendiği ‘Sepetim’ sayfasında doğrulanmalıdır.")
    public void checkSelectedProductsInCart(){
        System.out.println("Seçilen ürün için 2 tane farklı satıcıdan ürün seçilip sepete eklenir.");
        cartPage = new CartPage(driver);
        Assert.assertEquals("https://checkout.hepsiburada.com/sepetim",driver.getCurrentUrl());
        Assert.assertEquals(2,cartPage.productsName().size());
        Assert.assertTrue("Ürün Aynı mı kontrolü",cartPage.productsName().get(0).getText().contains("Apple AirPods 2. Nesil Kulaklık"));
        Assert.assertTrue("Ürün Aynı mı kontrolü",cartPage.productsName().get(0).getText().contains("Apple AirPods 2. Nesil Kulaklık"));
        Assert.assertEquals(2,cartPage.productsMerchantName().size());
        Assert.assertNotEquals("Satıcılar Farklı",cartPage.productsMerchantName().get(0).getText(),cartPage.productsMerchantName().get(1).getText());
        Assert.assertEquals(2,cartPage.productsInCart().size());

   }
}