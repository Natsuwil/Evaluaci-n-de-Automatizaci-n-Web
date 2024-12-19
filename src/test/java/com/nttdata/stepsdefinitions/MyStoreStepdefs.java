package com.nttdata.stepsdefinitions;

import com.nttdata.steps.ProductValidationSteps;
import com.nttdata.steps.LoginSteps;
import com.nttdata.page.Mystorepage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class MyStoreStepdefs {
    private WebDriver driver;
    private LoginSteps loginSteps;
    private ProductValidationSteps validationSteps;
    private WebDriverWait wait;

    @Given("estoy en la pagina de la tienda")
    public void estoyEnLaPaginaDeLaTienda() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://qalab.bensg.com/store/es/");

        loginSteps = new LoginSteps(driver);
        validationSteps = new ProductValidationSteps(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @And("logeo con el usuario: {string} y clave: {string}")
    public void logeoConElUsuarioYClave(String username, String password) {
        wait.until(ExpectedConditions.elementToBeClickable(Mystorepage.login)).click();
        loginSteps.typeUser(username);
        loginSteps.typePassword(password);
        loginSteps.login();
    }

    @When("navego a la categoria {string} y subcategoria {string}")
    public void navegoALaCategoriaYSubcategoria(String category, String subcategory) {
        wait.until(ExpectedConditions.elementToBeClickable(Mystorepage.clothesButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(Mystorepage.menButton)).click();
    }

    @And("agrego {int} unidades del primer producto al carrito")
    public void agregoUnidadesDelPrimerProductoAlCarrito(int quantity) {
        wait.until(ExpectedConditions.elementToBeClickable(Mystorepage.firstProduct)).click();
        wait.until(ExpectedConditions.elementToBeClickable(Mystorepage.quantityInput))
                .sendKeys(String.valueOf(quantity));
        wait.until(ExpectedConditions.elementToBeClickable(Mystorepage.addToCartButton)).click();
    }

    @Then("valio en el popup la confirmacion del producto agregado")
    public void valioEnElPopupLaConfirmacionDelProductoAgregado() {
        validationSteps.validateProductAddedPopup();
    }

    @And("valido en el popup que el monto total sea calculado correctamente")
    public void validoEnElPopupQueElMontoTotalSeaCalculadoCorrectamente() {
        validationSteps.validateTotalPriceInPopup(2); // 2 es la cantidad de productos
    }

    @When("finalizo la compra")
    public void finalizoLaCompra() {
        wait.until(ExpectedConditions.elementToBeClickable(Mystorepage.checkoutButton)).click();
    }

    @Then("valido el titulo de la pagina del carrito")
    public void validoElTituloDeLaPaginaDelCarrito() {
        validationSteps.validateCartPageTitle();
    }

    @And("vuelvo a validar el calculo de precios en el carrito")
    public void vuelvoAValidarElCalculoDePreciosEnElCarrito() {
        validationSteps.validateFinalCartPrices(2); // 2 es la cantidad de productos
    }

    @Then("valido que se muestre mensaje de error de login")
    public void validoQueSeMuestreMensajeDeErrorDeLogin() {
        validationSteps.validateLoginError();
    }

    @Then("valido que se muestre mensaje de categoria no encontrada")
    public void validoQueSeMuestreMensajeDeCategoriaNoEncontrada() {
        validationSteps.validateCategoryNotFound();
    }

    @After
    public void cerrarDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
