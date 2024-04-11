# Dylan Siew - Project Portfolio Page

## Overview
Finance tracking application that helps keep track of all things related to finances such as income, spending, expenditure, reminders, gifts, and more. 

### Summary of Contributions
[**Code Contributed**](https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2024-02-23&tabOpen=true&tabType=authorship&tabAuthor=dylansiew&tabRepo=AY2324S2-CS2113-F14-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false) by Dylan Siew

#### Enhancements Implemented

1. **Create Base User Class**

    Implemented the foundation for user data handling, enabling personalized user experiences and data management.

2. **Create Authentication Class**

    Developed authentication mechanisms to ensure user data security and privacy, involving password protection and secure user login.

3. **Create Date Handler Class**

    Designed a class to manage and parse date information, essential for scheduling and tracking financial transactions over time.

4. **Create Expenditure Class**

    Established a class to track and manage user expenditures, providing a structured way to record and analyze spending.

5. **Create Base Command Class**

    Crafted the core command class structure to streamline the processing and execution of user commands, enhancing the application's scalability and maintainability.

6. **Create Add Source/Transaction Command Class**

    Devised functionality for users to add income sources and financial transactions, enriching the application's capability to monitor financial flows.

7. **Create Delete Source/Transaction Command Class**

    Formulated a method for users to remove unwanted or incorrect income sources and transactions, maintaining the accuracy and relevance of financial data.

8. **Ensure UI Object Used in Printing of All Statements**

    Refined the application's user interface by standardizing the output mechanism, improving user interaction consistency and clarity.

#### Contributions to User Guide 

In the User Guide, I focused on expanding the Q&A section to address common queries and concerns users might have regarding FinTrack. My contributions aimed to clarify the application's capabilities, usage, and security aspects, enhancing user understanding and confidence in using FinTrack:

1. **Customization of Transaction Categories**: I clarified that FinTrack currently does not support custom transaction categories but mentioned the possibility of including this feature in future versions, reflecting the application's commitment to improvement based on user feedback.

2. **Multiple User Accounts**: I addressed the limitation of FinTrack in supporting only single user accounts and suggested workarounds for multiple users wishing to use the application, guiding users towards practical solutions for shared use.

3. **Reporting Errors or Bugs**: I provided guidance on how users can report errors or bugs they encounter, emphasizing the importance of detailed information and reproduction steps. This contribution is crucial for maintaining an open channel of communication with users and facilitating continuous improvement of FinTrack.

4. **Mobile Device Compatibility**: I explained that FinTrack is primarily a desktop application and outlined potential workarounds for accessing it on mobile devices through remote desktop software, offering users flexibility in how they access the application.

5. **Security of Financial Data**: I reassured users about the security of their financial data with FinTrack, emphasizing local data storage and the importance of device security and data backup practices. This response aims to build user trust in the application's data handling.

6. **Data Export Capabilities**: I clarified the current limitations regarding data export to other applications, while suggesting manual data entry as an interim solution, indicating an area for potential development in future versions of FinTrack.

#### Contributions to Developer Guide

1. **Main Application Loop**: I documented the initialization process of the application, emphasizing the sequence of operations from loading data, user authentication, to handling user commands. This section provides insight into the application's lifecycle and user interaction flow.

2. **Command Pattern**: My contribution highlighted how user commands are encapsulated as objects, facilitating the easy addition of new commands. This approach minimizes changes to existing code, showcasing the application's extensibility and maintainability.

3. **Transaction Management**: I elaborated on the implementation of separate classes for managing inflows and outflows, including a unified interface for transaction operations such as addition, editing, and deletion. This part of the guide explains how the application organizes and processes financial data.

4. **User Authentication**: I detailed the authentication process, ensuring that users are securely authenticated before accessing their financial data. This includes mechanisms to track and limit failed login attempts, reinforcing the application's security measures.

5. **Storage Management**: My documentation covered how the application handles the persistent storage of transaction data, allowing for the secure saving and loading of user financial information across sessions. This ensures data reliability and availability.
