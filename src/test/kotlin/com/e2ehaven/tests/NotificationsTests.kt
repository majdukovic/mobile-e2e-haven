
package com.e2ehaven.tests

import com.e2ehaven.BaseTest
import com.e2ehaven.pageobjects.LoginPage
import org.testng.Assert
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

/**
 * Tests for the notifications functionality
 */
class NotificationsTests : BaseTest() {
    
    override fun getAppPackage(): String = "com.example.app"
    override fun getAppActivity(): String = "com.example.app.activities.LoginActivity"
    override fun getBundleId(): String = "com.example.app"
    
    @BeforeMethod
    fun loginBeforeTest() {
        // Login before each test
        val loginPage = LoginPage(driver)
        loginPage.login("testuser", "password123")
    }
    
    @Test(description = "Verify notifications page displays correctly")
    fun testNotificationsPageDisplay() {
        val dashboardPage = LoginPage(driver).login("testuser", "password123")
        val notificationsPage = dashboardPage.openNotifications()
        
        Assert.assertTrue(notificationsPage.isNotificationsPageDisplayed(), "Notifications page should be displayed")
    }
    
    @Test(description = "Verify empty notifications state")
    fun testEmptyNotificationsState() {
        val dashboardPage = LoginPage(driver).login("testuser", "password123")
        val notificationsPage = dashboardPage.openNotifications()
        
        // Clear any existing notifications
        notificationsPage.clearAllNotifications()
        
        // Verify empty state message is displayed
        Assert.assertTrue(notificationsPage.isEmptyNotificationsMessageDisplayed(), "Empty notifications message should be displayed")
        Assert.assertEquals(notificationsPage.getEmptyNotificationsText(), "No notifications", "Empty notifications text should match")
    }
    
    @Test(description = "Verify navigation back to dashboard")
    fun testNavigationBackToDashboard() {
        val dashboardPage = LoginPage(driver).login("testuser", "password123")
        val notificationsPage = dashboardPage.openNotifications()
        
        // Navigate back to dashboard
        val returnedDashboard = notificationsPage.goBack()
        
        // Verify returned to dashboard
        Assert.assertTrue(returnedDashboard.isDashboardPageDisplayed(), "Should return to dashboard page after pressing back")
    }
}
