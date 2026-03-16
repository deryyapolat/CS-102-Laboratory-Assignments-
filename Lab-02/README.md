# CS102 – Programming Assignment 2

## Chain of Words

This project is a console-based Java application developed for the **CS102 – Algorithms and Programming II** course.

The program reads a list of English words from a text file and finds possible **word chains** where consecutive words differ by only **one character operation**:

* Character change
* Character insertion
* Character deletion

For example:
`GROW → GLOW → LOW → SLOW`

Each step changes only one character between consecutive words. 

## Features

* Reads words from **words.txt**
* Stores words using a `Word` class
* Finds possible chains between words
* Generates random chains based on user input
* Maximum chain length: **10 words**
* Prevents duplicate words in the chain
* Includes a **guessing game** where the user tries to find the hidden word in a chain

Example guessing game:

```
HEAL - ? - EAR
```

The user has **3 attempts** to guess the hidden word.

## Program Structure

Example classes used in the project:

```
Word.java
WordReader.java
Main.java
GameManager.java
```

Main components include:

* `Word` class to represent each word
* `ArrayList<Word>` to store all words
* `possibleChains` list to store chainable words
* Methods to check if two words can form a chain

## Input Files

The program uses:

```
words.txt
```

This file contains English words (3–7 letters) used to generate chains.

## Optional Optimization

To improve runtime, filtered word lists or chain data may be saved into:

```
filtered_words.txt
chains.txt
```

These files help reduce processing time when running the program again.

## How to Run

1. Place `words.txt` in the project folder
2. Compile the Java files
3. Run the main class

```
javac *.java
java Main
```

## Course

CS102 – Algorithms and Programming II
Spring 2025
