Feature: Handball League Team Management

  Scenario: Create a new team and verify its existence
    Given There is no team named "Cucumber Team"
    When I create a new team named "Cucumber Team"
    Then The team "Cucumber Team" should exist in the system
    And The team "Cucumber Team" should have a valid ID

  Scenario: Try to create a team with an invalid name
    When I try to create a team with an empty name
    Then I should receive a validation error
