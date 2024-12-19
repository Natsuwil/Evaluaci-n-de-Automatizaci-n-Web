package com.nttdata.steps;

import com.nttdata.page.Mystorepage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;
import java.time.Duration;

public class ProductValidationSteps {

    private WebDriver driver;
    private WebDriverWait wait;

    public ProductValidationSteps(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Validar el popup de confirmación de producto agregado
     */
    public void validateProductAddedPopup() {
        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(Mystorepage.popupConfirmation));
        Assert.assertTrue("El popup de confirmación no se mostró", popup.isDisplayed());
        String popupText = popup.getText();
        Assert.assertTrue("El mensaje de confirmación es incorrecto",
                popupText.contains("Producto añadido correctamente"));
    }

    /**
     * Validar el cálculo del precio total en el popup
     * @param quantity cantidad de productos
     */
    public void validateTotalPriceInPopup(int quantity) {
        WebElement priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(Mystorepage.productPrice));
        WebElement totalElement = wait.until(ExpectedConditions.visibilityOfElementLocated(Mystorepage.totalPrice));

        double unitPrice = getNumericPrice(priceElement.getText());
        double totalPrice = getNumericPrice(totalElement.getText());
        double expectedTotal = unitPrice * quantity;

        Assert.assertEquals("El cálculo del precio total es incorrecto",
                expectedTotal, totalPrice, 0.01);
    }

    /**
     * Validar el título de la página del carrito
     */
    public void validateCartPageTitle() {
        WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(Mystorepage.cartTitle));
        Assert.assertTrue("El título del carrito no es visible", titleElement.isDisplayed());
        Assert.assertEquals("El título de la página no es correcto",
                "CARRITO DE COMPRA", titleElement.getText().toUpperCase());
    }

    /**
     * Validar el cálculo final de precios en el carrito
     * @param quantity cantidad de productos
     */
    public void validateFinalCartPrices(int quantity) {
        WebElement subtotalElement = wait.until(ExpectedConditions.visibilityOfElementLocated(Mystorepage.subtotalPrice));
        WebElement totalElement = wait.until(ExpectedConditions.visibilityOfElementLocated(Mystorepage.finalTotalPrice));

        double subtotal = getNumericPrice(subtotalElement.getText());
        double total = getNumericPrice(totalElement.getText());

        // Validar que el total incluye el subtotal más cualquier cargo adicional
        Assert.assertTrue("El total final no es correcto", total >= subtotal);
    }

    /**
     * Método auxiliar para extraer el valor numérico de un precio
     * @param priceText texto del precio (ej: "$99.99")
     * @return valor numérico del precio
     */
    private double getNumericPrice(String priceText) {
        return Double.parseDouble(priceText.replace("$", "")
                .replace(",", "")
                .trim());
    }

    /**
     * Validar mensaje de error en caso de credenciales inválidas
     */
    public void validateLoginError() {
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(Mystorepage.loginError));
        Assert.assertTrue("No se mostró el mensaje de error de login",
                errorMessage.isDisplayed());
    }

    /**
     * Validar mensaje de categoría no encontrada
     */
    public void validateCategoryNotFound() {
        WebElement notFoundMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(Mystorepage.categoryNotFound));
        Assert.assertTrue("No se mostró el mensaje de categoría no encontrada",
                notFoundMessage.isDisplayed());
    }
}

