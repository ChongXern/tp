# Yong Chen How - Project Portfolio Page

## Overview
Finance tracking application that helps keep track of all things related to finances such as income, spending, expenditure, reminders, gifts, and more.

### Summary of Contributions
[**Code contributed**](https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2024-02-23&tabOpen=true&tabType=authorship&tabAuthor=chenhowy&tabRepo=AY2324S2-CS2113-F14-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false) by Chen How

#### Enhancements implemented
1. **Storage class**

    The storage class deals with loading and storing all user data. 
   - Loading user data when app launches.
   - Creating file directories if they do not exist.
   - Storing user data after each user action/ user quits.
   - Also created a create user method to allow the app to handle multiple users. However, this was later unused as it was out of project scope.


2. **Created reminder class**

    This class is similar to both inflows and outflows, however the main distinction is that reminders are for transactions that have yet to happen.
   - This class was extended from both inflows and outflows, which was not written by myself. 


3. **Generate report**

    There are two forms of generate report: generateReport and generateQuickReport.
   - generateQuickReport is only used when app is launched and after user has logged in. It gets a quick overview of the spendings in the past 1 month.
   - generateReport is called when the user wants to check any month that has already passed. It gets a full overview comparing inflows and outflows, so that the user can see their comprehensive spendings.


4. **Budget**
    Budget keeps track of total amount the user sets to spend each month. When logging in, the user is reminded of the budget they have left to spend.
   - Budget is currently just a variable under Transaction Manger. 
   - It could be more fleshed out as a full class on its own to have more comprehensive features such as having different budgets each month and having separate categories.

5. **Help command**
   This command generates a simple string which consists of all commands available for the user to use.

#### Contributions to user guide
Added personal contributions such as commands for budget, generate report and reminder class.

#### Contributions to developer guide
Added personal contributions such as commands for budget, generate report and reminder class.

#### Contributions to team-based tasks
Helped with debugging, especially when teammates faced certain issues with their build.