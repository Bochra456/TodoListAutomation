# TodoList Automation — Test Suite

Automated testing project for the React TodoList application, using Selenium, Cucumber, and JUnit/TestNG.

## Table of Contents

* [Prerequisites](#prerequisites)
* [Setup — React Application](#setup--react-application)
* [Running the Tests](#running-the-tests)
* [Viewing the Report](#viewing-the-report)
* [Project Structure](#project-structure)
* [Bonus — UI Improvements](#bonus--ui-improvements)

## Prerequisites

- **Java**: JDK 21.0.10
- **Maven**: 3.9.16
- **Node.js**: 14.21.3
- **Browser**: Google Chrome

### Additional dependencies

- Selenium WebDriver
- Cucumber
- JUnit or TestNG
- WebDriverManager
- ExtentReport (HTML)

### Verify installations

```bash
java -version
mvn -version
node -version
```

## Setup - React Application

Clone the React project and launch the front-end application.

From the front-end project folder (`TodoList`):

```bash
cd C:\Users\PC\Desktop\formation\react-todolist-qa-master\react-todolist-qa
npm install
npm start
```

The application will be accessible at `http://localhost:3000/` (or the port shown in the console).

> ⚠️ **Important**: The application must be started and accessible before running the tests, since they rely on the app's URL (see `tasksPage.goToUrl()`).

## Running the Tests

From the test project folder (`TodoListAutomation`):

```bash
cd TodoListAutomation
mvn clean test
```

This command will:

1. Compile the Java project
2. Launch Cucumber with the `.feature` files located in `src/test/resources/features`
3. Execute the associated step definitions (`TasksStepdefinition.java`)
4. Drive the Chrome browser via Selenium to interact with the application

### Running a subset of scenarios (tags)

If scenarios are tagged (e.g., `@modify`, `@add`, `@delete`), it is possible to filter their execution:

Right-click on the file `RunWebSuiteTest.java`, then select **Run as** → **1 JUnit Test**.

## Viewing the Report

After execution, Cucumber generates an HTML report (depending on the `@CucumberOptions` / runner configuration), generally available at:

```
target/cucumber-html-reports/index.html
```

*(path may vary if a reporting plugin such as cucumber-reporting / Cluecumber is configured)*

Open this file in a browser to view:

- The number of scenarios executed
- Passed / failed scenarios
- The detail of each step (Given/When/Then), with screenshots on failure (if configured)

A plain text summary is also available directly in the console after running `mvn test`.

### Example Test Results

| Test | Result |
|---|:---:|
| Submit button disabled if fields are empty | ❌ FAIL |
| Add button disabled if field is empty | ❌ FAIL |
| Wrong login → red message | ✅ PASS |
| localStorage updated after adding | ✅ PASS |
| localStorage updated after deletion | ✅ PASS |
| Tasks hidden before login | ❌ FAIL |
| Logout hidden before login | ❌ FAIL |
| Home hidden before login | ❌ FAIL |
| Home hidden after login | ❌ FAIL |
| "Add task" button is disabled before adding a task | ❌ FAIL |
| "Modify" button is displayed on the home page | ❌ FAIL |
| Red message appears when login credentials are incorrect | ❌ FAIL |

> This is a perfectly valid result — the tests have revealed real anomalies in the application.

## Project Structure

```
TodoListAutomation/
├── src/test/java/Step_definitions/TasksStepdefinition.java   # Step definitions
├── src/test/java/Pages/TasksPage.java                        # Page Object (xpath, css selectors, Selenium actions)
├── src/spec/features/feature_fils/tasks.feature   # Gherkin scenarios
└── pom.xml                                                    # Maven dependencies
```

## Bonus - UI Improvements

- [ ] Disable the "Submit" button when the form is invalid
- [ ] Disable the "Add task" button when the fields are empty
- [ ] Show "Tasks," "Home," and "Logout" only after login
- [ ] Display a counter of remaining tasks
- [ ] Add a confirmation dialog before deleting a task
- [ ] Measure application loading and response times to verify performance meets expected thresholds
- [ ] Add a "forgot password" feature (without a dedicated page) on the login page, linked to a 30-minute activation token sent by email
- [ ] Add a "create account" feature on the login page
- [ ] Add an "edit" feature in the task bar of the home page