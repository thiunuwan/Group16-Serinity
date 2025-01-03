Feature: Create Book API Tests

  @api
  Scenario Outline: Successfully create a new book
    Given I am a <user_role> user
    When I send a POST request to create a new book with title "<title>" and author "F. Scott Fitzgerald"
    Then the response status code should be 201
    And the response should contain the book details with title "<title>" and author "F. Scott Fitzgerald"
    Examples:
      | user_role |       title        |
      | admin     |  The Great Gatsby  |
      | normal    |  The Great Gatsby2 |

  @api
    Scenario Outline: Attempt to create a book with empty title and author as a normal user
      Given I am a <user_role> user
      When I send a POST request to create a book with empty title and author
      Then the response status code should be 400
      And the response should contain the error message "Mandatory Fields Required"
      Examples:
      | user_role |
      | admin     |
#      | normal    |

  @api
      Scenario Outline: Attempt to create a book with an ID that already exists
        Given I am a <user_role> user
        And a book with ID 1, title "Unique Book" and author "Jane Doe" already exists
        When I send a POST request to create a book with ID 1, title "Another Book" and author "John Smith"
        Then the response status code should be 208
        And the response should contain the error message "Book Already Exists"
     Examples:
      | user_role |
      | admin     |
      | normal    |

  @api
  Scenario Outline: Attempt to create a book with missing values in title with author
    Given I am a <user_role> user
    When I send a POST request to create a book with empty title with author "James"
    Then the response status code should be 400
    And the response should contain the error message "Mandatory fields required."
    Examples:
      | user_role |
      | admin     |
#      | normal    |

  @api
  Scenario Outline: Invalid ID format
    Given I am a <user_role> user
    When I send a request to create a book with invalid ID format
    Then the response status code should be 400
#    And the response should contain the error message "Invalid | Empty Input Parameters in the Request"
    Examples:
      | user_role |
      | admin     |
      | normal    |



  @api
  Scenario Outline: Book Already Exists by name
    Given I am a <user_role> user
    And a book with ID 2, title "Secret Book" and author "Jane Doe" already exists
    When I send a POST request to create a book with ID <id>, title "Secret Book" and author "<author>"
    Then the response status code should be 208
    And the response should contain the error message "Book Already Exists"
    Examples:
    | id  | user_role | author           |
    |  11 | admin     |     James John   |
    |  13 | normal    |     kamala Nimia |

