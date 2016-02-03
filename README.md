KWrealtors

Author:  Kevin Wilson

Purpose: 
Application utilized by employees of a Real Estate company.

Project was created in conjunction with LaunchCode - St. Louis, MO

Summary:
Application immediately opens to a GUI screen where the employee enters 
his employee ID & password. If correct, user is then directed to their 
individual portal screen based on their job type 
(A = agent / C = clerical / M = manager). At this screen the employee 
can perform various job functions. 

Agent's in their portal can perform: 

    property listing lookups. 

    map retrieval by street address of a listing

    Add a new listing / Delete a listing / Update an existing listing
    
Clerical's in their portal can perform: 

    Add a new employee / Move an employee to inactive / Update employee info.
    
Manager's in their portal can perform: 

  List all active employees 
  
  List all available properties for sale 
  
  List properties by agent
  
  List property by price from highest to lowest. 


Sample of ID & passw per job type:  
A - 11115  ABC
C - 11120  ABC
M - 11111  ABC

Hibernate table files stored in data directory. 

Written in:    Java SE8

DB:           Hibernate

Build tool:   Apache Maven

Other tools:  spring framework;  swing components

API:          Google Static Maps

IDE:          Intellij IDEA
