Feature: Login
  I want to verify that a user can successfully log in with valid credentials

  @ui
  Scenario: Successful Login
    Given I navigate to the login page
    When I enter valid credentials
    Then I should see the dashboard
