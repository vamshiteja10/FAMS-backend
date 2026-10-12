# FarmAssist Backend – Farmer Resource & Crop Management System

Spring Boot REST API backend service with MySQL integration.

## Technology Stack
- Java 21 LTS
- Spring Boot 3.2.5 (Spring Web, Spring Data JPA, Spring Validation)
- MySQL Connector / Hibernate
- Maven

## Database Setup
1. MySQL schemas and sample data are located in `/database`:
   - `database/schema.sql` (Creates `farmassist_db` and tables)
   - `database/seed_data.sql` (Loads initial farmers, land parcels, crops, etc.)

## Run Locally
```bash
mvn spring-boot:run
```
Or on Windows:
```cmd
mvn.cmd spring-boot:run
```
The server will run on `http://localhost:8080/api`.

## Environment Variables for Cloud Deployment (Render)
- `PORT`: Server port (automatically assigned by Render, defaults to `8080`)
- `MYSQL_URL`: Cloud MySQL JDBC URL (e.g., `jdbc:mysql://<host>:<port>/<dbname>?useSSL=true`)
- `MYSQL_USER`: Database username
- `MYSQL_PASSWORD`: Database password

## Build for Production
```bash
mvn clean package -DskipTests
java -jar target/farmassist-0.0.1-SNAPSHOT.jar
```
