package com.epam.ta.page;

import com.epam.ta.utils.Waiter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class GoogleCloudPricingCalculatorPage extends AbstractPage{
    private final Logger logger = LogManager.getRootLogger();
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
        Waiter.waitForElementLocatedBy(driver, numberOfInstancesInput);
        driver.findElement(numberOfInstancesInput).sendKeys(String.valueOf(number));
    }

    public void enterOperatingSystems(String operatingSystem) {
        Waiter.waitForElementLocatedBy(driver, operatingSystemsInput);
        driver.findElement(operatingSystemsInput).click();
        String locator = buildLocatorByText(operatingSystem);
        WebElement osChoice = driver.findElement(By.xpath(locator));
        Waiter.waitForElementLocated(driver, osChoice);
        driver.switchTo().activeElement();
        osChoice.click();
    }

    public void enterProvisioningModel(String provisioningModel) {
        Waiter.waitForElementLocatedBy(driver, provisioningModelInput);
        driver.findElement(provisioningModelInput).click();
        String locator = buildLocatorByText(provisioningModel);
        WebElement provisioningModelChoice = driver.findElement(By.xpath(locator));
        Waiter.waitForElementLocated(driver, provisioningModelChoice);
        provisioningModelChoice.click();
    }

    public void enterSeries(String series) {
        scrollToElement(operatingSystemsInput);
        driver.findElement(seriesInput).click();
        String locator = buildLocatorByText(series);
        WebElement seriesChoice = driver.findElement(By.xpath(locator));
        Waiter.waitForElementLocated(driver, seriesChoice);
        driver.switchTo().activeElement();
        seriesChoice.click();
    }

    public void enterMachineType(String machineType) {
        driver.findElement(machineTypeInput).click();
        String locator = buildLocatorByText(machineType);
        WebElement machineTypeChoice = driver.findElement(By.xpath(locator));
        Waiter.waitForElementLocated(driver, machineTypeChoice);
        clickToElement(By.xpath(locator));
    }

    public void checkAddGPUsCheckbox() {
        driver.findElement(addGPUsCheckbox).click();
    }

    public void enterGpuType(String gpuType) {
        driver.switchTo().activeElement();
        driver.findElement(gpuTypeInput).click();
        String locator = buildLocatorByText(gpuType);
        WebElement gpuTypeChoice = driver.findElement(By.xpath(locator));
        Waiter.waitForElementLocated(driver, gpuTypeChoice);
        gpuTypeChoice.click();
    }

    public void enterGpuNumber(int gpuNumber) {
        Waiter.waitForElementLocatedBy(driver, gpuNumberInput);
        driver.findElement(gpuNumberInput).click();
        String locator = String.format(gpuNumberSelectContainer, gpuNumber);
        WebElement gpuNumberChoice = driver.findElement(By.xpath(locator));
        Waiter.waitForElementLocated(driver, gpuNumberChoice);
        gpuNumberChoice.click();
    }

    public void enterLocalSSD(String LocalSSD) {
        Waiter.waitForElementLocatedBy(driver, hoursInput);
        driver.switchTo().activeElement();
        driver.findElement(localSSDInput).click();
        String locator = buildLocatorByText(LocalSSD);
        WebElement localSSDChoice = driver.findElement(By.xpath(locator));
        Waiter.waitForElementLocated(driver, localSSDChoice);
        driver.switchTo().activeElement();
        localSSDChoice.click();
    }

    public void enterDataCenterLocation(String dcLocation) {
        scrollToElement(committedUsageInput);
        Waiter.waitForElementLocatedBy(driver, dataCenterLocationInput);
        clickToElement(dataCenterLocationInput);
        String locator = String.format(dataCenterLocationContainer, dcLocation);
        WebElement dcLocationChoice = driver.findElement(By.xpath(locator));
        Waiter.waitForElementLocated(driver, dcLocationChoice);
        dcLocationChoice.click();
    }

    public void enterCommittedUsage(String committedUsage) {
        scrollToElement(addToEstimateButton);
        Waiter.waitForElementLocatedBy(driver, committedUsageInput);
        clickToElement(committedUsageInput);
        String locator = String.format(committedUsageContainer, committedUsage);
        WebElement usageChoice = driver.findElement(By.xpath("//md-option[@id='select_option_138']"));
        Waiter.waitForElementLocated(driver, usageChoice);
        usageChoice.click();
    }

    public void clickAddToEstimateButton() throws InterruptedException {
        scrollToElement(dataCenterLocationInput);
        clickToElement(addToEstimateButton);
    }

    public String getEstimationResult() {
        String estimation = driver.findElement(estimationResultContent).getText();
        logger.info("Estimation is test product is calculated:" + estimation);
        return estimation;
    }

    public void  highlightResultingContent() {
        scrollToElement(estimationResultFullContent);
        Actions builder = new Actions(driver);
        WebElement content = driver.findElement(estimationResultContent);

        builder.moveToElement(content, 0, 0).build().perform();
        builder.clickAndHold().moveToElement(content, content.getSize().getWidth()/ 2, content.getSize().getHeight() / 2).release().build().perform();
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
