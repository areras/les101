package llll;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class SignUpPageTest {
    private WebDriver driver;
    private SignUpPage signUpPage;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
        driver.manage().window().maximize();

        driver.get("https://diary.ru/user/registration");

        signUpPage = new SignUpPage(driver);
    }

    @Test
    public void signUpWithIncorrectEmail() {
        signUpPage.registerWithoutLogin("6667666");
        String textError = signUpPage.getTextError();
        Assert.assertEquals("Значение «E-mail» не является правильным email адресом.", textError);
    }

    @Test
    public void signUpWithCorrectEmail() {
        signUpPage.registerWithoutLogin("bububndic3@mail.ru");
        String textError = signUpPage.getTextError();
        Assert.assertEquals("", textError);
    }

    @After
    public void tearDown() {
        driver.close();
    }
}