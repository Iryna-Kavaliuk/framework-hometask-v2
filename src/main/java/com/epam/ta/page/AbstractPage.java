package com.epam.ta.page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class AbstractPage {

    protected WebDriver driver;
    protected WebElement lastElement;
    protected String lastElementColor;
    private static final String HIGHLIGHTING_BACKGROUND_COLOR = "yellow";

    protected AbstractPage (WebDriver driver) {
        this.driver = driver;
    }

    protected abstract AbstractPage openPage();

    public WebElement highLightElement(WebElement element) {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        if(lastElement != null) {
            js.executeScript("arguments[0].style.backgroundColor=arguments[1]", lastElement, lastElementColor);
        }

        lastElement = element;
        lastElementColor = element.getCssValue("backgroundColor");

        if(element != null) {
            js.executeScript("arguments[0].style.backgroundColor=arguments[1]", element, HIGHLIGHTING_BACKGROUND_COLOR);
        }
        return element;
    }

}
