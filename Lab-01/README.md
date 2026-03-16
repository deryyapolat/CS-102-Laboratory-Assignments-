# CS102 – Programming Assignment 1

## Simple Monopoly Game

This project is a console-based Monopoly-style game implemented in Java for the CS102 – Algorithms and Programming II course.

The program simulates a simplified Monopoly game where a user competes against 1–3 computer players on a board consisting of properties and special event cells. Players move by rolling a six-sided die and interact with the board by buying properties, paying rent, building houses, and triggering special events. 

## Game Features

1 human player vs 1–3 computer players
Each player starts with 10 coins
Turn order is determined randomly
Players move based on a die roll
Properties can be bought, sold, and upgraded with houses
Rent increases based on the number of houses
Special event cells affect players with different actions
Game ends when: Only one player remains or 100 turns have passed (tie)

## Board Structure

The game board contains:
Property cells labeled with letters (A–L)
Special event cells labeled with numbers (0–3)

Special events include:

Receiving coins when passing the starting cell
Random coin gain/loss or movement
Collecting coins from other players
Skipping a turn

## Example Gameplay Elements

Players can:

Buy properties if they have enough coins
Pay rent when landing on another player's property
Build houses on owned properties
Sell properties back to the bank

If a player cannot pay rent or loses all coins, they are eliminated from the game.
