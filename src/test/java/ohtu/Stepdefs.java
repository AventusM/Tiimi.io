package ohtu;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Stepdefs {

    WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:4567";

    @Given("^browse is selected$")
    public void browse_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.name("browse"));
        element.click();
    }

    @When("^author \"([^\"]*)\" and book name \"([^\"]*)\" are submitted")
    public void author_and_book_name_are_given(String author, String title) throws Throwable {
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(author);
        element = driver.findElement(By.name("author"));
        element.sendKeys(title);
        element = driver.findElement(By.name("title"));
        element.submit();
    }

    @Then("^book named \"([^\"]*)\" is added$")
    public void user_has_added_new_bookmark(String title) throws Throwable {
        pageHasContent(title);
    }

    /* helper methods */
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
