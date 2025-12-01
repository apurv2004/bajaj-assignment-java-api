# ⚡ NEXT STEPS - Action Plan

## 🎯 Your Immediate Tasks

### Task 1: Get Your SQL Query ✏️

1. Go to the Google Drive link provided in your assignment
2. Find the SQL question corresponding to your regNo: **22BCE9252**
3. Solve the SQL problem
4. Copy your final SQL query

**Example SQL Queries (for reference):**
```sql
SELECT COUNT(*) FROM employees WHERE department = 'IT';
SELECT * FROM products WHERE price > 1000 ORDER BY price DESC;
SELECT name, salary FROM employees WHERE salary > (SELECT AVG(salary) FROM employees);
```

### Task 2: Update Your SQL Query in the Code 🔄

**File Path:** 
```
src/main/java/com/example/bajaj/service/AssignmentService.java
```

**Line to Change:** Line 39

**Current Code:**
```java
private static final String FINAL_SQL_QUERY = "REPLACE THIS WITH YOUR FINAL SQL QUERY";
```

**What to Do:**
Replace the text above with your SQL query. For example:

```java
// Example 1: Simple query
private static final String FINAL_SQL_QUERY = "SELECT * FROM employees WHERE age > 25";

// Example 2: Complex query
private static final String FINAL_SQL_QUERY = "SELECT department, COUNT(*) as count FROM employees GROUP BY department";

// Example 3: Join query
private static final String FINAL_SQL_QUERY = "SELECT e.name, d.dept_name FROM employees e JOIN departments d ON e.dept_id = d.id";
```

### Task 3: Run the Application 🚀

Open terminal and navigate to your project:

```bash
cd /Users/apurvkulkarni/Downloads/Bajaj\ Assignment
```

Run the application:

```bash
mvn spring-boot:run
```

### Task 4: Check the Results ✅

Look for these success messages in the output:

```
✓ Webhook generated successfully
✓ SQL query submitted successfully
```

If you see both messages, **your assignment is complete!** 🎉

## 🔍 Step-by-Step Execution

When you run `mvn spring-boot:run`, here's what happens:

```
┌─ Application Starts ─────────────────────────┐
│ • Spring Boot initializes                    │
│ • AssignmentRunner is triggered              │
└──────────────────────────────────────────────┘
                    ↓
┌─ Step 1: Generate Webhook ──────────────────┐
│ • Sends your details to API 1                │
│ • Receives: webhook + accessToken            │
│ • Logs: "✓ Webhook generated successfully"   │
└──────────────────────────────────────────────┘
                    ↓
┌─ Step 2: Submit SQL Query ──────────────────┐
│ • Sends your SQL query to API 2              │
│ • Uses access token from step 1              │
│ • Logs: "✓ SQL query submitted successfully" │
└──────────────────────────────────────────────┘
                    ↓
┌─ Complete! ──────────────────────────────────┐
│ Check Bajaj's website for results            │
└──────────────────────────────────────────────┘
```

## 📋 Checklist Before Running

- [ ] Java 21 installed
  ```bash
  java -version
  ```
  Should show: `openjdk version "21.x.x"` or similar

- [ ] Maven installed
  ```bash
  mvn -version
  ```
  Should show Maven version info

- [ ] SQL query obtained from Google Drive
  - You have the link in your assignment
  - You've solved the SQL problem

- [ ] SQL query updated in AssignmentService.java
  - Edited the FINAL_SQL_QUERY constant
  - Your SQL query is there (not the placeholder text)

- [ ] Internet connection available
  - Can access the API endpoints
  - No firewall blocking HTTPS

## ⚠️ Common Mistakes to Avoid

### ❌ Mistake 1: Not Updating the SQL Query
```java
// DON'T do this:
private static final String FINAL_SQL_QUERY = "REPLACE THIS WITH YOUR FINAL SQL QUERY";
```
**Result:** Application will send the placeholder text to API, which will fail.

### ❌ Mistake 2: Wrong SQL Query Syntax
```sql
-- DON'T do this:
SELCT * FROM employees;  -- typo: SELCT instead of SELECT
```
**Result:** Database error, backend will reject it.

### ❌ Mistake 3: Not Using Maven
```bash
# DON'T do this:
javac src/main/java/com/example/bajaj/*.java
java com.example.bajaj.BajajAssignmentApplication
```
**Result:** Dependencies won't be on classpath, will crash.

### ❌ Mistake 4: Using Java 17 or Lower
```bash
# DON'T do this:
java -version  # Shows Java 17

# DO this:
# Install Java 21 first
```
**Result:** Spring Boot 3.x requires Java 17+, but we built for Java 21.

## ✅ Common Success Indicators

When everything works, you'll see:

```
09:30:45.123 [main] INFO  BajajAssignmentApplication - Starting Bajaj Assignment Application...
09:30:46.456 [main] INFO  AssignmentRunner - Application started. Initiating assignment workflow...
09:30:46.567 [main] INFO  AssignmentService - ========== Starting Bajaj Assignment Workflow ==========
09:30:46.678 [main] INFO  AssignmentService - Step 1: Generating webhook and access token...
09:30:47.789 [main] INFO  AssignmentService - Sending request to: https://bfhldevapigw.healthrx.co.in/...
09:30:48.890 [main] INFO  AssignmentService - ✓ Webhook generated successfully
09:30:48.901 [main] INFO  AssignmentService - Webhook: https://...unique-webhook...
09:30:49.012 [main] INFO  AssignmentService - Step 2: Submitting SQL query to webhook...
09:30:49.123 [main] INFO  AssignmentService - Sending request to: https://bfhldevapigw.healthrx.co.in/...
09:30:50.234 [main] INFO  AssignmentService - ✓ SQL query submitted successfully
09:30:50.345 [main] INFO  AssignmentService - Response: {"status":"success",...}
09:30:50.456 [main] INFO  AssignmentService - ========== Assignment Workflow Completed Successfully ==========
```

Look for the ✓ checkmarks!

## 🆘 Troubleshooting

### Issue 1: Maven not found
```
zsh: command not found: mvn
```
**Solution:**
```bash
brew install maven
```

### Issue 2: Java 21 not found
```
Unsupported major.minor version 65.0
```
**Solution:**
```bash
brew install java21
```

### Issue 3: Network error
```
java.net.ConnectException: Connection refused
```
**Solution:**
- Check internet connection
- Verify firewall isn't blocking API
- Wait a moment and try again

### Issue 4: 401 Unauthorized
```
HTTP 401: Unauthorized
```
**Solution:**
- Check API 1 succeeded (see logs)
- Access token might not be transferred correctly
- Run again and check logs

### Issue 5: SQL Syntax Error
```
SQL error: Unexpected token
```
**Solution:**
- Verify your SQL syntax
- Check quotes are balanced
- Test SQL on the provided platform

## 📞 If Something Goes Wrong

1. **Check the logs** - Look for error messages
2. **Verify SQL query** - Make sure it's valid SQL
3. **Check internet** - Make sure APIs are reachable
4. **Check Java/Maven** - Verify versions installed
5. **Try again** - Sometimes temporary network issues

## 🎓 Learning Resources (Optional)

If you want to understand the code better:

- **Spring Boot Basics:** https://spring.io/projects/spring-boot
- **REST Clients:** https://spring.io/guides/gs/consuming-rest/
- **Spring IoC Container:** https://spring.io/understanding/IoC
- **Logging in Spring:** https://spring.io/guides/gs/logging-logback/

## 📊 What Happens After You Submit

1. Backend validates your SQL query
2. Executes your query on their database
3. Stores your results
4. You'll receive feedback on the Bajaj website/email

## 🎉 Final Checklist

- [ ] SQL query obtained
- [ ] SQL query updated in code
- [ ] Java 21 verified
- [ ] Maven verified
- [ ] Ran `mvn spring-boot:run`
- [ ] Saw ✓ checkmarks in logs
- [ ] Both API calls succeeded

## 🚀 You're All Set!

Your Spring Boot application is complete and ready to use. Here's your quick command:

```bash
cd /Users/apurvkulkarni/Downloads/Bajaj\ Assignment && mvn spring-boot:run
```

**The rest is automatic!** The app handles everything:
- ✅ Calling API 1
- ✅ Getting the token
- ✅ Submitting your SQL query
- ✅ Logging all results

---

## 📝 Quick Reference Card

**Your Details (Pre-configured):**
- Name: Apurv Kulkarni
- RegNo: 22BCE9252
- Email: apurv.22bce9252@vitapstudent.ac.in

**What You Need to Do:**
1. Get your SQL query from Google Drive
2. Update `FINAL_SQL_QUERY` in `AssignmentService.java`
3. Run `mvn spring-boot:run`
4. Check logs for ✓ marks

**Success Indicators:**
- ✓ Webhook generated successfully
- ✓ SQL query submitted successfully

---

**Good luck! You've got this! 💪**

For detailed help, check:
- `README.md` - Full documentation
- `QUICK_START.md` - Quick reference
- `COMPLETION_CHECKLIST.md` - Verification
