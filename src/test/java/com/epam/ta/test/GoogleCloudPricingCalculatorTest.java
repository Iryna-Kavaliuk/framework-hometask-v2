package com.epam.ta.test;

import com.epam.ta.model.CloudProduct;
import com.epam.ta.page.GoogleCloudHomePage;
import com.epam.ta.page.GoogleCloudPricingCalculatorPage;
import com.epam.ta.service.CloudProductCreator;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.epam.ta.test.SearchCloudPricingCalculatorTest.RESULTING_TERM;
import static com.epam.ta.test.SearchCloudPricingCalculatorTest.SEARCH_TERM;

public class GoogleCloudPricingCalculatorTest extends CommonConditions {
    private GoogleCloudHomePage homePage;
    private GoogleCloudPricingCalculatorPage calculatorPage;

    private static final String MANUALLY_GOT_ESTIMATION = "1,840.40 / mo";

    @BeforeMethod
    public void browserSetup() {
        homePage = new GoogleCloudHomePage(driver);
    }

    @Test(description = "Check Google Cloud Calculator is opened")
    public void GCPCalculatorIsOpenFromSearch() {
        homePage.openPage();
        homePage.enterSearchTerm(SEARCH_TERM);
        String targetLink = homePage.findTargetingLinkInSearchResults(RESULTING_TERM);
        calculatorPage = homePage.navigateWithTargetingLinkFromSearchResults(targetLink);
        calculatorPage.openPage();
        Assert.assertTrue(calculatorPage.isPageOpened(), "Google Cloud Calculator is failed to open");
    }

    @Test(description = "Check estimation price is calculated correctly", dependsOnMethods = "GCPCalculatorIsOpenFromSearch")
    public void GCPCalculatorPriceEstimatingTest() throws InterruptedException {
        calculatorPage.closeCookiesAlert();

        CloudProduct testProduct = CloudProductCreator.withAllDataFromProperty();
        calculatorPage.activateProductType(testProduct.getComputeEngine());
        calculatorPage.enterNumberOfInstances(testProduct.getNumberOfInstances());
        calculatorPage.enterOperatingSystems(testProduct.getOperatingSystem());
        calculatorPage.enterProvisioningModel(testProduct.getProvisioningModel());
        calculatorPage.enterSeries(testProduct.getSeries());
        calculatorPage.enterMachineType(testProduct.getMachineType());
        calculatorPage.checkAddGPUsCheckbox();
        calculatorPage.enterGpuType(testProduct.getGpuType());
        calculatorPage.enterGpuNumber(testProduct.getNumberOfGPU());
        calculatorPage.enterLocalSSD(testProduct.getLocalSSD());
        calculatorPage.enterDataCenterLocation(testProduct.getDataCenterLocation());
        calculatorPage.enterCommittedUsage(testProduct.getCommittedUsage());
        calculatorPage.clickAddToEstimateButton();

        Assert.assertEquals(calculatorPage.getEstimationResult(), MANUALLY_GOT_ESTIMATION,
                "Estimations got manually and automatically are different!");
    }

}
