Feature: Job Titles

  @ui
  @loginAsAdmin
  Scenario: Add a new job title and verify through record
    Given I am in "Add Job Title" page
    When I add a new job title with job title "Application Manager"
    Then I should see the job title (testJobTitle) "Application Manager" in the job titles list


  @ui
  @loginAsAdmin
  Scenario: Update an added job title from the job titles page
    Given I am in "Job Title" page
    And I add a new job title with job title "Test manager"
    When I edit the existing job title named "Test manager" to "updated Test Manager"
    Then I should see the job title (testJobTitle) "updated Test Manager" in the job titles list


  @ui
  @loginAsAdmin
  Scenario: Delete an added job title from the job titles page
    Given I am in "Job Title" page
    And I add a new job title with job title "Application manager2"
    When I delete the job title named "Application manager2"
    Then the job title "Application manager2" should not appear in the job titles list