# Selenium Test Automation Framework helm_qa_aut_assessment

## Introduction

This project is a Selenium-based automation framework designed for end-to-end testing of the SauceDemo web application.
The framework uses TestNG for test execution, Maven for dependency management, and Allure for detailed reporting.

## Requirements

Ensure the following tools are installed on your system:
- **Java 11**: Verify installation with `java -version`.
- **Maven**: Verify installation with `mvn -version`.
- **Git**: Verify installation with `git --version`.
- **Chrome Browser**: Latest version is recommended.
- **ChromeDriver**: Managed automatically using WebDriverManager.

### Verifying Installation
Run the following commands to ensure the required tools are installed:
```bash
java -version
mvn -version
git --version
```

## Setup

Follow these steps to set up your environment:

1. **Clone the Repository**:
   ```bash
   git clone <https://github.com/fernando1496/helm_qa_aut_assessment>
   cd <repository_folder>
   ```

2. **Install Dependencies**:
   Maven will automatically install dependencies when you run the tests, but you can verify by running:
   ```bash
   mvn dependency:resolve
   ```

3. **Set Up the Environment File**:
   Create a `.env` file in the root directory of the project and add the following variables:
   ```env
   VALID_USERNAME=standard_user
   VALID_PASSWORD=secret_sauce
   LOCKED_USERNAME=locked_out_user
   ```
   **Note**: Storing sensitive information like environment variables in `.env` files is acceptable for this challenge. However, for production, use tools like [1Password](https://1password.com/) or [LastPass](https://www.lastpass.com/).

4. **Build the Project**:
   Run the following command to build the project:
   ```bash
   mvn clean compile
   ```

## How to Run the Tests

1. **Execute Tests**:
   Run the tests using Maven:
   ```bash
   mvn clean test
   ```

2. **Generate Allure Report**:
   After the tests are executed, generate the Allure report:
   ```bash
   allure serve target/allure-results
   ```

   This will open the Allure report in your default browser.

## Improvements

- **Behavior-Driven Development (BDD)**: Implement test scenarios using Cucumber or JBehave for improved readability and collaboration.
- **Parallel Execution**: Enhance the framework to support parallel test execution for faster feedback.
- **Cross-Browser Testing**: Extend support for additional browsers like Firefox, Edge, and Safari.
- **Continuous Integration/Continuous Deployment (CI/CD)**: Integrate with Jenkins, GitHub Actions, or CircleCI for automated test execution on code changes.
- **Enhanced Reporting**: Include additional details in the Allure reports, such as screenshots on failure.

## Conclusion

This framework is designed to be scalable, modular, and easy to use, providing a solid foundation for web automation testing.
Feel free to fork this repository and suggest enhancements to make it even better!

