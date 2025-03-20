
package com.e2ehaven.pageobjects

import com.e2ehaven.BasePage
import io.appium.java_client.AppiumDriver
import io.appium.java_client.pagefactory.AndroidFindBy
import io.appium.java_client.pagefactory.iOSXCUITFindBy
import org.openqa.selenium.WebElement

/**
 * Page Object representing the Settings screen
 */
class SettingsPage(driver: AppiumDriver) : BasePage(driver) {
    
    @AndroidFindBy(id = "com.example.app:id/settingsTitle")
    @iOSXCUITFindBy(accessibility = "settings_title")
    private lateinit var settingsTitle: WebElement
    
    @AndroidFindBy(id = "com.example.app:id/darkModeToggle")
    @iOSXCUITFindBy(accessibility = "dark_mode_toggle")
    private lateinit var darkModeToggle: WebElement
    
    @AndroidFindBy(id = "com.example.app:id/notificationsToggle")
    @iOSXCUITFindBy(accessibility = "notifications_toggle")
    private lateinit var notificationsToggle: WebElement
    
    @AndroidFindBy(id = "com.example.app:id/languageSelector")
    @iOSXCUITFindBy(accessibility = "language_selector")
    private lateinit var languageSelector: WebElement
    
    @AndroidFindBy(id = "com.example.app:id/backButton")
    @iOSXCUITFindBy(accessibility = "back_button")
    private lateinit var backButton: WebElement
    
    /**
     * Verifies that the settings page is displayed
     */
    fun isSettingsPageDisplayed(): Boolean {
        return isElementDisplayed(settingsTitle)
    }
    
    /**
     * Toggles dark mode on/off
     */
    fun toggleDarkMode(): SettingsPage {
        click(darkModeToggle)
        return this
    }
    
    /**
     * Toggles notifications on/off
     */
    fun toggleNotifications(): SettingsPage {
        click(notificationsToggle)
        return this
    }
    
    /**
     * Opens language selection
     */
    fun openLanguageSelection(): LanguagePage {
        click(languageSelector)
        return LanguagePage(driver)
    }
    
    /**
     * Returns to previous screen
     */
    fun goBack(): DashboardPage {
        click(backButton)
        return DashboardPage(driver)
    }
    
    /**
     * Checks if dark mode is enabled
     */
    fun isDarkModeEnabled(): Boolean {
        // Implementation would depend on how the toggle state is represented
        // This is a simplified example
        return darkModeToggle.getAttribute("checked") == "true"
    }
}
