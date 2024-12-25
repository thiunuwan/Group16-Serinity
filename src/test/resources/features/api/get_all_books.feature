Feature: Get All Book List

  @api
  Scenario: Retrieve all books as a regular user
    Given I am a normal user
    When I send a GET request to retrieve the book list
    Then the response status code for all books should be 200
    And the response should contain a list of books

  @api
  Scenario: Retrieve all books as an admin user
    Given I am a admin user
    When I send a GET request to retrieve the book list
    Then the response status code for all books should be 200
    And the response should contain a list of books

  @api
  Scenario: Retrieve books with an empty database as an admin user
    Given I am a admin user
    When I send a GET request to retrieve the book list
    Then the response status code for all books should be 200
    And the response should indicate an empty book list

  @api
  Scenario: Retrieve books with an empty database as a normal user
    Given I am a normal user
    When I send a GET request to retrieve the book list
    Then the response status code for all books should be 200
    And the response should indicate an empty book list

  @api
  Scenario: Unauthorized access to book list as a non-logged-in user
    Given I am an unauthorized user
    When I send a GET request to retrieve the book list
    Then the response status code should be 401
    And the response for all books should contain the error message "Unauthorized access"
