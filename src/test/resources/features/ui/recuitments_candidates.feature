Feature: Recruitment management in candidate module

  @ui
  @loginAsAdmin
  Scenario: Add a new candidate and verify through record
    Given I am in the "Recruitment" page
    When I add a new candidate with firstname "maven" and lastname "java" and email "maven12@gmail.com" and contact "09978925639"
    Then I should see the candidate (testCandidate) "maven java" in the candidate list


  @ui
  @loginAsAdmin
  Scenario: Add a new candidate and verify through notification
    Given I am in the "Recruitment" page
    When I add a new candidate with firstname "maven11" and lastname "java11" and email "maven1112@gmail.com" and contact "09978925639"
    Then I should see the notification "Successfully Saved"


  @ui
  @loginAsAdmin
  Scenario: Delete an added candidate from the recruitment page
    Given I am in the "Recruitment" page
    And I add a new candidate with firstname "maven123" and lastname "java123" and email "maven123@gmail.com" and contact "09978925639"
    When I delete the candidate named "maven123 java123"
    Then the candidate "maven123" should not appear in the candidate list

  @ui
  @loginAsAdmin
  Scenario: search an added candidate from the recruitment page saved without consent
    Given I am in the "Recruitment" page
    And I add a new candidate with firstname "maven1234" and lastname "java1234" and email "maven1234@gmail.com" and contact "09978925639" and consentCheckbox "false"
    When I search the candidate named "maven1234 java1234"
    Then the candidate "maven1234" should not appear in the candidate list

  @ui
  @loginAsAdmin
  Scenario: search an added candidate from the recruitment page with consent
    Given I am in the "Recruitment" page
    And I add a new candidate with firstname "maven12345" and lastname "java1234" and email "maven1234@gmail.com" and contact "09978925639" and consentCheckbox "true"
    When I search the candidate named "maven12345 java1234"
    Then I should see the candidate (testCandidate) "maven12345 java1234" in the candidate list