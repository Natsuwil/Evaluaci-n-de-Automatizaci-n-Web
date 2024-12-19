package com.nttdata.steps;

import com.nttdata.page.Mystorepage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginSteps {

    private WebDriver driver;
    private WebDriverWait wait;

    public LoginSteps(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Ingresa el nombre de usuario en el campo correspondiente
     * @param username nombre de usuario
     */
    public void typeUser(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Mystorepage.userInput))
                .sendKeys(username);
    }

    /**
     * Ingresa la contraseña en el campo correspondiente
     * @param password contraseña
     */
    public void typePassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Mystorepage.passInput))
                .sendKeys(password);
    }

    /**
     * Hace clic en el botón de login
     */
    public void login() {
        wait.until(ExpectedConditions.elementToBeClickable(Mystorepage.loginButton))
                .click();
    }
}