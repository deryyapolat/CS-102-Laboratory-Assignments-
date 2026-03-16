# Bank User Management System

This project is a Java console application that simulates a simple banking system for managing users and their accounts.

The program generates random users, assigns multiple accounts with different currency types, and allows operations such as listing users, searching by ID, and sorting users based on specific criteria.

## Features

Generate random bank users
Each user can have multiple accounts
Accounts store different currency types
Currency conversion rates can be updated
Calculate the total balance of each user in a common currency
Search for users by ID
Sort users using a hybrid sorting algorithm
  
### Class Responsibilities

**User**
Stores user ID, name, and surname
Contains a list of accounts
Calculates total balance across all accounts

**Account**
Stores currency type and amount
Converts account balance to a common currency

**Bank**
Manages currency conversion rates
 Provides methods to get and update rates

**UserNotFoundException**
Custom exception thrown when a user ID cannot be found

## Sorting Algorithm
The project implements a Hybrid Sorting Algorithm. QuickSort is used for large partitions, Insertion Sort is used when the partition size is below a specified limit

Users can be sorted by: User ID, Total balance
This improves sorting efficiency depending on the dataset size.

## Program Menu

The application provides the following menu options:
1. Generate random users
2. List users
3. Show user by ID
4. Set currency conversion rates
5. Sort users
6. Reset users to the original order
7. Exit
