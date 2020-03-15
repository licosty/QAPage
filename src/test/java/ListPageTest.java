import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ListPageTest {
    private WebDriver driver;
    private BoardPage boardPage;

    private static String name = "Тоётоми Хидэёси";
    private static String email = "30";
    private static String category = "1";
    private static String subcategory = "1";
    private static String group = "1";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("сайт-который-нельзя-называть");
        boardPage = new BoardPage(driver);
    }

    @Test
    public void addEntryTest() {
        boardPage.clickMenu();
        ListPage listsPage = boardPage.clickList();
        AddPage addPage = listsPage.clickAdd();

        addPage.addNewEntry(name, email, category, subcategory, group);
        Assert.assertEquals("Failed to add entry.", name + " " + email, listsPage.getLastEntry());
    }

    @Test
    public void openInnerMenuTest() {
        boardPage.clickMenu();
        Assert.assertTrue("The inner menu is not opened.", boardPage.checkInnerMenu());
    }

    @Test
    public void addTest() {
        boardPage.clickMenu();
        ListPage listPage = boardPage.clickList();
        AddPage addPage = listPage.clickAdd();
        Assert.assertTrue(addPage.getAddTitle().contains("Add"));
    }

    @Test
    public void deleteEntryTest() {
        boardPage.clickMenu();
        ListPage listPage = boardPage.clickList();
        int listSize = listPage.getListSize();
        listPage.clickDelete();
        listPage.clickAlertYes();

        Assert.assertEquals("Failed to delete entry.", listSize, listPage.getListSize());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
