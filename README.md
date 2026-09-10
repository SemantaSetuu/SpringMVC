# SpringMVC
Learning basic Spring + MVC
# SpringMVC
Learning basic Spring + MVC 

# Spring Core Revision Notes - Day 1

## Goal

Understand the foundations of Spring Framework before moving into Spring MVC and Spring Boot.

Topics covered:

- Spring Bean
- IoC (Inversion of Control)
- Dependency
- Constructor Injection
- @Component
- @Service
- @Bean
- @Configuration
- ApplicationContext
- getBean()
- Spring Core vs Spring Boot

---

# Q1. What is a Spring Bean?

### Answer

A Spring Bean is a Java object that is created, managed, and controlled by the Spring Container.

Example:

```java
@Component
public class Engine {
}
```

Spring creates and manages the `Engine` object.

---

# Q2. Who creates the object when a class is annotated with @Component?

### Answer

Spring Container creates and manages the object.

Example:

```java
@Component
public class Engine {
}
```

Spring automatically creates:

```java
Engine engine = new Engine();
```

behind the scenes.

---

# Q3. What is IoC (Inversion of Control)?

### Answer

IoC is a design principle where the responsibility of creating and managing objects is transferred from our code to the Spring Container.

Without Spring:

```java
Engine engine = new Engine();
```

With Spring:

```java
@Component
public class Engine {
}
```

Spring creates the object automatically.

---

# Q4. What is the dependency here?

```java
public class Car {

    private final Engine engine;

    public Car(Engine engine){
        this.engine = engine;
    }
}
```

### Answer

```text
Engine is the dependency.
Car depends on Engine.
```

---

# Q5. Why is Constructor Injection preferred?

### Answer

Benefits:

- Cleaner code
- Easier testing
- Dependency cannot be null
- Supports final variables
- Better maintainability

Example:

```java
public Car(Engine engine){
    this.engine = engine;
}
```

---

# Q6. What is @Configuration?

### Answer

`@Configuration` marks a class as a Spring Configuration Class.

Think of it as:

```text
Instruction Manual for Spring
```

Example:

```java
@Configuration
public class AppConfig {
}
```

---

# Q7. What is @Bean?

### Answer

`@Bean` tells Spring how to create a Bean.

Example:

```java
@Bean
public Engine engine() {
    return new Engine();
}
```

Spring executes the method and stores the returned object as a Bean.

---

# Q8. What does this line do?

```java
ApplicationContext context =
    new AnnotationConfigApplicationContext(AppConfig.class);
```

### Answer

This line:

- Starts Spring Container
- Loads AppConfig
- Reads all @Bean methods
- Creates and manages Beans

Think:

```text
ApplicationContext = Spring Container
```

---

# Q9. What does this line do?

```java
Car car = context.getBean(Car.class);
```

### Answer

This does NOT create a new object.

It retrieves the Car Bean that Spring has already created and stored.

```text
getBean() = Retrieve Existing Bean
```

---

# Q10. Difference between @Component and @Service

### Answer

Both create Spring-managed Beans.

Difference:

```java
@Component
```

→ Generic Bean

```java
@Service
```

→ Business Logic Bean

Example:

```java
@Service
public class PaymentService {
}
```

This tells another developer:

```text
This class contains business logic.
```

---

# Q11. What do these annotations do?

```java
@Component
@Service
@Repository
@Controller
```

### Answer

All of them create Spring-managed Beans.

The annotations mainly indicate the role of the class.

| Annotation | Purpose |
|------------|----------|
| @Component | Generic Bean |
| @Service | Business Logic Layer |
| @Repository | Data Access Layer |
| @Controller | Request Handling Layer |

---

# Q12. Why don't we need @Configuration, @Bean, and ApplicationContext everywhere in Spring Boot?

### Answer

Because Spring Boot provides:

```java
@SpringBootApplication
```

which internally includes:

```java
@Configuration
@ComponentScan
@EnableAutoConfiguration
```

Spring Boot automatically:

- Creates ApplicationContext
- Performs Component Scanning
- Creates Beans
- Injects Dependencies
- Manages Bean Lifecycle

This removes most manual configuration.

---

# Spring Bean Creation Flow

## Without Spring

```java
Engine engine = new Engine();
Car car = new Car(engine);
```

You create and manage everything.

---

## Spring Core (Manual)

```java
@Configuration
public class AppConfig {

    @Bean
    public Engine engine(){
        return new Engine();
    }

    @Bean
    public Car car(){
        return new Car(engine());
    }
}
```

Spring creates and manages everything.

---

## Spring Boot

```java
@Component
public class Engine {
}
```

```java
@Component
public class Car {

    private final Engine engine;

    public Car(Engine engine){
        this.engine = engine;
    }
}
```

```java
@SpringBootApplication
public class DemoApplication {
}
```

Spring Boot handles the configuration automatically.

---

# Mental Model

```text
Spring Container
       │
       ▼
Creates Beans
       │
       ▼
Injects Dependencies
       │
       ▼
Manages Lifecycle
```

---

# Day 1 Completion Status

✅ Spring Bean

✅ IoC

✅ Dependency

✅ Constructor Injection

✅ @Component

✅ @Service

✅ @Bean

✅ @Configuration

✅ ApplicationContext

✅ getBean()

✅ Spring Core vs Spring Boot

✅ Bean Creation Flow

---

# Next Topics (Day 2)

- Interface
- Loose Coupling
- @Qualifier
- @Primary
- Multiple Bean Implementations
- Real-world Dependency Injection

