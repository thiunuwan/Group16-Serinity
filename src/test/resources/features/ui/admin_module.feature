Feature: User Management in Admin Tab

  @ui
  @loginAsAdmin
  Scenario: Add a new user
    Given I am on the "Admin" page
    When I add a new user with username "Akash545" and password "test123" and role "ESS"
    Then I should see the user (testUser) "Akash545" in the user list

  @ui
  @loginAsAdmin
  Scenario: search an registered user
    Given I am on the "Admin" page
    And I add a new user with username "Akash545" and password "test123" and role "ESS"
    When I search the user named "Akash545"
    Then I should see the user (testUser) "Akash545" in the user list
