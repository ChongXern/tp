# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}
- [CS2113 Course Website](https://nus-cs2113-ay2324s2.github.io/website/index.html)

## Design & implementation

The financial manager application is designed using an object-oriented approach, focusing on user authentication, transaction management, and user interaction. The core components include:

- **Main Application Loop**: Handles the initialization of the application, including loading data from storage, authenticating the user, and processing user commands until the application exits.
- **Command Pattern**: User commands are encapsulated as objects, allowing for the addition of new commands with minimal changes to existing code.
- **Transaction Management**: Separate classes for managing inflows and outflows, with a unified interface for adding, editing, and deleting transactions.
- **User Authentication**: Ensures that users are authenticated before accessing their financial data, with a mechanism to track and limit failed attempts.
- **Inactivity Timer**: Automatically logs out users after a period of inactivity, enhancing security.
- **Storage Management**: Handles the persistent storage of transaction data, allowing users to save and load their financial information.
- **[Proposed] Security features**: A maximum of three login attempts are inputted, failing which would cause the application to be exited automatically.
- **[Proposed] Financial transaction visualiser**: When viewing history of transactions, the data will be visualised using bar charts for better user friendliness.
- **[Proposed] Undo functionality**: Allows users to undo  their last prompt, but only permittable 10 seconds after the last action. 

### View Transaction History feature:

#### Implementation Details:

#### Functionality:
The View History feature allows users to retrieve a list of their recent transactions, categorized as inflows and outflows. The user can specify the number of transactions they want to view, and the system presents these transactions in reverse chronological order, with the latest transactions displayed first. Each transaction is presented with its description, date, and time of occurrence.

#### Design Considerations:
- **Data Structure**: The transaction data is stored in an instance of the `TransactionList` class, which is part of the `financialtransactions` package. This list maintains a record of all transactions made by the user, ensuring easy retrieval and manipulation of transaction data.
- **Categorization**: Transactions are categorized as inflow or outflow based on their type. This categorization enables users to differentiate between different types of financial activities, such as income and expenses.
- **Formatting**: The transactions are presented in a formatted string, providing clear and concise information to the user. Each transaction includes its description, date, and time.

#### Implementation Details:
- The `TransactionManager#showLastNTransactions()` method is responsible for retrieving the last N transactions from the Transaction List.
- The method iterates over the transaction list in reverse order, starting from the latest transaction and moving towards older transactions.
- Transactions are categorized as inflows or outflows based on their instance type (Inflow or Outflow), and the relevant transactions are added to the output string accordingly.
- The formatted string containing the transaction details is returned to the user for display.

#### Alternatives Considered:
During the development of the View Transaction History feature, several alternatives were considered to achieve the desired functionality. These alternatives included:

- Implementing a more complex data structure for storing transaction history, such as a linked list. However, a simple ArrayList-based approach was chosen for its simplicity and efficiency in managing transaction data.
- Providing additional filtering options, such as filtering transactions by date range or transaction category. While these options could definitely enhance the feature, they were deemed unnecessary for the initial implementation and may be considered for future iterations.

## Product scope
### Target user profile

The financial manager application is designed for individual users seeking a simple yet powerful tool to manage personal finances, including tracking income and expenses, and viewing transaction history.


### Value proposition

This application simplifies personal financial management by providing an intuitive interface for tracking and analyzing income and expenses. Users can easily add, edit, or delete transactions, view recent transaction history, and ensure their data is securely managed with user authentication and automatic logout features.


## User Stories

|Version| As a/an ... | I want to ... | So that ...|
|-------|-------------|---------------|------------|
|v1.0|user|receive alerts or notifications when I exceed my budget limits for specific expense categories|I can stay within my financial goals|
|v1.0|user|generate monthly reports summarizing my income, expenses, and budget performance|I can track my financial progress over time|
|v1.0|user|edit or delete past transactions|I can correct any errors or update information as needed|
|v1.0|user|search for specific transactions based on keywords, dates, or categories|I can quickly find the information I need|
|v1.0|user|add income transactions with details such as amount, date, and category|I can keep track of my earnings|
|v1.0|user|add expense transactions with details such as amount, date, and category|I can monitor where my money is going|
|v1.0|user|categorize my transactions|I can organize my finances and have a clearer view of my income and expenses|
|v1.0|user|add instalment payments into the tracker|I can track finances for big ticket purchases such as furniture/TVs|
|v1.0|user|Use a customisable interface for my financial goals|I can adjust these financial goals accordingly as time passes|
|v1.0|user|add payment types such as credit card, debit card or cash|I can be reminded to pay credit card bills each month|
|v1.0|user|Be confident that my banking information is encrypted and safe from being accessed by others|The private information is not easily leaked to others|
|v2.0|user|Export my financial reports as a CSV|I can share my expenditure with my peers|
|v2.0|user|Visualise my transactions in more meaningful diagrams|I can better understand my spending|

## Non-Functional Requirements

1. **Security**: User authentication must be secure, with a limit on login attempts to prevent unauthorized access.
2. **Usability**: The application should be easy to use, with clear instructions and feedback for users.
3. **Performance**: The application should respond quickly to user inputs, with minimal delays in processing transactions.
4. **Reliability**: Data storage should be reliable, ensuring that user data is not lost between sessions.


## Glossary

1. **Inflow** - A financial transaction representing income or money received.
2. **Outflow** - A financial transaction representing expenses or money spent.


## Instructions for manual testing

### Login Procedure:
- Start the application and enter your username and password when prompted.
- Test incorrect passwords to ensure the application correctly limits login attempts. 
- [v2.0] If there are three incorrect attempts, the application will automatically exit and the user has to try again.

### Adding Transactions:
- Use the `add-inflow` and `add-outflow` commands to add new transactions, following the command format provided in the `help` command.
- Attempt to add transactions with missing or incorrect information to test validation.

### Editing and Deleting Transactions:
- Use the `edit-inflow` and `edit-outflow` commands to modify existing transactions.
- Use the `delete-inflow` and `delete-outflow` commands to remove transactions.
- Try editing or deleting transactions that do not exist to test error handling.

### Viewing Transaction History:
- Use the `view-history` command to display recent transactions.
- Test with different numbers of transactions to view.
- [v2.0] Additional bar charts will be shown to display the different percentage of categories used in the past n transactions.

### Inactivity Timeout:
- After logging in, do not input any commands for the duration specified by the inactivity timer to test automatic logout.
- A maximum of 2.5 minutes of inactivity will trigger the application to check with the user whether the user is still active or not. Input `yes` to indicate continued activity, otherwise input `no` to exit. 
- 30 seconds after the 2.5 min (3 min), the application will automatically exit.
- Implemented to provide enhanced security, so no third party can access finance transactions. 

### Data Persistence:
- Exit the application and restart it to ensure that previously entered transactions are still present.
