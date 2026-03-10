Feature: Handball League Player Management

  Scenario: Create a new player and assign to a team
    Given Create test team with name "Player Team"
    When I create a new player named "John Doe" for team "Player Team"
    Then The player "John Doe" should exist in the system
    And The player "John Doe" should belong to team "Player Team"

  Scenario: Get players by team
    Given Create test team with name "Squad Team"
    And I create a new player named "Player One" for team "Squad Team"
    And I create a new player named "Player Two" for team "Squad Team"
    When I request all players for team "Squad Team"
    Then I should receive a list containing "Player One" and "Player Two"

  Scenario: Delete a player
    Given Create test team with name "Delete Team"
    And I create a new player named "To Be Deleted" for team "Delete Team"
    When I delete the player named "To Be Deleted"
    Then The player "To Be Deleted" should not exist in the system
