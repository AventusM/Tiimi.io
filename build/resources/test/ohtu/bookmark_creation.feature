Feature: user can add and browse (book)marks

  Scenario: user can add a book
    Given browse is selected
    When author "Akira Toriyama" and book name "The Winning Universe is Decided!" are submitted
    Then book named "The Winning Universe is Decided!" is added
