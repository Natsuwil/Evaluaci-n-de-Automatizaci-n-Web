package com.nttdata.page;

import org.openqa.selenium.By;

public class Mystorepage {

    public static By login = By.xpath("//*[@id=\"_desktop_user_info\"]/div/a/span");
    public static By userInput = By.xpath("//*[@id=\"field-email\"]");
    public static By passInput = By.xpath("//*[@id=\"field-password\"]");
    public static By loginButton = By.xpath("//*[@id=\"submit-login\"]");


    public static By clothesButton = By.xpath("//*[@id=\"category-3\"]/a");
    public static By menButton = By.xpath("//*[@id=\"subcategories\"]/ul/li[1]/h5/a");
    public static By firstProduct = By.xpath("//*[@id=\"js-product-list\"]/div[1]/div");
    public static By quantityInput = By.xpath("//*[@id=\"add-to-cart-or-refresh\"]/div[2]/div/div[1]");
    public static By addToCartButton = By.xpath("//*[@id=\"add-to-cart-or-refresh\"]/div[2]/div/div[2]/button");
    public static By checkoutButton = By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[2]/div/div/a");
    public static By confirmPurchaseButton = By.xpath("//*[@id=\"main\"]/div/div[2]/div[1]/div[2]/div/a");


    public static By popupConfirmation = By.id("myModalLabel");
    public static By productPrice = By.className("current-price-value");
    public static By totalPrice = By.className("product-total");
    public static By cartTitle = By.xpath("//h1[contains(text(),'Carrito de compra')]");
    public static By subtotalPrice = By.cssSelector(".cart-summary-line.cart-subtotal .value");
    public static By finalTotalPrice = By.cssSelector(".cart-summary-line.cart-total .value");
    public static By loginError = By.cssSelector(".alert.alert-danger");
    public static By categoryNotFound = By.cssSelector(".alert.alert-warning");

}