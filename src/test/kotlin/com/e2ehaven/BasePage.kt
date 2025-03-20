
package com.e2ehaven

import io.appium.java_client.AppiumDriver
import io.appium.java_client.AppiumBy
import io.appium.java_client.pagefactory.AppiumFieldDecorator
import org.openqa.selenium.By
import org.openqa.selenium.NoSuchElementException
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

/**
 * Base class for all page objects.
 * Provides common interactions and utilities for page objects.
 */
open class BasePage(protected val driver: AppiumDriver) {
    init {
        PageFactory.initElements(AppiumFieldDecorator(driver), this)
    }
    
    private val defaultWaitTime: Duration = Duration.ofSeconds(15)
    
    /**
     * Waits for an element to be visible
     */
    protected fun waitForElementVisible(element: WebElement, timeout: Duration = defaultWaitTime): WebElement {
        val wait = WebDriverWait(driver, timeout)
        return wait.until(ExpectedConditions.visibilityOf(element))
    }
    
    /**
     * Waits for an element to be clickable
     */
    protected fun waitForElementClickable(element: WebElement, timeout: Duration = defaultWaitTime): WebElement {
        val wait = WebDriverWait(driver, timeout)
        return wait.until(ExpectedConditions.elementToBeClickable(element))
    }
    
    /**
     * Safely clicks on an element after ensuring it's clickable
     */
    protected fun click(element: WebElement) {
        waitForElementClickable(element).click()
    }
    
    /**
     * Safely enters text into an element after ensuring it's visible
     */
    protected fun sendKeys(element: WebElement, text: String) {
        val el = waitForElementVisible(element)
        el.clear()
        el.sendKeys(text)
    }
    
    /**
     * Checks if an element is displayed
     */
    protected fun isElementDisplayed(element: WebElement): Boolean {
        return try {
            element.isDisplayed
        } catch (e: NoSuchElementException) {
            false
        }
    }
    
    /**
     * Gets text from an element after ensuring it's visible
     */
    protected fun getText(element: WebElement): String {
        return waitForElementVisible(element).text
    }
    
    /**
     * Swipes from one point to another
     */
    protected fun swipe(startX: Int, startY: Int, endX: Int, endY: Int, duration: Int) {
        driver.executeScript(
            "mobile: dragFromToForDuration", 
            mapOf(
                "fromX" to startX, 
                "fromY" to startY, 
                "toX" to endX, 
                "toY" to endY, 
                "duration" to duration
            )
        )
    }
    
    /**
     * Scrolls down until an element with the given text is found
     */
    protected fun scrollToText(text: String) {
        val isAndroid = driver.capabilities.platformName.toString().equals("android", ignoreCase = true)
        
        if (isAndroid) {
            val scrollableScript = "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().textContains(\"$text\"))"
            driver.findElement(AppiumBy.androidUIAutomator(scrollableScript))
        } else {
            // iOS implementation
            val elementId = driver.findElement(By.className("XCUIElementTypeScrollView")).id
            
            // Use correct format for executeScript with a single map argument
            val scrollObject = mapOf(
                "element" to elementId,
                "predicateString" to "name CONTAINS '$text'"
            )
            driver.executeScript("mobile: scroll", scrollObject)
        }
    }
    
    /**
     * Takes a screenshot with a given filename
     */
    fun takeScreenshot(fileName: String) {
        logger.info("Taking screenshot: $fileName")
        // Implementation would depend on the screenshot utility being used
    }
    
    companion object {
        private val logger = java.util.logging.Logger.getLogger(BasePage::class.java.name)
    }
}
