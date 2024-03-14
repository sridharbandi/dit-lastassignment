package uk.axone.dit;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import uk.axone.dit.fw.BaseTest;

public class ShoppingTest extends BaseTest{


    @Test
    public void shoppingTest() throws InterruptedException {
        test = report.createTest("Axone shopping test");

        //Launch the browser
        launchBrowser();
        Thread.sleep(2000);

        //Navigates to URL
        navigateToUrl();
        Thread.sleep(2000);

        //CLick new products in footer
        clickElement("new_products_linkText");
        Thread.sleep(2000);

        //Click on Blouse section
        clickElement("blouse_section_linkText");
        Thread.sleep(2000);

        //Choose white color
        clickElement("blouse_color_name");
        Thread.sleep(2000);

        //Add item to cart
        clickElement("add_to_cart_id");
        Thread.sleep(2000);

        //Get cart title
        WebElement lblTitle =  identifyElement("product_added_to_cart_xpath");
        Thread.sleep(2000);

        //Verify cart title
        Assert.assertTrue(lblTitle.getText().contains("Product successfully added to your shopping cart"));

        //Proceed to checkout
        clickElement("proceed_to_checkout_btn_css");
        Thread.sleep(2000);

        //Get total price
        WebElement lblTotalPrice =  identifyElement("total_price_id");
        Thread.sleep(2000);

        //Verify total price
        Assert.assertEquals(lblTotalPrice.getText(), "£ 34.00");

        //Click on proceed to checkout
        clickElement("proceed_to_signin_btn_css");
        Thread.sleep(2000);

        //Enter email
        typeText("email_address_txt_id", "sridhar.bandi@axone.uk");

        //Enter password
        typeText("password_txt_name", "password");

        //Click sign in
        clickElement("sign_in_btn_id");
        Thread.sleep(2000);

        //Proceed to shipment
        clickElement("proceed_to_shipping_name");
        Thread.sleep(2000);

        //Agree terms
        WebElement btnAgreeTerms = identifyElement("agree_terms_id");
        btnAgreeTerms.click();
        Thread.sleep(2000);

        //Proceed to payment
        clickElement("proceed_to_payment_name");
        Thread.sleep(2000);

        //Pay by check
        clickElement("payby_check_className");
        Thread.sleep(2000);

        //Select pound as currency
       // selectByVisibleText("select_currency_id", "Pound");
        //Thread.sleep(2000);

        //Confirm order
        clickElement("confirm_order_btn_xpath");
        Thread.sleep(2000);

        //Get Order text
        WebElement confirmationText =  identifyElement("order_confirmation_text_xpath");
        Thread.sleep(2000);

        //Store the order number
        String allText = confirmationText.getText();
        System.out.println(allText);
        String[] splitAllLines = allText.split("\n");
        String orderRef = "";
        for (String splitAllLine : splitAllLines) {
            if(splitAllLine.contains("order reference")){
                String[] splitOrderRef = splitAllLine.split(" ");
                orderRef = splitOrderRef[splitOrderRef.length -1];
                System.out.println(orderRef);
            }
        }
        Thread.sleep(2000);

        //Go to order history
        clickElement("order_history_css");
        Thread.sleep(2000);

        //Get order number
        WebElement orderNumber=  identifyElement("order_number_className");
        Thread.sleep(2000);

        //Verify order number
        Assert.assertTrue(orderRef.contains(orderNumber.getText()));
        Thread.sleep(2000);

    }

    @AfterMethod
    public void cleanUp(){
        report.flush();

        getDriver().quit();
    }
}
