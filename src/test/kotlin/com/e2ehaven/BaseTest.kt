
package com.e2ehaven

import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.ios.IOSDriver
import io.appium.java_client.service.local.AppiumDriverLocalService
import io.appium.java_client.service.local.AppiumServiceBuilder
import org.openqa.selenium.remote.DesiredCapabilities
import org.testng.annotations.AfterClass
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeClass
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Parameters
import java.io.File
import java.net.URL
import java.time.Duration

/**
 * Base class for all test classes providing common setup and teardown functionality.
 * Handles Appium server startup/shutdown and driver initialization based on platform.
 */
abstract class BaseTest {
    protected lateinit var driver: AppiumDriver
    private lateinit var appiumService: AppiumDriverLocalService
    
    /**
     * Starts the Appium server before any tests run
     */
    @BeforeClass
    fun setupAppiumServer() {
        val appiumJsPath = System.getenv("APPIUM_JS_PATH") ?: "/usr/local/lib/node_modules/appium/build/lib/main.js"
        val builder = AppiumServiceBuilder()
            .withAppiumJS(File(appiumJsPath))
            .usingPort(4723)
            .withIPAddress("127.0.0.1")
            .withTimeout(Duration.ofSeconds(30))
        
        appiumService = AppiumDriverLocalService.buildService(builder)
        appiumService.start()
        
        println("Appium server started on: ${appiumService.url}")
    }
    
    /**
     * Initializes the driver before each test method
     * @param platform either "android" or "ios"
     */
    @BeforeMethod
    @Parameters("platform")
    fun setupDriver(platform: String) {
        val caps = DesiredCapabilities()
        
        when (platform.lowercase()) {
            "android" -> {
                caps.setCapability("platformName", "Android")
                caps.setCapability("automationName", "UiAutomator2")
                caps.setCapability("deviceName", "Android Device")
                caps.setCapability("appPackage", getAppPackage())
                caps.setCapability("appActivity", getAppActivity())
                caps.setCapability("noReset", false)
                
                // For installed app testing
                if (System.getProperty("app") != null) {
                    caps.setCapability("app", System.getProperty("app"))
                }
                
                driver = AndroidDriver(appiumService.url, caps)
            }
            "ios" -> {
                caps.setCapability("platformName", "iOS")
                caps.setCapability("automationName", "XCUITest")
                caps.setCapability("deviceName", "iPhone Simulator")
                caps.setCapability("bundleId", getBundleId())
                caps.setCapability("noReset", false)
                
                // For installed app testing
                if (System.getProperty("app") != null) {
                    caps.setCapability("app", System.getProperty("app"))
                }
                
                driver = IOSDriver(appiumService.url, caps)
            }
            else -> throw IllegalArgumentException("Unsupported platform: $platform. Use 'android' or 'ios'")
        }
        
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10))
    }
    
    /**
     * Quits the driver after each test method
     */
    @AfterMethod
    fun tearDownDriver() {
        if (::driver.isInitialized) {
            driver.quit()
        }
    }
    
    /**
     * Stops the Appium server after all tests complete
     */
    @AfterClass
    fun tearDownAppiumServer() {
        if (::appiumService.isInitialized && appiumService.isRunning) {
            appiumService.stop()
            println("Appium server stopped")
        }
    }
    
    /**
     * Abstract methods to be implemented by platform-specific test classes
     */
    protected abstract fun getAppPackage(): String
    protected abstract fun getAppActivity(): String
    protected abstract fun getBundleId(): String
}
