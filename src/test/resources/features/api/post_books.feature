Feature: Create Book API Tests

  @api
  Scenario: Successfully create a new book
    Given I am a normal user
    When I send a POST request to create a new book with title "The Great Gatsby" and author "F. Scott Fitzgerald"
    Then the response status code should 201
    And the response should contain the book details with title "The Great Gatsby" and author "F. Scott Fitzgerald"

    @api
    Scenario: Attempt to create a book with empty title and author as a normal user
      Given I am a normal user
      When I send a POST request to create a book with empty title and author
      Then the response status code should be 400 for empty input as a normal user
      And the response should contain validation errors for empty title and author as a normal user

#      @api
#      Scenario: Attempt to create a book with a title that already exists
#        Given a book with title "Duplicate Book" and author "John Doe" already exists
#        When I send a POST request to create a book with title "Duplicate Book" and author "John Doe"
#        Then the response status code should be 208 for duplicate book
#        And the response should contain the error message "Book with the same title and author already exists" for duplicate book



