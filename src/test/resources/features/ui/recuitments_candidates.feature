Feature: Recruitment management in candidate module

  @ui
  @loginAsAdmin
  Scenario: Add a new candidate
    Given I am in the "Recruitment" page
    When I add a new candidate with firstname "maven" and lastname "java" and email "maven12@gmail.com" and contact "09978925639"
    Then I should see the candidate (testCandidate) "maven java" in the candidate list