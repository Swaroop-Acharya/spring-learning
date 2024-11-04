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
