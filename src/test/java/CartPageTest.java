import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CartPage;
import pages.ItemDetailsPage;
import pages.SearchResultsPage;

import java.util.List;
import java.util.Random;

public class CartPageTest extends BaseTest {


    private static int generateRandomNumber(int min, int max) {
        Random random = new Random();
        int randomNumber = random.nextInt(max - min) + min;
        return randomNumber;
    }

    @DataProvider
    public  Object[][] validSearchForDetails (){

        return SearchDataProvider.validSearchForDetails ;
    }



    @Test(dataProvider = "validSearchForDetails")
    public void checkAddingToCart(String search) {

        SearchResultsPage results = homePage.goSearchResultsPage(search);
        SoftAssert sa = new SoftAssert();

        ItemDetailsPage itemPage;
        List<WebElement> colors;
        List<WebElement> sizes;
        List<WebElement> options;
        List<String> colorsNames;
        List<String> sizesNames;
        List<String> optionsNames;
        String color = null;
        String size = null;
        String option = null;
        String brand;
        String name;
        String price;
        boolean hasColor = false;
        boolean hasSize = false;
        boolean hasOption = false;

        itemPage = results.clickItem(results.getFirstItem());

        brand = itemPage.getBrand();
        name = itemPage.getName();
        price = itemPage.getPrice();
        itemPage.scroll();
        if (itemPage.AreColorsDisplayed()) {

            colors = itemPage.getAllColor();
            colorsNames = itemPage.getAllColorsNames();
            int index = generateRandomNumber(0, colors.size());
            color = colorsNames.get(index);
            colors.get(index).click();
            hasColor = true;

        }


        if (itemPage.AreSizesDisplayed()) {
            sizes = itemPage.getAllSizes();
            sizesNames = itemPage.getAllSizesNames();
            int index = generateRandomNumber(0, sizes.size());
            size = sizesNames.get(index);
            sizes.get(index).click();
            hasSize = true;
        }

        if (itemPage.AreOptionsDisplayed()) {

            options = itemPage.getAllOptions();
            optionsNames = itemPage.getAllOptionsNames();
            int index = generateRandomNumber(0, options.size());
            option = optionsNames.get(index);
            options.get(index).click();
            hasOption = true;

        }

        CartPage cartPage = itemPage.addAndViewCart();
        String brandCart = cartPage.getBrand();
        String nameCart = cartPage.getName();
        String priceCart = cartPage.getPrice();
        List<String> list = cartPage.details();

        if((hasColor && list.contains(color))){
            hasColor = true;
        }
        if(hasSize && list.contains(size)){
            hasSize = true;
        }
        if(hasOption && list.contains(option)) {
            hasOption = true;
        }
        if(!hasColor){
            hasColor = true;
        }
        if(!hasSize){
            hasSize = true;
        }
        if(!hasOption){
            hasOption = true;
        }



        sa.assertTrue(hasColor && hasSize && hasOption &&
                name.equals(nameCart) &&
                brand.equals(brandCart) &&
                price.equals(priceCart));


        sa.assertAll();
}

    @Test(dataProvider = "validSearchForDetails")
    public void checkRemove(String search){
        SearchResultsPage results = homePage.goSearchResultsPage(search);
        SoftAssert sa = new SoftAssert();

        ItemDetailsPage itemPage;
        List<WebElement> colors;
        List<WebElement> sizes;
        List<WebElement> options;

        itemPage = results.clickItem(results.getFirstItem());


        itemPage.scroll();

        if (itemPage.AreColorsDisplayed()) {

            colors = itemPage.getAllColor();

            int index = generateRandomNumber(0, colors.size());

            colors.get(index).click();


        }


        itemPage.scroll();
        if (itemPage.AreSizesDisplayed()) {
            sizes = itemPage.getAllSizes();

            int index = generateRandomNumber(0, sizes.size());

            sizes.get(index).click();

        }

        if (itemPage.AreOptionsDisplayed()) {

            options = itemPage.getAllOptions();

            int index = generateRandomNumber(0, options.size());

            options.get(index).click();


        }

        CartPage cartPage = itemPage.addAndViewCart();
        cartPage.remove();
        sa.assertTrue(cartPage.isNoResultsDisplayed(),"The only Item is removed but cart is not empty");
        sa.assertAll();

    }

    @Test
    public void checkItemCountNonZero(){

        SearchResultsPage results = homePage.goSearchResultsPage("loafers");
        SoftAssert sa = new SoftAssert();

        ItemDetailsPage itemPage;
        List<WebElement> colors;
        List<WebElement> sizes;
        List<WebElement> options;

        itemPage = results.clickItem(results.getFirstItem());


        itemPage.scroll();

        if (itemPage.AreColorsDisplayed()) {

            colors = itemPage.getAllColor();

            int index = generateRandomNumber(0, colors.size());

            colors.get(index).click();


        }


        itemPage.scroll();
        if (itemPage.AreSizesDisplayed()) {
            sizes = itemPage.getAllSizes();

            int index = generateRandomNumber(0, sizes.size());

            sizes.get(index).click();

        }

        if (itemPage.AreOptionsDisplayed()) {

            options = itemPage.getAllOptions();

            int index = generateRandomNumber(0, options.size());

            options.get(index).click();


        }

        CartPage cartPage = itemPage.addAndViewCart();

        for(int i = 1; i < cartPage.getSelectLength()-1; i++) {

            String count1 = cartPage.select(i);
            String count2 = cartPage.getCount();
            sa.assertTrue(count1.equals(count2), count1 + " is not equal to " + count2);

        }
        sa.assertAll();

    }

    @Test
    public void checkItemCountZero(){

        SearchResultsPage results = homePage.goSearchResultsPage("loafers");
        SoftAssert sa = new SoftAssert();

        ItemDetailsPage itemPage;
        List<WebElement> colors;
        List<WebElement> sizes;
        List<WebElement> options;

        itemPage = results.clickItem(results.getFirstItem());


        itemPage.scroll();

        if (itemPage.AreColorsDisplayed()) {

            colors = itemPage.getAllColor();

            int index = generateRandomNumber(0, colors.size());

            colors.get(index).click();


        }


        itemPage.scroll();
        if (itemPage.AreSizesDisplayed()) {
            sizes = itemPage.getAllSizes();

            int index = generateRandomNumber(0, sizes.size());

            sizes.get(index).click();

        }

        if (itemPage.AreOptionsDisplayed()) {

            options = itemPage.getAllOptions();

            int index = generateRandomNumber(0, options.size());

            options.get(index).click();


        }

        CartPage cartPage = itemPage.addAndViewCart();
        cartPage.select(0);

                sa.assertTrue(cartPage.isNoResultsDisplayed(),"The count is 0 but it is not removed");


        sa.assertAll();

    }








}
