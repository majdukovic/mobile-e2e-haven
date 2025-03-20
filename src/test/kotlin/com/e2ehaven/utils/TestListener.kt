
package com.e2ehaven.utils

import org.testng.ITestContext
import org.testng.ITestListener
import org.testng.ITestResult
import java.util.logging.Logger

/**
 * TestNG listener for test execution reporting and logging
 */
class TestListener : ITestListener {
    private val logger = Logger.getLogger(TestListener::class.java.name)
    
    override fun onTestStart(result: ITestResult) {
        logger.info("Starting test: ${result.name}")
    }
    
    override fun onTestSuccess(result: ITestResult) {
        logger.info("Test passed: ${result.name}")
    }
    
    override fun onTestFailure(result: ITestResult) {
        logger.severe("Test failed: ${result.name}")
        
        // Capture screenshot on failure
        try {
            val testClass = result.instance
            val method = testClass.javaClass.getMethod("getDriver")
            // Here you would implement screenshot capture using the driver
        } catch (e: Exception) {
            logger.severe("Failed to capture screenshot: ${e.message}")
        }
    }
    
    override fun onTestSkipped(result: ITestResult) {
        logger.warning("Test skipped: ${result.name}")
    }
    
    override fun onStart(context: ITestContext) {
        logger.info("Starting test suite: ${context.name}")
    }
    
    override fun onFinish(context: ITestContext) {
        logger.info("Test suite finished: ${context.name}")
        logger.info("Passed tests: ${context.passedTests.size()}")
        logger.info("Failed tests: ${context.failedTests.size()}")
        logger.info("Skipped tests: ${context.skippedTests.size()}")
    }
}
