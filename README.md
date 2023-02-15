# apex-backend

Problem Statement
-----------------
A retailer offers a rewards program to its customers, awarding points based on each recorded purchase. 
 
A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent between $50 and $100 in each transaction.
(e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).
 
Given a record of every transaction during a three month period, calculate the reward points earned for each customer per month and total.

Technology Stack
----------------

Database: H2 Database (In Memory)
          NOTE: For the sake of ease of use and demonstration, H2 Database was chosen.
          In a production setting, the database would not be in same process space as backend.
          
          
Java version: Java 8
API Documentation: Swagger UI

How To Build
-----------

1. cd to project root directory.
2. Type:
      mvn clean package
   
   NOTE: During the build phase, 4 tests are executed.
 
How To Run
----------

   Type:
   
     java -jar target\apex-backend-0.0.1-SNAPSHOT.jar
     
 
     - Next,  navigate to Swagger UI api @  http://localhost:8080/swagger-ui.html
      
     - Next, use the Swagger UI testing tool to invoke the API:
      
      GET /api/customer/points/{startDate}/{endDate}
     
       The api is invoked using path parameters where {startDate} and {endDate) is purchase range.
       
       startDate & endDate should be submitted in the form of MM-YYYY (ie: 11-2022)
 
       Example:
          startDate: 11-2022
          endDate: 02-2023
          
 Additional Notes
 ----------------
 To browse the H2 Database, simply navigate to http://localhost:8080/h2-console/
 
 DB User: sa
 DB Password: sa
 
     
     
      

