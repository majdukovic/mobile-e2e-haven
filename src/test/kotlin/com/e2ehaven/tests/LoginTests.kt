
package com.e2ehaven.tests

import com.e2ehaven.BaseTest
import com.e2ehaven.pageobjects.LoginPage
import org.testng.Assert
import org.testng.annotations.Test

/**
 * Tests for the login functionality
 */
class LoginTests : BaseTest() {
    
    override fun getAppPackage(): String = "com.example.app"
    override fun getAppActivity(): String = "com.example.app.activities.LoginActivity"
    override fun getBundleId(): String = "com.example.app"
    
    @Test(description = "Verify successful login with valid credentials")
    fun testSuccessfulLogin() {
        val loginPage = LoginPage(driver)
        val dashboardPage = loginPage
            .enterUsername("testuser")
            .enterPassword("password123")
            .clickLoginButton()
        
        Assert.assertTrue(dashboardPage.isDashboardPageDisplayed(), "Dashboard page should be displayed after successful login")
        
        val greetingText = dashboardPage.getUserGreetingText()
        Assert.assertTrue(greetingText.contains("testuser"), "Greeting should contain the username")
    }
    
    @Test(description = "Verify login failure with invalid credentials")
    fun testFailedLogin() {
        val loginPage = LoginPage(driver)
        loginPage
            .enterUsername("wronguser")
            .enterPassword("wrongpassword")
            .clickLoginButton()
        
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed")
        Assert.assertEquals(loginPage.getErrorMessage(), "Invalid username or password", "Error message text should match")
    }
    
    @Test(description = "Verify login with empty credentials")
    fun testEmptyCredentialsLogin() {
        val loginPage = LoginPage(driver)
        loginPage
            .enterUsername("")
            .enterPassword("")
            .clickLoginButton()
        
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed")
        Assert.assertEquals(loginPage.getErrorMessage(), "Username and password are required", "Error message for empty fields should match")
    }
}
