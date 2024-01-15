package com.epam.ta.page;

import com.epam.ta.utils.Waiter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GoogleCloudPricingCalculatorPage extends AbstractPage{
    private static final String CALCULATOR_PAGE_LEGACY_URL  = "https://cloud.google.com/products/calculator-legacy";
    private final Logger logger = LogManager.getRootLogger();
    private WebElement currentElement;
    public GoogleCloudPricingCalculatorPage(WebDriver driver) {
        super(driver);
    }

    By freeTrialButton = By.xpath("//a[@track-type = 'freeTrial']");
    By cookiesAlert = By.className("devsite-snackbar-action");
    By productTypeTabs = By.xpath("//md-tab-item[@role='tab']/div/div/div[@class='name']/span");
    By cloudSiteFrame = By.xpath("//article[@id='cloud-site']/devsite-iframe/iframe");
    By myResourcesFrame = By.id("myFrame");
    By numberOfInstancesInput = By.xpath("//input[@type='number']");
    By operatingSystemsInput = By.xpath("//label[text() = 'Operating System / Software']/../descendant::span/div[@class='md-text']");
    By provisioningModelInput = By.xpath("//label[text() = 'Provisioning model']/../descendant::span/div[@class='md-text']");
    By seriesInput = By.xpath("//label[text() = 'Series']/../descendant::span/div[@class='md-text ng-binding']");
    By machineTypeInput = By.xpath("//label[text() = 'Machine type']/../descendant::span/div[@class='md-text ng-binding']");
    By addGPUsCheckbox = By.xpath("//*[@aria-label='Add GPUs']/div");
    By gpuTypeInput = By.xpath("//md-select[@aria-label = 'GPU type']");
    By gpuNumberInput = By.xpath("//md-select[@placeholder = 'Number of GPUs']/md-select-value/span");
    By localSSDInput = By.xpath("//label[text() = 'Local SSD']/../descendant::span/div[@class='md-text ng-binding']");
    By dataCenterLocationInput = By.xpath("//label[text() = 'Datacenter location']/../descendant::span/div[@class='md-text ng-binding']");
    By committedUsageInput = By.xpath("//label[text() = 'Committed usage']/../descendant::span/div[@class='md-text']");
    By hoursInput = By.xpath("//input[@name='hours']");
    By addToEstimateButton = By.xpath("//button[text()[contains(., 'Add to Estimate')]]");
    String gpuNumberSelectContainer = "//div[contains(@id, 'select_container')]//md-option[contains(@ng-repeat, 'gpuType') and @value='%s']/div[1]";
    String dataCenterLocationContainer = "//md-option[contains(@ng-repeat, 'computeServer')]/div[text()[contains(.,'%s')]]";
    String committedUsageContainer = "//div[@class='md-ripple-container']/preceding-sibling::div[contains(.,'%s')]/parent::md-option";

    By estimationResultContent = By.xpath("//md-card-content[@id='resultBlock']//h2[@class='md-flex ng-binding ng-scope']");
    By estimationResultFullContent = By.xpath("//*[@id=\"resultBlock\"]/md-card//b[@class='ng-binding']");

    public void closeCookiesAlert() {
        driver.findElement(cookiesAlert).click();
    }

    @Override
    public GoogleCloudPricingCalculatorPage openPage() {
        driver.get(CALCULATOR_PAGE_LEGACY_URL);
        logger.info("Pricing calculator page opened");
        return this;
    }

    public boolean isPageOpened() {
        return Waiter.waitForElementLocatedBy(driver, freeTrialButton) != null;
    }

    public void activateProductType(String productType) {
        List<WebElement> productTypeList = driver.findElements(productTypeTabs);
        for (WebElement pt : productTypeList) {
            String text = pt.getText();
            if (text.contains(productType)) {
                pt.click();
                break;
            }
        }
        driver.switchTo().frame(driver.findElement(cloudSiteFrame)).switchTo().frame(driver.findElement(myResourcesFrame));
    }

    public void enterNumberOfInstances(int number) {
        currentElement = driver.findElement(numberOfInstancesInput);
        Waiter.waitForElementLocatedBy(driver, numberOfInstancesInput);
        highLightElement(currentElement);
        currentElement.sendKeys(String.valueOf(number));
    }

    public void enterOperatingSystems(String operatingSystem) {
        currentElement = driver.findElement(operatingSystemsInput);
        Waiter.waitForElementLocatedBy(driver, operatingSystemsInput);
        highLightElement(currentElement);
        currentElement.click();
        String locator = buildLocatorByText(operatingSystem);
        currentElement = driver.findElement(By.xpath(locator));
        Waiter.waitForElementLocated(driver, currentElement);
        highLightElement(currentElement);
        driver.switchTo().activeElement();
        currentElement.click();
    }

    public void enterProvisioningModel(String provisioningModel) {
        currentElement = driver.findElement(provisioningModelInput);
        Waiter.waitForElementLocatedBy(driver, provisioningModelInput);
        highLightElement(currentElement);
        currentElement.click();
        String locator = buildLocatorByText(provisioningModel);
        currentElement = driver.findElement(By.xpath(locator));
        Waiter.waitForElementLocated(driver, currentElement);
        highLightElement(currentElement);
        currentElement.click();
    }

    public void enterSeries(String series) {
        currentElement = driver.findElement(seriesInput);
        scrollToElement(operatingSystemsInput);
        highLightElement(currentElement);
        currentElement.click();
        String locator = buildLocatorByText(series);
        currentElement = driver.findElement(By.xpath(locator));
        Waiter.waitForElementLocated(driver, currentElement);
        driver.switchTo().activeElement();
        highLightElement(currentElement);
        currentElement.click();
    }

    public void enterMachineType(String machineType) {
        driver.findElement(machineTypeInput).click();
        String locator = buildLocatorByText(machineType);
        currentElement = driver.findElement(By.xpath(locator));
        Waiter.waitForElementLocated(driver, currentElement);
        highLightElement(currentElement);
        clickToElement(By.xpath(locator));
    }

    public void checkAddGPUsCheckbox() {
        currentElement = driver.findElement(addGPUsCheckbox);
        highLightElement(currentElement);
        currentElement.click();
    }

    public void enterGpuType(String gpuType) {
        driver.switchTo().activeElement();
        currentElement = driver.findElement(gpuTypeInput);
        highLightElement(currentElement);
        currentElement.click();
        String locator = buildLocatorByText(gpuType);
        currentElement = driver.findElement(By.xpath(locator));
        Waiter.waitForElementLocated(driver, currentElement);
        highLightElement(currentElement);
        currentElement.click();
    }

    public void enterGpuNumber(int gpuNumber) {
        Waiter.waitForElementLocatedBy(driver, gpuNumberInput);
        currentElement = driver.findElement(gpuNumberInput);
        highLightElement(currentElement);
        currentElement.click();
        String locator = String.format(gpuNumberSelectContainer, gpuNumber);
        currentElement = driver.findElement(By.xpath(locator));
        Waiter.waitForElementLocated(driver, currentElement);
        highLightElement(currentElement);
        currentElement.click();
    }

    public void enterLocalSSD(String LocalSSD) {
        Waiter.waitForElementLocatedBy(driver, hoursInput);
        driver.switchTo().activeElement();
        currentElement = driver.findElement(localSSDInput);
        highLightElement(currentElement);
        currentElement.click();
        String locator = buildLocatorByText(LocalSSD);
        currentElement = driver.findElement(By.xpath(locator));
        Waiter.waitForElementLocated(driver, currentElement);
        driver.switchTo().activeElement();
        highLightElement(currentElement);
        currentElement.click();
    }

    public void enterDataCenterLocation(String dcLocation) {
        scrollToElement(committedUsageInput);
        Waiter.waitForElementLocatedBy(driver, dataCenterLocationInput);
        clickToElement(dataCenterLocationInput);
        String locator = String.format(dataCenterLocationContainer, dcLocation);
        currentElement = driver.findElement(By.xpath(locator));
        Waiter.waitForElementLocated(driver, currentElement);
        highLightElement(currentElement);
        currentElement.click();
    }

    public void enterCommittedUsage(String committedUsage) {
        scrollToElement(addToEstimateButton);
        Waiter.waitForElementLocatedBy(driver, committedUsageInput);
        clickToElement(committedUsageInput);
        String locator = String.format(committedUsageContainer, committedUsage);
        currentElement = driver.findElement(By.xpath("//md-option[@id='select_option_138']"));
        Waiter.waitForElementLocated(driver, currentElement);
        highLightElement(currentElement);
        currentElement.click();
    }

    public void clickAddToEstimateButton() throws InterruptedException {
        scrollToElement(dataCenterLocationInput);
        clickToElement(addToEstimateButton);
    }

    public String getEstimationResult() {
        currentElement = driver.findElement(estimationResultContent);
        scrollToElement(estimationResultContent);
        highLightElement(currentElement);
        String estimation = currentElement.getText();
        logger.info("Estimation is test product is calculated:" + estimation);
        return estimation;
    }

    private void scrollToElement(By by) {
        WebElement element = driver.findElement(by);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    private void clickToElement(By by) {
        WebElement element = driver.findElement(by);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    private String buildLocatorByText(String valuePart) {
        return String.format("//div[contains(@id, 'select_container')]//div[text()[contains(.,'%s')]]", valuePart);
    }

}
