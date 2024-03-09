# Understanding Spring Boot Actuator with Code Example (Most asked Skills by IT company)

## Step  to implement

* Important Dependencies
* What Is an Actuator?
* Important Concept of Actuator?
* Predefined Endpoints
* We will learn this concept with coding practice

## 1 : Important Dependencies

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>

## 2 : What Is an Actuator?


  * Actuator brings production-ready features to our application.
  * **Monitoring our app, gathering metrics, and understanding traffic or the state of our database becomes trivial with this dependency.**
  * The main benefit of this library is that we can get production-grade tools **without actually having to implement these features ourselves.**
  * The actuator mainly exposes **operational information* about the running application** — **health, metrics, info, dump, env, etc.** It uses HTTP endpoints or JMX beans to enable us to interact with it.

## 3 : Important Concept of Actuator

  * Actuator comes with most endpoints disabled.
  * Thus, the only two available by default are **/health** and **/info**.
  * If we want to enable all of them, we could set management.endpoints.web.exposure.include=*. Alternatively, we can list endpoints that should be enabled.


## 4 : Predefined Endpoints

* **/beans**: returns all available beans in our BeanFactory. Unlike /auditevents, it doesn’t support filtering.
* **/conditions**: formerly known as /autoconfig, builds a report of conditions around autoconfiguration.
* **/configprops** allows us to fetch all @ConfigurationProperties beans.
* **/env** returns the current environment properties. Additionally, we can retrieve single properties.
* **/health** summarizes the health status of our application.
* **/info** returns general information. It might be custom data, build information or details about the latest commit.( **management.info.java.enabled=true**)
* **/loggers** enables us to query and modify the logging level of our application.
* **/metrics** details metrics of our application. This might include generic metrics as well as custom ones.
* **/scheduledtasks** provides details about every scheduled task within our application.
* **/shutdown** performs a graceful shutdown of the application. (**management.endpoint.shutdown.enabled=true, method = POST**)
* **/threaddump** dumps the thread information of the underlying JVM.
