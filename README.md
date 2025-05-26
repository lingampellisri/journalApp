Here's a clearly **formatted** version of your guide to **integrating MongoDB with Spring Boot**, step-by-step, including proper code formatting and layout for readability:

---

# 🗃️ MongoDB Integration with Spring Boot

This guide walks you through **10 clear steps** to integrate MongoDB in a Spring Boot application.

---

## ✅ Step 1: Set Up Your Spring Boot Project

Use [Spring Initializr](https://start.spring.io/) or your IDE.
**Include dependencies**:

* `Spring Web`
* `Spring Data MongoDB`

<details>
<summary>Gradle Example</summary>

```groovy
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-data-mongodb'
    implementation 'org.springframework.boot:spring-boot-starter-web'
}
```

</details>

---

## ⚙️ Step 2: Define MongoDB Configuration

Use `application.properties` or `application.yml`:

```properties
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=journal_db
```

---

## 🧱 Step 3: Define a MongoDB Entity

Create a model class and annotate with `@Document`:

```java
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "journal_entries")
public class JournalEntry {

    @Id
    private String id;
    private String title;
    private String content;
    private Date date;

    // Constructors
    public JournalEntry() {}

    public JournalEntry(String title, String content, Date date) {
        this.title = title;
        this.content = content;
        this.date = date;
    }

    // Getters and Setters
    // toString() method
}
```

---

## 💾 Step 4: Create Repository Interface

```java
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalRepository extends MongoRepository<JournalEntry, String> {}
```

---

## 🧠 Step 5: Implement Service Layer

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JournalService {

    @Autowired
    private JournalRepository repository;

    public List<JournalEntry> getAllEntries() {
        return repository.findAll();
    }

    public JournalEntry addEntry(JournalEntry entry) {
        return repository.save(entry);
    }

    // More CRUD operations if needed
}
```

---

## 🧪 Step 6: Data Initialization (Optional)

```java
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private JournalRepository repository;

    @Override
    public void run(String... args) throws Exception {
        repository.save(new JournalEntry("Welcome", "My first post!", new Date()));
    }
}
```

---

## 🌐 Step 7: Create REST Controller

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/journals")
public class JournalController {

    @Autowired
    private JournalService service;

    @GetMapping
    public List<JournalEntry> getAll() {
        return service.getAllEntries();
    }

    @PostMapping
    public JournalEntry create(@RequestBody JournalEntry entry) {
        return service.addEntry(entry);
    }
}
```

---

## 🔬 Step 8: Test API Endpoints

Use [Postman](https://www.postman.com/) or `curl` to test:

* **GET** `/api/journals` → Fetch all entries
* **POST** `/api/journals` → Add a new journal entry

Example `POST` JSON:

```json
{
  "title": "My Journey Begins",
  "content": "This is my first journal entry!",
  "date": "2025-05-26T10:00:00.000Z"
}
```

---

## ▶️ Step 9: Run & Verify

Run your Spring Boot application:

```bash
./gradlew bootRun
```

Check logs or use **MongoDB Compass** to inspect inserted documents.

---

## 🛑 Step 10: Handle Errors Gracefully

Example of global exception handler:

```java
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        return ResponseEntity.status(500).body("Error: " + ex.getMessage());
    }
}
```

---

Made By Lingampelli Srinivas🛰️✨
