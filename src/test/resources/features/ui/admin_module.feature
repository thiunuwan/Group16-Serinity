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

  @ui
  @loginAsAdmin
  Scenario: Create user with existing username
    Given I am on the "Admin" page
    And the user test exists with username "Akash5328" and password "test123" and role "ESS"
    When I try to add a new user with same username "Akash545" and password "test456" and role "Admin"
    Then I should see an error message "Username already exists"


