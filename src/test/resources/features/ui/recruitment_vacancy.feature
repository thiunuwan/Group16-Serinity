Feature: Recruitment Vacancies
  I want to verify that the user can perform actions related to recruitment vacancies.


  @ui
  @loginAsAdmin
  Scenario: Add a new vacancy
    Given I navigate to the Recruitment Vacancies page
    When I add a new vacancy with name "Senior Software Engineer", job title "Account Assistant", hiring manager "Ranga Akunuri", and number of positions "3"
    Then I should see the vacancy (testVacancy) "Senior Software Engineer" in the vacancy list


  @ui
  @loginAsAdmin
  Scenario: Search for a vacancy
    Given I navigate to the Recruitment Vacancies page
    And I add a new vacancy with name "Senior Software Engineer", job title "Account Assistant", hiring manager "Ranga Akunuri", and number of positions "3"
    When I search the vacancy named "Senior Software Engineer"
    Then I should see the vacancy (testVacancy) "Senior Software Engineer" in the vacancy list


  @ui
  @loginAsAdmin
  Scenario: Delete an added vacancy
    Given I navigate to the Recruitment Vacancies page
    And I add a new vacancy with name "Senior Software Engineer", job title "Account Assistant", hiring manager "Ranga Akunuri", and number of positions "3"
    When I delete the vacancy named "Senior Software Engineer"
    Then the vacancy "Senior Software Engineer" should not appear in the vacancy list