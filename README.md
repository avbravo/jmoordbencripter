Jmoordbencripter

Es un API para realizar encriptaciones en los proyectos con Jmoordb-core.

Varias librerias son usadas con el consentimiento de sus autores entre ellos

 * Author Lokesh Gupta  
   Email: howtodoinjava@gmail.com  
   Twitter: [@HowToDoInJava](@HowToDoInJava)  
   [Java AES Encryption Decryption Example](https://howtodoinjava.com/java/java-security/java-aes-encryption-example/)  

* Author Deepak Mishra   
  Twitter: [@D3EP4K](@D3EP4K)  
  Generate JWT Token and Verify in Plain Java
  [https://metamug.com/article/security/jwt-java-tutorial-create-verify.html](https://metamug.com/article/security/jwt-java-tutorial-create-verify.html)  



## Encriptar

```java

String encryptedString = Encryptor.encrypt(password, secretKey);

```

## Desencriptar

```java

 String decryptedString = Encryptor.decrypt(encryptedString , secretKey);

```

## Microservicio

### Token JWT con Password encriptado y con expiracion

Este ejemplo muestra un microservicio con Microprofile

```java
 @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public JWTToken encripter(@QueryParam("subject") final String subject,
            @QueryParam("password") final String password,
            @QueryParam("group") final String group,
            @QueryParam("expirationDays") final int expirationDays,
            @QueryParam("secretKey") final String secretKey
    ) {
        JWTToken jWTToken = new JWTToken();
        String token = "";
        try {

            String encryptedString = Encryptor.encrypt(password, secretKey);

            JWTEntity jWTEntity = new JWTEntity.Builder()
                    .subject(subject)// vacio o el nombre del subject
                    .group(group) // vacio o el nombre de grupo
                    .password(encryptedString) // password
                    .expirationDays(expirationDays) // tiempo de expiracion en dias 90
                    .secretKey(secretKey) // llave secreta
                    .build();

            token = JWTEncrypter.encrypt(jWTEntity);

            jWTToken.setToken(token);

       

        } catch (Exception ex) {
            Logger.getLogger(EncripterController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return jWTToken;

    }
```
Podemos usar un jwtToken

Invocarlo

[http://localhost:8080/api/encripter?subject=avbravo&&password=mipassword&&group=admin&&secretKey=MiClaveSecreta&&expirationDays=90](http://localhost:8080/api/encripter?subject=avbravo&&password=mipassword&&group=admin&&secretKey=MiClaveSecreta&&expirationDays=90)

resultado

```json
{"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhdmJyYXZvIiwiYXVkIjpbImFkbWluIiwidUl4MGV1UlJEcGZFVURkK2Q3VzV4UT09Il0sImlzcyI6ImF2YnJhdm8uYmxvZ3Bvc3QuY29tIiwiZXhwIjoxNjgxMDc5MTY0LCJpYXQiOjE2NzMzMDMxNjQsImp0aSI6Ijk5OGI5YzgyLTFlY2MtNDZlYS05ODUxLWFkOTk1YThjMWUzOCJ9.rgL_ttvweBHI356riZkAUd1D3JD4Gqo30aghXTm36qY"}

```



## Leer el token y obtener el password

```java

@Path("decript")
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})

    public JWTEntity decript(@QueryParam("token") final String token,
            @QueryParam("secretKey") final String secretKey) {

        JWTEntity jWTEntity = new JWTEntity();
        try {
    
            jWTEntity = JWTEncrypter.decrypt(token, secretKey);
           
            String decryptedString = Encryptor.decrypt(jWTEntity.getPassword(), secretKey);

           
            jWTEntity.setPassword(decryptedString);

           
        } catch (Exception e) {
            System.out.println("decript() " + e.getLocalizedMessage());
        }

        return jWTEntity;
    }

```

Invocarlo


[http://localhost:8080/api/encripter/decript?secretKey=MiClaveSecreta&&token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhdmJyYXZvIiwiYXVkIjpbImFkbWluIiwidUl4MGV1UlJEcGZFVURkK2Q3VzV4UT09Il0sImlzcyI6ImF2YnJhdm8uYmxvZ3Bvc3QuY29tIiwiZXhwIjoxNjgxMDc5MTY0LCJpYXQiOjE2NzMzMDMxNjQsImp0aSI6Ijk5OGI5YzgyLTFlY2MtNDZlYS05ODUxLWFkOTk1YThjMWUzOCJ9.rgL_ttvweBHI356riZkAUd1D3JD4Gqo30aghXTm36qY](http://localhost:8080/api/encripter/decript?secretKey=MiClaveSecreta&&token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhdmJyYXZvIiwiYXVkIjpbImFkbWluIiwidUl4MGV1UlJEcGZFVURkK2Q3VzV4UT09Il0sImlzcyI6ImF2YnJhdm8uYmxvZ3Bvc3QuY29tIiwiZXhwIjoxNjgxMDc5MTY0LCJpYXQiOjE2NzMzMDMxNjQsImp0aSI6Ijk5OGI5YzgyLTFlY2MtNDZlYS05ODUxLWFkOTk1YThjMWUzOCJ9.rgL_ttvweBHI356riZkAUd1D3JD4Gqo30aghXTm36qY)



Resultado

```json
{"expirationDays":90,"group":"admin","isValid":true,"password":"mipassword","status":0,"subject":"avbravo"}

```

Podemos observar que isValid es true indicando que el password no ha vencido aun, si el password vence devuelve false.

se puede observar el password desencriptado


## https://jwt.io/
si lo pegamos en [https://jwt.io/](https://jwt.io/)
El resultado seria

```json
{
  "sub": "avbravo",
  "aud": [
    "admin",
    "uIx0euRRDpfEUDd+d7W5xQ=="
  ],
  "iss": "avbravo.blogpost.com",
  "exp": 1681078662,
  "iat": 1673302662,
  "jti": "732626ca-e65f-4559-941b-9f58f14a9dd1"
}

```
