Feature: Recruitment management in candidate module

  @ui
  @loginAsAdmin
  Scenario: Add a new candidate
    Given I am in the "Recruitment" page
    When I add a new candidate with firstname "milanka" and lastname "tharangana" and email "tharangana12@gmail.com" and contact "0711717255"
    Then I should see the candidate (testCandidate) "milaka" in the candidate list