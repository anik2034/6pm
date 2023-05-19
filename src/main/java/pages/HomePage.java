package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static locators.HomePageLocators.searchBarxpath;
import static locators.HomePageLocators.searchButtonxpath;

public class HomePage extends BasePage{

    private By searchBar = By.xpath(searchBarxpath);
    private By searchButton = By.xpath(searchButtonxpath);
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void setSearchString(String search){
        type(searchBar, search);
    }
    public void clickSearchButton(){
        click(searchButton);
    }


    public SearchResultsPage goSearchResultsPage(String search){
        setSearchString(search);
        clickSearchButton();
        return new SearchResultsPage(driver);

    }


}

