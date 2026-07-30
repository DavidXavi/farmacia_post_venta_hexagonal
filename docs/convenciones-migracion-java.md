# Convenciones para migrar cada contexto (.NET Onion -> Java Hexagonal)

Documento de trabajo para quien implemente cada bounded context del backend. Léelo completo antes de escribir código. Ante cualquier duda de regla de negocio, revisar `base/reglas_de_negocio.md` y el texto extraído de `base/Sistema_POS_Farmacia_Arquitectura_Hexagonal_Java_Spring.docx`.

## Paquete base y módulos

`groupId` = `com.posfarmacia`. Módulos Maven en `backend/` (ya existen con sus `pom.xml`, no los edites salvo para añadir una dependencia que tu propio contexto necesite):

| Módulo | Paquete raíz | Contenido |
|---|---|---|
| `pos-domain` | `com.posfarmacia.domain` | `model` (entidades/agregados, sin anotaciones de framework), `valueobject`, `enums`, `exception`, `service` (servicios/políticas de dominio: funciones puras, sin I/O) |
| `pos-application` | `com.posfarmacia.application` | `port.in` (interfaces de casos de uso), `port.out` (interfaces hacia repos/servicios externos), `usecase` (implementaciones), `dto` |
| `pos-adapter-in-rest` | `com.posfarmacia.adapter.in.rest` | `controller`, `request`, `response`, `security` |
| `pos-adapter-out-persistence` | `com.posfarmacia.adapter.out.persistence` | `entity` (JPA, `@Entity`), `repository` (Spring Data `JpaRepository`), `mapper` (dominio <-> JPA), migraciones Flyway en `src/main/resources/db/migration` |
| `pos-adapter-out-external` | `com.posfarmacia.adapter.out.external` | integraciones externas (central, seguros, pagos, comprobante, impresión), reloj del sistema |
| `pos-bootstrap` | `com.posfarmacia.bootstrap` | ya tiene la clase principal Spring Boot y `application.yml`; normalmente no necesitas tocarlo |

## Ya existe en el kernel compartido (no lo dupliques)

- `com.posfarmacia.domain.model.Entidad`: clase base de agregados, expone `UUID getId()`.
- `com.posfarmacia.domain.valueobject`: `Cantidad`, `Dinero`, `Dni`, `Porcentaje`, `CodigoBarras`, `CodigoLote`, `CodigoProducto`, `NumeroReceta`, `CodigoAutorizacionSeguro`, `FechaVencimiento`, `NumeroComprobante`, `PeriodoVigencia`.
- `com.posfarmacia.domain.exception`: `DomainException` (abstracta) y subclases `ValorInvalidoException`, `EntidadNoEncontradaException`, `CajaCerradaException`, `StockInsuficienteException`, `PagoInsuficienteException`, `VentaYaConfirmadaException`, `PromocionInvalidaException`, `RecetaInvalidaException`, `RecetaYaUtilizadaException`, `ConvenioNoDisponibleException`, `LineaCreditoInvalidaException`, `AnulacionNoPermitidaException`, `DevolucionInvalidaException`.
- `com.posfarmacia.domain.enums`: `RolNombre`, `EstadoCuenta`, `EstadoCaja`, `TipoProducto`, `EstadoProducto`, `TipoReceta`, `EstadoReceta`, `EstadoLote`, `EstadoVenta`, `TipoMovimientoStock`, `TipoComprobante`, `EstadoAfiliacion`, `EstadoLineaCredito`, `TipoFormaPago`, `TipoMovimientoCredito`, `PermisoEspecial` (úsalo como `EnumSet<PermisoEspecial>`, no bitmask), `TipoBeneficioPromocion`.
- `com.posfarmacia.application.port.out.ClockPort`: puerto para `hoy()`/`ahora()`. Implementado por `SystemClockAdapter` en `pos-adapter-out-external`. Los casos de uso obtienen la fecha/hora de aquí, nunca de `LocalDate.now()` directo (para que el dominio sea comprobable con fechas fijas en tests).

Si tu contexto necesita un Value Object o excepción nuevo que no está en la lista, créalo dentro de tu propio paquete de contexto (ver abajo), no en el kernel compartido, para evitar colisiones con otros contextos que se están implementando en paralelo.

## Regla de oro: agregados se referencian por UUID, nunca por objeto

Igual que en `arquitectura_2_t2` (revisa `PosFarmacia.Domain/Entities/Venta.cs`: `CajaId`, `ClienteId`, `ProductoId` son todos `Guid`, no referencias de objeto). Cada entidad de dominio extiende `Entidad` y expone su propio `UUID`. Cuando una entidad de tu contexto necesite "apuntar" a una entidad de OTRO contexto (ej. una `Venta` apunta a un `Cliente`), guarda solo el `UUID` (campo `clienteId`), nunca importes la clase del otro contexto. Esto permite que cada contexto se desarrolle y compile de forma independiente.

## Convención de sub-paquete por contexto

Dentro de cada capa, organiza tus clases en un sub-paquete con el nombre de tu contexto para evitar choques de nombres de archivo con otros contextos que trabajan en paralelo, ejemplo para el contexto de Promociones:

- `com.posfarmacia.domain.model.promocion.Promocion`
- `com.posfarmacia.domain.service.promocion.EvaluadorPromociones`
- `com.posfarmacia.application.port.in.promocion.EvaluarPromocionesUseCase`
- `com.posfarmacia.application.port.out.promocion.PromocionRepositoryPort`
- `com.posfarmacia.application.usecase.promocion.EvaluarPromocionesUseCaseImpl`
- `com.posfarmacia.adapter.in.rest.controller.promocion.PromocionesController`
- `com.posfarmacia.adapter.out.persistence.entity.promocion.PromocionJpaEntity`
- `com.posfarmacia.adapter.out.persistence.repository.promocion.PromocionJpaRepository`
- `com.posfarmacia.adapter.out.persistence.mapper.promocion.PromocionMapper`

## Migraciones Flyway: numeración asignada (no reutilizar números de otro contexto)

Archivo en `pos-adapter-out-persistence/src/main/resources/db/migration/`, nombre `V<n>__<descripcion>.sql`:

- V1: Identidad, roles, cajas, sesiones de caja, auditoría (`usuarios`, `roles`, `usuarios_roles`, `locales`, `cajas`, `sesiones_caja`, `auditoria_operaciones`).
- V2: Catálogo e inventario (`categorias`, `laboratorios`, `presentaciones`, `productos`, `lotes`, `existencias_lote`, `movimientos_inventario`).
- V3: Promociones (`promociones`, `promocion_condiciones`).
- V4: Recetas (`recetas`, `usos_receta`).
- V5: Clientes, seguros y crédito (`clientes`, `convenios_seguro`, `coberturas_seguro`, `lineas_credito`, `movimientos_credito`).
- V6: Ventas y pagos (`ventas`, `detalles_venta`, `detalle_venta_lotes`, `pagos`, `comprobantes`).
- V7: Anulaciones y notas de crédito (`notas_credito`, y columnas/tablas de devolución necesarias).
- V8: Reportes e incentivos (`incentivos_venta`).

Cada migración solo crea las tablas de su propio número. Usa `UUID` (tipo `uuid` de Postgres, `gen_random_uuid()` si necesitas default, o generado en aplicación) como PK, y columnas `*_id uuid` para las referencias a otros contextos (FK opcional entre contextos: si la tabla referenciada aún no existe en tu migración, NO agregues `REFERENCES`, deja la columna simple; la integridad referencial fuerte entre contextos es responsabilidad de la aplicación, no de la BD, para no bloquear el desarrollo paralelo).

## Reglas de hexagonal (resumen, ver `CLAUDE.md` en la raíz del repo para el detalle completo)

- `pos-domain`: cero imports de Spring/JPA/HTTP. Los servicios de dominio (`domain.service.*`) son funciones puras: reciben todos los datos que necesitan como parámetros (incluida la fecha "hoy" si la necesitan) y no llaman a ningún puerto ellos mismos.
- `pos-application`: casos de uso implementan las interfaces de `port.in`, dependen solo de `port.out` (interfaces) y de `pos-domain`. Nunca hacen `new` sobre una implementación concreta de adaptador.
- Adaptadores de entrada (`pos-adapter-in-rest`) no calculan reglas de negocio; solo mapean HTTP <-> casos de uso.
- Adaptadores de salida (`pos-adapter-out-persistence`, `pos-adapter-out-external`) implementan los `port.out` de tu contexto.
- Transacciones: anota `@Transactional` (de `org.springframework.transaction.annotation.Transactional`) directamente en la clase/método de caso de uso dentro de `pos-application` (es una concesión pragmática documentada; `pos-application` sí puede depender de `spring-tx`, ya está en su `pom.xml`).

## Pruebas mínimas por contexto

- Unitarias de dominio (JUnit 5 + AssertJ, sin Spring) para cada regla de negocio (RN) que implementes. Ver la lista de 15 pruebas mínimas en el Word sección 11.1 — implementa las que correspondan a tu contexto.
- No necesitas levantar Spring ni base de datos para probar `domain.service.*` ni los casos de uso con mocks de los puertos (Mockito).

## Corrección importante sobre rutas REST (léelo si ya implementaste controllers)

El frontend React (`frontend/src/pages/*.jsx`, ya copiado a este repo) llama endpoints **sin versionar** (`/api/...`, no `/api/v1/...`) y con una superficie más amplia que el listado "mínimo" del Word sección 10 (ej. CRUD completo de promociones, catálogos, usuarios/roles, convenios como recurso propio, etc.). El Word mismo aclara que su lista es "como mínimo". Como decidimos mantener el frontend sin reescribir, el contrato real a preservar es el de `arquitectura_2_t2` (controllers .NET reales en `PosFarmacia.Presentation/Controllers/*.cs` y lo que llama cada página en `frontend/src/pages/*.jsx`), no solo el Word. Si ya implementaste tus controllers siguiendo únicamente el Word, revisa el controller .NET equivalente y la(s) página(s) del frontend que consumen tu contexto, y ajusta rutas/verbos para que coincidan exactamente (mismo path, sin prefijo `/v1`). Puedes mantener además las rutas del Word como alias si no cuesta nada extra, pero las que debe atender primero son las que el frontend ya llama.

## Qué NO hacer

- No toques archivos fuera de tu contexto/sub-paquete, ni los `pom.xml` de otros módulos.
- No agregues el número de migración Flyway de otro contexto.
- No repliques anotaciones `@Entity`/`@Table` en `pos-domain`.
- No captures excepciones de framework (JPA, HTTP) dentro de `pos-domain`/`pos-application`; deja que las excepciones de dominio (`com.posfarmacia.domain.exception.*`) se propaguen y que el adaptador de entrada las traduzca a códigos HTTP (400/404/409/422 según el Word sección 10).
