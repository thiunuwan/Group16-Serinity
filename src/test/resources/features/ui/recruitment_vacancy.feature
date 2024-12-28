Feature: Recruitment Vacancies
  I want to verify that the user can perform actions related to recruitment vacancies.


  @ui
  @loginAsAdmin
  Scenario: Add a new vacancy
    Given I navigate to the Recruitment Vacancies page
    When I add a new vacancy with valid details
    Then I should see the new vacancy in the list


