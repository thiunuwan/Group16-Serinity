Feature: Add Employee

  @ui
  @loginAsAdmin
  Scenario: Add a new employee without username and password
    Given I am in "PIM" page
    When I add a new test employee with firstname "Denuwan" and middlename "Avishka" and lastname "Fernando"
    Then I should see "Denuwan Avishka" in the employee list

  @ui
  @loginAsAdmin
  Scenario: Delete an added employee from the PIM page
    Given I am in "PIM" page
    And I add a new test employee with firstname "Denuwan" and middlename "Avishka" and lastname "Fernando"
    When I delete test employee
    Then test employee should not appear in the employee list

  @ui
  @loginAsAdmin
  Scenario: Add a new employee with username and password
    Given I am in "PIM" page
    When I add a new employee with firstname "Denuwan" and middlename "Avishka" and lastname "Fernando" and username "Denuwan1234" and password "Abcde123#" confirmpassword "Abcde123#"
    Then I should see "Denuwan Avishka" in the employee list