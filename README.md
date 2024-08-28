# Spring Boot

# what is spring boot - Its a module of spring which speedsup the developement of the application
-  spring framework + Embeded servers - [Configuration] = spring boot
- convention over configuration software design style
- It Decreases the efforts of developers
- scan the class path and find the dependency it will automatically configure the things

# Advantages of Spring boot
- It creates stand-alone spring appliations that can be started using Java -jar.
- Embeded tomacat,jetty or undertow directly(no need to deploy WARfiles)
- Provided opinionated starter dependecies to simply your build configuration
- automatically configure spring and 3rd party libraries
- no code generation and no requirement for XML configuration

# CURD operation

# Service class
In a Spring Boot application, a service class is used to encapsulate the business logic of the application. It sits between the controller and the repository layers. Here's what a service class typically does:

- Business Logic: Contains the business logic that might be too complex to include directly in the controller. This keeps the controller focused on handling HTTP requests and responses.
- Transaction Management: The service layer is often where transactions are managed. You can use annotations like @Transactional to define the scope of a single transaction.
- Interaction with Repositories: The service class interacts with the repository classes to retrieve, save, update, or delete data in the database.
- Decoupling: By using a service layer, you can change the business logic without affecting the controllers. This decoupling makes your application more modular and easier to maintain.

# DTO(Data Transfer Object) class
A DTO (Data Transfer Object) is a design pattern used to transfer data between different layers of an application or between different systems. DTOs are especially useful for managing the data that travels over the network, such as when interacting with a database, an API, or different parts of an application.
- Separation of Concerns: By separating the internal model from the data transfer format, you can modify the internal data structures without affecting external interfaces.
- Validation and Transformation: DTOs allow you to validate and transform data before processing or persisting it.
- Data Shaping: You can include only the necessary fields in a DTO, making API responses leaner and more efficient.
- Security: By using DTOs, you can avoid exposing sensitive data that is part of your internal models.

# Response Entity Class
- ResponseEntity is a powerful class in Spring that allows you to build and customize HTTP responses. It provides control over the HTTP status code, headers, and the body of the response, making it very useful for creating RESTful APIs.
