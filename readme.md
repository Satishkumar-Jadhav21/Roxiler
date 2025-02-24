```markdown
# Assignment for Roxiler Pvt Ltd

## Features
- Initialize database from external API
- Transaction listing with search/pagination
- Monthly sales statistics
- Price range distribution chart
- Category-wise pie chart
- Combined data endpoint

## Technologies Used
- Java 21
- Spring Boot 
- MySQL 
- Maven 
- REST APIs
- Chart.js (Frontend)

## Project Structure

src/main/
├── java/
│   └── com/roxiler/
│       ├── controller/       # API endpoints (REST Controllers)
│       ├── service/          # Business logic implementations
│       ├── repository/       # Database interfaces (JPA Repositories)
│       ├── model/            # Entity classes (JPA Models)
│       ├── dto/              # Data Transfer Objects
│       └── Application.java  # Main entry point
├── resources/
│   ├── static/              # Frontend assets (HTML/CSS/JS)
│   └── application.properties # Configuration
```

## Component Relationships
1. **Controllers** handle HTTP requests and delegate to Services
2. **Services** contain business logic and use Repositories
3. **Repositories** interact with MySQL database via JPA
4. **Models** map to database tables
5. **DTOs** structure API responses
6. **Frontend** makes API calls and renders charts

## Prerequisites
- Java 21 JDK
- MySQL Server 
- Maven 

## Database Setup

1. **Create Database and User**:
```sql
CREATE DATABASE roxiler;
USE roxiler;

CREATE TABLE products (
    id BIGINT PRIMARY KEY,
    title VARCHAR(1000),
    description TEXT,
    price DECIMAL(10,2),
    category VARCHAR(1000),
    date_of_sale TIMESTAMP,
    sold BOOLEAN
);

```

## Installation & Setup

```

2. **Configure Database**:
   Edit `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/roxiler
spring.datasource.username=roxiler_user
spring.datasource.password=roxiler_pass
spring.jpa.hibernate.ddl-auto=update
```

3. **Build Project**:
```bash
mvn clean install
```

## Running the Application

**Using IntelliJ IDEA**:
1. Open project
2. Maven > Reload Project
3. Run `RoxilerApplication.java`

**Command Line**:
```bash
mvn spring-boot:run
```

## Initialize Database
```bash
url  POST http://localhost:8080/api/initialize
```

## API Endpoints
| Endpoint | Method | Parameters | Description |
|----------|--------|------------|-------------|
| `/api/initialize` | POST | - | Seed database |
| `/transactions` | GET | month, page, search | Paginated transactions |
| `/statistics` | GET | month | Sales statistics |
| `/bar-chart` | GET | month | Price ranges data |
| `/pie-chart` | GET | month | Category distribution |
| `/combined` | GET | month | All combined data |

## Frontend Access
Access dashboard at: `http://localhost:8080`

## Troubleshooting

**Common Issues**:
1. **Database Connection Issues**:
    - Verify MySQL service is running
    - Check credentials in `application.properties`
    - 
2. **Data Initialization Failure**:
    - Check third-party API availability
    - Verify internet connection
    - Ensure MySQL user has write privileges

3. **Port Conflicts**:
   ```properties
   server.port=8081
   ```




