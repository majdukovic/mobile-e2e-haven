
package com.e2ehaven.tests

import com.e2ehaven.BaseTest
import com.e2ehaven.pageobjects.LoginPage
import org.testng.Assert
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

/**
 * Tests for the settings functionality
 */
class SettingsTests : BaseTest() {
    
    override fun getAppPackage(): String = "com.example.app"
    override fun getAppActivity(): String = "com.example.app.activities.LoginActivity"
    override fun getBundleId(): String = "com.example.app"
    
    @BeforeMethod
    fun loginBeforeTest() {
        // Login before each test
        val loginPage = LoginPage(driver)
        loginPage.login("testuser", "password123")
    }
    
    @Test(description = "Verify dark mode toggle functionality")
    fun testDarkModeToggle() {
        val dashboardPage = LoginPage(driver).login("testuser", "password123")
        val settingsPage = dashboardPage.navigateToSettings()
        
        // Check initial state
        val initialDarkModeState = settingsPage.isDarkModeEnabled()
        
        // Toggle dark mode
        settingsPage.toggleDarkMode()
        
        // Verify the state has changed
        val newDarkModeState = settingsPage.isDarkModeEnabled()
        Assert.assertNotEquals(initialDarkModeState, newDarkModeState, "Dark mode state should change after toggle")
    }
    
    @Test(description = "Verify language selection")
    fun testLanguageSelection() {
        val dashboardPage = LoginPage(driver).login("testuser", "password123")
        val settingsPage = dashboardPage.navigateToSettings()
        
        // Navigate to language page
        val languagePage = settingsPage.openLanguageSelection()
        Assert.assertTrue(languagePage.isLanguagePageDisplayed(), "Language selection page should be displayed")
        
        // Select Spanish language
        val updatedSettings = languagePage.selectSpanish()
        
        // Verify returned to settings page
        Assert.assertTrue(updatedSettings.isSettingsPageDisplayed(), "Should return to settings page after language selection")
        
        // Additional verification would check that UI elements are now in Spanish
        // This would depend on how your app handles localization
    }
    
    @Test(description = "Verify navigation back to dashboard")
    fun testNavigationBackToDashboard() {
        val dashboardPage = LoginPage(driver).login("testuser", "password123")
        val settingsPage = dashboardPage.navigateToSettings()
        
        // Navigate back to dashboard
        val returnedDashboard = settingsPage.goBack()
        
        // Verify returned to dashboard
        Assert.assertTrue(returnedDashboard.isDashboardPageDisplayed(), "Should return to dashboard page after pressing back")
    }
}
