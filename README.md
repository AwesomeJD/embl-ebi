

# EMBL-EBI Assignment


## Get the solution running...
The application is a spring-boot application.

### Prerequisites:
* Docker installed
* GIT installed.
* A shell to run the .sh file. 

### Steps to run:
1. Checkout the code from the git repo:
2. run the run.sh 
    1. This will build the jar file
    2. Then will build the image.
    3. Then will run the application on the port 8080

The application postman collection can be found in the git repo. 

The CRUD operations are available for the Person entity. 

1. To get a person by Id:

            GET : http://localhost:8080/manage/person/2
2. To Persist a Person:

            POST : http://localhost:8080/manage/person/

3. To Update a Person:

            PUT : http://localhost:8080/manage/person/

4. To delete a person by Id:

            DELETE : http://localhost:8080/manage/person/1

5. To get Persons by Ids:

            GET : http://localhost:8080/manage/person?ids=1,2,3



### Exception scenarios.
If the input is invalid, like firstName or lastname or age.

        {
                "errors": [
                    {
                        "code": "1001",
                        "message": "[The first name cannot be null. ]",
                        "type": "BAD USER INPUT"
                    }
                ]
        }

If an ID is not found then the below exception is thrown:

        {
                "errors": [
                    {
                        "code": "1001",
                        "message": "No Person found for the Id provided.",
                        "type": "Business Error"
                    }
                ]
        }

## Design details:
1. The application is a spring-boot application. 
2. Embedded H2 database is used for this assignment. This is totally configuration via profiles. 
And in production or higher environments any DB can be used.
   
3. The Caching is sued between the service and database layer. The caching time to live is controlled by the application property.
For Caching embedded Hazlecast is used. This can be again configured if distributed cache needs to be used.
   
4. The validation framework of spring is used.
5. The Exception handling is controlled via a control advice. The application throws only a single exception known as ApplicationException. 
All the exceptions are neatly caught and can be handled as per convenience.
   
6. The application can be deployed in a kubernetes cluster and properties for each environment can be managed via a package manager like helm-chart.
7. A few unit tests and functional tests has been added. 
8. The Query pattern can be added to retrieve a large amount of Person entities. 
9. Looging has been added that can help in debugging the application. 
10. Springfox is used for the documentation of the API and can be accessed using the URL:
    
        http://localhost:8080/swagger-ui/index.html
11. Google code formatter is used. To format the code, run the maven goal spotless:apply
