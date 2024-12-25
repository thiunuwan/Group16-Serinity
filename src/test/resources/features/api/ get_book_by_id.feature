Feature: Get Book by ID

 @api
  @createTestBook
  Scenario: Retrieve a book with a valid ID as a regular user
    Given I am a normal user
    When I send a GET request to retrieve the test book
    Then the response status code should be 200
    And the response should contain the book details for testBook

  @api
  @createTestBook
  Scenario: Retrieve a book with a valid ID as an admin
    Given I am a admin user
    When I send a GET request to retrieve the test book
    Then the response status code should be 200
    And the response should contain the book details for testBook


 @api
  Scenario Outline: Retrieve a non-existent book by ID
    Given I am a <user_role> user
    When I send a GET request to retrieve a book with a non-existent ID <book_id>
    Then the response status code should be 404
    And the response should contain the error message "Book not found"

    Examples:
      | user_role | book_id |
      | admin     | 999   |
      | normal    | 999   |



