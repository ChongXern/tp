# FinTrack User Guide

## Introduction

FinTrack is a desktop app designed for individuals who want to manage their finances using a Command Line Interface (CLI). FinTrack offers a convenient way for users to track their income, expenses, and budgets through typed commands. With FinTrack, users can easily add income and expense transactions, categorize them, set budgets, and generate reports to gain valuable insights into their financial activities.

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `FinTrack.jar` from [here](https://github.com/AY2324S2-CS2113-F14-4/tp/releases/tag/v2.0).
3. Copy the file to the folder you want to use as the home folder for your FinTrack app.
4. Open a command terminal, navigate to the folder containing the jar file, and run the command `java -jar FinTrack.jar` to run the application.
5. Upon starting FinTrack, you will first be prompted to type the username and password. The username is **Bob** and the password is **password**

## Features
- [Logging in into user session](#logging-in-into-user-session)
- [Viewing help](#viewing-help-help)
- [View Transaction History](#view-transaction-history-view-history)
- [Add Inflow](#add-inflow-add-inflow)
- [Delete Inflow](#delete-inflow-delete-inflow)
- [Add Outflow](#add-outflow-add-outflow)
- [Delete Outflow](#delete-outflow-delete-outflow)
- [Add Reminder](#add-reminder-add-reminder)
- [Delete Reminder](#delete-reminder-delete-reminder)
- [Edit Inflow](#edit-inflow-edit-inflow)
- [Edit Outflow](#edit-outflow-edit-outflow)
- [Edit Reminder](#edit-reminder-edit-reminder)
- [Undo last action](#undo-last-action-undo)
- [Set budget](#set-a-budget-set-budget)
- [Generate report](#generating-a-report-generate-report)
- [Exit Program](#exiting-the-program-quit)
- [Saving Data](#saving-the-data)
- [Inactivity Timer](#inactivity-timer)


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
- `NAME` should not have any white space characters in it.
- Date input is in DD/MM/YYYY format. Time input is 24-hour format.
- Only the following categories are allowed: *INCOME, INVESTMENT, GIFT, LOAN, REFUND, OTHER*

Format: `add-inflow n/NAME a/AMOUNT d/DATE t/TIME c/CATEGORY`

Example: `add-inflow n/Salary a/5000 d/21/02/2024 t/1700 c/income`

### Delete Inflow: `delete-inflow`

- Checks for and deletes existing entry of inflow from financial record.
- Before deleting, the list of inflows along with their indices can be viewed using the `view-history` command.

Format: `delete-inflow i/INDEX`

Example: `delete-inflow i/2`

### Add Outflow: `add-outflow`

- Adds a new outflow of money to your financial records.
- `NAME` should not have any white space characters in it.
- Date input is in DD/MM/YYYY format. Time input is 24-hour format.
- Only the following categories are allowed: *FOOD, RENT, DEBT, SHOPPING, TREAT, EDUCATION, TAX, OTHER*

Format: `add-outflow n/NAME a/AMOUNT d/DATE t/TIME c/CATEGORY`

Example: `add-outflow n/John a/100 d/19/02/2024 t/1600 c/treat`

### Delete Outflow: `delete-outflow`

- Checks for and deletes existing entry of outflow from financial record.
- Before deleting, the list of outflows along with their indices can be viewed using the `view-history` command.

Format: `delete-outflow i/INDEX`

Example: `delete-outflow i/5`

### Add Reminder: `add-reminder`

- Adds a new reminder of upcoming payment to your financial records.
- `NAME` should not have any white space characters in it.
- Date input is in DD/MM/YYYY format. Time input is 24-hour format.
- Take note that the date entered must be in the future.
- Only the following categories are allowed: *INSTALLMENT, CREDITCARD, UTILITIES, OTHER*

Format: `add-reminder n/NAME a/AMOUNT d/DATE t/TIME c/CATEGORY`

Example: `add-reminder n/water_bills a/35 d/21/06/2024 t/1200 c/UTILITIES`

### Delete Reminder: `delete-reminder`

- Checks for and deletes existing entry of reminder from financial record.
- Before deleting, the list of reminders along with their indices can be viewed using the `view-history` command.

Format: `delete-reminder i/INDEX`

Example: `delete-reminder i/5`

### Edit Inflow: `edit-inflow`

- Edits an existing entry of inflow from your financial records.
- Before editing, the list of inflows along with their indices can be viewed using the `view-history` command.
- `NAME` should not have any white space characters in it.
- Date input is in DD/MM/YYYY format. Time input is 24-hour format.
- Only the following categories are allowed: *INCOME, INVESTMENT, GIFT, LOAN, REFUND, OTHER*
- Input the index you want to edit and provide the details you want to update for the inflow.

Format: `edit-inflow i/INDEX n/NAME a/AMOUNT d/DATE t/TIME c/CATEGORY`

Example: `edit-inflow i/7 n/Salary a/5000 d/21/02/2024 t/1700 c/income`

### Edit Outflow: `edit-outflow`

- Edits an existing entry of outflow from your financial records.
- Before editing, the list of outflows along with their indices can be viewed using the `view-history` command.
- `NAME` should not have any white space characters in it.
- Date input is in DD/MM/YYYY format. Time input is 24-hour format.
- Only the following categories are allowed: *FOOD, RENT, DEBT, SHOPPING, TREAT, EDUCATION, TAX, OTHER*
- Input the index you want to edit and provide the details you want to edit for the outflow.

Format: `edit-outflow i/INDEX n/NAME a/AMOUNT d/DATE t/TIME c/CATEGORY`

Example: `edit-outflow i/6 n/John a/100 d/19/02/2024 t/1600 c/food`

### Edit Reminder: `edit-reminder`

- Edits an existing entry of reminder in your financial records.
- Before editing, the list of reminders along with their indices can be viewed using the `view-history` command.
- `NAME` should not have any white space characters in it.
- Date input is in DD/MM/YYYY format. Time input is 24-hour format.
- Only the following categories are allowed: *INSTALLMENT, CREDITCARD, UTILITIES, OTHER*
- Input the index you want to edit and provide the details you want to edit for the outflow.

Format: `edit-reminder i/INDEX n/NAME a/AMOUNT d/DATE t/TIME c/CATEGORY`

Example: `edit-reminder i/2 n/water bills a/35 d/21/06/2024 t/1200 c/UTILITIES`

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

### Set a budget `set-budget`
- Sets a per-month budget from a default of $0.00.

Format: `set-budget a/AMOUNT`

Examples of usage:

`set-budget a/2000.00`

### Generating a report `generate-report`
- Generates a report of a certain month.
- Take note that MMM is the 3-letter abbreviation of the month in all-caps.

Format: `generate-report m/MMM y/YYYY`

Examples of usage:

`generate-report m/JUN y/2023`

`generate-report m/MAR y/2024`

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

**Q**: Can I customize the categories for transactions in FinTrack?

**A**: Currently, FinTrack comes with predefined categories for both inflow and outflow transactions. Custom categories are not supported in the current version, but we're always looking to improve and may include this feature in future releases.

**Q**: Is it possible to have multiple user accounts on FinTrack?

**A**: FinTrack is designed to be used by a single user, and it does not support multiple user accounts in its current version. If multiple users wish to use FinTrack, they would need to install it in separate directories or use different machines.

**Q**: What should I do if I encounter an error or a bug?

**A**: If you encounter any errors or bugs while using FinTrack, please report them to our support team. You can do this by creating an issue on our GitHub repository. Please provide detailed information about the issue and steps to reproduce it, if possible.

**Q**: Can FinTrack be used on mobile devices?

**A**: FinTrack is a desktop application designed for use on computers with Java 11 or above. It is not currently available for mobile devices. However, accessing the application via remote desktop software might be a workaround for accessing FinTrack on a mobile device.

**Q**: How secure is my financial data with FinTrack?

**A**: Your financial data is stored locally on your device and is not transmitted over the internet. We recommend keeping your device secure and regularly backing up your FinTrack data file to prevent data loss. Ensure your device has adequate security measures, such as updated antivirus software and a strong login password.

**Q**: Can I export FinTrack data for use in other applications?

**A**: FinTrack does not currently support exporting data directly to other financial software or applications. However, you can manually input the data into other applications if they support similar transaction formats.

## Command Summary

* View Help: `help`
* View Transaction History: `view-history n/NUM [w/CHART]`
* Add Inflow: `add-inflow n/NAME a/AMOUNT d/DATE t/TIME c/CATEGORY`
* Delete Inflow: `delete-inflow i/INDEX`
* Edit Inflow: `edit-inflow i/INDEX n/NAME a/AMOUNT d/DATE t/TIME c/CATEGORY`
* Add Outflow: `add-outflow n/NAME a/AMOUNT d/DATE t/TIME c/CATEGORY`
* Delete Outflow: `delete-outflow i/INDEX`
* Edit Outflow: `edit-outflow i/INDEX n/NAME a/AMOUNT d/DATE t/TIME c/CATEGORY`
* Undo: `undo`
* Set budget: `set-budget a/AMOUNT`
* Generate report: `generate-report m/MMM y/YYYY`
* Exit Program: `quit`