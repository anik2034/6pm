import org.testng.annotations.DataProvider;

public class SearchDataProvider {

    public static Object[][] validSearchForDetails =  new Object[][]{
          {"loafers"}, {"pants"}, {"shoulder bag"},  {"mom jeans"}
    };

    public static Object[][] numberOfFilterCombinations =  new Object[][]{
            {1}, {2}, {3},  {4},
            {5}, {6}, {7},  {8},
            {9}, {10}, {11},  {12}
    };


    public static Object[][] nonEmptyResults = new Object[][]{
            {"1"}, {"2"}, {"3"}, {"4"}, {"5"}, {"6"}, {"7"},
            {"10"}, {"25"}, {"30"}, {"35"}, {"40"}, {"50"}, {"60"}, {"70"}, {"75"}, {"100"}, {"200"},
            {"10.0"}, {"25.0"}, {"30.0"}, {"35.0"}, {"40.0"}, {"50.0"}, {"60.0"}, {"70.0"}, {"75.0"}, {"100.0"}, {"200.0"},
            {"10.00"}, {"25.00"}, {"30.00"}, {"35.00"}, {"40.00"}, {"50.00"}, {"60.00"}, {"70.00"}, {"75.00"}, {"100.00"}, {"200.00"},
            {"$"}, {"."}, {"under"}, {"Under"}, {"and"}, {"And"}, {"UNDER"}, {"AND"}, {"unDeR"}, {"anD"}
    };

    public static Object[][] emptyResults = new Object[][]{
            {"8"}, {"9"}, {"11.2"}, {"-4"}, {"summer"}, {"  "}, {"804"},
            {"6543"}, {"94567890"}, {"9944.34"}

    };
}
