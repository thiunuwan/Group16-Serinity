Feature: User Management in Admin Tab

  @ui
  @loginAsAdmin
  Scenario: Add a new user
    Given I am on the "Admin" page
    When I add a new user with username and password "test123" and role "ESS"
    Then I should see the added user in the user list

  @ui
  @loginAsAdmin
  Scenario: Delete a user
    Given I am on the "Admin" page
    And the user test exists with username "Akash548" and password "test123" and role "ESS"
    When I delete the user with username "Akash548"
    Then I should not see the user (testUser) "Akash548" in the user list