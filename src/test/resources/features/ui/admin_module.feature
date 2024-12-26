Feature: User Management in Admin Tab

  @ui
  @loginAsAdmin
  Scenario: Add a new user
    Given I am on the "Admin" page
    When I add a new user with username "Akash544" and password "test123" and role "ESS"
    Then I should see the user (testUser) "Akash544" in the user list
