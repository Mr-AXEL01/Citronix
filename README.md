# Citronix 🍋

[![Java](https://img.shields.io/badge/Java-21-red.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green.svg)](https://spring.io/projects/spring-boot)
[![Tests](https://img.shields.io/badge/Tests-JUnit%205-orange.svg)](https://junit.org/junit5/)
[![Docker](https://img.shields.io/badge/Docker-Available-blue.svg)](https://hub.docker.com/r/mraxel01/citronix)

## About

Citronix is a comprehensive farm management system designed specifically for lemon farms. It enables farmers to efficiently track production, harvesting, and sales of their products while optimizing tree productivity based on age. The system provides tools for managing farms, fields, trees, harvests, and sales with a focus on maintaining strict agricultural constraints and maximizing productivity.

## Features

### Farm Management
- Create, update, and view farm information (name, location, area, creation date)
- Multi-criteria search using Criteria Builder
- Comprehensive farm analytics and reporting

### Field Management
- Associate fields with farms with defined areas
- Automatic area consistency validation
- Maximum 10 fields per farm
- Field area must be between 0.1 and 50% of farm area

### Tree Management
- Track planting date and age
- Calculate productivity based on tree age:
  - Young trees (<3 years): 2.5 kg/season
  - Mature trees (3-10 years): 12 kg/season
  - Old trees (>10 years): 20 kg/season
- Maximum density: 100 trees per hectare
- Planting restricted to March-May period

### Harvest Management
- Seasonal harvest tracking (Winter, Spring, Summer, Fall)
- One harvest per season (every 3 months)
- Detailed tracking per tree
- Prevention of double harvesting in the same season

### Sales Management
- Record sales with date, unit price, and customer information
- Automatic revenue calculation (Revenue = quantity * unit price)
- Link sales to specific harvests
- Sales analytics and reporting

## Technical Stack

- **Backend Framework**: Spring Boot
- **Database**: PostgreSQL
- **Architecture**: Layered Architecture
  - Controllers
  - Services
  - Repositories
  - Entities
- **Data Validation**: Spring Validation
- **Testing**: JUnit 5 & Mockito
- **Code Generation**: Lombok with Builder Pattern
- **Object Mapping**: MapStruct
- **Exception Handling**: Centralized exception management
- **Documentation**: Swagger/OpenAPI
- **Containerization**: Docker

## Prerequisites

- Docker and Docker Compose installed
- Git (optional, for development)
- Minimum 4GB RAM recommended
- 10GB free disk space

## Installation and Setup

### 1. Create Environment File
Create an `.env` file in your project directory:
```env
POSTGRES_DB=your_db_name
DB_USERNAME=your_username
DB_PASSWORD=your_password
PGADMIN_DEFAULT_EMAIL=your_email@example.com
PGADMIN_DEFAULT_PASSWORD=your_pgadmin_password
```

### 2. Create Docker Compose File
Create a `docker-compose.yml` file:
```yaml
version: '3.8'
services:
  app:
    image: mraxel01/citronix:latest
    ports:
      - "8080:8080"
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/${POSTGRES_DB}
      SPRING_DATASOURCE_USERNAME: ${DB_USERNAME}
      SPRING_DATASOURCE_PASSWORD: ${DB_PASSWORD}
    depends_on:
      - postgres
    networks:
      - app-network
    restart: unless-stopped

  postgres:
    image: 'postgres:latest'
    environment:
      - POSTGRES_DB=${POSTGRES_DB}
      - POSTGRES_PASSWORD=${DB_PASSWORD}
      - POSTGRES_USER=${DB_USERNAME}
    ports:
      - "5432:5432"
    networks:
      - app-network

  pgadmin:
    image: 'dpage/pgadmin4:latest'
    environment:
      PGADMIN_DEFAULT_EMAIL: ${PGADMIN_DEFAULT_EMAIL}
      PGADMIN_DEFAULT_PASSWORD: ${PGADMIN_DEFAULT_PASSWORD}
      PGADMIN_LISTEN_PORT: 5050
    ports:
      - '5050:5050'
    networks:
      - app-network

networks:
  app-network:
    driver: bridge
```

### 3. Run the Application
```bash
# Start all services
docker-compose up -d

# Check status
docker-compose ps

# View logs
docker-compose logs -f
```

### 4. Access the Application
- **API**: http://localhost:8080
- **Swagger Documentation**: http://localhost:8080/swagger-ui.html
- **PgAdmin**: http://localhost:5050
- **Database**: localhost:5432

## Project Structure

```
citronix/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── net/
│   │   │       └── axel/
│   │   │           └── citronix/
│   │   │               ├── controller/
│   │   │               ├── domain/
│   │   │               │   ├── dtos/
│   │   │               │   │   ├── farm/
│   │   │               │   │   ├── field/
│   │   │               │   │   ├── harvest/
│   │   │               │   │   ├── harvestDetail/
│   │   │               │   │   ├── sale/
│   │   │               │   │   └── tree/
│   │   │               │   ├── embeddeds/
│   │   │               │   ├── entities/
│   │   │               │   └── enums/
│   │   │               ├── exception/
│   │   │               │   └── domains/
│   │   │               ├── mapper/
│   │   │               ├── repository/
│   │   │               ├── service/
│   │   │               │   └── impl/
│   │   │               └── validation/
│   │   │                   └── validator/
│   │   └── resources/
│   └── test/
```

## Business Rules

### Field Constraints
- Minimum area: 0.1 hectare (1,000 m²)
- Maximum area: 50% of farm's total area
- Maximum 10 fields per farm

### Tree Management
- Maximum density: 100 trees per hectare (10 trees per 1,000 m²)
- Maximum productive age: 20 years
- Trees can only be planted between March and May
- Productivity varies by age group:
  - Young: <3 years
  - Mature: 3-10 years
  - Old: >10 years

### Harvest Rules
- One harvest per season per field
- No double harvesting of trees in the same season
- Seasonal harvests: Winter, Spring, Summer, Fall
- Each tree can only be harvested once per season

## API Documentation

Complete API documentation is available through Swagger UI at `http://localhost:8080/swagger-ui.html` when the application is running.

## Error Handling

The application implements a centralized error handling mechanism that provides:
- Consistent error response format
- Detailed error messages
- Appropriate HTTP status codes
- Validation error details

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## Troubleshooting

### Common Issues
1. **Port Conflicts**: Ensure ports 8080, 5432, and 5050 are available
2. **Memory Issues**: Ensure Docker has enough allocated memory
3. **Database Connection**: Check environment variables in .env file

### Solutions
```bash
# Check container logs
docker-compose logs [service_name]

# Restart services
docker-compose restart

# Reset everything
docker-compose down
docker-compose up -d --force-recreate
```

## Contact

- **Project Link**: [https://github.com/Mr-AXEL01/citronix](https://github.com/Mr-AXEL01/citronix)
- **Email**: abdelhakazrour3@gmail.com
- **LinkedIn**: [Abdelhak Azrour](https://www.linkedin.com/in/abdelhak-azrour-0742b1264/)
- **X (Twitter)**: [@azrourax](https://twitter.com/azrourax)

Feel free to reach out for any questions, suggestions, or collaboration opportunities!
