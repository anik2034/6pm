import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.SearchResultsPage;

import java.util.List;

public class HomePageTest extends BaseTest{


    @Test
    public  void validOneWordSearch(){
        SearchResultsPage results = homePage.goSearchResultsPage("shirt");
        List<String> names = results.getAllNames();
        SoftAssert sa = new SoftAssert();
        for(String name : names){
            sa.assertTrue(name.toLowerCase().contains("shirt"), name+" does not contain shirt");
        }
        sa.assertAll();

    }
    @Test
    public  void validMultiWordSearch(){
        SearchResultsPage results = homePage.goSearchResultsPage("jean shorts");
        List<String> names = results.getAllNames();
        SoftAssert sa = new SoftAssert();
        for(String name : names){
            sa.assertTrue(name.toLowerCase().contains("jean") || name.toLowerCase().contains("shorts"), name+ " does not contain jean or shorts");
        }
        sa.assertAll();

    }

    @Test
    public void validBrandName(){
        SearchResultsPage results = homePage.goSearchResultsPage("adidas");
        SoftAssert sa = new SoftAssert();
        sa.assertEquals(results.getBrand().toLowerCase(),"adidas");
        sa.assertAll();

    }

    @Test
    public void invalidOneWordSearch(){
        SearchResultsPage results = homePage.goSearchResultsPage("nkarchutyun");
        SoftAssert sa = new SoftAssert();
        sa.assertTrue(results.hasNoResults());
    }

    @Test
    public void invalidMultiWordSearch(){
        SearchResultsPage results = homePage.goSearchResultsPage("hayoc patmutyun");
        SoftAssert sa = new SoftAssert();
        sa.assertTrue(results.hasNoResults());
    }

    @Test
    public void emptySearch(){
        SearchResultsPage results = homePage.goSearchResultsPage("");
        SoftAssert sa = new SoftAssert();
        sa.assertFalse(results.getAllNames().isEmpty());
    }


}
