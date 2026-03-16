# CS102 – Programming Assignment 3

## Calculating Shapes

This project is a Java console application developed for the CS102 – Algorithms and Programming II course.

The program allows users to create, store, and manipulate different 2D and 3D geometric shapes, calculate their areas and volumes and organize them using a hierarchical class structure. 

## Features

Create and store various 2D and 3D shapes
Calculate area for 2D shapes
Calculate area and volume for 3D shapes
Use inheritance and polymorphism for shape classes
Support multi-shapes that combine several shapes
Menu-driven console interface for user interaction

## Implemented Shapes
2D Shapes: Rectangle, Circle, Square, Ellipse, Equilateral Triangle, MultiShape2D
3D Shapes: Cuboid, Sphere, Cylinder, Cube, Pyramid

## MultiShape2D

The `MultiShape2D` class allows multiple 2D shapes to be combined into a single object.
The total area is calculated as the sum of all included shapes
Shapes can be added dynamically
Multi-shapes can also contain other multi-shapes
The mergeShapes() function replaces all shapes with one square having the same total area

## Menu Functions

The program includes a menu with the following options:

1. Create and store a new shape
2. Add an existing shape to a multi-shape
3. List all shapes and show details
4. Merge multi-shapes
5. Edit an existing shape

## Data Storage

Shapes are stored using object arrays instead of Java Collections (as required in the assignment).

This allows the program to dynamically expand the array when new shapes are added.

