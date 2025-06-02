Zoo API — це RESTful веб-застосунок, розроблений з використанням Spring Boot, Spring Data JPA (Hibernate) та PostgreSQL. 
Проєкт реалізує CRUD-функціональність, роботу зі зв’язками між сутностями, а також відображення документації за допомогою Swagger.

Технологічний стек
- Java 21
- Spring Boot
- Spring Data JPA (Hibernate)
- PostgreSQL
- Swagger
- Maven

Опис проєкту
Проєкт моделює інформаційну систему зоопарку з такими основними сутностями:
- Animals — тварини
- Employees — працівники
- Enclosure — вольєри
- FeedingSchedule — графіки годування
- Food — харчування

Між сутностями реалізовано зв’язки:
- Animal M:1 Enclosure
- Enclosure M:M Employees
- FeedingSchedule M:1 Enclosure
- FeedingSchedule M:M Employees
- FeedingSchedule M:M Food

Для кожної сутності реалізовано повну багаторівневу структуру:
Entity -> DTO -> Repository -> Service -> Controller
