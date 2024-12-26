Feature: Recruitment management in candidate module

  @ui
  @loginAsAdmin
  Scenario: Add a new candidate
    Given I am in the "Recruitment" page
    When I add a new candidate with username "Akash545" and password "test123" and role "ESS"
    Then I should see the candidate (testUser) "Akash545" in the user list