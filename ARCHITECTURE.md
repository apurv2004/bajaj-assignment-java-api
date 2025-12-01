# 📊 Architecture & Flow Diagrams

## System Architecture

```
┌─────────────────────────────────────────────────────────────────┐
│                    Your Local Machine                           │
├─────────────────────────────────────────────────────────────────┤
│                                                                 │
│  ┌──────────────────────────────────────────────────────────┐  │
│  │         Spring Boot Application                          │  │
│  │  (com.example.bajaj.BajajAssignmentApplication)         │  │
│  └──────────────────────────────────────────────────────────┘  │
│                              ▲                                  │
│                              │ Starts                           │
│                              │                                  │
│  ┌──────────────────────────────────────────────────────────┐  │
│  │         AssignmentRunner (ApplicationRunner)             │  │
│  │  - Triggered automatically on startup                    │  │
│  │  - Calls AssignmentService                              │  │
│  └──────────────────────────────────────────────────────────┘  │
│                              │                                  │
│                              ▼                                  │
│  ┌──────────────────────────────────────────────────────────┐  │
│  │         AssignmentService                                │  │
│  │  - executeAssignment()                                   │  │
│  │  - generateWebhookAndToken()  ────────┐                │  │
│  │  - submitSqlQueryToWebhook()  ────────┤                │  │
│  └──────────────────────────────────────────────────────────┘  │
│                              │                                  │
│                              │                                  │
│  ┌──────────────────────────────────────────────────────────┐  │
│  │         RestTemplate (HTTP Client)                       │  │
│  │  - Configured in AppConfig                              │  │
│  │  - Connect Timeout: 10s                                 │  │
│  │  - Read Timeout: 20s                                    │  │
│  └──────────────────────────────────────────────────────────┘  │
│                              │                                  │
└──────────────────────────────┼──────────────────────────────────┘
                               │
                  ┌────────────┴─────────────┐
                  │                         │
                  ▼                         ▼
         ┌──────────────────┐      ┌──────────────────┐
         │  API 1           │      │  API 2           │
         ├──────────────────┤      ├──────────────────┤
         │ generateWebhook  │      │ testWebhook      │
         │                  │      │                  │
         │ POST Request     │      │ POST Request     │
         │ (User Details)   │      │ (SQL Query)      │
         │                  │      │                  │
         │ Response:        │      │ Response:        │
         │ webhook +        │      │ success          │
         │ accessToken      │      │                  │
         └──────────────────┘      └──────────────────┘
         
         Bajaj API Server (HTTPS)
```

## Request/Response Flow

```
┌─ Your Application ─────────────┐         ┌─ Bajaj API Server ────────────┐
│                                │         │                               │
│ 1. Start Application          │         │                               │
│    ↓                          │         │                               │
│ 2. Spring Boot Starts         │         │                               │
│    ↓                          │         │                               │
│ 3. AssignmentRunner.run()     │         │                               │
│    ↓                          │         │                               │
│ 4. AssignmentService.        │         │                               │
│    executeAssignment()         │         │                               │
│    ↓                          │         │                               │
├─ API 1 CALL ─────────────────┼────────┤                               │
│ generateWebhookAndToken()     │ ──────>│ POST /generateWebhook/JAVA     │
│                               │ Request│                               │
│ POST Request                  │ ──────>│ {                             │
│ {                             │        │   "name": "...",             │
│   "name": "...",             │ ──────>│   "regNo": "22BCE9252",      │
│   "regNo": "22BCE9252",      │        │   "email": "..."             │
│   "email": "..."             │        │ }                             │
│ }                             │        │                               │
│                               │        │ Process & Generate            │
│                               │ <──────│ Webhook & Token              │
│ Response Received             │ <──────│                               │
│ {                             │        │ {                             │
│   "webhook": "https://...",  │ <──────│   "webhook": "https://...",  │
│   "accessToken": "jwt..."    │ <──────│   "accessToken": "jwt..."    │
│ }                             │        │ }                             │
│ ↓                            │        │                               │
│ Extract & Store               │        │                               │
│ webhook + accessToken         │        │                               │
│ ↓                            │        │                               │
├─ API 2 CALL ─────────────────┼────────┤                               │
│ submitSqlQueryToWebhook()     │ ──────>│ POST /testWebhook/JAVA        │
│                               │ Request│                               │
│ Headers:                      │ ──────>│ Headers:                      │
│ - Authorization: <token>      │        │ - Authorization: <token>      │
│ - Content-Type: application/  │ ──────>│ - Content-Type: application/  │
│   json                        │        │   json                        │
│                               │        │                               │
│ POST Request                  │ ──────>│ {                             │
│ {                             │        │   "finalQuery": "SELECT..." │
│   "finalQuery": "SELECT..."  │ ──────>│ }                             │
│ }                             │        │                               │
│                               │        │ Validate Token                │
│                               │        │ Execute Query                 │
│                               │        │ Store Results                 │
│                               │ <──────│ Return Response               │
│ Response Received             │ <──────│                               │
│ {                             │        │ {                             │
│   "status": "success",...    │ <──────│   "status": "success",...    │
│ }                             │        │ }                             │
│ ↓                            │        │                               │
│ Workflow Complete!            │        │                               │
│ ✓ Success                     │        │                               │
└────────────────────────────────┘        └───────────────────────────────┘
```

## Component Interaction Diagram

```
┌─────────────────────────────────────────────────────────────┐
│                   Spring Boot Container                      │
├─────────────────────────────────────────────────────────────┤
│                                                              │
│  ┌─────────────────┐                                        │
│  │ AppConfig       │  Creates & provides                   │
│  │                 │─────────────────────┐                 │
│  │ @Configuration  │                    ▼                 │
│  │ - restTemplate()│              ┌──────────────┐        │
│  └─────────────────┘              │ RestTemplate │        │
│                                    │              │        │
│                                    │ Timeouts:    │        │
│                                    │ Connect: 10s │        │
│                                    │ Read: 20s    │        │
│                                    └──────────────┘        │
│                                          ▲                 │
│                                          │ Used by         │
│  ┌──────────────────────────┐            │                │
│  │ AssignmentService        │            │                │
│  │ @Service                 │────────────┘                │
│  │                          │                             │
│  │ Methods:                 │                             │
│  │ - executeAssignment()    │                             │
│  │ - generateWebhook...()   │ Injected                    │
│  │ - submitSqlQuery...()    │ Dependencies                │
│  │ - getAccessToken()       │                             │
│  │ - getGeneratedWebhook()  │                             │
│  │                          │                             │
│  │ Fields:                  │                             │
│  │ - generatedWebhook       │                             │
│  │ - accessToken            │                             │
│  │ - FINAL_SQL_QUERY        │◄─── UPDATE THIS!            │
│  └──────────────────────────┘                             │
│           ▲                                                │
│           │ Dependency Injection                          │
│           │                                                │
│  ┌──────────────────────────┐                             │
│  │ AssignmentRunner         │                             │
│  │ @Component               │                             │
│  │                          │                             │
│  │ Implements:              │                             │
│  │ ApplicationRunner        │                             │
│  │                          │                             │
│  │ run() method             │                             │
│  │ - Called on startup      │                             │
│  │ - Triggers workflow      │                             │
│  └──────────────────────────┘                             │
│                                                              │
│  ┌─────────────────────────────────────────────────────┐  │
│  │ DTO Classes                                         │  │
│  │ - WebhookGenerationRequest                         │  │
│  │   (name, regNo, email)                             │  │
│  │                                                     │  │
│  │ - WebhookGenerationResponse                        │  │
│  │   (webhook, accessToken)                           │  │
│  │                                                     │  │
│  │ - WebhookTestRequest                              │  │
│  │   (finalQuery)                                      │  │
│  └─────────────────────────────────────────────────────┘  │
│                                                              │
└─────────────────────────────────────────────────────────────┘
```

## Deployment & Execution Flow

```
Start
  │
  ▼
mvn spring-boot:run
  │
  ├─ Maven resolves dependencies
  ├─ Compiles source code
  ├─ Creates Spring context
  │
  ▼
Spring Boot Application Starts
  │
  ├─ Initializes Spring components
  ├─ Scans for @Component, @Service, @Configuration
  │
  ▼
Application Context Ready
  │
  ├─ AssignmentRunner instantiated
  │
  ▼
ApplicationRunner.run() Called
  │
  ├─ Call: assignmentService.executeAssignment()
  │
  ▼
AssignmentService.executeAssignment()
  │
  ├─ Log: "========== Starting Workflow =========="
  │
  ▼
generateWebhookAndToken()
  │
  ├─ Create WebhookGenerationRequest
  ├─ Set HTTP headers (Content-Type: application/json)
  ├─ Call RestTemplate.postForEntity()
  │
  ▼
HTTP POST to API 1
  │
  ├─ Endpoint: .../generateWebhook/JAVA
  ├─ Body: {name, regNo, email}
  │
  ▼
Parse Response
  │
  ├─ Extract: webhook
  ├─ Extract: accessToken
  ├─ Store in instance variables
  │
  ▼
submitSqlQueryToWebhook()
  │
  ├─ Create WebhookTestRequest with SQL query
  ├─ Set HTTP headers:
  │  ├─ Authorization: <accessToken>
  │  └─ Content-Type: application/json
  ├─ Call RestTemplate.postForEntity()
  │
  ▼
HTTP POST to API 2
  │
  ├─ Endpoint: .../testWebhook/JAVA
  ├─ Body: {finalQuery: "SELECT ..."}
  │
  ▼
Parse Response
  │
  ├─ Log success message
  │
  ▼
Workflow Complete
  │
  ├─ Log: "========== Workflow Completed Successfully =========="
  │
  ▼
Application Continues Running
  │
  └─ Ready to be stopped with Ctrl+C
```

## Data Flow Diagram

```
Your Details (Pre-configured)
├─ Name: "Apurv Kulkarni"
├─ RegNo: "22BCE9252"
└─ Email: "apurv.22bce9252@vitapstudent.ac.in"
    │
    ▼
Create WebhookGenerationRequest DTO
    │
    ▼
  [HTTP POST to API 1]
    │
    ▼
Receive WebhookGenerationResponse DTO
├─ webhook: "https://unique-webhook-url"
└─ accessToken: "eyJhbGciOiJIUzI1NiIs..."
    │
    ├─ Store webhook
    │
    └─ Store accessToken
        │
        ▼
Your SQL Query (in code)
├─ FINAL_SQL_QUERY: "SELECT * FROM..."
    │
    ▼
Create WebhookTestRequest DTO
├─ finalQuery: "SELECT * FROM..."
    │
    ▼
Add Authorization Header
├─ Authorization: <accessToken>
    │
    ▼
  [HTTP POST to API 2]
    │
    ▼
Receive Response from API 2
├─ status: "success"
├─ message: "Query processed"
└─ ... other fields
    │
    ▼
✓ Assignment Complete
```

## Package Structure

```
com.example.bajaj
├── BajajAssignmentApplication (Main class)
│   └── @SpringBootApplication
│       └── Starts the application
│
├── config/
│   └── AppConfig
│       └── @Configuration
│           └── @Bean: RestTemplate
│
├── dto/
│   ├── WebhookGenerationRequest
│   │   └── Fields: name, regNo, email
│   ├── WebhookGenerationResponse
│   │   └── Fields: webhook, accessToken
│   └── WebhookTestRequest
│       └── Fields: finalQuery
│
├── service/
│   └── AssignmentService
│       └── @Service
│           ├── Fields: generatedWebhook, accessToken
│           ├── Method: executeAssignment()
│           ├── Method: generateWebhookAndToken()
│           ├── Method: submitSqlQueryToWebhook()
│           ├── Method: getAccessToken()
│           └── Method: getGeneratedWebhook()
│
└── runner/
    └── AssignmentRunner
        └── @Component
            └── Implements ApplicationRunner
                └── Method: run()
```

---

These diagrams illustrate:
1. **System Architecture** - Overall structure
2. **Request/Response Flow** - How data flows between components
3. **Component Interaction** - How Spring components interact
4. **Deployment & Execution** - Step-by-step execution flow
5. **Data Flow** - How data transforms through the system
6. **Package Structure** - Code organization

For more details, refer to README.md or QUICK_START.md.
