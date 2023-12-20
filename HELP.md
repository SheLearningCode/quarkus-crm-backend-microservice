# **1 Project Description**

The 'Student Customer Relationship Management (CRM) Service' should be implemented to avoid tons of paperwork at the university. In the end it should be possible to do simple data works with students and courses.
Courses can contain a bunch of students and a student can be part of a bunch of courses.
As an administrative user I want to create, read, update and delete (CRUD) student- and course data.
Beside the base functionality the developers want to have a dockerized environment and keep the DB/configurations/… in their hands as much as possible. Beside the infrastructure needs they want to make sure the code quality is as good as possible. So, code analysis, auto-formatting-rules, Unit-, Integration- and Documentation-Tests are needed.
E2E (end-to-end) tests will be ignored in that state since it will be a monolithic service that contains 2 domains. E2E tests are part of chapter 3.

## **The business focus and implementation order:**

• Student-CRM:
◦ Independent services for:
▪ Database with needed structure
▪ Backend functionality (microservice)
▪ Frontend available for end-users

**In order to get all required fields, here is a draft how the entities could look like. 
In order to keep the quality high use validations (length, regex) and own types for fields which can be wrapped.**

### **_Student-Entity:_**

• ID: numeric db sequence
• Firstname: text max 20 letters
• Lastname: text max 20 letters
• Email: unique, valid email format
• Courses: List of Courses, lazy loaded

### **_Course-Entity:_**

• ID: numeric db sequence
• Course name: text max 35 letters
• Students: List of Students, lazy loaded

### **_2 Tech-Stack description_**

• Programming language: Kotlin
• Build Tool: Maven
• Architecture: Classic MV(S)C (Model-View-(Service)-Controller)
• Application Framework: Quarkus
• Database (Dockerized):
◦ Postgres (Relational) + Flyway DB Migrator + Spring-JPA
• Dockerized Services
◦ Docker compose to wrap up the full environment (Services, DB,..)
◦ Each service (backend, frontend) should be provided as own docker-container
• Code formatting: ktlint
• Code smell detection: detect
• Component Testing: JUnit 5 + MockK
◦ Rest-Client for Int-/Component-Testing: MockMvc
◦ Testcontainers-Framework for Int-Tests
• Documentation-Tests: ASCII-doctor
◦ HTML files should be published to “/doc” when App is running
• Frontend with functional Typescript:
◦ VueJS (3+ / Composition API)  + elementPlus UI
◦ Dockerized the frontend service as well
• Front-End testing: Jest for Component-Testing

### **_Steps:**_ 

1. Created a Rest-Endpoint and checked the connection with Postman
2. Created the MVCS project Structure with : 
   a) Controller(StudentController, CourseController, StudentCourseController)
   b) Model
     aa: Entities for Student, Course && CourseToStudent
     bb: Repositories for Student, Course && CourseToStudent
   c) Service with StudentResource, CourseResource & StudentToCourseResource
   d) StudentResourceTest
3. Created the frontend Project-Structure with Vue.Js
4. Implementation of StudentController 
5. Implementation of StudentDto
6. Implementation of StudentRepository
7. Implementing a StudentEntity
8. Implementing the StudentService
9. Same with course 
10. Creating ManyToMany Relationship with Quarkus Annotations


# **_Some tips:_**

#### **What is Dev Services?**

Quarkus supports the automatic provisioning of  not configured services in development and test mode. We refer to this capability as Dev Services. From a developer’s perspective this means that if you include an extension and don’t configure it then Quarkus will automatically start the relevant service (usually using Testcontainers behind the scenes) and wire up your application to use this service.
https://quarkus.io/guides/dev-services

#### **What is a Container?**

A container is a package of software that includes all dependencies: code, runtime, configuration, and system libraries so that it can run on any host system. At runtime, the container is also granted its own isolated slice of operating system resources like CPU, RAM, Disk, and Networking.

#### **What is Flyway DB?**

Flyway is an open-source database migration tool. It strongly favors simplicity and convention over configuration.
It is based around just 7 basic commands: Migrate, Clean, Info, Validate, Undo, Baseline and Repair.
Migrations can be written in SQL (database-specific syntax (such as PL/SQL, T-SQL, ...) is supported) or Java (for advanced data transformations or dealing with LOBs).
https://flywaydb.org/

#### **_What is Hibernate-ORM?_**

Hibernate ORM is the de facto JPA implementation and offers you the full breadth of an Object Relational Mapper. It makes complex mappings possible, but it does not make simple and common mappings trivial. Hibernate ORM with Panache focuses on making your entities trivial and fun to write in Quarkus.

#### **_What is JPA?_**

Java Persistence API is a collection of classes and methods to persistently store vast amounts of data into a database which is provided by the Oracle Corporation. To reduce the burden of writing codes for relational object management, a programmer follows the ‘JPA Provider’ framework, which allows easy interaction with the database instance. Here the required framework is taken over by JPA.


#### **_What is RestAssured_**

Testing and validating REST services in Java is harder than in dynamic languages such as Ruby and Groovy. REST Assured brings the simplicity of using these languages into the Java domain.
https://rest-assured.io/


#### **_What is PostgreSQL?_**

PostgreSQL is also known as Postgres, is a free and open-source relational database management system (RDBMS) emphasizing extensibility and SQL compliance. 

#### **_What is JAX-RS ?_**

Jakarta RESTful Web Services, (JAX-RS; formerly Java API for RESTful Web Services) is a Jakarta EE API specification that provides support in creating web services according to the Representational State Transfer (REST) architectural pattern.
JAX-RS uses annotations, introduced in Java SE 5, to simplify the development and deployment of web service clients and endpoints.

#### **_What is RESTEasy ?_**

RESTEasy is a JBoss / Red Hat project that provides various frameworks to help you build RESTful Web Services and RESTful Java applications. Moreover, RESTEasy also implements the MicroProfile REST Client specification API.

#### **_What is Quarkus ?_**

Quarkus is a Cloud Native, (Linux) Container First framework for writing Java applications. Quarkus is a MicroProfile implementation that focuses on efficiently running Java applications in containers in general and Kubernetes in particular. The MicroProfile project is aimed at
optimizing Enterprise Java for the microservices' architecture.

#### **_What is Apache Maven ?_**

Apache Maven is a software project management and comprehension tool. Based on the concept of a project object model (POM), Maven can manage a project's build, reporting, and documentation from a central piece of information.


#### **_What is Swagger?_**

Swagger UI, a part of Swagger, is an open-source tool that generates a web page that documents the APIs generated by the Swagger specification. ... SwaggerUI is automatically generated from any API defined in the OpenAPI Specification and can be viewed within a browser.

#### **_What is Eclipse Microprofile OpenAPI ?_**

This MicroProfile specification, called OpenAPI 1.0, aims to provide a set of Java interfaces and programming models that allow Java developers to natively produce OpenAPI v3 documents from their JAX-RS applications.

#### **_What is Microprofile RestClient?_**

The MicroProfile Rest Client provides a type-safe approach to invoke RESTful services over HTTP. As much as possible the MP Rest Client attempts to use JAX-RS 2.0 APIs for consistency and easier re-use.


#### **_What is CORS?_**

Cross-Origin Resource Sharing (CORS) is an HTTP-header based mechanism that allows a server to indicate any other origins (domain, scheme, or port) than its own from which a browser should permit loading of resources. CORS also relies on a mechanism by which browsers make a “preflight” request to the server hosting the cross-origin resource, in order to check that the server will permit the actual request. In that preflight, the browser sends headers that indicate the HTTP method and headers that will be used in the actual request.
An example of a cross-origin request: the front-end JavaScript code served from https://domain-a.com uses XMLHttpRequest to make a request for https://domain-b.com/data.json.

#### **_What is Reactive Programming?_**

Reactive programming is a declarative programming paradigm concerned with data streams and the propagation of change. With this paradigm, it is possible to express static (e.g., arrays) or dynamic (e.g., event emitters) data streams with ease, and also communicate that an inferred dependency within the associated execution model exists, which facilitates the automatic propagation of the changed data flow.

#### _**What is Mutiny?**_

Mutiny is a reactive programming library allowing to express and compose asynchronous actions. 
It offers two types:
- Uni: for asynchronous action providing 0 or 1 result
- Multi: for multi-item (with back-pressure) streams.
Both types are lazy and follow a subscription pattern


#### _**What is Microprofile Fault Tolerance?**_

It is increasingly important to build fault-tolerant microservices. Fault tolerance is about leveraging different strategies to guide the execution and result of some logic. Retry policies, bulkheads, and circuit breakers are popular concepts in this area. They dictate whether and when executions should take place, and fallbacks offer an alternative result when execution does not complete successfully.
Fault Tolerance specification is to focus on the following aspects:
- Timeout: Define a duration for a timeout
- Retry: Define criteria on when to retry
 -Fallback: provide an alternative solution for a failed execution.
- CircuitBreaker: offer a way to fail fast by automatically failing execution to prevent the system overloading and indefinite wait or timeout by the clients.
- Bulkhead: isolate failures in part of the system while the rest part of the system can still function.

#### **_What is Microprofile RestClient?_**

The MicroProfile Rest Client provides a type-safe approach to invoke RESTful services over HTTP. As much as possible the MP Rest Client attempts to use JAX-RS 2.0 APIs for consistency and easier re-use.

#### **_What is MicroProfile Config?_**

The majority of applications need to be configured based on a running environment. It must be possible to modify configuration data from outside an application so that the application itself does not need to be repackaged.
The configuration data can come from different locations and in different formats (e.g. system properties, system environment variables, .properties, .xml, datasource). We call these config locations ConfigSources. If the same property is defined in multiple ConfigSources, we apply a policy to specify which one of the values will effectively be used.
Under some circumstances, some data sources may change dynamically. The changed values should be fed into the client without the need for restarting the application. This requirement is particularly important for microservices running in a cloud environment. The MicroProfile Config approach allows to pick up configured values immediately after they got changed.

#### _**What is YAML?**_

YAML is a human-readable data-serialization language. It is commonly used for configuration files and in applications where data is being stored or transmitted.

#### **_What is Quarkus profiles?_**

Quarkus supports the notion of Profiles. These allow you to have multiple configuration per profile in the same application.properties file. The syntax is %{profile}.config.key=value. 


#### **_What is Junit 5?_**

JUnit 5 is the next generation of JUnit. The goal is to create an up-to-date foundation for developer-side testing on the JVM. This includes focusing on Java 8 and above, as well as enabling many styles of testing.
https://junit.org/junit5/


#### **_What is Mockito?_**

Mockito is a mocking framework that tastes perfect. It lets you write beautiful tests with a clean & simple API. Mockito does not give you hangover because the tests are very readable, and they produce clean verification errors. 
https://site.mockito.org/

#### **_What is Quarkus Logging?_**

Internally, Quarkus uses JBoss Log Manager and the JBoss Logging facade. You can use the JBoss Logging facade inside your code as it’s already provided, or any of the supported Logging API listed in the next chapter as Quarkus will send them to JBoss Log Manager. All the logging configuration will then be done inside your application.properties


#### _**What is Wiremock?_**

WireMock is a library for stubbing and mocking web services. It constructs an HTTP server that we could connect to as we would to an actual web service. 
http://wiremock.org/


#### **_What is MicroProfile Health?_**

MicroProfile Health allows services to report their health, and it publishes the overall health status to a defined endpoint. A service reports UP if it is available and reports DOWN if it is unavailable. MicroProfile Health reports an individual service status at the endpoint and indicates the overall status as UP if all the services are UP. A service orchestrator can then use the health statuses to make decisions.


#### **_What is MapStruct?_**

MapStruct is a code generator that greatly simplifies the implementation of mappings between Java bean types based on a conventional configuration approach.
https://mapstruct.org/


#### **_What is Project Lombok?_**

Project Lombok is a java library that automatically plugs into your editor and build tools, spicing up your java.
Never write another getter or equals method again, with one annotation your class has a fully featured builder, Automate your logging variables, and much more.
https://projectlombok.org/


#### **What is RBAC?**

Role-Based Access Control (RBAC) attributes permissions to a user based on their business responsibilities. As the most common access control system, it determines access based on your role in the company—ensuring lower-level employees aren’t gaining access to high-level information.

#### **_What is JWT?_**

JSON Web Token  is a proposed Internet standard for creating data with optional signature and/or optional encryption whose payload holds JSON that asserts some number of claims. The tokens are signed either using a private secret or a public/private key.

#### **_What is a bean?_**

a bean is a container-managed object that supports a set of basic services, such as injection of dependencies, lifecycle callbacks and interceptors. 
An application developer can focus on the business logic rather than finding out "where and how" to obtain a fully initialized component with all of its dependencies:
There are 

    1. Class beans

    2. Producer methods

    3. Producer fields

    4. Synthetic beans



**class based beans are most used:** 
`@ApplicationScoped (1)
public class Translator {

    @Inject (2)
    Dictionary dictionary; 

    @Counted  (3)
    String translate(String sentence) {
      // ...
    }
}`

	(1) This is a scope annotation. It tells the container which context to associate the bean instance with. In this particular case, a single bean instance is created for the application and used by all other beans that inject Translator.
	(2) This is a field injection point. It tells the container that Translator depends on the Dictionary bean. If there is no matching bean the build fails.
	(3) This is an interceptor binding annotation. In this case, the annotation comes from the MicroProfile Metrics. The relevant interceptor intercepts the invocation and updates the relevant metrics. 

 In CDI the process of matching a bean to an injection point is type-safe. Each bean declares a set of bean types. In our example above, the Translator bean has two bean types: Translator and java.lang.Object. Subsequently, a bean is assignable to an injection point if the bean has a bean type that matches the required type and has all the required qualifiers. We’ll talk about qualifiers later. For now, it’s enough to know that the bean above is assignable to an injection point of type Translator and java.lang.Object.
 There is a simple rule: exactly one bean must be assignable to an injection point, otherwise the build fails. If none is assignable the build fails with UnsatisfiedResolutionException. If multiple are assignable the build fails with AmbiguousResolutionException. This is very useful because your application fails fast whenever the container is not able to find an unambiguous dependency for any injection point.
 
Your can use programmatic lookup via j**avax.enterprise.inject.Instance** to resolve ambiguities at runtime and even iterate over all beans implementing a given type:

`public class Translator {

    @Inject 
    Instance<Dictionary> dictionaries; (1)

    String translate(String sentence) {
      for (Dictionary dict : dictionaries) { (2)
         // ...
      }
    }
}`

	(1) This injection point will not result in an ambiguous dependency even if there are multiple beans that implement the Dictionary type.
	(2) javax.enterprise.inject.Instance extends Iterable.
	
	
#### **_Can I use setter and constructor injection?_**
	
In CDI the "setter injection" is superseded by more powerful initializer methods. Initializers may accept multiple parameters and don’t have to follow the JavaBean naming conventions.
Initialized and Constructor Injection Example

`@ApplicationScoped
public class Translator {

    private final TranslatorHelper helper;

    Translator(TranslatorHelper helper) { (1)
       this.helper = helper;
    }

    @Inject (2)
    void setDeps(Dictionary dic, LocalizationService locService) { (3)
      / ...
    }
}`

	(1) This is a constructor injection. In fact, this code would not work in regular CDI implementations where a bean with a normal scope must always declare a no-args constructor and the bean constructor must be annotated with @Inject. However, in Quarkus we detect the absence of no-args constructor and "add" it directly in the bytecode. It’s also not necessary to add @Inject if there is only one constructor present.
	(2) An initializer method must be annotated with @Inject.
	(3) An initializer may accept multiple parameters - each one is an injection point.


#### **_What are Qualifiers?**_ 

Qualifiers are annotations that help the container to distinguish beans that implement the same type. As we already said a bean is assignable to an injection point if it has all the required qualifiers. If you declare no qualifier at an injection point the @Default qualifier is assumed.

A qualifier type is a Java annotation defined as @Retention(RUNTIME) and annotated with the @javax.inject.Qualifier meta-annotation:
Qualifier Example

`@Qualifier
@Retention(RUNTIME)
@Target({METHOD, FIELD, PARAMETER, TYPE})
public @interface Superior {}`

The qualifiers of a bean are declared by annotating the bean class or producer method or field with the qualifier types:
Bean With Custom Qualifier Example

`@Superior (1)
@ApplicationScoped
public class SuperiorTranslator extends Translator {

    String translate(String sentence) {
      // ...
    }
}`

	(1) @Superior is a qualifier annotation.

This bean would be assignable to @Inject @Superior Translator and @Inject @Superior SuperiorTranslator but not to @Inject Translator. 
The reason is that @Inject Translator is automatically transformed to @Inject @Default Translator during typesafe resolution. 
And since our SuperiorTranslator does not declare @Default only the original Translator bean is assignable.


#### **_What is the bean scope?_**

The scope of a bean determines the lifecycle of its instances, i.e. when and where an instance should be created and destroyed. Every bean has exactly one scope. 
You can use all the built-in scopes mentioned by the specification except for javax.enterprise.context.ConversationScoped.


#### **_@ApplicationScoped and @Singleton look very similar. Which one should I choose for my Quarkus application?_**

A @Singleton bean has no client proxy and hence an instance is created eagerly when the bean is injected. By contrast, an instance of an @ApplicationScoped bean is created lazily, i.e. when a method is invoked upon an injected instance for the first time.
Furthermore, client proxies only delegate method invocations, and thus you should never read/write fields of an injected @ApplicationScoped bean directly. You can read/write fields of an injected @Singleton safely.
@Singleton should have a slightly better performance because the is no indirection (no proxy that delegates to the current instance from the context).
On the other hand, you cannot mock @Singleton beans using QuarkusMock.
@ApplicationScoped beans can be also destroyed and recreated at runtime. Existing injection points just work because the injected proxy delegates to the current instance.
Therefore, we recommend to stick with @ApplicationScoped by default unless there’s a good reason to use @Singleton.

### **_Core concepts and terms in Keycloak: authentication and authorisation tool_**

#### **users**

    Users are entities that are able to log into your system. They can have attributes associated with themselves like 
    email, username, address, phone number, and birthday. They can be assigned group membership 
    and have specific roles assigned to them.
#### **authentication**

    The process of identifying and validating a user.
#### **authorization**

    The process of granting access to a user.
#### **credentials**

    Credentials are pieces of data that Keycloak uses to verify the identity of a user.
    Some examples are passwords, one-time-passwords, digital certificates, or even fingerprints.
#### **roles**

    Roles identify a type or category of user. Admin, user, manager, 
    and employee are all typical roles that may exist in an organization. 
    Applications often assign access and permissions to specific roles rather than 
    individual users as dealing with users can be too fine-grained and hard to manage.
#### **user role mapping**

    A user role mapping defines a mapping between a role and a user.
    A user can be associated with zero or more roles. This role mapping information can be encapsulated 
    into tokens and assertions so that applications can decide access permissions on various resources they manage.
#### **composite roles**

    A composite role is a role that can be associated with other roles. 
    For example a superuser composite role could be associated with the sales-admin and order-entry-admin roles. 
    If a user is mapped to the superuser role they also inherit the sales-admin and order-entry-admin roles.
#### **groups**

    Groups manage groups of users. Attributes can be defined for a group. 
    You can map roles to a group as well. Users that become members of a group
    inherit the attributes and role mappings that group defines.
#### **realms**

    A realm manages a set of users, credentials, roles, and groups. 
    A user belongs to and logs into a realm. Realms are isolated from one another 
    and can only manage and authenticate the users that they control.
#### **clients**

    Clients are entities that can request Keycloak to authenticate a user. 
    Most often, clients are applications and services that want to use Keycloak to secure themselves 
    and provide a single sign-on solution. Clients can also be entities that just want to request 
    identity information or an access token so that they can securely invoke 
    other services on the network that are secured by Keycloak.
#### **identity token**

    A token that provides identity information about the user. 
    Part of the OpenID Connect specification.
#### **access token**

    A token that can be provided as part of an HTTP request that grants access 
    to the service being invoked on. This is part of the OpenID Connect and OAuth 2.0 specification.

#### **authentication flows**

    Authentication flows are work flows a user must perform when interacting with certain aspects of the system.
    A login flow can define what credential types are required.
    A registration flow defines what profile information a user must enter and whether something 
    like reCAPTCHA must be used to filter out bots. 
    Credential reset flow defines what actions a user must do before they can reset their password.

# **HTTP-Requests: GET, POST,  PUT/PATCH, DELETE:**

#### **difference between PUT and Patch:**
In summary, PUT is used to update all fields in the resource and PATCH is used to update only specific fields in the resource.

# **best practice and clean code at nt:** 
- better using for each then while or for loop when possible!
- functional programming is more testable and with kotlin even more powerful
- always declaring val when variable never changes
- Keycloak: JWT-Tokens are secure and useful
- less code and readable, meaningful naming of classes/variables etc
- think always when planing the structure: 
  will this app get bigger and when yes, 
  can my code structure handle it without having to change a lot of code?
- Is my code extendable and flexible enough?
- don't use swagger ui for documentation
- better using assertions then hamcrest, also with java assertj, with kotlin assert-k
- mockk for mocking when programing with kotlin
- as many unit tests, that are fast testable as possible
- test driven development is a nice approach!

**KISS** = keep it stupid simple
**DRY** = don't repeat yourself
**SOLID** = 
_1. S- Single Responsibility_
MVVM(View , ViewModel, Model) Architecture is Single Responsibility Principle since all 3 layers means View Layer, 
Logic Layer and Data layer have different responsibilities

_2. O- Open for Extension and Closed for Modification_
we should extend the class and use inheritance for supporting new kinds of this class.
So we can use factory Design Pattern here which will allow us to follow this principle.

_3. L- Liskov Substitution Principle: Derived classes should be substitutable for Parent Class_
The "is an" example: apple and orange are both fruits(parent class) but not the same object

_4. I- Interface Segregation Principle_
All the interfaces should be segregated with cohesive responsibility.
For example: Printer can have functionality for FAX, Scan and Print but all not mandatory so 
segregate them in different interfaces will be more efficient if in future any new Print Technology is introduced or older one is removed.

_5. D- Dependency Inversion Principle_
High-level modules should not depend on low-level modules. Both should depend on abstractions.
Abstractions should not depend on details. Details should depend on abstractions.

### **How to connect and integrate keycloak to quarkus:**

    -Create a realm in Keycloak for your application. This will allow you to manage the users, roles, and clients that will have access to your Quarkus application.
    -Add the Keycloak settings to your Quarkus application.properties file. You will need to specify the Keycloak server URL, the realm name, the client ID, and the client secret.
    -Use the Quarkus Keycloak extension to integrate Keycloak with your Quarkus application. The Keycloak extension provides a convenient API for configuring Keycloak in your application and handling authentication and authorization.
    -Add the Keycloak adapter libraries to your Quarkus project. You will need to add the appropriate adapter library for the type of authentication you want to use (e.g. OIDC).
    -Create the security constraints in your Quarkus application. This will specify which parts of your application require authentication and authorization.
    -Start the Keycloak server using Docker Compose as specified in your docker-compose.yaml file.
    -Run your Quarkus application and test the Keycloak integration by logging in and accessing the parts of your application that are protected by Keycloak.

### **How to use GIT-VersionControl:**
    Ablauf erstes mal Branch erstellen/ pushen:

    git checkout -b <branch-name>
    git status
    git add . (wenn man alles hinzufügen möchte)
    git status ( zum kontrollieren ist aber nicht so wichtig)
    git commit -m "<commit message>"
    git push --set-upstream origin <branch-name>

    Ablauf wenn man Änderungen zu einem bestehenden branch hinzufügen möchte:
    git status
    git add . (das gleiche wie oben nur was man hinzufügen möchte mit . fügt man alles hinzu)
    git status
    git commit -m "<message>" ODER git commit --amend --no-edit 
    git push ODER git push -f  
