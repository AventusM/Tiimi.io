Feature: user can add and browse (book)marks

  Scenario: user can add a book
    Given books are selected
    When author "Akira Toriyama" and book name "The Winning Universe is Decided!" and ISBN "978-4-08-880867-3" are submitted
    Then book named "The Winning Universe is Decided!" has been added
