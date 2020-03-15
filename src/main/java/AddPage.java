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

    private By nameField = By.id("mat-input-0");
    private By emailField = By.id("mat-input-1");
    private By categoryList = By.id("mat-input-2");
    private By subcategoryList = By.id("mat-select-0");
    private By groupList = By.id("mat-select-1");
    private By addGroupButton = By.xpath("//mat-card[4]/mat-card-content//button[1]");
    private By saveButton = By.xpath("//mat-checkbox//..//button");

    public ListPage addNewEntry(String name, String email, String category, String subcategory, String group) {
        typeName(name);
        typeEmail(email);

        driver.findElement(categoryList).click();
        if (categoryExists()) {
            selectCategory(1);
        } else { addCategory(category); }

        driver.findElement(subcategoryList).click();
        if (subcategoryExists()) {
            selectSubcategory(0);
        } else { addSubcategory(subcategory); }

        driver.findElement(groupList).click();
        if (groupExists()) {
            selectGroup(0);
        } else { addGroup(group); }

        clickAddGroup();
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
        List<WebElement> list = driver.findElements(By.xpath("//select[@id = 'mat-input-2']/option"));
        list.get(num).click();
        return this;
    }

    public AddPage selectSubcategory(int num) {
        List<WebElement> list = driver.findElements(By.xpath("//mat-option"));
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
        driver.findElement(By.id("mat-input-4")).sendKeys(category);
        driver.findElement(By.xpath("//div[@class = 'mat-dialog-actions']/button[2]")).click();
        return this;
    }

    public AddPage addSubcategory(String network) {
        driver.findElement(By.xpath("//mat-form-field[2]//button")).click();
        driver.findElement(By.id("mat-input-10")).sendKeys(network);
        driver.findElement(By.xpath("//div[@class = 'mat-dialog-actions']/button[2]")).click();
        return this;
    }

    public AddPage addGroup(String group) {
        driver.findElement(By.xpath("//mat-form-field[3]//button")).click();
        driver.findElement(By.id("mat-input-10")).sendKeys(group);
        driver.findElement(By.xpath("//div[@class = 'mat-dialog-actions']/button[2]")).click();
        return this;
    }

    public boolean categoryExists() {
        List<WebElement> list = driver.findElements(By.xpath("//select[@id = 'mat-input-2']/option"));
        return list.size() > 1;
    }

    public boolean subcategoryExists() {
        List<WebElement> list = driver.findElements(By.xpath("//mat-option"));
        return list.size() > 0;
    }

    public boolean groupExists() {
        List<WebElement> list = driver.findElements(By.xpath("//mat-option"));
        return list.size() > 0;
    }
}