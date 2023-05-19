import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.SearchResultsPage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SearchResultsPageTest extends BaseTest {


    private SearchResultsPage applyFilter(
            SearchResultsPage results, int count) {
        List<WebElement> filtered = results.getAllPricesFilters();

        for (int i = 0; i < count; i++) {
            filtered = results.getAllPricesFilters();
            results = results.clickFilter(filtered.get(i));
            //Thread sleep was the only way to make it stop, to be able to apply next filter
            // I have tried everything else, nothing worked
            //all my methods used here also have waits in their implementation, but in this case they did not assist
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        }
        return results;


    }

    private boolean isInRange(Double price, List<Double> list, int count) {
        boolean inRange = false;
        for (int i = 0; i < count; i++) {
            if (count == 12 && list.get(i) == 200) inRange = inRange || price >= list.get(i);
            else inRange = inRange || price <= list.get(i);
        }
        return inRange;
    }

    private String getResponse(List<Double> list, Double price, int count) {
        String response = price + " does not match range ";
        for (int i = 0; i < count; i++) {
            if (i < count - 1) response += "or " + list.get(i);

            else response += ", " + list.get(i);
        }
        return response;
    }

    @DataProvider
    public Object[][] numberOfFilterCombinations() {

        return SearchDataProvider.numberOfFilterCombinations;
    }

    @Test(dataProvider = "numberOfFilterCombinations")
    private void applyMultiplePriceFilters(int count) {
        SearchResultsPage results = homePage.goSearchResultsPage("shirt");
        SoftAssert sa = new SoftAssert();
        results = applyFilter(results, count);
        List<Double> prices = results.getAllPrices();
        List<Double> filteredPrices = results.getDefaultPricesNumber();
        for (Double price : prices) {
            sa.assertTrue(isInRange(price, filteredPrices, count), getResponse(filteredPrices, price, count));
        }
        sa.assertAll();
    }


    private boolean containsPrice(List<String> list, String price) {
        boolean result = true;

        for (int i = 0; i < list.size(); i++) {
            if (!(list.get(i).toLowerCase().contains(price.toLowerCase()))) {
                result = false;
                break;
            }
        }
        return result;
    }







    @DataProvider
    public  Object[][] nonEmptyResults(){

     return SearchDataProvider.nonEmptyResults;
    }

    @DataProvider
    public  Object[][] emptyResults(){

        return SearchDataProvider.emptyResults;
    }


    @Test(dataProvider = "nonEmptyResults")
    public void nonEmptyPriceSearchFilters(String price){

        SearchResultsPage results = homePage.goSearchResultsPage("hoodie");
        SoftAssert sa = new SoftAssert();
        results.searchPriceFilter(price);
        List<String> filteredPrices = results.getDefaultPricesString();
        sa.assertTrue(containsPrice(filteredPrices,price),filteredPrices +"\n not all ranges contain " + price);
        sa.assertAll();


    }

    @Test(dataProvider = "emptyResults")
    public void emptyPriceSearchFilters(String price){

        SearchResultsPage results = homePage.goSearchResultsPage("pants");
        SoftAssert sa = new SoftAssert();
        results.searchPriceFilter(price);





            sa.assertTrue(!results.arePriceFiltersDispleyed(),price + " search is not empty");
            sa.assertAll();



    }




}
