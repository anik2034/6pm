package pages;

import locators.SearchResultsPageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static locators.SearchResultsPageLocators.*;

public class SearchResultsPage extends BasePage {

    private By names = By.xpath(namesXPath);
    private By defaultPricesFilters = By.xpath(defaultPricesFiltersXPath);
    private By prices = By.xpath(pricesXPath);
    private By item = By.xpath(itemXPath);
    private By brand = By.xpath(brandXPath);
    private By noResults = By.xpath(noResultsXPath);
    private By defaultPricesNumber = By.xpath(defaultPricesNumberXPath);
    private By priceFilterSearch = By.xpath(priceFilterSearchXPath);


    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getAllNames() {
        List<WebElement> elements = getAllElements(names);
        List<String> elementsNames = new ArrayList<String>();
        for (int i = 0; i < elements.size(); i++) {
            elementsNames.add(elements.get(i).getText());
        }
        return elementsNames;
    }



    public void searchPriceFilter(String number) {
        type(priceFilterSearch, number);
    }

    public List<Double> getDefaultPricesNumber() {
        List<WebElement> elements = getAllElements(defaultPricesNumber);
        List<Double> elementsNames = new ArrayList<Double>();
        for (int i = 0; i < elements.size(); i++) {
            String price = elements.get(i).getText();
            price = price.substring(1, price.indexOf(" "));
            elementsNames.add(Double.parseDouble(price));
        }
        return elementsNames;

    }

    public List<String> getDefaultPricesString() {
        List<WebElement> elements = getAllElements(defaultPricesNumber);
        List<String> elementsNames = new ArrayList<>();
        for (int i = 0; i < elements.size(); i++) {
            String price = elements.get(i).getText();

            elementsNames.add(price);
        }
        return elementsNames;

    }


    public SearchResultsPage clickFilter(WebElement w) {
        clickElement(w);
        return new SearchResultsPage(driver);
    }

    public ItemDetailsPage clickItem(WebElement w) {
        clickElement(w);
        return new ItemDetailsPage(driver);
    }


    public boolean hasNoResults() {
        return isElementDisplayed(noResults);
    }

    public String getBrand() {
        return getText(brand);
    }

    public List<Double> getAllPrices() {
        List<WebElement> elements = getAllElements(prices);
        List<Double> elementsPrices = new ArrayList<Double>();
        for (int i = 0; i < elements.size(); i++) {
            String price = elements.get(i).getText().trim();
            elementsPrices.add(Double.parseDouble(price.substring(1)));
        }
        return elementsPrices;
    }

    public List<WebElement> getAllPricesFilters() {
        WebElement ul = getElement(defaultPricesFilters);
        return ul.findElements(By.tagName("li"));

    }



    public boolean arePriceFiltersDispleyed() {
        return isElementDisplayed(defaultPricesFilters);
    }

    public WebElement getFirstItem() {
        return getElement(item);
    }


}
