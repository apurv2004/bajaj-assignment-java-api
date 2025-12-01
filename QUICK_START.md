# Quick Start Guide - Bajaj Assignment Application

## ⚡ 5-Minute Setup

### Step 1: Update Your SQL Query (CRITICAL ❗)

Open: `src/main/java/com/example/bajaj/service/AssignmentService.java`

Find line 39:
```java
private static final String FINAL_SQL_QUERY = "REPLACE THIS WITH YOUR FINAL SQL QUERY";
```

Replace with your actual SQL query:
```java
private static final String FINAL_SQL_QUERY = "SELECT COUNT(*) FROM employees WHERE department = 'IT'";
```

### Step 2: Run the Application

From the project root directory:

```bash
mvn spring-boot:run
```

### Step 3: Check the Logs

Look for these success messages:

```
✓ Webhook generated successfully
✓ SQL query submitted successfully
```

## 📍 Key Configuration Points

| Item | Location | Details |
|------|----------|---------|
| SQL Query | `AssignmentService.java:39` | **MUST be updated before running** |
| User Info | `AssignmentService.java:35-37` | Pre-configured (name, regNo, email) |
| API 1 URL | `AssignmentService.java:42` | Webhook generation endpoint |
| API 2 URL | `AssignmentService.java:45` | Query submission endpoint |
| Logging Level | `application.properties:8` | Change from INFO to DEBUG if needed |

## 🔧 Common Modifications

### Add More Logging
Edit `application.properties`:
```properties
logging.level.com.example.bajaj=DEBUG
```

### Change Server Port
Edit `application.properties`:
```properties
server.port=9090
```

### Increase Request Timeout
Edit `AppConfig.java`:
```java
.setConnectTimeout(Duration.ofSeconds(30))
.setReadTimeout(Duration.ofSeconds(60))
```

## 📊 Project Files Created

```
✅ pom.xml                                      (Maven configuration)
✅ src/main/java/com/example/bajaj/
   ✅ BajajAssignmentApplication.java          (Main class)
   ✅ config/AppConfig.java                    (REST configuration)
   ✅ dto/WebhookGenerationRequest.java        (API 1 request)
   ✅ dto/WebhookGenerationResponse.java       (API 1 response)
   ✅ dto/WebhookTestRequest.java              (API 2 request)
   ✅ service/AssignmentService.java           (Business logic)
   ✅ runner/AssignmentRunner.java             (Startup trigger)
✅ src/main/resources/application.properties    (Configuration)
✅ README.md                                    (Full documentation)
✅ QUICK_START.md                              (This file)
✅ .gitignore                                   (Git configuration)
```

## ✅ Verification Checklist

Before running:
- [ ] Java 21 installed (`java -version`)
- [ ] Maven installed (`mvn -version`)
- [ ] SQL query updated in `AssignmentService.java`
- [ ] Internet connection available
- [ ] No firewall blocking API URLs

After running:
- [ ] See "Webhook generated successfully" in logs
- [ ] See "SQL query submitted successfully" in logs
- [ ] No errors or exceptions in logs

## 🎯 What Happens When You Run It

```
1. Spring Boot starts
   ↓
2. AssignmentRunner triggers on startup
   ↓
3. AssignmentService.executeAssignment() called
   ↓
4. API 1: POST to generate webhook
   ✓ Receive webhook + access token
   ↓
5. API 2: POST to submit SQL query
   ✓ Receive success response
   ↓
6. Application continues running (can be stopped with Ctrl+C)
```

## 🚫 Important: Do NOT Change

- ❌ User details (already configured correctly)
- ❌ API endpoints (already correct)
- ❌ Package structure
- ❌ HTTP method (POST for both APIs)
- ❌ Authorization header format (no "Bearer " prefix)

## ✅ You MUST Change

- ✅ `FINAL_SQL_QUERY` - Replace with your actual SQL query

## 📱 Logs Explained

| Log Message | Meaning |
|-------------|---------|
| `Starting Bajaj Assignment Application...` | Application starting |
| `Application started. Initiating workflow...` | Runner triggered |
| `Step 1: Generating webhook...` | Calling API 1 |
| `✓ Webhook generated successfully` | API 1 succeeded |
| `Step 2: Submitting SQL query...` | Calling API 2 |
| `✓ SQL query submitted successfully` | API 2 succeeded |
| `Error during assignment execution:` | Something went wrong (check error details) |

## 🐛 Quick Troubleshooting

**Q: App says "REPLACE THIS WITH YOUR FINAL SQL QUERY"**
A: Update the SQL query in `AssignmentService.java` line 39

**Q: Connection refused error**
A: Check internet connection and verify APIs are accessible

**Q: 401 Unauthorized on second API**
A: Access token wasn't properly retrieved from first API. Check logs.

**Q: "Cannot find Maven home"**
A: Ensure Maven is installed: `brew install maven` (on macOS)

**Q: Java version error**
A: Install Java 21: `java -version` should show 21.x.x

## 📞 Need Help?

1. Check the full `README.md` for detailed documentation
2. Review the logs for error messages
3. Verify your SQL query syntax
4. Ensure all prerequisites are installed
5. Check internet connection to APIs

---

**Quick Command Reference:**

```bash
# Navigate to project
cd /Users/apurvkulkarni/Downloads/Bajaj\ Assignment

# Run application
mvn spring-boot:run

# Build only
mvn clean package

# Run built JAR
java -jar target/bajaj-assignment-1.0.0.jar

# View logs with filtering
mvn spring-boot:run | grep -E "(Starting|✓|Error|Step)"
```

That's it! 🎉 You're ready to go!
