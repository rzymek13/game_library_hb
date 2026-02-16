Feature: Handball League Match Management

  Scenario: Create a match and verify result
    Given A team named "Home Team" exists
    And A team named "Away Team" exists
    And A player named "Scorer One" exists in team "Home Team"
    When I create a match between "Home Team" and "Away Team" with score 5-0
    And Player "Scorer One" scores 5 goals
    Then The match should be created successfully
    And The home team should have 5 goals
    And The away team should have 0 goals

  Scenario: Verify top scorers
    Given A team named "Scorer Team" exists
    And A team named "Opponent Team" exists
    And A player named "Top Scorer" exists in team "Scorer Team"
    When I create a match between "Scorer Team" and "Opponent Team" with score 10-0
    And Player "Top Scorer" scores 10 goals
    Then The player "Top Scorer" should be listed as a top scorer with 10 goals

  Scenario: Delete a match
    Given A match between "Delete Home" and "Delete Away" exists
    When I delete the match
    Then The match should not exist in the system
