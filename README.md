
# Mobile App Test Automation Framework

A comprehensive Appium-based test automation framework for testing mobile applications on both iOS and Android platforms. This framework is written in Kotlin and follows the Page Object Model design pattern.

## Features

- **Cross-Platform Testing**: Test both iOS and Android applications using a single codebase
- **Page Object Model**: Clean separation of test code from page-specific code
- **Robust Base Classes**: Common functionality abstracted in base classes
- **Flexible Configuration**: Easy to configure for different environments and test suites
- **Detailed Reporting**: Comprehensive test reports with screenshots for failures
- **Parallel Execution**: Support for parallel test execution

## Project Structure

```
src/
├── test/
│   ├── kotlin/
│   │   └── com/
│   │       └── e2ehaven/
│   │           ├── BaseTest.kt              # Base test class with common setup/teardown
│   │           ├── BasePage.kt              # Base page object with common interactions
│   │           ├── pageobjects/             # Page object classes
│   │           │   ├── LoginPage.kt
│   │           │   ├── DashboardPage.kt
│   │           │   ├── SettingsPage.kt
│   │           │   ├── NotificationsPage.kt
│   │           │   └── LanguagePage.kt
│   │           ├── tests/                   # Test classes
│   │           │   ├── LoginTests.kt
│   │           │   ├── SettingsTests.kt
│   │           │   └── NotificationsTests.kt
│   │           └── utils/                   # Utility classes
│   │               ├── TestListener.kt      # TestNG listeners for reporting
│   │               └── WaitUtils.kt         # Wait utility functions
│   └── resources/
│       ├── testng.xml                       # TestNG configuration
│       └── logback.xml                      # Logging configuration
└── main/
    └── kotlin/
        └── com/
            └── e2ehaven/
                └── Main.kt                  # Main application entry point (if needed)
```

## Prerequisites

- JDK 11 or higher
- Appium 2.0 or higher
- Android SDK (for Android testing)
- Xcode (for iOS testing)
- Real devices or emulators/simulators
- Kotlin Multiplatform app to test

## Getting Started

1. Clone this repository
2. Install the required dependencies:
   ```
   ./gradlew build
   ```
3. Configure the test properties in `src/test/resources/config.properties` (create if not exists)
4. Start the Appium server (or let the framework handle it)
5. Run the tests:
   ```
   ./gradlew test
   ```

## Running Specific Tests

To run a specific test suite:

```
./gradlew test -Dsuite=android
```

To run a specific test class:

```
./gradlew test -Dtest.single=LoginTests
```

## Configuration

The framework can be configured using environment variables or a properties file:

- `APPIUM_JS_PATH`: Path to Appium executable
- `DEVICE_NAME`: Target device name
- `PLATFORM_NAME`: Target platform (android/ios)
- `APP_PATH`: Path to the application (.apk or .ipa)

## Adding New Tests

1. Create a new Page Object class in the `pageobjects` directory
2. Implement the necessary page interactions and verifications
3. Create a new Test class in the `tests` directory
4. Use the Page Object classes in your test methods

## Best Practices

- Keep page objects focused on a single page or component
- Use meaningful names for page objects and test methods
- Make tests independent and self-contained
- Use test data providers for data-driven testing
- Add descriptive comments and follow Kotlin coding conventions

## Reporting

Test reports are generated in the `build/reports/tests` directory after test execution.

## Logging

Logs are saved to the `logs` directory. Configuration can be modified in `src/test/resources/logback.xml`.
