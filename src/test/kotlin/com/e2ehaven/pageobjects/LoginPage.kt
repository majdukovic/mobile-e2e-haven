
package com.e2ehaven.pageobjects

import com.e2ehaven.BasePage
import io.appium.java_client.AppiumDriver
import io.appium.java_client.pagefactory.AndroidFindBy
import io.appium.java_client.pagefactory.iOSXCUITFindBy
import org.openqa.selenium.WebElement

/**
 * Page Object representing the Login screen
 */
class LoginPage(driver: AppiumDriver) : BasePage(driver) {
    
    @AndroidFindBy(id = "com.example.app:id/usernameInput")
    @iOSXCUITFindBy(accessibility = "username_input")
    private lateinit var usernameField: WebElement
    
    @AndroidFindBy(id = "com.example.app:id/passwordInput")
    @iOSXCUITFindBy(accessibility = "password_input")
    private lateinit var passwordField: WebElement
    
    @AndroidFindBy(id = "com.example.app:id/loginButton")
    @iOSXCUITFindBy(accessibility = "login_button")
    private lateinit var loginButton: WebElement
    
    @AndroidFindBy(id = "com.example.app:id/errorMessage")
    @iOSXCUITFindBy(accessibility = "error_message")
    private lateinit var errorMessage: WebElement
    
    /**
     * Enters username in the username field
     */
    fun enterUsername(username: String): LoginPage {
        sendKeys(usernameField, username)
        return this
    }
    
    /**
     * Enters password in the password field
     */
    fun enterPassword(password: String): LoginPage {
        sendKeys(passwordField, password)
        return this
    }
    
    /**
     * Clicks the login button
     */
    fun clickLoginButton(): DashboardPage {
        click(loginButton)
        return DashboardPage(driver)
    }
    
    /**
     * Performs login with given credentials
     */
    fun login(username: String, password: String): DashboardPage {
        enterUsername(username)
        enterPassword(password)
        return clickLoginButton()
    }
    
    /**
     * Checks if error message is displayed
     */
    fun isErrorMessageDisplayed(): Boolean {
        return isElementDisplayed(errorMessage)
    }
    
    /**
     * Gets the error message text
     */
    fun getErrorMessage(): String {
        return getText(errorMessage)
    }
    
    /**
     * Verifies that the login page is displayed
     */
    fun isLoginPageDisplayed(): Boolean {
        return isElementDisplayed(usernameField) && isElementDisplayed(passwordField) && isElementDisplayed(loginButton)
    }
}
