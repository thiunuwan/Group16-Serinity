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
    Given I am an admin user
    When I send a GET request to retrieve the test book
    Then the response status code should be 200
    And the response should contain the book details for testBook

