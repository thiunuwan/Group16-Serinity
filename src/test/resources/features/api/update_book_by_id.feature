Feature: Update Book by ID

  @api
  @createTestBook
  Scenario: Update a book with a valid ID as an admin
    Given I am a admin user
    When I send a PUT request to update the test book
    Then the response status code should be 200
    And the response should contain the updated book details

  @api
  @createTestBook
  Scenario: Update a book with a valid ID as a regular user
    Given I am a normal user
    When I send a PUT request to update the test book
    Then the response status code should be 403
    And the response should contain the error message "User is not permitted."

  @api
  Scenario: Update a non-existent book by ID
    Given I am a admin user
    When I send a PUT request to update a book with a non-existent ID 999
    Then the response status code should be 404
    And the response should contain the error message "Book not found"

  @api
  @createTestBook
  Scenario: Update a book with missing mandatory fields as an admin
    Given I am a admin user
    When I send a PUT request to update the test book with missing mandatory fields
    Then the response status code should be 400
    And the response should contain the error message "Mandatory parameters should not be null"




