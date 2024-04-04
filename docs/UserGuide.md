# FinTrack User Guide

## Introduction

FinTrack is a desktop app designed for individuals who want to manage their finances using a Command Line Interface (CLI). FinTrack offers a convenient way for users to track their income, expenses, and budgets through typed commands. With FinTrack, users can easily add income and expense transactions, categorize them, set budgets, and generate reports to gain valuable insights into their financial activities.

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `FinTrack.jar` from [here].
3. Copy the file to the folder you want to use as the home folder for your FinTrack app.
4. Open a command terminal, navigate to the folder containing the jar file, and run the command `java -jar FinTrack.jar` to run the application.
5. Upon starting FinTrack, you will first be prompted to type the username and password. The username is **Bob** and the password is **password**.

## Features

### Logging in into user session:

- Securely logs the user into their Finance Manager session. 

### Viewing Help: `help`

- Shows a message explaining the different commands available and their formats.

Format: `help`

### View Transaction History: `view-history`

- Displays the last n transactions made.
- Optional `w/chart` can be added at the end to visualise transaction history with bar charts.
- `all` tag allows the user to view all transactions made.

Format: `view-history n/NUM [w/CHART]`

Examples:
- `view-history n/20 w/chart`
- `view-history all`

### Add Inflow: add-inflow

- Adds a new inflow of money to your financial records.
- Date input is in DD/MM/YYYY format. Time input is 24-hour format.
- Only the following categories are allowed: *INCOME, INVESTMENT, GIFT, LOAN, REFUND, OTHER*

Format: `add-inflow n/NAME a/AMOUNT d/DATE t/TIME c/CATEGORY`

Example: `add-inflow n/Salary a/5000 d/21/02/2024 t/1700 c/income`

### Delete Inflow: `delete-inflow`

- Checks for and deletes existing entry of inflow from financial record.
- Before deleting, the list of inflows along with their indices can be viewed using the `view-history` command.

Format: `delete-source i/INDEX`

Example: `delete-inflow i/2`

### Add Outflow: `add-outflow`

- Adds a new outflow of money to your financial records.
- Date input is in DD/MM/YYYY format. Time input is 24-hour format.
- Only the following categories are allowed: *FOOD, RENT, DEBT, SHOPPING, TREAT, EDUCATION, TAX, OTHER*

Format: `add-outflow n/NAME a/AMOUNT d/DATE t/TIME c/CATEGORY`

Example: `add-outflow n/John a/100 d/19/02/2024 t/1600 c/treat`

### Delete Outflow: `delete-outflow`

- Checks for and deletes existing entry of outflow from financial record.
- Before deleting, the list of outflows along with their indices can be viewed using the `view-history` command.

Format: `delete-outflow i/INDEX`

Example: `delete-outflow i/5`

### Edit Inflow: `edit-inflow`

- Edits an existing entry of inflow from your financial records.
- Before editing, the list of inflows along with their indices can be viewed using the `view-history` command.
- Date input is in DD/MM/YYYY format. Time input is 24-hour format.
- Only the following categories are allowed: *INCOME, INVESTMENT, GIFT, LOAN, REFUND, OTHER*
- Input the index you want to edit and provide the details you want to update for the inflow.

Format: `edit-inflow i/INDEX n/NAME a/AMOUNT d/DATE t/TIME c/CATEGORY`

Example: `edit-inflow i/7 n/Salary a/5000 d/21/02/2024 t/1700 c/income`

### Edit Outflow: `edit-outflow`

- Edits an existing entry of outflow from your financial records.
- Before editing, the list of outflows along with their indices can be viewed using the `view-history` command.
- Date input is in DD/MM/YYYY format. Time input is 24-hour format.
- Only the following categories are allowed: *FOOD, RENT, DEBT, SHOPPING, TREAT, EDUCATION, TAX, OTHER*
- Input the index you want to edit and provide the details you want to edit for the outflow.

Format: `edit-outflow i/INDEX n/NAME a/AMOUNT d/DATE t/TIME c/CATEGORY`

Example: `edit-outflow i/6 n/John a/100 d/19/02/2024 t/1600 c/food`

### Undo last action: `undo`
- This command will undo the last command inputted by the user.
- Only applicable for the commands:
  - `add-inflow`
  - `add-outflow`
  - `add-reminder`
  - `delete-inflow`
  - `delete-outflow`
  - `delete-reminder`

Format: `undo`

### Exiting the Program: `quit`

- Quits from  the FinTrack app.

Format: `quit`

### Saving the Data

FinTrack data is saved in the hard disk automatically when the user exits the program. There is no need to save the data manually.

### Inactivity Timer

FinTrack allows a maximum of 3 minutes of inactivity on the software, then it will shut down by itself. However, a grace period of 30 seconds is offered to the user, whereby the user is asked whether there will be any further activity or not. 

## FAQ

**Q**: How do I transfer my data to another Computer?

**A**: Install the app on the other computer and overwrite the empty data file it creates with the file that contains the data of your previous FinTrack home folder.

## Command Summary

* View Help: `help`
* View Transaction History: `view-history n/NUM [w/CHART]`
* Add Inflow: `add-inflow n/NAME a/AMOUNT d/DATE t/TIME c/CATEGORY`
* Delete Inflow: `delete-inflow i/INDEX`
* Edit Inflow: `edit-inflow i/INDEX n/NAME a/AMOUNT d/DATE t/TIME c/CATEGORY`
* Add Outflow: `add-outflow n/NAME a/AMOUNT d/DATE t/TIME c/CATEGORY`
* Delete Outflow: `delete-outflow i/INDEX`
* Edit Outflow: `edit-outflow i/INDEX n/NAME a/AMOUNT d/DATE t/TIME c/CATEGORY`
* Exit Program: `quit`
* Undo: `undo`
