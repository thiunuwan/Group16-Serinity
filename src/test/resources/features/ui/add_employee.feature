Feature: Add Employee

@ui
@loginAsAdmin
Scenario: Add a new employee without username and password
Given I am in "PIM" page
When I add a new employee with firstname "Denuwan" and middlename "Avishka" and lastname "Fernando" and employeeId "345"
Then I should see "Denuwan Fernando Avishka" in the employee list