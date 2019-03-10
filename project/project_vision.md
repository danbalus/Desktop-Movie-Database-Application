# (Movie Database) Vision

# Introduction
<!---
Mi-am ales ca tema de proiect Movie Database ce presupune o platforma(asemanatoare cu site-ul http://www.imdb.com/) in care utilizatorii pot cauta, nota filme si vedea trailere ale acestora.
-->

The purpose of this document is to collect, analyze, and define high-level needs and features of the Movie Database. It focuses on the capabilities needed by the stakeholders and the target users, and why these needs exist. The details of how the Movie Database fulfills these needs are detailed in the use-case and supplementary specifications.
The introduction of the Vision document provides an overview of the entire document. It includes the purpose, scope, definitions, acronyms, abbreviations, references, and overview of this Vision document.
---------------------------------------
## Purpose
To define the high-level requirements of the in terms of the needs of the users.

## Scope
This system will allow users to access a SQL database server, read, display, edit, add or delete information from tables.

## Definitions, Acronyms, and Abbreviations
- SQL = Structured Query Language – language used to manage databases;
- GUI = Graphical User Interface;
- DAVE = Delete, Add, View, Edit;
- DB = Database;
- JDBC = Java Database Connectivity;
## References
This subsection provides a complete list of all documents referenced elsewhere in the Vision document. Identify each document by title, report number if applicable, date, and publishing organization. Specify the sources from which the references can be obtained. This information may be provided by reference to an appendix or to another document.

## Overview
In this document you will find information about the function of the application in the specific environment. 
In the subsections they will be able to see how applications solve the tasks.

# Positioning
## Problem Statement
Provide a statement summarizing the problem being solved by this project. The following format may be used:

|||
|----|------- |
| **The problem of** | Definition and application of queries on databases
| **affects**  | Non-technical users
| **the impact of which is** |  to apply queries on databases **without learning** SQL
| **a successful solution would be** | a GUI that allows the user to _create queries by pressing multiple buttons_ in a desktop aplication. Also via the user interface can see the result of the queries.

## Product Position Statement
<!---
Provide an overall statement summarizing, at the highest level, the unique position the product intends to fill in the marketplace. The following format may be used:
-->
|||
|----|------- |
| **For** | For 	Non-technical users |
| **Who** | Want to query a database and see the results |
| **The** | (Movie Database) is a software product/ application
| **That** | Provide a desktop application which allows DAVE information from DB
| **Unlike** | The current state requires that they must write query statements in SQL to obtain results.
| **Our product** | Allows a better database management without writing any SQL code lines

A product position statement communicates the intent of the application and the importance of the project to all concerned personnel.

# Stakeholder and User Descriptions
<!---
To effectively provide products and services that meet your stakeholders’ and users' real needs, it is necessary to identify and involve all of the stakeholders as part of the Requirements Modeling process. You must also identify the users of the system and ensure that the stakeholder community adequately represents them. This section provides a profile of the stakeholders and users involved in the project, and the key problems that they perceive to be addressed by the proposed solution. It does not describe their specific requests or requirements as these are captured in a separate stakeholder requests artifact. Instead, it provides the background and justification for why the requirements are needed.
-->
The target market is people that doesn’t know a query language, but they want to use queries on databases and manipulate a database. 

## Stakeholder Summary
There are a number of stakeholders with an interest in the development and not all of them are end users. Present a summary list of these non-user stakeholders. (The users are summarized in section 3.2.)

### Stakeholder 1
* **Name**: Regular end-user.
* **Description**: People who don’t know any query language and want to manipulate DB.
* **Responsibilities**:Connecting to a database server and apply queries on DB.

### Stakeholder 2
* **Name**: Administrator
* **Description**: The application designer
* **Responsibilities**:He ensures that the application works in optimal parameters, ensures that the system will be maintainable and  monitors the project’s progress.

## User Summary
Present a summary list of all identified users.

### User 1
* **Name**: Regular end-user
* **Description**: Manipulate the DB server.
* **Responsibilities**: Applies queries.
* **Stakeholder**: Self-represented.

### User 2
* **Name**: Administrator.
* **Description**: Extract reports about the system.
* **Responsibilities**: Ensures that the system will be maintainable and good working.
* **Stakeholder**: Self-represented.

### User 3
* **Name**: PRO user.
* **Description**: Manipulate the DB server.
* **Responsibilities**: Applies queries.
* **Stakeholder**: Self-represented.

## User Environment

Number of people involved in completing the task is one.

The user will be able to choose from several actions as follows:
- review a movie;
- generate raports about movies;
- notified;
- sign up and log in;
- to buy PRO account for more;
- movie suggestions;
- create whatchlist and anther lists;
- news about movies;
- see actors/ directors etc;
- see top movies.

Amount of time spent in each activity depends on how complex is the DB and how many DBs are on aplication.
Environmental constraints: this desktop applications needs desktop platforms to run like PCs.
Can be exended on mobile.

# Product Requirements
The server must be compatible with JDBC and shall run under the all operating systems.
 The client component of the system shall operate on any personal computer with a dual core processor or better and not require more than 1 GB RAM and 50 MB Disk Space. The client component of the system shall run on  Windows 7 or newer.
 The system shall provide access to the server databases and return the results in maximum  5 second latency. 


# Bibliography

- [Markdown online editor](http://dillinger.io/)
- [Markdown Tutorial](https://www.markdowntutorial.com )
- [Mastering Markdown](https://guides.github.com/features/mastering-markdown/)
