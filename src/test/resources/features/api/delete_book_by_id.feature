Feature: Delete Book by ID

  @api
  @createTestBook
  Scenario: Delete an existing book with a valid ID as an admin
    Given I am a admin user
    When I send a DELETE request to delete the test book
    Then the response status code should be 200
    And the response should contain the success message "Successfully delete the book"