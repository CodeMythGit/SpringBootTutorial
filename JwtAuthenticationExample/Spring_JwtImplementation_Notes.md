# JWT Authentication using Spring Boot Notes

## Step To Be Followed 

> 1. Create Basic Rest API that will return the list of Details like Employee,Student or any other.
> 2. Secure the Rest API by adding security dependecy
> 3. Use the properties file to create custom username and password for authentication
> 4. Create the **SpringSecurityConfig** class to define the bean like **PasswordEncoder, UserDetailsService and AuthenticationManager**
> 5. Create **JwtAuthenticationEntryPoint** Class Which implements AuthenticationEntryPoint interface and override method commence
> 6. Create **JwtHelper** Class which is used to perform action like validateToken and generateToken etc
> 7. Create **JWTAuthenticationFilter** class which is used for the filter purpose
> 8. Create **SecurityFilterConfig** class to define request processing logic
> 9. Create **JwtRequest** and **JwtResponse** class
> 10. Create **JwtAuthenticationController** to return the JwtResponse if everything works fine


## Important Dependency to be used
1. For rest api

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

2. For Getter and Setter
   
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>

3. For Security

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>

4. For JWT

        <!-- https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-api -->
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-api</artifactId>
            <version>0.11.5</version>
        </dependency>


        <!-- https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-impl -->
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-impl</artifactId>
            <version>0.11.5</version>
            <scope>runtime</scope>
        </dependency>

        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-jackson</artifactId> <!-- or jjwt-gson if Gson is preferred -->
            <version>0.11.5</version>
            <scope>runtime</scope>
        </dependency>

## JWT Authentication Work Flow
![image](https://github.com/CodeMythGit/ReadMeNotes/assets/90126232/16a3904a-0445-4f41-a083-722fc953d6f1)

> [!WARNING]  
> Before going forward with the implementation of JWT Authentication load all the jwt dependency

https://jwt.io/#debugger

## Create Class JwtAuthenticationEntryPoint and implements AuthenticationEntryPoint

Method of this class is called whenever an exception is thrown due to unauthenticated user trying to access the resource that required authentication.
Class is going to override the method commence

```java
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter writer = response.getWriter();
        writer.println("Access Denied !! Message :" + authException.getMessage());
    }
}
```
    

## Create JwtHelper Class

This class is used to perform action like **validateToken** and **generateToken** etc.
```java
@Component
public class JwtHelper {

    //requirement :
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60; // this is 5hr * 60min * 60 seconds

    private String secret = "afafasfafafasfasfasfafacasdasfasxASFACASDFACASDFASFASFDAFASFASDAADSCSDFADCVSGCFVADXCcadwavfsfarvf";

    //retrieve username from jwt token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //for retrieving any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //generate token for user
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    //while creating the token -
    //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    //2. Sign the JWT using the HS512 algorithm and secret key.
    //3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
    //   compaction of the JWT to a URL-safe string
    private String doGenerateToken(Map<String, Object> claims, String subject) {

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000)) // converting to milliseconds
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    //validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
```

## Create a class _JWTAuthenticationFilter_ that extends _OncePerRequestFilter_  and override the method _doFilterInternal_ 

In doFilterInternal method we are going to implement below logic:
> 1. Get header from the request based on key Authorization
> 2. Get Token from the header
> 3. Validate token
> 4. Get Username from token
> 5. Load Associated with this token
> 6. Set Authentication **SecurityContextHolder**

```java
@Component
@Slf4j
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtHelper jwtHelper;


    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Bearer
        String requestHeader = request.getHeader("Authorization");
        log.info("Header :  {}", requestHeader);
        String username = null;
        String token = null;
        if (requestHeader != null && requestHeader.startsWith("Bearer")) {
            //looking good
            token = requestHeader.substring(7);
            try {
                username = this.jwtHelper.getUsernameFromToken(token);
            } catch (IllegalArgumentException e) {
                logger.info("Illegal Argument while fetching the username !!");
                e.printStackTrace();
            } catch (ExpiredJwtException e) {
                logger.info("Given jwt token is expired !!");
                e.printStackTrace();
            } catch (MalformedJwtException e) {
                logger.info("Some changed has done in token !! Invalid Token");
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            logger.info("Invalid Header Value !! ");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            //fetch user detail from username
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            Boolean validateToken = this.jwtHelper.validateToken(token, userDetails);
            if (validateToken) {
                //set the authentication
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                logger.info("Validation fails !!");
            }
        }
        filterChain.doFilter(request, response);
    }
}
```

## Create a class SecurityFilterConfig
In this class we are going to create a bean of SecurityFilterChain to define the request processing logic

```java
@Configuration
@AllArgsConstructor
public class SecurityFilterConfig {
    private JwtAuthenticationEntryPoint point;
    private JwtAuthenticationFilter filter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        return security.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .authorizeHttpRequests(auth -> auth.requestMatchers("/authenticate").permitAll()
                        .anyRequest().authenticated())
                .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
```

## Create a class JwtRequest
This class will be use to pass a request body and based on that we will get the response.
```java
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequest {

    private String username;
    private String password;
}
```

## Create a class JwtResponse
This class will be response body of the jwt request
```java
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtResponse {
    private String jwtToken;
    private String username;
}
```

## Create a class JwtAuthenticationController 
Expose a POST API /authenticate using the JwtAuthenticationController. The POST API gets username and password in the body- Using Spring Authentication Manager we authenticate the username and password.If the credentials are valid, a JWT token is created using the JWTTokenUtil and provided to the client.

```java
@RestController
@AllArgsConstructor
@Slf4j
public class JwtAuthenticationController {

    private UserDetailsService userDetailsService;

    private AuthenticationManager manager;

    private JwtHelper helper;

    @PostMapping("/authenticate")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        this.doAuthenticate(request.getUsername(), request.getPassword());


        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = this.helper.generateToken(userDetails);

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Credentials Invalid !!");
        }

    }
}
```

## How to do testing
1. **Create a token using username and password as below (Method type should be : POST)**
![image](https://github.com/CodeMythGit/ReadMeNotes/assets/90126232/b0d34f40-313b-4303-92c5-fb5c00032454)


2. **Use the token generated in above step 1 for accessing the endpoint: (localhost:8080/welcome/employees, Method Type : GET)**
* Use the token in following manner and hit the endpoint
![image](https://github.com/CodeMythGit/ReadMeNotes/assets/90126232/1baab147-e936-4fca-9d91-2307123a0ece)





