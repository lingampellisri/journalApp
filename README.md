# MongoDB Integration with Spring Boot

This guide provides a concise 10-step process to integrate **MongoDB** in a **Spring Boot** application.



## 🚀 Step-by-Step Guide

### ✅ Step 1: Set Up Your Spring Boot Project
Create a new Spring Boot project using [Spring Initializr](https://start.spring.io/) or your preferred IDE.  
Include the following dependencies:
- Spring Web
- Spring Data MongoDB



### ⚙️ Step 2: Define a MongoDB Configuration
Create a configuration class or use `application.properties` to specify:

```properties
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=your_database_name
🧱 Step 3: Define a MongoDB Entity
Create a Java class to represent the MongoDB document:


@Document(collection = "journal_entries")
public class JournalEntry {
    @Id
    private String id;
    private String title;
    private String content;
    private Date date;
    // Getters, Setters, Constructor, toString...
}
--- 
### 💾 Step 4: Create a Repository Interface

public interface JournalRepository extends MongoRepository<JournalEntry, String> {}
🧠 Step 5: Implement Service Layer

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

    // Other CRUD methods...
}
🧪 Step 6: Implement Data Initialization (Optional)

@Component
public class DataSeeder implements CommandLineRunner {
    @Autowired
    private JournalRepository repository;

    @Override
    public void run(String... args) {
        repository.save(new JournalEntry("Welcome", "My first post!", new Date()));
    }
}
🌐 Step 7: Implement Controller for REST API

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
### 🧪 Step 8: Test Endpoints
Use tools like Postman or curl to send HTTP requests to your API:

GET /api/journals

POST /api/journals

▶️ Step 9: Run and Test the Application
Run the Spring Boot application and ensure everything connects correctly.
Check logs, use MongoDB Compass, or query MongoDB to verify.

🛑 Step 10: Handle Exceptions
Use @ControllerAdvice or @ExceptionHandler to manage application errors.
Log meaningful error messages and return appropriate HTTP status codes.
