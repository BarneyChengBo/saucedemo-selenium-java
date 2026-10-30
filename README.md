# saucedemo-selenium-java

[![Tests](https://github.com/BarneyChengBo/saucedemo-selenium-java/actions/workflows/tests.yml/badge.svg?branch=main)](https://github.com/BarneyChengBo/saucedemo-selenium-java/actions/workflows/tests.yml)

End-to-end UI test automation for the [Sauce Demo](https://www.saucedemo.com) website, built as part of my test automation portfolio.

📊 **[Latest test report (Allure)](https://barneychengbo.github.io/saucedemo-selenium-java/)**

## Tech stack

Java 25, Maven (Maven Wrapper), Selenium WebDriver, JUnit (Jupiter), AssertJ, Page Object Model, GitHub Actions

## Test coverage

- **Smoke test**
- **Login:** valid login and 4 negative cases (parameterized)
- **Product sorting:** name A–Z / Z–A, price low–high / high–low
- **Cart:** badge count, removing items from the cart page and from the inventory page
- **Checkout:** required field validation (parameterized) and order total check
- **End-to-end purchase flow:** from login to order confirmation


## Project structure

- `pages/` – Page Objects, one class per page
- `BaseTest` – handles browser setup and teardown
- `ScreenshotOnFailureExtension` – saves a screenshot to `target/screenshots/` when a test fails
-  Test classes are organized by feature

## How to run

Prerequisites: JDK 25 and Chrome

```
./mvnw test
```

On Windows: `mvnw.cmd test`

Run in headless mode (no browser window):

```
./mvnw test -Dheadless=true
```

View the Allure report locally after a test run:

```
./mvnw allure:serve
```

## Continuous integration

Tests run on GitHub Actions on every push to `main`, on every pull request and on a weekly schedule. After each run on `main`, the Allure report is published to GitHub Pages. If a test fails, the failure screenshots are uploaded as a build artifact.

## Test design notes

- Negative login tests are designed with equivalence partitioning.
- The page is sorted A–Z by default, so the A–Z sorting test first switches to Z–A. Otherwise the test would pass even if sorting were broken.
- Explicit waits are used instead of `Thread.sleep`.
- Flaky tests were investigated by repeating test runs. One root cause was Chrome's password breach popup blocking clicks, which is now disabled in `BaseTest`.

## Bug reports

Exploratory testing with `problem_user` found several defects, including a blocking issue in checkout. 
They are reported as [GitHub Issues](https://github.com/BarneyChengBo/saucedemo-selenium-java/issues) with steps to reproduce, expected and actual results, severity and screenshots.


