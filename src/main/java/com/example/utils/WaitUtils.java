package com.example.utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.config.ConfigReader;

public class WaitUtils {
    private static final int TIMEOUT = Integer.parseInt(ConfigReader.get("timeout"));

    public static void waitForElement(WebDriver driver, By locator){
        new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT))
            .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void waitForTitleToContains(WebDriver driver, String title){
        new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT)).until(ExpectedConditions.titleContains(title));
    }

    public static void waitForPageLoad(WebDriver driver){
        new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT))
            .until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd)
                .executeScript("return document.readyState").equals("complete"));
    }

    public static boolean isElementVisible(WebDriver driver, By locator){
        try{
            new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));

            return true;
        } catch (TimeoutException e){
            return false;
        }
    }
}
