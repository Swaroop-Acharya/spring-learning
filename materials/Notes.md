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

A Spring Boot application typically produces a fat JAR file in the `target` folder, which contains all necessary dependencies to run the application, including an embedded Tomcat server. The `.jar.original` file contains only the original source code.

 


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

