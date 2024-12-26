Feature: POST Book

  @api
  Scenario: Attempt to create a book with missing values in title with author
    Given I am a normal user
    When I send a POST request to create a book with empty title with author "James"
    Then the response status code should be 400
    And the response should contain the error message "Mandatory fields required."


  @api
  Scenario: Invalid ID format
    Given I am a normal user
    When I send a request to create a book with invalid ID format
    Then the response status code should be 400
#    And the response should contain the error message "Invalid | Empty Input Parameters in the Request"


#  @api
#  Scenario: Invalid title format or author
#    Given I am a normal user
#    When I send a request to create a book with invalid title or author format
#    Then the response status code should be 400
##    And the response message should indicate invalid title or author format


  @api
  Scenario: Book Already Exists by name (Admin/user)
    Given I am a normal user
    And a book with ID 2, title "Secret Book" and author "Jane Doe" already exists
    When I send a POST request to create a book with ID 3, title "Secret Book" and author "James John"
    Then the response status code should be 208
    And the response should contain the error message "Book already exists."