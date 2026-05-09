# DVD Rental Web Project

**[Versão em Português](README-PT.md)**

This project is a simple DVD rental CRUD, developed to practice the fundamentals of Java Web development. The primary focus was implementing the communication between a Web application and a relational database using **Java Servlets** and the **DAO (Data Access Object)** pattern, powered by **MySQL**.

## Features
* Full CRUD (Create, Read, Update, Delete) for DVD management.
* Direct database interaction using JDBC.
* Clean separation of database logic using the DAO pattern.

## Technologies
* **Java** (JDK 17 or higher)
* **Java Servlets** (Native API)
* **MySQL / MariaDB** (via XAMPP & MySQL Workbench)
* **JSP & JSTL** (JavaServer Pages)
* **GlassFish** (Application Server)
* **NetBeans** (IDE/Build System)

## Database Setup
This project includes a visual data model file: `ModeloBanco.mwb`.

1. Open **XAMPP Control Panel** and start the **MySQL** module.
2. Open **MySQL Workbench**.
3. You can open `ModeloBanco.mwb` to view the ERR Diagram or simply run the script below in a new Query Tab:

## How to Run
1. Clone this repository.
2. Ensure the **MariaDB/MySQL Connector** and **JSTL** libraries (located in `lib/`) are added to your project's Libraries in NetBeans.
3. Update your `ConnectionFactory` class with your MySQL credentials (user: `root`, no password by default in XAMPP).
4. **Clean and Build** the project.
5. Deploy and run on the **GlassFish** server.