
package com.e2ehaven.pageobjects

import com.e2ehaven.BasePage
import io.appium.java_client.AppiumDriver
import io.appium.java_client.pagefactory.AndroidFindBy
import io.appium.java_client.pagefactory.iOSXCUITFindBy
import org.openqa.selenium.WebElement

/**
 * Page Object representing the Notifications screen
 */
class NotificationsPage(driver: AppiumDriver) : BasePage(driver) {
    
    @AndroidFindBy(id = "com.example.app:id/notificationsTitle")
    @iOSXCUITFindBy(accessibility = "notifications_title")
    private lateinit var notificationsTitle: WebElement
    
    @AndroidFindBy(id = "com.example.app:id/notificationsList")
    @iOSXCUITFindBy(accessibility = "notifications_list")
    private lateinit var notificationsList: WebElement
    
    @AndroidFindBy(id = "com.example.app:id/emptyNotificationsMessage")
    @iOSXCUITFindBy(accessibility = "empty_notifications_message")
    private lateinit var emptyNotificationsMessage: WebElement
    
    @AndroidFindBy(id = "com.example.app:id/clearAllButton")
    @iOSXCUITFindBy(accessibility = "clear_all_button")
    private lateinit var clearAllButton: WebElement
    
    @AndroidFindBy(id = "com.example.app:id/backButton")
    @iOSXCUITFindBy(accessibility = "back_button")
    private lateinit var backButton: WebElement
    
    /**
     * Verifies that the notifications page is displayed
     */
    fun isNotificationsPageDisplayed(): Boolean {
        return isElementDisplayed(notificationsTitle)
    }
    
    /**
     * Checks if the empty notifications message is displayed
     */
    fun isEmptyNotificationsMessageDisplayed(): Boolean {
        return isElementDisplayed(emptyNotificationsMessage)
    }
    
    /**
     * Gets the text of the empty notifications message
     */
    fun getEmptyNotificationsText(): String {
        return getText(emptyNotificationsMessage)
    }
    
    /**
     * Clears all notifications
     */
    fun clearAllNotifications(): NotificationsPage {
        if (isElementDisplayed(clearAllButton)) {
            click(clearAllButton)
        }
        return this
    }
    
    /**
     * Returns to previous screen
     */
    fun goBack(): DashboardPage {
        click(backButton)
        return DashboardPage(driver)
    }
    
    /**
     * Checks if notifications are present
     */
    fun hasNotifications(): Boolean {
        return !isEmptyNotificationsMessageDisplayed()
    }
}
