# saucedemo-selenium-java

End-to-end UI test automation for the [Sauce Demo](https://www.saucedemo.com) website, built as part of my test automation portfolio.

## Tech stack

Java 25, Maven (Maven Wrapper), Selenium WebDriver, JUnit (Jupiter), AssertJ, Page Object Model

## Test coverage

- **Smoke test**
- **Login:** valid login and 4 negative cases (parameterized)
- **Product sorting:** name A–Z / Z–A, price low–high / high–low
- **Cart:** badge count, removing items from the cart page and from the inventory page
- **End-to-end purchase flow:** from login to order confirmation

## Project structure

- `pages/` – Page Objects, one class per page
- `BaseTest` – handles browser setup and teardown
- Test classes are organized by feature

## How to run

Prerequisites: JDK 25 and Chrome
```
./mvnw test
```
On Windows: `mvnw.cmd test`

## Test design notes

- Negative login tests are designed with equivalence partitioning.
- The page is sorted A–Z by default, so the A–Z sorting test first switches to Z–A. Otherwise the test would pass even if sorting were broken.
- Explicit waits are used instead of `Thread.sleep`.
- Flaky tests were investigated by repeating test runs. One root cause was Chrome's password breach popup blocking clicks, which is now disabled in `BaseTest`.
- Bug reports for issues found with `problem_user`

## Work in progress

- Checkout tests (required fields, order total)
- Screenshots on failure and headless mode
- CI with GitHub Actions
