package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

import static locators.CartPageLocators.*;


public class CartPage extends BasePage{
    public CartPage(WebDriver driver) {
        super(driver);
    }

    private By brand = By.xpath(brandXPath);

    private By name = By.xpath(nameXPath);

    private By price = By.xpath(priceXPath);

    private By details = By.xpath(detailsXPath);

    private By remove = By.xpath(removeXPath);

    private By noResults = By.xpath(noResultsXPath);

    private By selectBy = By.xpath(selectXPath);

    private By count = By.xpath(countXpath);




    public String getBrand(){
        return getText(brand);
    }

    public String getName(){
        return getText(name);
    }

    public String getPrice(){
        return getText(price);
    }

    public void remove(){
        click(remove);
    }

    public boolean isNoResultsDisplayed(){
        return isElementDisplayed(noResults);
    }


    public List<String> details(){
      List<WebElement> elements  = getAllElements(details);
      List<String> results = new ArrayList<>();
      for(WebElement w : elements){
          results.add(w.getText());
      }
      return results;
    }

    public String select(int index){
        Select select = new Select(getElement(selectBy));
        select.selectByIndex(index);
       return select.getFirstSelectedOption().getText();
    }

    public int getSelectLength(){
        Select select = new Select(getElement(selectBy));
        return select.getOptions().size();
    }

    public String getCount() {
      String text =   getText(count);
      text = text.substring(text.indexOf("(")+1, text.indexOf("I")-1);
      return text;
    }







}
