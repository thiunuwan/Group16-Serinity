Feature: Get Book by ID

  @api @createTestBook
  Scenario Outline: Retrieve a book with a valid ID as an admin/user
      Given I am a <user_role> user
      When I send a GET request to retrieve the test book
      Then the response status code should be 200
      And the response should contain the book details for testBook

    Examples:
      | user_role |
      | admin     |
      | normal    |

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


  @api
  @createTestBook
  Scenario: Retrieve a book with a valid ID as a non-logged-in user
    Given I am an unauthorized user
    When I send a GET request to retrieve the test book non-logged-in user
    Then the response status code should be 401
#    And the response should contain the error message "Unauthorized access"





