package com.epam.ta.test;

import com.epam.ta.page.GoogleCloudHomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchCloudPricingCalculatorTest extends CommonConditions {

    protected GoogleCloudHomePage homePage;

    protected static final String SEARCH_TERM = "Google Cloud Platform Pricing Calculator";
    protected static final String RESULTING_TERM = "Google Cloud Pricing Calculator";

    @BeforeMethod
    public void browserSetup() {
        homePage = new GoogleCloudHomePage(driver);
    }

    @Test(description = "Check that targeting search term is in the search results")
    public void GCPCanBeFoundViaSearch() {
        homePage.openPage();
        homePage.enterSearchTerm(SEARCH_TERM);
        String targetLink = homePage.findTargetingLinkInSearchResults(RESULTING_TERM);
        Assert.assertNotNull(targetLink,
                "Search Term is not found!!");
    }
}
