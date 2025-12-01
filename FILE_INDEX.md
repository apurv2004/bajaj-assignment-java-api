# 📑 Complete File Index & Guide

## 📂 Project Root Files

### Configuration Files
| File | Purpose | Key Info |
|------|---------|----------|
| `pom.xml` | Maven configuration | Spring Boot 3.2.0, Java 21, all dependencies |
| `.gitignore` | Git ignore patterns | Excludes target/, IDE files, OS files |
| `application.properties` | Spring Boot config | Logging, server port, timeouts |

### Documentation Files
| File | Purpose | Read If... |
|------|---------|-----------|
| `README.md` | 📖 Full documentation | You want comprehensive info |
| `QUICK_START.md` | ⚡ Quick reference | You want to get running fast |
| `NEXT_STEPS.md` | 🎯 Action plan | You're ready to execute |
| `COMPLETION_CHECKLIST.md` | ✅ Verification | You want to verify everything |
| `IMPLEMENTATION_SUMMARY.md` | 📋 Summary | You want a quick overview |
| `ARCHITECTURE.md` | 🏗️ Architecture diagrams | You want to understand the design |
| `FILE_INDEX.md` | 📑 This file | You're looking at it! |

## 📂 Java Source Files Location

All Java files are in: `src/main/java/com/example/bajaj/`

### Main Application Class
```
src/main/java/com/example/bajaj/
└── BajajAssignmentApplication.java
    • Entry point for Spring Boot
    • Contains main() method
    • @SpringBootApplication annotation
    • Starts the entire application
```

### Configuration Package
```
src/main/java/com/example/bajaj/config/
└── AppConfig.java
    • @Configuration class
    • Provides RestTemplate bean
    • Configure HTTP timeouts (10s connect, 20s read)
    • Enables proper REST client setup
```

### DTO Package (Data Transfer Objects)
```
src/main/java/com/example/bajaj/dto/

├── WebhookGenerationRequest.java
│   • DTO for API 1 request
│   • Fields: name, regNo, email
│   • Used to send user details
│
├── WebhookGenerationResponse.java
│   • DTO for API 1 response
│   • Fields: webhook, accessToken
│   • Receives webhook URL + JWT token
│   • @JsonProperty annotations for JSON mapping
│
└── WebhookTestRequest.java
    • DTO for API 2 request
    • Fields: finalQuery
    • Used to submit SQL query
```

### Service Package
```
src/main/java/com/example/bajaj/service/
└── AssignmentService.java
    • @Service annotation - Business logic layer
    • Main Methods:
    │   • executeAssignment() - Orchestrates entire workflow
    │   • generateWebhookAndToken() - Calls API 1
    │   • submitSqlQueryToWebhook() - Calls API 2
    │   • getAccessToken() - Returns stored token
    │   • getGeneratedWebhook() - Returns stored webhook
    •
    • Key Constants:
    │   • WEBHOOK_GENERATION_URL - API 1 endpoint
    │   • WEBHOOK_TEST_URL - API 2 endpoint
    │   • USER_NAME, REG_NO, USER_EMAIL - Your details
    │   • FINAL_SQL_QUERY - ⚠️ YOU MUST UPDATE THIS
    •
    • Fields:
        • generatedWebhook - Stored webhook URL
        • accessToken - Stored JWT token
```

### Runner Package
```
src/main/java/com/example/bajaj/runner/
└── AssignmentRunner.java
    • @Component annotation
    • Implements ApplicationRunner
    • run() method called automatically on startup
    • Triggers AssignmentService.executeAssignment()
    • Gracefully handles errors
```

## 📄 Resources

```
src/main/resources/
└── application.properties
    • spring.application.name=bajaj-assignment
    • server.port=8080
    • logging.level.com.example.bajaj=DEBUG
    • Jackson & Connection configurations
```

## 🗺️ Quick Navigation

### I Want To...

**...understand the project**
→ Read: `README.md` (15 min read)

**...get it running quickly**
→ Read: `QUICK_START.md` (5 min read)

**...know what to do next**
→ Read: `NEXT_STEPS.md` (10 min read)

**...verify everything is correct**
→ Read: `COMPLETION_CHECKLIST.md` (5 min verify)

**...understand the architecture**
→ Read: `ARCHITECTURE.md` (10 min read)

**...see what was built**
→ Read: `IMPLEMENTATION_SUMMARY.md` (5 min read)

**...update my SQL query**
→ Edit: `src/main/java/com/example/bajaj/service/AssignmentService.java` (Line 39)

**...run the application**
→ Execute: `mvn spring-boot:run`

**...change logging level**
→ Edit: `src/main/resources/application.properties` (Line 8)

**...change server port**
→ Edit: `src/main/resources/application.properties` (Line 5)

## 🔍 File Details

### AssignmentService.java - MOST IMPORTANT FILE
```
WHAT TO CHANGE:
Line 39: FINAL_SQL_QUERY = "YOUR SQL QUERY HERE"

Constants to know:
- WEBHOOK_GENERATION_URL (Line 42)
- WEBHOOK_TEST_URL (Line 45)
- USER_NAME, REG_NO, USER_EMAIL (Lines 35-37)

Methods you should understand:
- executeAssignment() (Line 52) - Main entry
- generateWebhookAndToken() (Line 68) - API 1 call
- submitSqlQueryToWebhook() (Line 119) - API 2 call
```

### BajajAssignmentApplication.java
```
ENTRY POINT
- main() method starts the application
- Spring Boot auto-configuration happens
- AssignmentRunner triggers on startup
```

### application.properties
```
PORT: 8080
LOGGING LEVEL: DEBUG (for com.example.bajaj)
TIMEOUTS: 10s connect + 20s read
```

## 📊 Dependency Map

```
BajajAssignmentApplication
    │
    └─── Spring Boot Container
            │
            ├─── AppConfig
            │       └─── RestTemplate Bean
            │
            ├─── AssignmentRunner
            │       │
            │       └─── AssignmentService
            │               │
            │               ├─── RestTemplate (from AppConfig)
            │               ├─── WebhookGenerationRequest DTO
            │               ├─── WebhookGenerationResponse DTO
            │               └─── WebhookTestRequest DTO
            │
            └─── application.properties
                    └─── Logging Configuration
```

## 🎯 Key Files to Modify

| File | Line | Change | Reason |
|------|------|--------|--------|
| AssignmentService.java | 39 | Update FINAL_SQL_QUERY | ✅ CRITICAL - Your SQL query |
| application.properties | 5 | Change server.port | 🔧 Optional - Different port |
| application.properties | 8 | Change logging.level | 🔧 Optional - More/less logs |

## 🚀 Execution Sequence

```
1. START: mvn spring-boot:run
   ├─ Compile code
   ├─ Download dependencies
   └─ Start Spring Boot
   
2. Spring Boot Creates Context
   ├─ Load configuration
   ├─ Create beans
   └─ Instantiate components
   
3. Application Started
   ├─ Scan for @Component
   ├─ Find AssignmentRunner
   └─ Call run() method
   
4. AssignmentRunner.run()
   ├─ Get AssignmentService (injected)
   └─ Call executeAssignment()
   
5. AssignmentService.executeAssignment()
   ├─ Call generateWebhookAndToken()
   │   └─ HTTP POST to API 1
   │       ├─ Send: name, regNo, email
   │       └─ Receive: webhook, accessToken
   │
   └─ Call submitSqlQueryToWebhook()
       └─ HTTP POST to API 2
           ├─ Send: SQL query
           └─ Receive: success response
           
6. Application Continues
   └─ Ready for Ctrl+C to stop
```

## 📚 Reading Order (Recommended)

1. **START HERE:** `QUICK_START.md` (5 min)
2. **THEN:** `NEXT_STEPS.md` (10 min)
3. **FOR DETAILS:** `README.md` (15 min)
4. **TO VERIFY:** `COMPLETION_CHECKLIST.md` (5 min)
5. **FOR ARCHITECTURE:** `ARCHITECTURE.md` (10 min)
6. **FOR SUMMARY:** `IMPLEMENTATION_SUMMARY.md` (5 min)

## ✅ Pre-Execution Checklist

Before running `mvn spring-boot:run`:

- [ ] Java 21 installed: `java -version`
- [ ] Maven installed: `mvn -version`
- [ ] SQL query obtained from Google Drive
- [ ] SQL query updated in AssignmentService.java (Line 39)
- [ ] Internet connection available
- [ ] No firewall blocking HTTPS connections

## 🎯 Success Indicators

When you run the app, look for these in logs:

```
✓ Webhook generated successfully
✓ SQL query submitted successfully
```

If you see both, your assignment is complete! ✅

## 🐛 Troubleshooting Quick Links

| Problem | File to Check |
|---------|---------------|
| "REPLACE THIS WITH..." sent to API | AssignmentService.java Line 39 |
| Connection timeout | Check internet + application.properties |
| 401 Unauthorized | Check logs in AssignmentService.java |
| Port 8080 already in use | Change in application.properties Line 5 |
| Maven/Java not found | Check installation on system |

## 📦 Total Files Created

### Java Source Files (8)
1. BajajAssignmentApplication.java
2. AppConfig.java
3. WebhookGenerationRequest.java
4. WebhookGenerationResponse.java
5. WebhookTestRequest.java
6. AssignmentService.java
7. AssignmentRunner.java
8. (application.properties - not Java but source)

### Documentation Files (7)
1. README.md
2. QUICK_START.md
3. NEXT_STEPS.md
4. COMPLETION_CHECKLIST.md
5. IMPLEMENTATION_SUMMARY.md
6. ARCHITECTURE.md
7. FILE_INDEX.md (this file)

### Configuration Files (2)
1. pom.xml
2. .gitignore

**TOTAL: 17 files**

## 🎓 Understanding the Code

### Key Concepts

1. **@SpringBootApplication** - Marks main class, enables auto-configuration
2. **@Configuration** - Marks configuration class, defines beans
3. **@Service** - Marks business logic layer
4. **@Component** - Generic component annotation
5. **ApplicationRunner** - Interface for startup tasks
6. **RestTemplate** - HTTP client for API calls
7. **DTO** - Data Transfer Object (request/response mapping)
8. **@Autowired / Constructor Injection** - Dependency injection
9. **Logging** - SLF4J for application logging

### Design Patterns Used

1. **Singleton Pattern** - Spring beans are singletons
2. **Dependency Injection** - Constructor injection
3. **DTO Pattern** - Request/response mapping
4. **Builder Pattern** - RestTemplateBuilder
5. **Callback Pattern** - ApplicationRunner.run()

## 🔗 External Resources

- **Spring Boot Documentation:** https://spring.io/projects/spring-boot
- **REST Template Guide:** https://spring.io/guides/gs/consuming-rest/
- **Spring Boot Logging:** https://spring.io/blog/2015/12/10/logging-configuration-in-spring-boot
- **Maven Documentation:** https://maven.apache.org/

## 📞 Support Resources

| Need | Resource |
|------|----------|
| How to run? | QUICK_START.md |
| What to do next? | NEXT_STEPS.md |
| Detailed explanation? | README.md |
| Architecture details? | ARCHITECTURE.md |
| Troubleshooting? | README.md (Troubleshooting section) |

---

## 🎉 You Have Everything!

✅ Complete Spring Boot application
✅ All source code
✅ All configuration
✅ Comprehensive documentation
✅ Quick start guide
✅ Architecture diagrams
✅ Troubleshooting guide

**Ready to run:** `mvn spring-boot:run`

**Don't forget:** Update your SQL query in AssignmentService.java Line 39! 🚀
