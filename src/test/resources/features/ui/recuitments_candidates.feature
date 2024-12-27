Feature: Recruitment management in candidate module

  @ui
  @loginAsAdmin
  Scenario: Add a new candidate
    Given I am in the "Recruitment" page
    When I add a new candidate with firstname "maven" and lastname "java" and email "maven12@gmail.com" and contact "09978925639"
    Then I should see the candidate (testCandidate) "maven java" in the candidate list


  @ui
  Scenario: Add a new candidate
    Given I am in the "Recruitment" page
    When I add a new candidate with firstname "maven11" and lastname "java11" and email "maven1112@gmail.com" and contact "09978925639"
    Then I should see the notification "Successfully saved"


  @ui
  Scenario: Delete an added candidate from the recruitment page
    Given I am in the "Recruitment" page
    And I add a new candidate with firstname "maven123" and lastname "java123" and email "maven123@gmail.com" and contact "09978925639"
    When I delete the candidate named "maven123"
    Then the candidate "maven123" should not appear in the candidate list