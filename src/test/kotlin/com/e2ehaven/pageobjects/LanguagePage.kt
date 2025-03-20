
package com.e2ehaven.pageobjects

import com.e2ehaven.BasePage
import io.appium.java_client.AppiumDriver
import io.appium.java_client.pagefactory.AndroidFindBy
import io.appium.java_client.pagefactory.iOSXCUITFindBy
import org.openqa.selenium.WebElement

/**
 * Page Object representing the Language selection screen
 */
class LanguagePage(driver: AppiumDriver) : BasePage(driver) {
    
    @AndroidFindBy(id = "com.example.app:id/languageTitle")
    @iOSXCUITFindBy(accessibility = "language_title")
    private lateinit var languageTitle: WebElement
    
    @AndroidFindBy(id = "com.example.app:id/englishOption")
    @iOSXCUITFindBy(accessibility = "english_option")
    private lateinit var englishOption: WebElement
    
    @AndroidFindBy(id = "com.example.app:id/spanishOption")
    @iOSXCUITFindBy(accessibility = "spanish_option")
    private lateinit var spanishOption: WebElement
    
    @AndroidFindBy(id = "com.example.app:id/frenchOption")
    @iOSXCUITFindBy(accessibility = "french_option")
    private lateinit var frenchOption: WebElement
    
    @AndroidFindBy(id = "com.example.app:id/backButton")
    @iOSXCUITFindBy(accessibility = "back_button")
    private lateinit var backButton: WebElement
    
    /**
     * Verifies that the language page is displayed
     */
    fun isLanguagePageDisplayed(): Boolean {
        return isElementDisplayed(languageTitle)
    }
    
    /**
     * Selects English language
     */
    fun selectEnglish(): SettingsPage {
        click(englishOption)
        return SettingsPage(driver)
    }
    
    /**
     * Selects Spanish language
     */
    fun selectSpanish(): SettingsPage {
        click(spanishOption)
        return SettingsPage(driver)
    }
    
    /**
     * Selects French language
     */
    fun selectFrench(): SettingsPage {
        click(frenchOption)
        return SettingsPage(driver)
    }
    
    /**
     * Returns to the settings screen
     */
    fun goBack(): SettingsPage {
        click(backButton)
        return SettingsPage(driver)
    }
}
