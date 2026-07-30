# Sistema POS Farmacia — reglas del proyecto (migración a Hexagonal / Java)

Fuentes de verdad funcional:
- `base/reglas_de_negocio.md` — reglas de negocio del POS farmacia.
- `base/Sistema_POS_Farmacia_Arquitectura_Hexagonal_Java_Spring.docx` — arquitectura objetivo, requerimientos funcionales (RF01–RF19), reglas de negocio (RN01–RN44), API mínima y plan de migración.

Ante cualquier duda de requerimiento, regla de negocio o entidad, releer esos documentos antes de asumir.

Este proyecto es la migración de `arquitectura_2_t2` (Arquitectura Onion, .NET 10, EF Core) hacia Arquitectura Hexagonal (Ports & Adapters) con Java + Spring Boot. No es un proyecto nuevo: cada entidad, regla y endpoint de `arquitectura_2_t2` debe tener un equivalente aquí, salvo que se documente explícitamente lo contrario.

## Rol

Actúa como ingeniero de software senior. Código correcto, simple y que respete SOLID. Cero sobre-ingeniería, cero abstracciones especulativas.

## Skills activos siempre

En cada tarea de este proyecto, mantener activos:
- `/codebase-memory` — usar los tools del knowledge graph (search_graph, trace_path, get_code_snippet, query_graph, get_architecture, search_code) para explorar código antes de Grep/Read cuando aplique.
- `/gstack`
- `/ponytail:ponytail` (nivel full) — la solución más simple que funcione; nada de boilerplate ni flexibilidad no pedida.

## Arquitectura: Hexagonal obligatoria (Ports & Adapters)

Proyecto Maven multimódulo en `backend/`:

```
backend/
├── pom.xml                          # parent reactor
├── pos-domain/                      # entidades, VOs, enums, excepciones, servicios/políticas de dominio
├── pos-application/                 # casos de uso, DTOs, commands, queries, puertos de entrada y de salida
├── pos-adapter-in-rest/             # Controllers Spring MVC, request/response, seguridad HTTP
├── pos-adapter-out-persistence/     # entidades JPA, repos Spring Data, mappers, migraciones Flyway
├── pos-adapter-out-external/        # integraciones: central, seguros, pagos, comprobante electrónico, impresión
└── pos-bootstrap/                   # Spring Boot app, @Configuration/@Bean, wiring por inyección de constructor
```

Regla de dependencias (innegociable, ver sección 6-7 del Word):

```
ACTORES EXTERNOS
   -> [ADAPTADORES DE ENTRADA] -> [PUERTOS DE ENTRADA]
                                        -> [CASOS DE USO + DOMINIO]
                                        <- [PUERTOS DE SALIDA]
   <- [ADAPTADORES DE SALIDA: JPA, SQL Server/Postgres, central, pagos, facturación]
```

- `pos-domain` no depende de nada: sin Spring, sin JPA/Hibernate, sin HTTP/JSON, sin Angular/React.
- `pos-application` solo depende de `pos-domain` y de las interfaces de los puertos (in/out) que ella misma define. Nunca importa entidades JPA, `JpaRepository`, `WebClient` ni controladores.
- Los adaptadores de entrada (`pos-adapter-in-rest`) invocan puertos de entrada (casos de uso); no acceden a JPA ni calculan promociones, FEFO, copago o validación de recetas.
- Los adaptadores de salida (`pos-adapter-out-persistence`, `pos-adapter-out-external`) implementan los puertos de salida definidos en `pos-application`/`pos-domain`; sus entidades `@Entity` se mapean a modelos de dominio y nunca se usan como el único modelo de negocio.
- `pos-bootstrap` es el único módulo que conoce todas las implementaciones concretas: allí viven los `@Configuration`/`@Bean` que conectan casos de uso con adaptadores. Los casos de uso nunca crean adaptadores con `new`.
- Toda validación de stock/negocio se reconfirma en el servidor al confirmar la venta (RN04), aunque ya se haya validado en pantalla.
- El núcleo (`pos-domain` + `pos-application`) debe poder probarse sin iniciar Spring, sin servidor web y sin base de datos.

## Reglas de negocio clave (detalle completo: RN01–RN44 en el Word, sección 5)

- Caja debe estar abierta antes de vender (RN01).
- Stock disponible se revalida en servidor antes de confirmar (RN02, RN04).
- Una promoción como máximo por línea de venta; una promoción no se repite en el mismo comprobante (RN07, RN09).
- Medicamento controlado exige receta válida y aprobada antes de dispensar (RN14).
- Receta normal no vence; receta especial tiene vigencia y compra presencial; receta especial retenida se usa una sola vez y queda retenida tras la venta (RN16–RN20).
- Copago se calcula solo si el convenio está activo y vigente; validar cobertura por línea/producto (RN22–RN27).
- Compra a crédito exige DNI, línea activa y no puede superar el saldo disponible (RN28–RN32).
- Despacho de lotes por FEFO (vencimiento más cercano primero); nunca vender lotes vencidos ni dentro del periodo preventivo de 3 meses (RN33–RN38).
- Venta facturada nunca se borra físicamente; anulación solo el mismo día, después se emite nota de crédito (RN39–RN44).
- Venta, pago, comprobante y descuento de stock se confirman como una sola operación atómica (RN06).
- Operaciones sensibles (anulaciones, notas de crédito, cambios de precio, ajustes de stock, validación de recetas, cambios de promoción) quedan auditadas (RF19).

## Stack técnico

- Backend: **Java 17** + **Spring Boot 4.1.0**, gestionado por su BOM (`spring-boot-starter-parent`). Coincide con el stack objetivo del Word (Spring Boot 4.1.0); se usa Java 17 en vez de Java 25 porque es la única JDK instalada en este entorno y Spring Boot 4.1.0 la soporta (verificado contra Spring Initializr).
- Build: Maven (Maven Wrapper `mvnw`, no requiere instalación previa).
- Frontend: **React 19 + Vite** (se conserva el de `arquitectura_2_t2`; el Word sugiere Angular, pero no se reescribe el frontend en esta migración — decisión registrada, ver PPTX).
- API REST: Spring MVC + `@RestController`. **Las rutas deben coincidir con las que ya consume `frontend/src/pages/*.jsx` y con los controllers reales de `arquitectura_2_t2` (`/api/...` sin versionar), no solo con el listado mínimo del Word (sección 10), que declara explícitamente "como mínimo".** El Word describe un subconjunto; el contrato real a no romper es el que ya usa el frontend (igual que exige la sección 8.4 del Word sobre no romper el frontend existente, aplicado aquí a React en vez de Angular).
- Persistencia: Spring Data JPA + Hibernate. Motor: **PostgreSQL 16** (el mismo que ya usa `arquitectura_2_t2`, no SQL Server como sugiere el Word) para reducir el riesgo del corte y no migrar lenguaje y motor de BD a la vez.
- Migraciones de esquema: Flyway.
- Auth: Spring Security + JWT, control de acceso por rol.
- Validación de formato de entrada: Jakarta Validation. Las decisiones de negocio (promociones, FEFO, recetas, copago, crédito) nunca se validan solo con anotaciones, viven en `pos-domain`.
- Documentación API: springdoc-openapi (OpenAPI 3 + Swagger UI).
- Tests: JUnit 5 + Mockito (dominio/aplicación, sin BD ni Spring) + Testcontainers (integración) + ArchUnit (reglas de dependencia entre módulos).
- Logging técnico: SLF4J + Logback.
- Contenedores: Docker + docker-compose (backend, frontend, BD, mocks de servicios externos si aplica).
- Secretos y cadenas de conexión: solo por variables de entorno, nunca hardcodeados en el repo.

## Equivalencias de migración desde `arquitectura_2_t2` (.NET)

| .NET (Onion, t2) | Java (Hexagonal, t3) |
|---|---|
| `PosFarmacia.Presentation` (Controllers) | `pos-adapter-in-rest` (`@RestController`) |
| `PosFarmacia.Application` (UseCases, Commands, Queries, DTOs, Ports) | `pos-application` (casos de uso, puertos in/out, DTOs) |
| `PosFarmacia.Domain` (Entities, ValueObjects, Services) | `pos-domain` (entidades, VOs, servicios/políticas de dominio) |
| `PosFarmacia.Infrastructure` (EF Core, Repositories, CentralClients, Insurance, ElectronicBilling) | `pos-adapter-out-persistence` + `pos-adapter-out-external` |
| Inyección de dependencias .NET | Inyección por constructor de Spring, cableada en `pos-bootstrap` |
| Entity Framework Core | Spring Data JPA + Hibernate |
| Data Annotations / FluentValidation | Jakarta Validation (formato) + reglas de dominio (negocio) |
| Middleware de autenticación | Cadena de filtros de Spring Security |
| `appsettings.json` | `application.yml` + perfiles + variables de entorno |
| Serilog | SLF4J + Logback |
| xUnit + Moq | JUnit 5 + Mockito |
| Swagger de ASP.NET | springdoc-openapi |

No migrar copiando la estructura interna .NET línea por línea: primero identificar la regla/caso de uso/contrato en `arquitectura_2_t2`, luego expresarlo en Java desacoplado de Spring/JPA.

## Docker obligatorio

- `backend/` y `frontend/` son proyectos independientes, cada uno con su propio `Dockerfile`.
- Ambos se levantan junto con la base de datos vía `docker-compose.yml` en la raíz (`docker compose up -d`). Nunca asumir que el backend o frontend corren directo en el host como flujo principal.
- Cualquier servicio nuevo (mocks de seguros, central, pagos, etc.) se agrega como servicio adicional en `docker-compose.yml`, no como proceso paralelo fuera de Docker.
- Secretos y cadenas de conexión van por variables de entorno (`.env`, ignorado por git) usando `.env.example` como plantilla. Nunca hardcodear credenciales en `docker-compose.yml`, `application.yml` ni en el código.

## Git

- Nunca agregar `Co-Authored-By: Claude` (ni ninguna variante) en los mensajes de commit de este repositorio. Los commits van solo con la autoría del usuario.

## Límites de código

- Ningún archivo debe superar **1000 líneas**. Si una clase se acerca al límite, dividir por responsabilidad (SRP) en vez de seguir agregando.
- Preferir métodos y clases pequeños con una sola razón para cambiar.
- Aplicar los 5 principios SOLID de forma consciente, especialmente inversión de dependencias (puertos en `pos-domain`/`pos-application`, implementaciones en adaptadores) y segregación de interfaces (puertos pequeños por caso de uso, no interfaces "todo en uno").

## Restricciones explícitas (del Word, sección 16)

No está permitido:
- Lógica de negocio en controladores o endpoints.
- Acceso directo a la base de datos desde un adaptador de entrada.
- Usar entidades JPA como único modelo de dominio, ni anotarlas con `@Entity` dentro de `pos-domain`.
- Que `pos-domain`/`pos-application` dependan de Spring Boot, Spring MVC, Spring Security o JPA.
- Adaptadores concretos usados dentro de los casos de uso (nada de `new` sobre implementaciones).
- Definir puertos únicamente como nombres de paquete sin interfaces reales.
- Calcular promociones, recetas, copagos, crédito o FEFO dentro de controladores.
- Migrar el código .NET copiando sus dependencias técnicas al núcleo Java.
- Modificar simultáneamente lenguaje, base de datos y frontend sin un plan gradual (ver estrategia de migración, sección 8.3 del Word).
- Entregar código sin pruebas.
- Credenciales o secretos en el repositorio.

## Pruebas mínimas exigidas (Word, sección 11)

- 15 pruebas unitarias de dominio listadas en 11.1 (caja cerrada, pago insuficiente, una promoción por línea, receta controlada, FEFO, anulación, etc.).
- Pruebas de integración de venta completa (venta + pago + comprobante + stock) y de concurrencia sobre el mismo lote.
- Pruebas de contrato/API con MockMvc, y pruebas de arquitectura con ArchUnit verificando que el dominio no dependa de Spring/JPA.
