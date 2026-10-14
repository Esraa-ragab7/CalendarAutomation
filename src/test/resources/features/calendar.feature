Feature: Calendar event management

  Scenario Outline: Add a new event successfully
    Given the Calendar application is open
    When I create a new event with title "<eventTitle>"
    Then the event "<eventTitle>" should be displayed successfully

    Examples:
      | eventTitle              |
      | Automation Test Event 1 |
      | Automation Test Event 2 |
      | Automation Test Event 3 |