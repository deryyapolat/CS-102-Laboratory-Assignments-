# CS102 – Programming Assignment 4

## Monopoly Game with GUI

This project is a GUI-based version of the Simple Monopoly Game developed for the CS102 – Algorithms and Programming II course.

The application uses Java Swing and AWT to provide a graphical interface where players interact with the game using buttons and visual components instead of the console. 

## Features

Graphical user interface using Java Swing
Interactive Monopoly-style board
One human player and three computer players
Player actions controlled through GUI buttons
Game log displayed using a JTextArea
Visual representation of players and properties on the board

## Game Flow

1. The program starts with a welcome screen where the player enters the names of all players.
2. After clicking the Start, the main game screen opens.
3. Players take turns performing actions using the GUI buttons.

Available actions:
Roll the dice
Buy property
Sell property
Build houses
End turn

The interface updates automatically after each action.

## GUI Components

The application uses several Java Swing components:

JFrame for main windows
JPanel for drawing the game board
GridLayout for organizing the interface
JButton for player actions
JTextArea for displaying game logs
JOptionPane for pop-up messages

The board is drawn using the Graphics API inside the paintComponent() method of a custom panel.

## Gameplay Elements

Players roll a die to move on the board
Properties can be bought, sold, or upgraded
Cells change color depending on the owner
Houses are displayed visually on the board
The game log records all player actions

If a player’s coins drop below zero, they lose the game.
The game continues until one player remains or the game ends in a tie.
