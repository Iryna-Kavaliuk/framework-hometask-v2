package com.epam.ta.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waiter {

    private static final int WAIT_TIMEOUT_SECONDS = 10;

    public static WebElement waitForElementLocatedBy(WebDriver driver, By by) {
        return new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS)).
                until(ExpectedConditions.elementToBeClickable(by));
    }

    public static WebElement waitForElementLocated(WebDriver driver, WebElement webElement) {
        return new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS)).
                until(ExpectedConditions.elementToBeClickable(webElement));
    }
}
