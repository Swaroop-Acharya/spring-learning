# Spring Boot Application Overview

## Introduction

In the past, we created a Spring application context using `AnnotationConfigApplicationContext`. Now, Spring Boot manages this process behind the scenes.

The `@SpringBootApplication` annotation provides a wealth of preconfigured features, including automatic component scanning and embedded server configuration.

## Beans

A **bean** is an object created from a class that can be used multiple times throughout the application. With Spring Boot, beans are automatically scanned thanks to the `@SpringBootApplication` annotation.

## Application Context

The **application context** is responsible for creating beans that can be injected where needed. In Spring Boot, this process is automated, eliminating the need for manual configuration.

## JAR and WAR Files

- A **JAR (Java Archive)** file can be executed directly from the command line.
- A **WAR (Web Application Archive)** file must be deployed on an external server to run.

### Context Path

For example, the URL `localhost:8080/my-project/hello` has `my-project` as its context path.

## Maven

[Maven](https://maven.apache.org/) is a build automation tool that manages project dependencies.

### Maven Lifecycle

The Maven lifecycle consists of several phases:

- `validate`
- `compile`
- `test`
- `package`
- `verify`
- `install`
- `deploy`
- `clean`

The Maven wrapper simplifies the execution of Maven commands.

- **`mvn package`**: Compiles the project, runs tests, and packages it into a JAR file located in the `target` folder. It also creates a fat JAR, which includes all dependencies, due to the `org.springframework.boot.maven.plugin`.
  
- **`mvn install`**: Copies the JAR file from the `target` folder to the local `.m2` repository, where all dependencies are stored.
  
- **`mvn clean`**: Deletes the `target` folder, removing all built artifacts.

## File Structure of a Spring Boot Application

- **`.mvn`**: Contains the Maven wrapper.
- **`src`**: Divided into `main` and `test`.
  - Inside `main`, there are two folders:
    - `java`: For source code.
    - `resources`: For configuration files, such as database settings.

## `pom.xml`

The `pom.xml` file (Project Object Model) contains project information and configurations. When executing Maven commands, it utilizes plugins defined within this file to convert source code into JAR or WAR files.

## JAR Files


### Inversion of Control (IoC) Container

In Spring, the **Inversion of Control (IoC) Container** is responsible for managing the lifecycle and configuration of application objects (beans). Instead of creating objects directly, we request instances from the IoC Container, which handles dependencies and configurations automatically.

### Application Context

The **Application Context** is a central interface in the Spring Framework for creating and accessing the IoC Container. It initializes and configures beans, making it a way to set up an IoC Container. When the Application Context is started, it scans the application for classes with specific annotations (such as `@Component`, `@Service`, `@Repository`, etc.), creates instances of these classes, and manages them as beans within the container.

---

## Core Annotations

### `@Component`

The `@Component` annotation is a general-purpose annotation that marks a class as a Spring bean. When a class is annotated with `@Component`, Spring detects it during the component scan process, creates an instance of it, and manages it as a bean in the IoC Container.

In addition to `@Component`, Spring provides specialized stereotypes:
- **`@Service`**: For service-layer classes.
- **`@Repository`**: For data access objects (DAOs) and persistence handling.
- **`@Controller`** and **`@RestController`**: For web controllers in Spring MVC.

### `@SpringBootApplication`

The `@SpringBootApplication` annotation is typically used in the main class of a Spring Boot application. It combines three essential annotations:
1. **`@Configuration`**: Marks the class as a source of bean definitions.
2. **`@EnableAutoConfiguration`**: Enables Spring Boot's auto-configuration, which configures the application based on its dependencies (e.g., setting up a DataSource if `spring-boot-starter-data-jpa` is on the classpath).
3. **`@ComponentScan`**: Scans the package of the annotated class and subpackages for Spring components (like `@Component`, `@Service`, `@Repository`, `@Controller`) and registers them as beans in the Application Context.

### `@ComponentScan`

The `@ComponentScan` annotation tells Spring which packages to scan for `@Component` and other stereotype annotations. It enables Spring to automatically detect and register classes as beans in the IoC Container.

### `@RestController`

The `@RestController` annotation is a specialization of `@Controller`. It is used to define RESTful web services. In addition to registering the class as a Spring bean, `@RestController` combines `@Controller` and `@ResponseBody`, meaning methods in a `@RestController` return data directly as JSON or XML responses.

### `@Autowired`

The `@Autowired` annotation is used for **dependency injection**. It allows Spring to resolve and inject collaborating beans into a given bean. For instance, if a class needs to use another class (e.g., a service needing a repository), `@Autowired` tells Spring to automatically inject the required bean.

### `@EnableAutoConfiguration`

The `@EnableAutoConfiguration` annotation, enabled by default in Spring Boot applications through `@SpringBootApplication`, configures Spring Boot based on the libraries it detects on the classpath. For instance, if it finds MongoDB dependencies, it will automatically configure MongoDB-related beans.

### `@Configuration`

The `@Configuration` annotation marks a class as a source of bean definitions. Classes annotated with `@Configuration` often contain `@Bean`-annotated methods that define the beans to be managed by the IoC Container. By declaring a class as `@Configuration`, you allow Spring to treat it as a configuration class for setting up beans.

### `@Bean`

The `@Bean` annotation is applied to methods within a `@Configuration` class. It tells Spring that the object returned from that method should be registered as a bean in the Application Context. This enables defining beans that require specific configurations or complex construction.

---

# Spring Boot REST API Example

This project is a basic example of a REST API in Spring Boot, covering concepts like REST verbs, controllers, `@RestController`, and entity classes.

---

## REST API Overview

A **REST API** (Representational State Transfer) is an architectural style that allows communication between a client and a server through standard HTTP requests. REST APIs are typically organized around **URLs** and **HTTP verbs** (also known as REST verbs).

### REST Verbs

The key REST verbs used in REST APIs are:
- **GET**: Retrieve data from the server.
- **POST**: Create new data on the server.
- **PUT**: Update existing data on the server.
- **DELETE**: Remove data from the server.

---

## Key Components in a Spring Boot REST API

### Controller

A **Controller** in Spring is a special component that handles HTTP requests from clients. Controllers map client requests to specific methods and define the response behavior.

### `@RestController`

The `@RestController` annotation is a specialized version of `@Controller` in Spring. It is used specifically for RESTful web services and automatically serializes responses to JSON format, allowing easy communication with web clients.

### Entity Class

An **Entity Class** is a Plain Old Java Object (POJO) that represents data to be stored and retrieved, typically from a database. In Spring, entity classes are often annotated with `@Entity` and mapped to database tables, but they can also simply be used as data transfer objects (DTOs) for carrying data between layers in the application.

---

## Example Code

### Controller Example

Below is an example of a simple controller that handles requests for a `User` entity:

```java
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    // GET: Retrieve all users
    @GetMapping
    public List<User> getAllUsers() {
        // Return list of users (dummy data for this example)
        return List.of(new User(1, "John Doe"), new User(2, "Jane Smith"));
    }

    // POST: Create a new user
    @PostMapping
    public User createUser(@RequestBody User user) {
        // In a real application, save the user to the database
        return user;
    }

    // PUT: Update an existing user
    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User updatedUser) {
        // In a real application, update the user in the database
        return updatedUser;
    }

    // DELETE: Delete a user by ID
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {
        // In a real application, delete the user from the database
        return "User with ID " + id + " deleted";
    }
}
```

## Understanding ORM, JPA, and Spring Data JPA

## ORM (Object-Relational Mapping)
Object-Relational Mapping (ORM) is a technique used to map Java objects to database tables. It allows developers to interact with databases using object-oriented programming concepts, making it easier to manage and manipulate relational data in an object-oriented manner.

For example, consider a Java class `User` and a database table `users`. An ORM framework like Hibernate can map the fields in the `User` class to the columns in the `users` table. This makes it easier to perform operations such as **Insert**, **Update**, **Retrieve**, and **Delete** (CRUD operations) on database records using Java objects.

## JPA (Java Persistence API)
JPA (Java Persistence API) is a specification that provides a standard way of managing relational data in Java applications. It defines the set of interfaces and annotations that you can use to work with databases, offering a high-level abstraction for database operations. 

However, JPA by itself is just a specification, and to actually perform ORM operations, you need a persistence provider, which is an implementation of the JPA specification. 

### JPA Persistence Providers
A persistence provider is a framework or library that implements the JPA interfaces and provides the actual functionality for interacting with the database. Examples of JPA persistence providers include:
- **Hibernate** (the most popular implementation)
- **EclipseLink**
- **OpenJPA**

These providers handle all the low-level details of interacting with the database and allow you to focus on writing your Java code.

## Spring Data JPA
Spring Data JPA is a high-level abstraction built on top of the JPA specification. It simplifies working with JPA by providing utility methods and additional functionality. Spring Data JPA is **not** a JPA implementation itself. Instead, it helps you use JPA more easily and efficiently.

Despite the higher-level abstractions, you still need a JPA implementation such as **Hibernate** or **EclipseLink** to handle the actual database interactions. Spring Data JPA reduces boilerplate code and streamlines the process of working with relational data.

### Key Features of Spring Data JPA:
- Provides repository interfaces with common CRUD operations (e.g., `findAll()`, `save()`, `deleteById()`).
- Supports dynamic query creation through method names.
- Integrates seamlessly with Spring Boot for easy configuration and setup.

## JPA vs. MongoDB
JPA is designed to work with **relational databases** (like MySQL, PostgreSQL, Oracle, etc.), and **cannot** be used directly with non-relational databases like **MongoDB**. 

For MongoDB, Spring provides a different persistence provider: **Spring Data MongoDB**. This is a separate library tailored specifically for interacting with MongoDB, which is a NoSQL database.

## Querying in Spring Data JPA

### 1. Query Methods (Method DSL)
Spring Data JPA allows you to create queries based on the method naming conventions. This is a simple and convenient way to build queries by just defining method names. For example:

## 2. Criteria API
Provides more dynamic and programatic approach for building complex and custom queries.

# Lombok Library, MongoDB Relationships, and Indexing in Spring Boot

## Lombok Library

Lombok is a Java library that helps reduce boilerplate code by automatically generating getters, setters, constructors, `toString()`, `equals()`, `hashCode()`, and other methods during compilation. This allows developers to focus more on the logic of their applications instead of writing repetitive code.

### Key Annotations:
- `@Getter` and `@Setter`: Automatically generates getter and setter methods for all fields.
- `@ToString`: Generates a `toString()` method that includes all fields.
- `@EqualsAndHashCode`: Generates `equals()` and `hashCode()` methods based on the fields.
- `@NoArgsConstructor`, `@AllArgsConstructor`, and `@RequiredArgsConstructor`: Generate constructors with various argument configurations.
- `@Data`: A shorthand for generating `@Getter`, `@Setter`, `@ToString`, `@EqualsAndHashCode`, and `@RequiredArgsConstructor` in one annotation.


# Spring Boot with MongoDB and Spring Security

## Table of Contents
1. [Transactional and Transaction Management](#transactional-and-transaction-management)
2. [MongoDB Clusters](#mongodb-clusters)
   - Sharding
   - Replication
3. [Spring Security](#spring-security)
   - Authentication and Authorization
   - Stateless Authentication
4. [YAML Configuration](#yaml-configuration)

---

## Transactional and Transaction Management

### @Transactional
The `@Transactional` annotation is used in Spring to manage transactions declaratively. It ensures that operations performed within a method are treated as a single unit of work. If any step fails, the transaction rolls back.

### @EnableTransactionManagement
The `@EnableTransactionManagement` annotation enables annotation-driven transaction management within a Spring application.

### Transaction Context
The transaction mechanism works with the following components:
1. **Interface**: `PlatformTransactionManager` - This is the core interface for managing transactions in Spring.
2. **Implementation**: `MongoTransactionManager` - The implementation specific to MongoDB.
3. **Configuration**: Configure the `MongoTransactionManager` in the main application class for transaction support.

### MongoDatabaseFactory
This factory is used to create database connections for MongoDB and is integral to configuring transactions.

---

## MongoDB Clusters

MongoDB provides two key techniques for managing data across clusters:

### Sharding
Sharding is a technique to distribute data across multiple servers. It is useful for:
- Handling large datasets.
- Managing high-performance operations.

### Replication
Replication involves maintaining multiple copies of the same data across different servers. It helps in:
- Improving **data availability**.
- Ensuring **fault tolerance**.
- Providing **scalability** by distributing the load across multiple nodes.

---

## Spring Security

### Overview
Spring Security is a powerful and customizable framework for handling **authentication** and **authorization** in Spring Boot applications. 

### Features
- Adding the **Spring Security dependency** makes all endpoints secure by default.
- By default, Spring Security uses HTTP Basic Authentication, requiring a username and password in the header.
- Spring Security manages authentication across requests using **sessions** and **cookies** (e.g., `JSESSIONID`).

### Stateless Authentication
HTTP Basic Authentication is **stateless**, meaning credentials are sent with every request. However, Spring Security can still maintain authentication across multiple requests by leveraging sessions and cookies.

#### Example Configuration
`WebSecurityConfigurerAdapter` can be used to configure custom security rules in a Spring Boot application.

---

## YAML Configuration

The `application.yml` file in Spring Boot allows you to define configurations in a clean and structured format. YAML (Yet Another Markup Language) is not a markup language; it's a human-readable data serialization standard.

### Example
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/mydb
    username: user
    password: password
  security:
    user:
      name: admin
      password: password

