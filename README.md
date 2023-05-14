# Automatic Irrigation System
This is an Irrigation System Application that enables farmars to manage the irrigation of their lands automatically based on predefined time slots. The application is built using the Java programming language and the Spring boot framework.



## Technologies Used
- Java
- Spring Boot
- Spring Data JPA
- MySQL
- Maven
- Postman
- Lombok
- mapstruct


## Getting Started
- To run the application you will need to have Java 8+, Maven and MySQL installed on your machine.

### Clone the Repository
- Start by cloning the repository to your local machine:

```
git clone https://github.com/SohailaBarakat/Automatic_Irrigation_System.git
```

### Database Setup
- Create db named irrigation_system and set the username and password values in the persistence.xml

### Build and Run the Application
- To build and run the application, navigate to the project directory and execute the following command:

```
mvn spring-boot: run
```

- The application should now be running on http://localhost:8080.


## API documentation
The API has the following endpoints:

### Plot endpoints
- POST /plot - Create a new plot of land.
- PUT /plot - Update an existing plot.
- GET /plot - Get all plots.
- DELETE /plot/{name} - Delete a plot by name.
- GET /plot/{id} - Get a plot by ID

### Time Slot endpoints
- POST /irrigation - Create a new irrigation time slot.


## Scheduler
There is also a scheduler that runs every 1 hour to check if any plots need to be irrigated according to their time slot. If irrigation fails, it will retry up to 5 times and then send an alert.

At midnight, the scheduler resets all time slots status so the irrigation processes starts over the next day.  



