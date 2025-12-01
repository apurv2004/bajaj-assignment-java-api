# Bajaj Finserv Health Hiring Assignment - Spring Boot Application

## 📋 Project Overview

This is a complete Spring Boot application built for the Bajaj Finserv Health hiring assignment. The application automatically executes a workflow on startup that:

1. **Generates Webhook & Access Token** - Calls the first API to receive a webhook URL and JWT token
2. **Submits SQL Query** - Uses the token to submit your final SQL query to the second API

## 🏗️ Project Structure

```
bajaj-assignment/
├── pom.xml                                          # Maven configuration
├── README.md                                        # This file
└── src/
    └── main/
        ├── java/com/example/bajaj/
        │   ├── BajajAssignmentApplication.java      # Main Spring Boot application
        │   ├── config/
        │   │   └── AppConfig.java                   # RestTemplate bean configuration
        │   ├── dto/
        │   │   ├── WebhookGenerationRequest.java    # Request DTO for API 1
        │   │   ├── WebhookGenerationResponse.java   # Response DTO for API 1
        │   │   └── WebhookTestRequest.java          # Request DTO for API 2
        │   ├── service/
        │   │   └── AssignmentService.java           # Business logic service
        │   └── runner/
        │       └── AssignmentRunner.java            # Startup trigger
        └── resources/
            └── application.properties               # Application configuration
```

## 🛠️ Technologies Used

- **Java 21**
- **Spring Boot 3.2.0**
- **Maven**
- **Lombok** (for reducing boilerplate)
- **Jackson** (for JSON serialization)

## 📝 Prerequisites

- Java 21 or higher
- Maven 3.8.0 or higher
- Internet connectivity (to call the APIs)

## ⚙️ Setup Instructions

### 1. Navigate to Project Directory

```bash
cd /Users/apurvkulkarni/Downloads/Bajaj\ Assignment
```

### 2. Install Dependencies

```bash
mvn clean install
```

### 3. Update Your SQL Query

Before running, you **MUST** update the SQL query in the `AssignmentService` class:

**File:** `src/main/java/com/example/bajaj/service/AssignmentService.java`

**Find this line (line 39):**
```java
private static final String FINAL_SQL_QUERY = "REPLACE THIS WITH YOUR FINAL SQL QUERY";
```

**Replace it with your actual SQL query:**
```java
private static final String FINAL_SQL_QUERY = "SELECT * FROM your_table WHERE condition";
```

## 🚀 Running the Application

### Option 1: Using Maven (Recommended)

```bash
mvn spring-boot:run
```

### Option 2: Using Java Command

First, build the application:
```bash
mvn clean package
```

Then run:
```bash
java -jar target/bajaj-assignment-1.0.0.jar
```

## 📊 Workflow Execution

When you run the application, it will:

1. **Start Spring Boot Application**
   - Logs: `Starting Bajaj Assignment Application...`

2. **Trigger AssignmentRunner on Startup**
   - Logs: `Application started. Initiating assignment workflow...`

3. **Call API 1: Generate Webhook**
   - Endpoint: `https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA`
   - Sends: Name, RegNo, Email
   - Receives: Webhook URL + Access Token
   - Logs: `✓ Webhook generated successfully`

4. **Call API 2: Submit SQL Query**
   - Endpoint: `https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA`
   - Sends: Your SQL query with authorization token
   - Receives: Success/Response from backend
   - Logs: `✓ SQL query submitted successfully`

## 📝 Logging

The application uses SLF4J for logging. Log levels are configured in `application.properties`:

- **Root Level**: `INFO`
- **com.example.bajaj Package**: `DEBUG` (for detailed output)

Example log output:
```
09:15:23.456 [main] INFO  BajajAssignmentApplication - Starting Bajaj Assignment Application...
09:15:24.123 [main] INFO  AssignmentRunner - Application started. Initiating assignment workflow...
09:15:24.234 [main] INFO  AssignmentService - Step 1: Generating webhook and access token...
09:15:25.567 [main] INFO  AssignmentService - ✓ Webhook generated successfully
09:15:25.678 [main] INFO  AssignmentService - Step 2: Submitting SQL query to webhook...
09:15:26.789 [main] INFO  AssignmentService - ✓ SQL query submitted successfully
```

## 🔑 Important Notes

### SQL Query Placeholder
- **Location:** `AssignmentService.java` (line 39)
- **Current Value:** `"REPLACE THIS WITH YOUR FINAL SQL QUERY"`
- **Action Required:** Replace with your actual SQL query after solving the SQL problem

### Access Token Handling
- The token is stored after API 1 call and automatically used for API 2
- No "Bearer " prefix is added (as per requirements - token is used as-is)
- The token is passed in the `Authorization` header

### Error Handling
- Application gracefully handles errors without crashing
- All errors are logged with full stack traces
- Application continues running even if workflow encounters issues
- Check logs for troubleshooting

## 🔍 Debugging

### Enable More Detailed Logging
Edit `src/main/resources/application.properties`:
```properties
logging.level.org.springframework.web=DEBUG
logging.level.org.springframework.http=DEBUG
```

### Check HTTP Requests/Responses
Add this to `application.properties`:
```properties
logging.level.org.springframework.web.client=DEBUG
```

### View Full Request Body
The service logs the request body in DEBUG mode:
```
09:15:24.234 [main] DEBUG AssignmentService - Request body - Name: Apurv Kulkarni, RegNo: 22BCE9252, Email: apurv.22bce9252@vitapstudent.ac.in
```

## ✅ Key Features

✔ **Automatic Startup Execution** - Workflow runs automatically when application starts
✔ **Clean Code Structure** - Organized into config, dto, service, runner packages
✔ **Comprehensive Logging** - Every step is logged for easy debugging
✔ **Error Handling** - Graceful error handling with informative logs
✔ **Configurable** - Easy to modify SQL query and user details
✔ **REST Client Setup** - Proper RestTemplate configuration with timeouts
✔ **DTO Mapping** - Jackson-based JSON serialization/deserialization
✔ **Spring Boot Best Practices** - Follows Spring Boot conventions and best practices

## 📚 File Descriptions

### BajajAssignmentApplication.java
- Main entry point for the Spring Boot application
- Starts the Spring context and triggers the workflow

### AppConfig.java
- Configuration class that provides RestTemplate bean
- Sets connection and read timeouts for HTTP requests

### WebhookGenerationRequest.java
- DTO for the first API request
- Contains: name, regNo, email

### WebhookGenerationResponse.java
- DTO for the first API response
- Contains: webhook, accessToken

### WebhookTestRequest.java
- DTO for the second API request
- Contains: finalQuery (your SQL query)

### AssignmentService.java
- Core service class containing business logic
- **generateWebhookAndToken()** - Calls API 1
- **submitSqlQueryToWebhook()** - Calls API 2
- **executeAssignment()** - Orchestrates the complete workflow
- Stores webhook and token from API 1 response

### AssignmentRunner.java
- Implements ApplicationRunner interface
- Automatically triggered on application startup
- Calls AssignmentService to execute the workflow

### application.properties
- Spring Boot configuration
- Logging configuration
- Server settings

## 🐛 Troubleshooting

### Issue: "REPLACE THIS WITH YOUR FINAL SQL QUERY" is sent to API
**Solution:** Update the `FINAL_SQL_QUERY` constant in `AssignmentService.java` with your actual SQL query

### Issue: Connection timeout errors
**Solution:** Check internet connectivity and verify API endpoints are accessible

### Issue: 401 Unauthorized on API 2
**Solution:** Ensure the access token from API 1 is being passed correctly in the Authorization header

### Issue: Application crashes on startup
**Solution:** Check logs for detailed error messages. The application is designed to handle errors gracefully, so a crash indicates a serious issue. Verify:
- Java version is 21 or higher
- All dependencies installed successfully
- API endpoints are reachable

## 📞 Support

For issues or questions about this assignment, refer to the Bajaj official PDF documentation.

## ✨ Summary

This application provides a production-ready Spring Boot solution for the Bajaj Finserv Health hiring assignment. Simply update your SQL query and run the application using `mvn spring-boot:run`. The entire workflow executes automatically on startup with comprehensive logging for debugging.

---

**Assignment Details:**
- **Name:** Apurv Kulkarni
- **Registration Number:** 22BCE9252
- **Email:** apurv.22bce9252@vitapstudent.ac.in
- **Java Version:** 21
- **Spring Boot Version:** 3.2.0
