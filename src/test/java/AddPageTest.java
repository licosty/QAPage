import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class AddPageTest {
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
    public void selectCategoryTest() {
        boardPage.clickMenu();
        ListPage listPage = boardPage.clickList();
        AddPage addPage = listPage.clickAdd();

        driver.findElement(addPage.getCategoryField()).click();
        addPage.selectCategory(3);
        Assert.assertTrue("No category selected.", driver.findElement(addPage.getCategoryField()).getAttribute("class").contains("ng-valid"));
    }

    @Test
    public void selectNetworkTest() {
        boardPage.clickMenu();
        ListPage listPage = boardPage.clickList();
        AddPage addPage = listPage.clickAdd();

        driver.findElement(addPage.getSubcategoryField()).click();
        addPage.selectSubcategory(1);
        Assert.assertTrue("No subcategory selected.", driver.findElement(addPage.getSubcategoryField()).getAttribute("class").contains("ng-valid"));
    }

    @Test
    public void selectGroupTest() {
        boardPage.clickMenu();
        ListPage listPage = boardPage.clickList();
        AddPage addPage = listPage.clickAdd();

        driver.findElement(addPage.getGroupField()).click();
        addPage.selectGroup(1);
        Assert.assertTrue("No group selected.", driver.findElement(addPage.getGroupField()).getAttribute("class").contains("ng-valid"));
    }

    @Test
    public void addNewCategoryTest() {
        boardPage.clickMenu();
        ListPage listPage = boardPage.clickList();
        AddPage addPage = listPage.clickAdd();

        addPage.addCategory(category);
        Assert.assertTrue("Category not found.", addPage.containsCategory(category));
    }

    @Test
    public void addNewNetworkTest() {
        boardPage.clickMenu();
        ListPage listPage = boardPage.clickList();
        AddPage addPage = listPage.clickAdd();

        addPage.addSubcategory(subcategory);
        Assert.assertTrue("Subcategory not found.", addPage.containsSubcategory(subcategory));
    }

    @Test
    public void addNewGroupTest() {
        boardPage.clickMenu();
        ListPage listPage = boardPage.clickList();
        AddPage addPage = listPage.clickAdd();

        addPage.addGroup(group);
        Assert.assertTrue("Group not found.", addPage.containsGroup(group));
    }

    @Test
    public void saveTest() {
        boardPage.clickMenu();
        ListPage listPage = boardPage.clickList();
        AddPage addPage = listPage.clickAdd();
        addPage.typeName(name);
        addPage.typeEmail(email);

        driver.findElement(addPage.getCategoryField()).click();
        addPage.selectCategory(3);

        driver.findElement(addPage.getSubcategoryField()).click();
        addPage.selectSubcategory(1);
        new WebDriverWait(driver, 5).until(ExpectedConditions.invisibilityOfElementLocated(addPage.getSubcategoryField()));

        driver.findElement(addPage.getGroupField()).click();
        addPage.selectGroup(1);
        addPage.clickAddGroup();
        Assert.assertTrue("There are blank fields or no group has been added.", addPage.isClickable(addPage.getSaveButton()));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
