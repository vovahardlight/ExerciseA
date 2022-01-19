import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class Exercise {

    @Test
    public void Test() {
        WebDriverManager.chromedriver().browserVersion("4.1.0").setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.navigate().to("http://demowebshop.tricentis.com/");
        WebElement menus = driver.findElement(By.xpath("//ul[@class='top-menu']"));

        WebElement menu = menus.findElement(By.xpath(".//a[@href='/computers']"));
        Actions a = new Actions(driver);
        a.moveToElement(menu).perform();
        WebDriverWait wait = new WebDriverWait(driver, 4);
        WebElement subMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[@href='/desktops'])[1]")));
        a.moveToElement(subMenu).click().perform();

        WebElement dropDown = driver.findElement(By.xpath("//select[@name='products-pagesize']"));
        Select select1 = new Select(dropDown);
        select1.selectByVisibleText("4");

        WebElement dDownSort = driver.findElement(By.xpath("//select[@name='products-orderby']"));
        Select select2 = new Select(dDownSort);
        select2.selectByVisibleText("Price: High to Low");

        String leadXpath = "//span[contains(@class,'price')]";
        String xpathForButton = "./following::input";

        List<WebElement> spans = driver.findElements(By.xpath(leadXpath));
        WebElement maxSpan = spans.get(0);

        for (int i = 0; i < spans.size(); i++) {
            WebElement currentSpan = spans.get(i);
            String textValue = currentSpan.getText();
            double currentValue = Double.parseDouble(textValue);
            double maxValue = Double.parseDouble(maxSpan.getText());

            if (currentValue > maxValue) {
                maxSpan = currentSpan;
            }
        }
        maxSpan.findElement(By.xpath(xpathForButton)).click();

        WebElement buy = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class,'price')]/following::input[@class='button-1 add-to-cart-button']")));
        buy.click();
        WebElement shopingCart = driver.findElement(By.xpath("//span[text()='Shopping cart']"));
        shopingCart.click();

        WebElement checkout = driver.findElement(By.xpath("//button[@name='checkout']"));

        Assert.assertEquals("Checkout", checkout.getAccessibleName());

    }

    @Test
    public void Test2() {
        WebDriverManager.chromedriver().browserVersion("4.1.0").setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("http://demowebshop.tricentis.com/build-your-own-expensive-computer-2");

        WebElement radioFast = driver.findElement(By.xpath("//input[@id='product_attribute_74_5_26_82']"));
        WebElement radio8Gb = driver.findElement(By.xpath("//input[@id='product_attribute_74_6_27_85']"));
        WebElement checkImageViewer = driver.findElement(By.xpath("//input[@id='product_attribute_74_8_29_88']"));
        WebElement checkOfficeSuite = driver.findElement(By.xpath("//input[@id='product_attribute_74_8_29_89']"));
        WebElement checkOtherOfficeSuite = driver.findElement(By.xpath("//input[@id='product_attribute_74_8_29_90']"));
        WebElement checkoutButton = driver.findElement(By.xpath("//input[@id='add-to-cart-button-74']"));

        WebElement subPriceComputer = driver.findElement(By.xpath("//span[@itemprop='price']"));
        String textXpathSubPrice = subPriceComputer.getText();
        double valueSubPrice = Double.parseDouble(textXpathSubPrice);

        radioFast.click();
        String xPathPriceOfProcessor = "./following-sibling::label[@for='product_attribute_74_5_26_82']";
        String textOfProc = radioFast.findElement(By.xpath(xPathPriceOfProcessor)).getText();
        String amountBetweenBrackets = textOfProc.substring(textOfProc.indexOf("+") + 1, textOfProc.indexOf("]"));
        double priceProc = Double.parseDouble(amountBetweenBrackets);

        radio8Gb.click();
        String xPathPriceOfRam = "./following-sibling::label[@for='product_attribute_74_6_27_85']";
        String textOfRam = radio8Gb.findElement(By.xpath(xPathPriceOfRam)).getText();
        String amountRamBetweenBrackets = textOfRam.substring(textOfRam.indexOf("+") + 1, textOfRam.indexOf("]"));
        double priceRam = Double.parseDouble(amountRamBetweenBrackets);

        checkImageViewer.click();
        String xPathPriceViewer = "./following-sibling::label[@for='product_attribute_74_8_29_88']";
        String textOfViewer = checkImageViewer.findElement(By.xpath(xPathPriceViewer)).getText();
        String amountViewerBetweenBrackets = textOfViewer.substring(textOfViewer.indexOf("+") + 1, textOfViewer.indexOf("]"));
        double priceViewer = Double.parseDouble(amountViewerBetweenBrackets);

        checkOfficeSuite.click();
        String xPathPriceSuite = "./following-sibling::label[@for='product_attribute_74_8_29_89']";
        String textOfSuite = checkOfficeSuite.findElement(By.xpath(xPathPriceSuite)).getText();
        String amountSuiteBetweenBrackets = textOfSuite.substring(textOfSuite.indexOf("+") + 1, textOfSuite.indexOf("]"));
        double priceSuite = Double.parseDouble(amountSuiteBetweenBrackets);

        checkOtherOfficeSuite.click();
        String xPathPriceOtherSuite = "./following-sibling::label[@for='product_attribute_74_8_29_90']";
        String textOfOtherSuite = checkOtherOfficeSuite.findElement(By.xpath(xPathPriceOtherSuite)).getText();
        String amountOtherSuiteBetweenBrackets = textOfOtherSuite.substring(textOfOtherSuite.indexOf("+") + 1, textOfOtherSuite.indexOf("]"));
        double priceOtherSuite = Double.parseDouble(amountOtherSuiteBetweenBrackets);

        WebElement valueInCart = driver.findElement(By.xpath("//a[@href='/cart'][1]/child::span[@class='cart-qty']"));
        final String cartText = valueInCart.getText();
        checkoutButton.click();

        char[] first = cartText.toCharArray();
        String numFirst = String.valueOf(first[1]);
        int numberFirst = Integer.parseInt(numFirst);

        WebDriverWait wait = new WebDriverWait(driver, 4);
        int finalNumberOfCart = numberFirst + 1;
        String numberLikeText = String.valueOf(finalNumberOfCart);
        String textOfValueInCart = "(" + numberLikeText + ")";

        wait.until(ExpectedConditions.textToBePresentInElement(valueInCart, textOfValueInCart));
        String text = valueInCart.getText();
        char[] numbers = text.toCharArray();
        String num = String.valueOf(numbers[1]);
        int number = Integer.parseInt(num);

        boolean iteme = numberFirst + 1 == number;
        Assert.assertTrue(iteme);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementsByClassName('cart-label')[0].click()");

        double amountOfAllComputer = valueSubPrice + priceProc + priceRam + priceViewer + priceSuite + priceOtherSuite;
        WebElement spanPrice = driver.findElement(By.xpath("//span[@class='product-unit-price']"));
        String textSpanPrice = spanPrice.getText();
        double valueTextSpanPrice = Double.parseDouble(textSpanPrice);

        Assert.assertTrue(amountOfAllComputer == valueTextSpanPrice);

        WebElement checkboxRemove = driver.findElement(By.xpath("//td/child::input[@type='checkbox']"));
        checkboxRemove.click();
        wait.until(ExpectedConditions.elementToBeSelected(By.xpath("//td/child::input[@type='checkbox']")));

        WebElement buttonUpdateCart = driver.findElement(By.xpath("//input[@name='updatecart']"));
        buttonUpdateCart.click();
        
    }
}






