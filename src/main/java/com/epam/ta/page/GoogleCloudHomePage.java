package com.epam.ta.page;

import com.epam.ta.utils.Waiter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.epam.ta.utils.Waiter.waitForElementLocatedBy;

public class GoogleCloudHomePage extends AbstractPage{

    private static final String HOMEPAGE_URL = "https://cloud.google.com/ ";
    private final Logger logger = LogManager.getRootLogger();

    public GoogleCloudHomePage(WebDriver driver) {
        super(driver);
    }

    By searchButton = By.xpath("//form[@action = \"https://cloud.google.com/s/results\"]");
    By searchInput = By.name("q");
    By searchResultLink = By.xpath("//div[@class='gs-title']/a");

    @Override
    public GoogleCloudHomePage openPage() {
        driver.get(HOMEPAGE_URL);
        waitForElementLocatedBy(driver, searchInput);
        logger.info("GC home page opened");
        return this;
    }

    public void enterSearchTerm(String term) {
        driver.findElement(searchButton).click();
        Waiter.waitForElementLocatedBy(driver, searchInput);
        driver.findElement(searchInput).sendKeys(term);
        logger.debug("Search term is entered");
        driver.findElement(searchInput).submit();
        logger.info("Search term is submitted");
    }

    public String findTargetingLinkInSearchResults(String term) {
        Waiter.waitForElementLocatedBy(driver, searchResultLink);
        List<WebElement> searchResultList = driver.findElements(searchResultLink);
        for(WebElement result: searchResultList) {
            String text = result.getText();
            if(text.contains(term)) {
                logger.info("Targeting link is found");
                return result.getAttribute("href");
            }
        }
        logger.error("Targeting search is NOT found");
        return null;
    }

    public GoogleCloudPricingCalculatorPage navigateWithTargetingLinkFromSearchResults(String targetURL) {
        driver.navigate().to(targetURL);
        logger.debug("Redirect to CP calculator");
        return new GoogleCloudPricingCalculatorPage(driver);
    }
}
