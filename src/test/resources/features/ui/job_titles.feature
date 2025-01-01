Feature: Job Titles

  @ui
  @loginAsAdmin
  Scenario: Add a new job title and verify through record
    Given I am in the "Add Job Title" page
    When I add a new job title with jobtitle "manager"
    Then I should see the jobtitle (testJobTitle) "manager" in the jobtitle list


  @ui
  Scenario: Add a new job title and verify through notification
    Given I am in the "Add Job Title" page
    When I add a new job title with jobtitle "manager"
    Then I should see the notification "Successfully saved"

  @ui
  Scenario: Update an added job title from the job titles page
    Given I am in the "Add Job Title" page
    And I add a new job title with jobtitle "manager"
    When I update the job title named "manager" into "head"
    Then the job title "manager" should be changed and "head" should be appeared in the job titles list


  @ui
  Scenario: Delete an added job title from the job titles page
    Given I am in the "Add Job Title" page
    And I add a new job title with jobtitle "manager"
    When I delete the job title named "manager"
    Then the job title "manager" should not appear in the job titles list