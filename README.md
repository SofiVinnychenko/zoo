Zoo API is a RESTful web application developed using Spring Boot, Spring Data JPA (Hibernate), and PostgreSQL.
The project implements CRUD functionality, working with relationships between entities, as well as documentation display using Swagger.

Technology Stack
- Java 21
- Spring Boot
- Spring Data JPA (Hibernate)
- PostgreSQL
- Swagger
- Maven
  
The project models a zoo information system with the following main entities:
- Animals
- Employees
- Enclosure
- FeedingSchedule
- Food
  
Relationships between entities are implemented:
- Animal M:1 Enclosure
- Enclosure M:M Employees
- FeedingSchedule M:1 Enclosure
- FeedingSchedule M:M Employees
- FeedingSchedule M:M Food
  
For each entity, a complete multi-level structure is implemented:
Entity -> DTO -> Repository -> Service -> Controller
