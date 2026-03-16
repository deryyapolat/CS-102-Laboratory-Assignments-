# CS102 – Programming Assignment 5

## Mouse Maze (Recursion)

This project is a Java GUI application developed for the CS102 – Algorithms and Programming II course.

The program allows users to create a small maze where a mouse tries to reach a piece of cheese. The application uses a recursive algorithm to find one of the shortest paths from the starting position to the destination. 

## Features
Interactive 5×5 maze grid
Ability to place the mouse (start) and cheese (end) positions
Users can add or remove walls
Pathfinding using recursion
Visual display of the path to the cheese
Error message if no valid path exists

## GUI Components

The application is implemented using Java Swing and includes two frames:

1. Maze Frame: Displays the grid, Shows the mouse, cheese, walls, and path, Uses a custom panel with `paintComponent()` for drawing graphics
2. Control Frame contains action buttons: Set Start, Set End, Add Wall, Remove Wall, Find Path, Reset
The layout of the control panel uses GridLayout.

## Images
Images are used to visually represent: Mouse, Cheese, Walls
These images are loaded using `BufferedImage` and `ImageIO`.

## Pathfinding Algorithm

The program uses recursion to explore neighboring cells and calculate the shortest path to the cheese.

Rules of movement:
Only horizontal and vertical moves are allowed
Diagonal movement is not allowed
Walls cannot be crossed
The algorithm avoids revisiting cells with longer paths

After the recursive search, the program reconstructs one of the shortest paths and highlights it on the maze.
5
