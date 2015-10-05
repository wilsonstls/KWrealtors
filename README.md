# KWrealtors

final project for the LaunchCode JAVA course

JAVA swing application for an in-house employee portal used by a real estate company.
The portal frame that an employee is directed to depends on their job type. The type of employees are:   Agents; Clerical Staff; Managers. Each job type is allowed to preform certain activities such as add or update a property listing or delete an employee from the database. 

Will need to know employee IDs; real estate license no; property listing ids
Program immediately asked for an Employee ID - start by using id: 11111
this ID can list out other ids, property listings

Two hibernate tables are use to store the company info. 
Employee - stores all employee info. Only the Clerical staff can make changes to this table.
Property - stores all the avaliable property listed by the company. Only an agent can make changes to this table. Agents can add a listing and only that agent can then update or delete that listing. 

java version:  1.8.0 
