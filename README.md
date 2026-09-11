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

---

# Day 2 - Interface, Loose Coupling, Dependency Injection & @Qualifier

## Q13. What is Tight Coupling?

### Answer

Tight Coupling occurs when one class directly depends on a concrete implementation.

Example:

```java
public class OrderService {

    private CreditService creditService =
            new CreditService();
}
```

Problem:

If tomorrow we replace:

```java
CreditService
```

with:

```java
UpiService
```

we must modify the `OrderService` class.

This makes the code harder to maintain and extend.

---

# Q14. What is Loose Coupling?

### Answer

Loose Coupling means a class depends on an abstraction (Interface) instead of a concrete implementation.

Example:

```java
public class OrderService {

    private PaymentService paymentService;

    public OrderService(PaymentService paymentService){
        this.paymentService = paymentService;
    }
}
```

Benefits:

- Flexible
- Easy Maintenance
- Easy Testing
- Easy Implementation Replacement
- Follows SOLID Principles

---

# Q15. What is an Interface?

### Answer

An Interface defines a contract.

It tells implementing classes:

```text
WHAT must be done
```

without specifying:

```text
HOW it should be done
```

Example:

```java
public interface PaymentService {

    void processPayment();
}
```

Any class implementing this interface must provide an implementation for:

```java
processPayment();
```

---

# Plain Java Example - Loose Coupling + Constructor Injection

This example demonstrates Loose Coupling without Spring.

```java
interface PaymentService {

    void processPayment();
}

class CreditService implements PaymentService {

    @Override
    public void processPayment() {

        System.out.println(
                "Payment Processed by Credit Card");
    }
}

class UpiService implements PaymentService {

    @Override
    public void processPayment() {

        System.out.println(
                "Payment Processed by UPI");
    }
}

class OrderService {

    private PaymentService paymentService;

    public OrderService(
            PaymentService paymentService) {

        this.paymentService = paymentService;
    }

    public void orderProceed() {

        paymentService.processPayment();

        System.out.println(
                "Order Completed");
    }
}

public class Main {

    public static void main(String[] args) {

        OrderService orderService =
                new OrderService(
                        new CreditService());

        orderService.orderProceed();
    }
}
```

### Why is this Loose Coupling?

Because:

```java
OrderService
```

depends on:

```java
PaymentService
```

instead of:

```java
CreditService
```

We can easily switch:

```java
new CreditService()
```

to:

```java
new UpiService()
```

without modifying `OrderService`.

---

# Spring Version of Loose Coupling

## Interface

```java
public interface PaymentService {

    void processPayment();
}
```

---

## CreditService

```java
@Service
public class CreditService
        implements PaymentService {

    @Override
    public void processPayment() {

        System.out.println(
                "Payment Processed By Credit Card");
    }
}
```

---

## UpiService

```java
@Service
public class UpiService
        implements PaymentService {

    @Override
    public void processPayment() {

        System.out.println(
                "Payment Processed By UPI");
    }
}
```

---

## OrderService

```java
@Service
public class OrderService {

    private final PaymentService paymentService;

    public OrderService(
            PaymentService paymentService) {

        this.paymentService = paymentService;
    }
}
```

---

# Problem: Multiple Bean Implementations

Spring found:

```java
CreditService
```

and

```java
UpiService
```

because both implement:

```java
PaymentService
```

Then Spring sees:

```java
public OrderService(
        PaymentService paymentService)
```

and becomes confused.

Error:

```text
required a single bean,
but 2 were found:

creditService
upiService
```

Reason:

Spring doesn't know which implementation should be injected.

---

# Q16. What is @Qualifier?

### Answer

`@Qualifier` tells Spring exactly which Bean to inject when multiple implementations exist.

Example:

```java
public OrderService(

    @Qualifier("creditService")
    PaymentService paymentService
) {

    this.paymentService = paymentService;
}
```

Meaning:

```text
Inject CreditService Bean.
```

---

# What Happens Without @Qualifier?

Spring finds:

```text
CreditService
UpiService
```

and throws:

```text
NoUniqueBeanDefinitionException
```

because multiple implementations match the same interface.

---

# What Happens With @Qualifier?

Example:

```java
@Qualifier("creditService")
```

Output:

```text
Payment Processed By Credit Card
Order Completed
```

If changed to:

```java
@Qualifier("upiService")
```

Output:

```text
Payment Processed By UPI
Order Completed
```

---

# Important Understanding

The following class never changes:

```java
OrderService
```

Only the injected implementation changes.

Example:

```java
CreditService
```

↓

```java
UpiService
```

This is the biggest benefit of Loose Coupling.

---

# Day 2 Completion Status

✅ Interface

✅ Tight Coupling

✅ Loose Coupling

✅ Constructor Injection (Java)

✅ Constructor Injection (Spring)

✅ Polymorphism

✅ Programming to Interfaces

✅ Multiple Implementations

✅ Spring Bean Ambiguity

✅ NoUniqueBeanDefinitionException

✅ @Qualifier

---

# Next Topics

- @Primary
- @Repository
- @Controller
- MVC Flow Revision

MVC Flow:

```text
Browser
      ↓
Controller
      ↓
Service
      ↓
Repository
      ↓
Database
```

After completing the above topics:

✅ Spring Core Revision Complete

✅ Spring MVC Quick Revision

✅ Move Back To Spring Boot

