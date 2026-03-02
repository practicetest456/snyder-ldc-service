# Snyder LDC Configuration Microservice

## Overview
This is a comprehensive Spring Boot microservice for Local Distribution Company (LDC) configuration management as part of the Snyder Natural Gas Operational Excellence project. The service handles all LDC configuration functionalities including charges, meter types, quantities, pooling points, storage types, tariffs, transportation overrun limits (TOL), and price products.

## Project Structure

```
snyder-ldc-service/
├── pom.xml                              # Maven configuration
├── src/main/
│   ├── java/com/snyder/ldc/
│   │   ├── SnyderLdcApplication.java   # Main Spring Boot application
│   │   ├── controller/                  # REST Controllers
│   │   ├── service/                     # Business Logic Services
│   │   ├── repository/                  # JPA Repositories
│   │   ├── entity/                      # JPA Entity Classes
│   │   ├── model/                       # Request/Response DTOs
│   │   ├── exception/                   # Custom Exceptions
│   │   └── util/                        # Utility Classes
│   └── resources/
│       ├── application.properties       # Spring Boot Configuration
│       ├── messages.properties          # Localization
│       ├── schema.sql                   # Database Schema
│       └── data.sql                     # Sample Data
└── README.md
```

## Technologies Used
- **Java 17**
- **Spring Boot 3.1.5**
- **Spring Data JPA**
- **H2 Database** (In-Memory)
- **Maven** (Build Tool)
- **Hibernate** (ORM Framework)

## Key Features

### 1. LDC Charges
Manage operational and distribution charges applied by LDC
- Create, Read, Update, Delete (CRUD) operations
- Support for FIXED and VARIABLE charge types
- Effective date tracking
- Service area-based filtering

### 2. LDC Meter Types
Define and manage different meter categories
- Pressure category classification (LOW, MEDIUM, HIGH, VERY_HIGH)
- Max capacity tracking
- Support for Residential, Commercial, Industrial, and Smart meters

### 3. LDC Quantity
Track and manage gas volume measurements
- Support for ALLOCATED, DELIVERED, and SCHEDULED quantities
- Unit conversion support (MCF, MMBTU)
- Meter-based quantity recording

### 4. Pooling Points
Manage locations where gas supply from multiple sources is aggregated
- Capacity tracking
- Active contract management
- Service area association
- Volume balancing capabilities

### 5. LDC Storage Types
Define and manage gas storage categories
- Support for Underground, LNG, and Line Pack storage
- Injection and Withdrawal rate management
- Capacity overflow prevention

### 6. LDC Tariff
Configure pricing rules applied per unit of gas
- Slab-based pricing
- Meter type association
- Effective date management
- Dynamic tariff application

### 7. Monthly TOL (Transportation Overrun Limit)
Calculate and manage imbalance tolerance limits
- Variance percentage calculation
- Automatic penalty application
- Monthly tracking

### 8. Price Product
Define gas pricing contracts
- Support for FIXED and FLOATING pricing types
- Pooling point association
- Formula-based pricing support
- Product linking with tariffs

## API Endpoints

### LDC Charges
```
POST   /api/v1/ldc/charges                    - Create new charge
GET    /api/v1/ldc/charges                    - Get all charges
GET    /api/v1/ldc/charges/{id}              - Get charge by ID
GET    /api/v1/ldc/charges/service-area/{id} - Get charges by service area
PUT    /api/v1/ldc/charges/{id}              - Update charge
DELETE /api/v1/ldc/charges/{id}              - Delete charge
```

### LDC Meter Types
```
POST   /api/v1/ldc/meter-types                - Create new meter type
GET    /api/v1/ldc/meter-types                - Get all meter types
GET    /api/v1/ldc/meter-types/{id}          - Get meter type by ID
PUT    /api/v1/ldc/meter-types/{id}          - Update meter type
DELETE /api/v1/ldc/meter-types/{id}          - Delete meter type
```

### LDC Quantities
```
POST   /api/v1/ldc/quantities                 - Record new quantity
GET    /api/v1/ldc/quantities                 - Get all quantities
GET    /api/v1/ldc/quantities/{id}           - Get quantity by ID
GET    /api/v1/ldc/quantities/meter/{id}    - Get quantities by meter ID
PUT    /api/v1/ldc/quantities/{id}           - Update quantity
DELETE /api/v1/ldc/quantities/{id}           - Delete quantity
```

### Pooling Points
```
POST   /api/v1/ldc/pooling-points             - Create pooling point
GET    /api/v1/ldc/pooling-points             - Get all pooling points
GET    /api/v1/ldc/pooling-points/{id}      - Get pooling point by ID
GET    /api/v1/ldc/pooling-points/service-area/{id} - Get by service area
PUT    /api/v1/ldc/pooling-points/{id}      - Update pooling point
DELETE /api/v1/ldc/pooling-points/{id}      - Delete pooling point
```

### LDC Storage Types
```
POST   /api/v1/ldc/storage-types              - Create storage type
GET    /api/v1/ldc/storage-types              - Get all storage types
GET    /api/v1/ldc/storage-types/{id}       - Get storage type by ID
PUT    /api/v1/ldc/storage-types/{id}       - Update storage type
DELETE /api/v1/ldc/storage-types/{id}       - Delete storage type
```

### LDC Tariffs
```
POST   /api/v1/ldc/tariffs                    - Create tariff
GET    /api/v1/ldc/tariffs                    - Get all tariffs
GET    /api/v1/ldc/tariffs/{id}             - Get tariff by ID
GET    /api/v1/ldc/tariffs/meter-type/{id}  - Get tariffs by meter type
PUT    /api/v1/ldc/tariffs/{id}             - Update tariff
DELETE /api/v1/ldc/tariffs/{id}             - Delete tariff
```

### Monthly TOL
```
POST   /api/v1/ldc/monthly-tol                - Create Monthly TOL
GET    /api/v1/ldc/monthly-tol                - Get all Monthly TOLs
GET    /api/v1/ldc/monthly-tol/{id}         - Get Monthly TOL by ID
GET    /api/v1/ldc/monthly-tol/meter/{id}   - Get TOLs by meter ID
PUT    /api/v1/ldc/monthly-tol/{id}         - Update Monthly TOL
DELETE /api/v1/ldc/monthly-tol/{id}         - Delete Monthly TOL
```

### Price Products
```
POST   /api/v1/ldc/price-products             - Create price product
GET    /api/v1/ldc/price-products             - Get all price products
GET    /api/v1/ldc/price-products/{id}      - Get price product by ID
GET    /api/v1/ldc/price-products/pooling-point/{id} - Get by pooling point
PUT    /api/v1/ldc/price-products/{id}      - Update price product
DELETE /api/v1/ldc/price-products/{id}      - Delete price product
```

## Installation & Setup

### Prerequisites
- Java JDK 17 or higher
- Maven 3.8.0 or higher
- Git

### Steps to Build and Run

1. **Extract the project**
   ```bash
   unzip snyder-ldc-service.zip
   cd snyder-ldc-service
   ```

2. **Build the project**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```
   
   Or run the JAR directly:
   ```bash
   java -jar target/snyder-ldc-service-1.0.0.jar
   ```

4. **Access the application**
   - REST API: http://localhost:8081/api/v1/
   - H2 Console: http://localhost:8081/h2-console
     - JDBC URL: jdbc:h2:mem:snyder-ldc-db
     - Username: sa
     - Password: (leave blank)

## Configuration

### application.properties
The application uses H2 in-memory database by default. Key configurations:
```properties
spring.datasource.url=jdbc:h2:mem:snyder-ldc-db
spring.jpa.hibernate.ddl-auto=create-drop
server.port=8081
```

### Database Initialization
- Schema is created from `schema.sql`
- Sample data is loaded from `data.sql`
- Both files are automatically executed on application startup

## Exception Handling

The application includes comprehensive exception handling:
- `ResourceNotFoundException` - Resource not found (404)
- `DuplicateResourceException` - Duplicate resource (409)
- `ValidationException` - Validation errors (400)
- `DateValidationException` - Date range errors (400)
- `CapacityException` - Capacity exceeded (400)
- `DatabaseException` - Database operation failures (500)

## Sample API Requests

### Create LDC Charge
```json
POST /api/v1/ldc/charges
Content-Type: application/json

{
  "chargeCode": "CHG005",
  "chargeName": "New Distribution Charge",
  "chargeType": "FIXED",
  "amount": 35.75,
  "effectiveFrom": "2024-03-01T00:00:00",
  "effectiveTo": "2024-12-31T23:59:59",
  "serviceAreaId": 1,
  "status": "ACTIVE"
}
```

### Create Price Product
```json
POST /api/v1/ldc/price-products
Content-Type: application/json

{
  "productCode": "PROD005",
  "productName": "Spot Market Contract",
  "pricingType": "FLOATING",
  "basePrice": 4.10,
  "formulaReference": "Spot Market Index",
  "effectiveFrom": "2024-03-01T00:00:00",
  "poolingPointId": 1,
  "status": "ACTIVE"
}
```

## Logging

Logging is configured in `application.properties`:
```properties
logging.level.com.snyder.ldc=DEBUG
logging.level.org.springframework.web=INFO
```

## Testing

Sample data is pre-loaded in the database for testing. Use the H2 console or REST client to interact with the API.

## Future Enhancements

1. **Authentication & Authorization** - Add Spring Security
2. **API Documentation** - Implement Swagger/OpenAPI
3. **Pagination** - Add pagination to list endpoints
4. **Caching** - Implement Redis caching
5. **Event Publishing** - Add Kafka integration
6. **Advanced Reporting** - Analytics and reporting endpoints
7. **Batch Processing** - Bulk operations support

## Support & Contribution

For issues, questions, or contributions, please refer to the project's documentation or contact the development team.

## License

This project is part of Snyder Natural Gas Operational Excellence Program.
