Feature: Handball League Team Management

  Scenario: Create a new team and verify its existence
    Given There is no team named "Cucumber Team"
    When I create a new team named "Cucumber Team"
    Then The team "Cucumber Team" should exist in the system
    And The team "Cucumber Team" should have a valid ID

  Scenario: Try to create a team with an invalid name
    When I try to create a team with an empty name
    Then I should receive a validation error

  Scenario: Update an existing team's name
    Given Create test team with name "test team for update"
    When I update the name of team "Original Name" to "Updated Name"
    Then The team name should be "Updated Name"

  Scenario: Delete a specific team
    Given Create test team with name "team for delete"
    When I delete the team named "team for delete"
    Then The team "team for delete" should not exist in the system

  Scenario: Delete all teams
    Given Create test team with name "some team"
    And Create test team with name "some team 2"
    When I delete all teams
    Then There should be no teams in the system
