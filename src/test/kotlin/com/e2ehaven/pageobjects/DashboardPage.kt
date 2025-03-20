
package com.e2ehaven.pageobjects

import com.e2ehaven.BasePage
import io.appium.java_client.AppiumDriver
import io.appium.java_client.pagefactory.AndroidFindBy
import io.appium.java_client.pagefactory.iOSXCUITFindBy
import org.openqa.selenium.WebElement

/**
 * Page Object representing the Dashboard screen
 */
class DashboardPage(driver: AppiumDriver) : BasePage(driver) {
    
    @AndroidFindBy(id = "com.example.app:id/userGreeting")
    @iOSXCUITFindBy(accessibility = "user_greeting")
    private lateinit var userGreeting: WebElement
    
    @AndroidFindBy(id = "com.example.app:id/menuButton")
    @iOSXCUITFindBy(accessibility = "menu_button")
    private lateinit var menuButton: WebElement
    
    @AndroidFindBy(id = "com.example.app:id/notificationIcon")
    @iOSXCUITFindBy(accessibility = "notification_icon")
    private lateinit var notificationIcon: WebElement
    
    @AndroidFindBy(id = "com.example.app:id/settingsButton")
    @iOSXCUITFindBy(accessibility = "settings_button")
    private lateinit var settingsButton: WebElement
    
    @AndroidFindBy(id = "com.example.app:id/logoutButton")
    @iOSXCUITFindBy(accessibility = "logout_button")
    private lateinit var logoutButton: WebElement
    
    /**
     * Verifies that the dashboard page is loaded correctly
     */
    fun isDashboardPageDisplayed(): Boolean {
        return isElementDisplayed(userGreeting) && isElementDisplayed(menuButton)
    }
    
    /**
     * Gets the greeting text displayed to the user
     */
    fun getUserGreetingText(): String {
        return getText(userGreeting)
    }
    
    /**
     * Opens the menu
     */
    fun openMenu(): DashboardPage {
        click(menuButton)
        // Wait for the menu to be fully displayed
        waitForElementVisible(logoutButton)
        return this
    }
    
    /**
     * Clicks on the settings button
     */
    fun navigateToSettings(): SettingsPage {
        click(settingsButton)
        return SettingsPage(driver)
    }
    
    /**
     * Performs logout
     */
    fun logout(): LoginPage {
        if (!isElementDisplayed(logoutButton)) {
            openMenu()
        }
        click(logoutButton)
        return LoginPage(driver)
    }
    
    /**
     * Clicks on the notification icon
     */
    fun openNotifications(): NotificationsPage {
        click(notificationIcon)
        return NotificationsPage(driver)
    }
}
