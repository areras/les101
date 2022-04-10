package llll;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignUpPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By emailField = By.xpath("//input[@id='signupform-email']");
    private final By emailBlock = By.xpath("//input[@id='signupform-email']/parent::div");
    private final By registerButton = By.xpath("//button[@id='signup_btn']");
    private final By emailErrorField = By.xpath("//input[@id='signupform-email']/following-sibling::p");
    private String email;

    SignUpPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));
    }

    public void typeEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void registerWithoutLogin(String email) {
        this.email = email;
        this.typeEmail(email);
        driver.findElement(registerButton).click();
        if (!email.matches("\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,6}")) {
            wait.until(ExpectedConditions.attributeContains(emailBlock, "class", "form-group field-signupform-email required has-error"));
        }
        new SignUpPage(driver);
    }

    public String getTextError() {
        return driver.findElement(emailErrorField).getText();
    }
}