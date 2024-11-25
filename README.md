# Citronix 🍋

[![Java](https://img.shields.io/badge/Java-21-red.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green.svg)](https://spring.io/projects/spring-boot)
[![Tests](https://img.shields.io/badge/Tests-JUnit%205-orange.svg)](https://junit.org/junit5/)

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

## Prerequisites

- Java 21 or higher
- Maven 3.8+
- Your favorite IDE (IntelliJ IDEA recommended)
- PostgreSQL (configure in application.properties)

## Installation

```bash
# Clone the repository
git clone https://github.com/yourusername/citronix.git

# Navigate to project directory
cd citronix

# Install dependencies
mvn clean install

# Run the application
mvn spring-boot:run
```

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
│   │       ├── static/
│   │       └── templates/
│   └── test/
│       └── java/
│           └── net/
│               └── axel/
│                   └── citronix/
│                       └── service/
│                           └── impl/
└── pom.xml
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

The API documentation is automatically generated using Swagger and is available at:
```
http://localhost:8080/swagger-ui.html
```
when running the application.

## Testing

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=FarmServiceTest

# Generate test coverage report
mvn verify
```

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

## Development Guidelines

- Follow Java code conventions
- Write unit tests for new features
- Update documentation when adding new features
- Use meaningful commit messages
- Keep the code clean and maintainable

## Contact

Project Link: [https://github.com/Mr-AXEL01/citronix](https://github.com/Mr-AXEL01/citronix)
