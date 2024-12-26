Feature: POST Book

  @api
  Scenario: Attempt to create a book with missing values in title or author
    Given I am a normal user
    When I send a POST request to create a book with empty title or author
    Then the response status code should be 400 error
    And the response should contain validation errors


  @api
  Scenario: Invalid ID format
    Given I am a normal user
    When I send a request to create a book with invalid ID format
    Then the response status code should be 400 error
    And the response message should indicate invalid ID format

  @api
  Scenario: Invalid title format or author
    Given I am a normal user
    When I send a request to create a book with invalid title or author format
    Then the response status code should be 400 error
    And the response message should indicate invalid title or author format


  @api
  Scenario: Book Already Exists by name (Admin/user)
    Given I am a normal user
    When I send a POST request to create a book with an existing title "Existing Book Title"
    Then the response status code should be 208 error
    And the response message should indicate that the book already exists