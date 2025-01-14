# Gestor de Finanzas Personales

Una aplicación web robusta desarrollada con Spring Boot para gestionar finanzas personales, permitiendo el registro y seguimiento de transacciones financieras.

## Características Principales

- **Gestión de Transacciones**
  - Registro de ingresos y gastos
  - Validación de saldo disponible para retiros
  - Historial de transacciones paginado
  - Descripción detallada para cada movimiento

- **Persistencia de Datos**
  - Integración con PostgreSQL vía Supabase
  - Caché con Redis para optimizar consultas frecuentes
  - Manejo de transacciones con JPA/Hibernate

- **Interfaz de Usuario**
  - Vista de saldo actual
  - Historial de movimientos paginado
  - Formulario para registro de transacciones
  - Manejo de errores con páginas personalizadas

## Tecnologías Utilizadas

- **Backend**
  - Java 17+
  - Spring Boot
  - Spring Data JPA
  - Spring Cache
  - Lombok

- **Base de Datos**
  - PostgreSQL (Supabase)
  - Redis (Caché)

- **Frontend**
  - Thymeleaf
  - HTML/CSS

## Requisitos Previos

- JDK 17 o superior
- Maven
- Redis Server
- Credenciales de Supabase

## Configuración

1. **Clonar el repositorio**
   ```bash
   git clone <URL-del-repositorio>
   cd <nombre-del-directorio>
   ```

2. **Configurar credenciales de Supabase**
   - Crear archivo `src/main/resources/supabase.properties`
   - Agregar las siguientes propiedades:
     ```properties
     SB_URL=<tu-url-supabase>
     SB_USER=<tu-usuario>
     SB_PASS=<tu-contraseña>
     ```

3. **Configurar Redis**
   - Asegurarse de que Redis está corriendo en el puerto por defecto (6379)

## Ejecución

1. **Compilar el proyecto**
   ```bash
   mvn clean install
   ```

2. **Ejecutar la aplicación**
   ```bash
   mvn spring-boot:run
   ```
   O ejecutar `RevenuemanagerApplication.java` desde tu IDE

La aplicación estará disponible en `http://localhost:8080`

## Estructura del Proyecto

- `model/`: Entidades y lógica de negocio
- `controller/`: Controladores web
- `repository/`: Interfaces de acceso a datos
- `service/`: Servicios de negocio
- `config/`: Configuraciones de Spring
- `exception/`: Manejo de excepciones personalizado

## Contribuciones

Las contribuciones son bienvenidas. Por favor, asegúrate de:

1. Hacer fork del repositorio
2. Crear una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit de tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abrir un Pull Request