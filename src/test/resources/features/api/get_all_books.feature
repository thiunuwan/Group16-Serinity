Feature: Get All Book List

  @api
  Scenario Outline: Retrieve all books as a logged-in user
    Given I am a <user_role> user
    When I send a GET request to retrieve the book list
    Then the response status code for all books should be 200
    And the response should contain a list of books

    Examples:
      | user_role |
      | admin     |
      | normal    |

  @api

  Scenario Outline: Retrieve books with an empty database
    Given I am a <user_role> user
    When I send a GET request to retrieve the book list
    Then the response status code for all books should be 200
    And the response should indicate an empty book list

    Examples:
      | user_role |
      | admin     |
      | normal    |


  @api
  Scenario: Unauthorized access to book list as a non-logged-in user
    When I send an unauthorized GET request to retrieve the book list
    Then the response status code should be 401
