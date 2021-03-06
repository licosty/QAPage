import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ListPage {
    private WebDriver driver;

    public ListPage(WebDriver driver) {
        this.driver = driver;
    }

    private By menuSwitch = By.xpath("//div[@class='mat-slide-toggle-thumb']");
    private By addButton = By.xpath("//app-list/button");
    private By deleteButton = By.xpath("//span[text()=' Delete ']//..");
    private By yesAlertButton = By.xpath("//span[text()='yes!']//..");

    public AddPage clickAdd() {
        driver.findElement(addButton).click();
        return new AddPage(driver);
    }

    public ListPage clickDelete() {
        if (listNotEmpty()) {
            driver.findElement(deleteButton).click();
        } else {
            System.out.println("Empty list");
        }

        driver.findElement(deleteButton);
        return this;
    }

    public ListPage clickAlertYes() {
        driver.findElement(yesAlertButton).click();
        return this;
    }

    public void clickMenu() {
        driver.findElement(menuSwitch).click();
    }

    public boolean listNotEmpty() {
        List<WebElement> delete = driver.findElements(By.xpath("//span[text()=' Delete ']"));
        return delete.size() > 0;
    }

    public int getListSize() {
        List<WebElement> list = driver.findElements(By.xpath("//tbody/tr"));
        return list.size();
    }

    public String getLastEntry() {
        WebElement name = driver.findElement(By.xpath("//tbody/tr[" + getListSize() + "]/td[2]"));
        WebElement key = driver.findElement(By.xpath("//tbody/tr[" + getListSize() + "]/td[3]"));
        return name.getText() + " " + key.getText();
    }
}
