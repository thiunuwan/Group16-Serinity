Feature: Recruitment Vacancies
  I want to verify that the user can perform actions related to recruitment vacancies.

  @ui
  @loginAsAdmin
  Scenario: Add a new vacancy
    Given I navigate to the Recruitment Vacancies page
    When I add a new vacancy with name "Senior Software Engineer", job title "Software Engineer", hiring manager "Jennifer Aniston", and number of positions "3"
    Then I should see a success message confirming the vacancy was added
