package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static locators.ItemDetailsPageLocators.*;


public class ItemDetailsPage extends BasePage{
    public ItemDetailsPage(WebDriver driver) {
        super(driver);
    }


    private By sizes = By.xpath(sizesXPath);

    private By colors = By.xpath(colorsXPath);

    private By options = By.xpath(optionsXPath);
    private By sizesNames = By.xpath(sizesNamesXPath);

    private By colorsNames = By.xpath(colorsNamesXPath);

    private By optionsNames = By.xpath(optionsNamesXPath);

    private By addToCart = By.xpath(addToCartXPath);

    private By viewCart = By.xpath(viewCartXPath);

    private By brand = By.xpath(brandXPath);

    private By name = By.xpath(nameXPath);

    private By price = By.xpath(priceXPath);








    public List<WebElement> getAllSizes(){
        return getAllElements(sizes);
    }

    public List<WebElement> getAllColor(){
        return getAllElements(colors);
    }

    public List<WebElement> getAllOptions(){
        return getAllElements(options);
    }

    public String getBrand(){
       return  getText(brand);
    }

    public String getName(){
        return  getText(name);
    }

    public String getPrice(){
        return  getElement(price).getAttribute("aria-label").toString();
    }


    public void scroll(){
        scrollBy(driver, 0,400);
    }

    public List<String> getAllColorsNames() {
        List<WebElement> elements = getAllElements(colorsNames);
        List<String> colors = new ArrayList<String>();
        for (int i = 0; i < elements.size(); i++) {
            colors.add(elements.get(i).getText());
        }
        return colors;
    }

    public List<String> getAllSizesNames() {
        List<WebElement> elements = getAllElements(sizesNames);
        List<String> colors = new ArrayList<String>();
        for (int i = 0; i < elements.size(); i++) {
            colors.add(elements.get(i).getText());
        }
        return colors;
    }

    public List<String> getAllOptionsNames() {
        List<WebElement> elements = getAllElements(optionsNames);
        List<String> colors = new ArrayList<String>();
        for (int i = 0; i < elements.size(); i++) {
            colors.add(elements.get(i).getText());
        }
        return colors;
    }

    public boolean AreColorsDisplayed(){
      return  isElementDisplayed(colorsNames);
    }

    public boolean AreSizesDisplayed(){
        return  isElementDisplayed(sizes);
    }

    public boolean AreOptionsDisplayed(){
        return  isElementDisplayed(optionsNames);
    }





    public CartPage addAndViewCart(){
        click(addToCart);
        click(viewCart);
        return new CartPage(driver);
    }





}
