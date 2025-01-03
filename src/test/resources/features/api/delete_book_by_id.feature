Feature: Delete Book by ID

  @api
  @createTestBook
  Scenario: Delete an existing book with a valid ID as an admin
    Given I am a admin user
    When I send a DELETE request to delete the test book
    Then the response status code should be 200
    And the response should contain the success message "Successfully delete the book"
    
  @api
  @createTestBook
  Scenario: Delete an existing book with a valid ID as a normal user
    Given I am a normal user
    When I send a DELETE request to delete the test book
    Then the response status code should be 403
    And the response should contain the error message "Request api call is forbidden"

  @api
  Scenario: Delete a non-existent book by ID
    Given I am a admin user
    When I send a DELETE request to delete a book with a non-existent ID 999
    Then the response status code should be 404
    And the response should contain the error message "Book is not found"

