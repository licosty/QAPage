import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BoardPage {
    private WebDriver driver;

    public BoardPage(WebDriver driver) {
        this.driver = driver;
    }

    private By menuSwitch = By.xpath("//div[@class='mat-slide-toggle-thumb']");
    private By boardButton = By.xpath("//button[1]");
    private By listButton = By.xpath("//button[2]");

    private By innerMenu = By.xpath("//mat-sidenav");

    public void clickMenu() {
        driver.findElement(menuSwitch).click();
    }

    public BoardPage clickBoard() {
        driver.findElement(boardButton).click();
        return this;
    }

    public ListPage clickList() {
        driver.findElement(listButton).click();
        return new ListPage(driver);
    }

    public boolean checkInnerMenu() {
        return driver.findElement(innerMenu).isDisplayed();
    }
}
