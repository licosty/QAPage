import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AddPage {
    private WebDriver driver;

    public AddPage(WebDriver driver) {
        this.driver = driver;
    }

    private By menuSwitch = By.xpath("//div[@class='mat-slide-toggle-thumb']");
    private By boardButton = By.xpath("//button[1]");
    private By listButton = By.xpath("//button[2]");
    private By addGroupButton = By.xpath("//mat-card[4]/mat-card-content//button[1]");
    private By saveButton = By.xpath("//mat-checkbox//..//button");

    private By nameField = By.id("mat-input-0");
    private By emailField = By.id("mat-input-1");
    private By categoryField = By.id("mat-input-2");
    private By subcategoryField = By.id("mat-select-0");
    private By groupField = By.id("mat-select-1");

    private By categoriesList = By.xpath("//mat-card[3]//select/option");
    private By subcategoriesList = By.xpath("//div[2]/div[2]//mat-option");
    private By groupsList = By.xpath("//div[2]/div[2]//mat-option");

    private By titleAdd = By.xpath("//form/mat-card[1]/mat-card-title");

    public ListPage addNewEntry(String name, String email, String category, String subcategory, String group) {
        typeName(name);
        typeEmail(email);

        driver.findElement(categoryField).click();
        if (listExists(categoriesList, 1)) {
            selectCategory(1);
        } else { addCategory(category); }

        driver.findElement(subcategoryField).click();
        if (listExists(subcategoriesList, 0)) {
            selectSubcategory(1);
        } else { addSubcategory(subcategory); }

        driver.findElement(groupField).click();
        if (listExists(groupsList, 0)) {
            selectGroup(1);
        } else { addGroup(group); }

        clickAddGroup();
        if (isClickable(saveButton)) {
            clickSave();
        } else {
            System.out.println("There are blank fields or no group has been added.");
        }
        clickSave();

        return new ListPage(driver);
    }

    public AddPage clickAddGroup() {
        driver.findElement(addGroupButton).click();
        return this;
    }

    public ListPage clickSave() {
        driver.findElement(saveButton).click();
        return new ListPage(driver);
    }

    public AddPage typeName(String name) {
        driver.findElement(nameField).sendKeys(name);
        return this;
    }

    public AddPage typeEmail(String key) {
        driver.findElement(emailField).sendKeys(key);
        return this;
    }

    public AddPage selectCategory(int num) {
        List<WebElement> list = driver.findElements(categoriesList);
        list.get(num).click();
        return this;
    }

    public AddPage selectSubcategory(int num) {
        List<WebElement> list = driver.findElements(subcategoriesList);
        list.get(num).click();
        list.get(num).sendKeys(Keys.TAB);
        return this;
    }

    public AddPage selectGroup(int num) {
        List<WebElement> list = driver.findElements(By.xpath("//mat-option"));
        list.get(num).click();
        return this;
    }

    public AddPage addCategory(String category) {
        driver.findElement(By.xpath("//mat-form-field[1]//button")).click();
        driver.findElement(By.xpath("//mat-dialog-container//..//input")).sendKeys(category);
        driver.findElement(By.xpath("//div[@class = 'mat-dialog-actions']/button[2]")).click();
        return this;
    }

    public AddPage addSubcategory(String subcategory) {
        driver.findElement(By.xpath("//mat-form-field[2]//button")).click();
        driver.findElement(By.xpath("//mat-dialog-container//..//input")).sendKeys(subcategory);
        driver.findElement(By.xpath("//div[@class = 'mat-dialog-actions']/button[2]")).click();
        return this;
    }

    public AddPage addGroup(String group) {
        driver.findElement(By.xpath("//mat-form-field[3]//button")).click();
        driver.findElement(By.xpath("//mat-dialog-container//..//input")).sendKeys(group);
        driver.findElement(By.xpath("//div[@class = 'mat-dialog-actions']/button[2]")).click();
        return this;
    }

    public boolean listExists(By location, int count) {
        List<WebElement> list = driver.findElements(location);
        return list.size() > count;
    }

    public boolean isClickable(By location) {
        return driver.findElement(location).isEnabled();
    }

    public String getAddTitle() {
        return  driver.findElement(titleAdd).getText();
    }

    public void clickMenu() {
        driver.findElement(menuSwitch).click();
    }

    public BoardPage clickBoard() {
        driver.findElement(boardButton).click();
        return new BoardPage(driver);
    }

    public ListPage clickList() {
        driver.findElement(listButton).click();
        return new ListPage(driver);
    }

    public boolean containsCategory(String categoryName) {
        driver.findElement(categoryField).click();
        List<WebElement> list = driver.findElements(categoriesList);

        for (WebElement element : list) {
            if (element.getText().trim().equals(categoryName))
                return true;
        }
        return false;
    }

    public boolean containsSubcategory(String subcategoryName) {
        driver.findElement(subcategoryField).click();
        List<WebElement> list = driver.findElements(subcategoriesList);

        for (WebElement element : list) {
            if (element.getText().trim().equals(subcategoryName))
                return true;
        }
        return false;
    }

    public boolean containsGroup(String groupName) {
        driver.findElement(groupField).click();
        List<WebElement> list = driver.findElements(groupsList);

        for (WebElement element : list) {
            if (element.getText().trim().equals(groupName))
                return true;
        }
        return false;
    }

    public By getCategoryField() {
        return categoryField;
    }

    public By getSubcategoryField() {
        return subcategoryField;
    }

    public By getGroupField() {
        return groupField;
    }

    public By getSubcategoryList() {
        return subcategoriesList;
    }

    public By getSaveButton() {
        return saveButton;
    }

}
