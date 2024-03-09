# Properties with Spring and Spring Boot : Beginner to Advance Level | Latest Update

## Overview
This tutorial will show how to set up and use properties in Spring via Java configuration and **@PropertySource**, **@Value**, **@TestPropertySource** and **@ConfigurationProperties**

We’ll also see how properties work in Spring Boot with coding impmentation not just theory part.

## Before Jumping to Concept: Let's see important depedency

    <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <scope>test</scope>
        </dependency>

## Topic Covered in this video

>1. Register a Properties File via Annotations
>2. Defining Multiple Property Locations
>3. Using/Injecting Properties **(@Value)**
>4. **@ConfigurationProperties** Annotation
>5. **@TestPropertySource** Annotation

## 1. Register a Properties File via Annotations

Spring 3.1 introduces the **@PropertySource** annotation, enabling easy addition of property sources to the environment.

Which can be used in conjunction with the **@Configuration** annotation.
**For Example :**
```java
@Configuration
@PropertySource(value = "classpath:codemyth.properties")
@Data
public class CodeMythProperties {

   ...
}
```

## 2. Defining Multiple Property Locations
The @PropertySource annotation, as per Java 8 conventions, can be used to define multiple property locations if Java 8 or higher is used.
**For Example :**
```java
@Configuration
@PropertySource(value = "classpath:codemyth.properties")
@PropertySource(value = "classpath:application.properties")
@Data
public class CodeMythProperties {

   ...
}
```

The **@PropertySources** annotation allows us to specify an array of **@PropertySource** in any supported Java version, **not just Java 8 or higher**.
**For Example :**
```java
@Configuration
@PropertySources({
        @PropertySource(value = "classpath:codemyth.properties"),
        @PropertySource(value = "classpath:application.properties")
}
)
@Data
public class CodeMythProperties {

    ...
}
```

## 3. Using/Injecting Properties **(@Value)**
The **Spring 3.1** update introduces the **PropertySourcesPlaceholderConfigurer**, 
which resolves **${…}** placeholders within bean definition property values and **@Value** annotations.
**For Example :**
```java
@Value("${channel}")
private String channel;

@Value("${name:DEFAULT_VALUE}")
private String name;
```

## 4. **@ConfigurationProperties** Annotation
Spring Boot applies it’s convention over configuration approach again, automatically mapping between property names and their corresponding fields. 

All that we need to supply is the property prefix.
**For Example :**

```java
@Configuration
@ConfigurationProperties(prefix = "student")
@Data
public class PropertiesConfiguration {

    private String name;
}
```

## 5. **@TestPropertySource** Annotation
We might also have a requirement to use different property values when our application is under test.

**Spring Boot handles this for us by looking in our src/test/resources directory during a test run.**

If we need more granular control over test properties, then we can use the **@TestPropertySource** annotation.

This allows us to set test properties for a specific test context, taking precedence over the default property sources:
```java
@RunWith(SpringRunner.class)
@TestPropertySource("/codemyth.properties")
public class CodeMythPropertiesTest {
    @Value("${channel}")
    private String foo;

    @Test
    public void whenFilePropertyProvided_thenProperlyInjected() {
        assertThat(foo).isEqualTo("codemyth");
    }
}
```

                                          ꧁༺ 𝙩𝙝𝙖𝙣𝙠 𝙮𝙤🅄༻꧂
