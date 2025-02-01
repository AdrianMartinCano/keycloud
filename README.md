# Keycloud Backend

!Bienvenido al repositorio del backend de **Keycloud**, un gestor de contraseñas!

Este proyecto está construido con Java, JPA, Spring, Maven y Docker.

## Pasos para que funcione

Sigue estos pasos para configurar y ejecutar el proyecto:

### 1. Crear el archivo `email-config.properties`

Dentro de la carpeta `/src/main/resources/` crea el archivo con el siguiente contenido:
```
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username= CUENTA_DE_GMAIL_SIN_COMILLAS
spring.mail.password=CONTRASEÑA_DE_APLICACIÓN_SIN_ESPACIOS
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.starttls.required=true
spring.mail.properties.mail.smtp.ssl.trust=smtp.gmail.com
spring.mail.properties.mail.transport.protocol=smtp
email.to=DIRECCIÓN_DE_CORREO
```
Este archivo contiene la configuración del servidor SMTP para el envío de correos. También define la dirección de correo a la que se enviarán los mensajes del formulario de contacto.
Es necesario activar SMTP. En este caso, se ha utilizado el servicio de Gmail.
Además, necesitarás generar una contraseña de aplicación para permitir el envío de correos desde la API. [Aquí](https://support.google.com/accounts/answer/185833?hl=es) te explican como obtener una.
** Es importante que esté en la ruta mencionada, ya que si no la clase EmailConfig dará error y nunca compilará el proyecto **

### 2. Modificar el `application.properties`

En la carpeta `/src/main/resources/` tienes el archivo mencionado.
Aquí es importante que cambies la linea 
```
spring.datasource.url=jdbc:mysql://localhost:3306/keycloud
```
Por tu ruta y tu base de datos.


### 3. Modificar el `application-docker.properties`
En la carpeta `/src/main/resources/` hay este otro archivo, este archivo se usa cuando la aplicación se ejecuta en Docker.
Este archivo se cargará automáticamente cuando ejecutes la aplicación en un contenedor Docker, asegurando que se use la configuración correcta para la base de datos dentro del contenedor.
En este caso, la linea: 
```
spring.datasource.url=jdbc:mysql://mysql:3306/keycloud
```
Depende del nombre que le des en el `docker-compose.yml`
Es recomendable no modificar esta configuración, ya que el contenedor de MySQL usará el nombre definido en `docker-compose.yml`


### 4. Docker-compose.yml
En este archivo, puedes cambiar la versión de la imagen de SQL, que es lo que he usado en mi caso.
Este archivo define la configuración de los servicios que se ejecutarán en Docker. Aquí puedes modificar el nombre del contenedor de MySQL, la versión de la imagen y los volúmenes donde se almacenarán los datos de la base de datos.

### 5. Obtener el .JAR para dockerizar la API y la BBDD
Ejecuta el comando
```
mvn clean package
```
Una vez terminado, debería haber una carpeta `/target/` en el directorio raíz. Esta carpeta contendrá el archivo `.jar` que se usará en el microservicio.

### 6. Construir la imagen
Asegúrate de que el archivo Dockerfile está en la raíz del proyecto. Si necesitas personalizar la imagen, puedes modificar el Dockerfile en la raíz del proyecto.
Luego, ejecuta el siguiente comando en la terminal:
```
docker build -t keycloud-api .
```

Puedes verificar si se ha creado la imagen con el comando:
```
docker images
```

### 7. Levantar contenedores
Ejecuta el comando
```
docker-compose up -d
```
Una vez levantados los contenedores, la API estará disponible en:
```
http://localhost:8080/
```
### 8. Detener contenedores
Para detener y eliminar los contenedores, usa:
```
docker-compose down
```

En estos momentos, la aplicación ya estaría desplegada.

### 8. Anexo
Puedes acceder a un bash de docker y comprobar si la base de datos se ha creado bien:

```
docker exec -it mysql_container bash
mysql -u root -p
show schemas;
use keycloud;
```

## Funcionalidades:
- Login de usuarios
- Alta de usuarios
- CRUD de contraseñas
- Envío de email al registrar, y al cambiar contraseñas.

## Endpoints

### Usuarios
- **POST** `/api/usuarios/login` → Login de usuario  
- **POST** `/api/usuarios/registrar` → Alta de usuario  
- **GET** `/api/usuarios/{id}` → Obtener datos de un usuario  
- **PUT** `/api/usuarios/modificar` → Modificar datos de un usuario  
- **POST** `/api/usuarios/nuevaPassword` → Restablecer contraseña  
- **GET** `/api/usuarios/email/{email}` → Obtener usuario por email  

### Contraseñas
- **GET** `/api/contrasenas/{id}` → Obtener contraseñas de un usuario  
- **POST** `/api/contrasenas/crear` → Crear contraseña  
- **PUT** `/api/contrasenas/modificar` → Modificar contraseña  

### Contacto
- **POST** `/api/contacto/guardar` → Guardar mensaje de contacto  

  ## Tecnologías usadas
- **Spring boot:** Framework principal para la creación de la API.
- **JPA:** Para las operaciones del CRUD.
- **Maven:** Para la instalación de paquetes de manera sencilla.
- **Docker:** Para la creación de un microservicio.
  
## Contacto
Si tienes alguna pregunta o sugerencia, no dudes en ponerte en contacto conmigo a través de [LinkedIn](https://www.linkedin.com/in/adrian-martin-cano/)
