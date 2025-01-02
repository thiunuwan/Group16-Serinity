Feature: Login
  I want to verify that a user can successfully log in with valid credentials

  @ui
  Scenario: Successful Login
    Given I navigate to the login page
    When I enter valid credentials
    Then I should see the dashboard

  @ui
  Scenario: Invalid credentials
    Given I navigate to the login page
    When I enter invalid credentials (incorrect username or password)
    Then I should see an error message indicating incorrect login credentials

  @ui
  @loginAsAdmin
  Scenario: Successful Logout
    When I click the logout button
    Then I should be redirected to the login page

