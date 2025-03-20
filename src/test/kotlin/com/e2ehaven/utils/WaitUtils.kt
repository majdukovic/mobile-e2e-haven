
package com.e2ehaven.utils

import io.appium.java_client.AppiumDriver
import org.openqa.selenium.By
import org.openqa.selenium.TimeoutException
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

/**
 * Utility class for implementing various wait strategies
 */
object WaitUtils {
    private val DEFAULT_TIMEOUT = Duration.ofSeconds(15)
    
    /**
     * Waits for an element to be present in the DOM
     */
    fun waitForElementPresence(driver: AppiumDriver, locator: By, timeout: Duration = DEFAULT_TIMEOUT): WebElement {
        val wait = WebDriverWait(driver, timeout)
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator))
    }
    
    /**
     * Waits for an element to be visible
     */
    fun waitForElementVisibility(driver: AppiumDriver, locator: By, timeout: Duration = DEFAULT_TIMEOUT): WebElement {
        val wait = WebDriverWait(driver, timeout)
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator))
    }
    
    /**
     * Waits for an element to become invisible
     */
    fun waitForElementInvisibility(driver: AppiumDriver, locator: By, timeout: Duration = DEFAULT_TIMEOUT): Boolean {
        val wait = WebDriverWait(driver, timeout)
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator))
    }
    
    /**
     * Waits for an element to be clickable
     */
    fun waitForElementClickable(driver: AppiumDriver, locator: By, timeout: Duration = DEFAULT_TIMEOUT): WebElement {
        val wait = WebDriverWait(driver, timeout)
        return wait.until(ExpectedConditions.elementToBeClickable(locator))
    }
    
    /**
     * Waits for text to be present in an element
     */
    fun waitForTextPresent(driver: AppiumDriver, locator: By, text: String, timeout: Duration = DEFAULT_TIMEOUT): Boolean {
        val wait = WebDriverWait(driver, timeout)
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text))
    }
    
    /**
     * Checks if an element exists without waiting the full timeout
     */
    fun isElementPresent(driver: AppiumDriver, locator: By, timeout: Duration = Duration.ofSeconds(5)): Boolean {
        try {
            waitForElementPresence(driver, locator, timeout)
            return true
        } catch (e: TimeoutException) {
            return false
        }
    }
    
    /**
     * Waits for a specified time
     */
    fun staticWait(milliseconds: Long) {
        try {
            Thread.sleep(milliseconds)
        } catch (e: InterruptedException) {
            Thread.currentThread().interrupt()
        }
    }
    
    /**
     * Waits for a page to load completely
     */
    fun waitForPageLoad(driver: AppiumDriver, timeout: Duration = DEFAULT_TIMEOUT) {
        val wait = WebDriverWait(driver, timeout)
        wait.until { jsExecutor ->
            val result = (driver).executeScript("return document.readyState") as String
            result == "complete"
        }
    }
}
