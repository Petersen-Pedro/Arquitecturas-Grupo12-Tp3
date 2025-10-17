# TP3 - Spring

Resolución de TP3 utilizando Spring y testeando la invocación de los servicios REST mediante Postman

## Referencia API

#### A) Dar de alta un estudiante

```http
  POST /estudiantes/alta
```
| Parameter       | Type     | Description       |
|:----------------|:---------|:------------------|
| `nro_documento` | `String` | **Required**. DNI |
| `nombres`       | `String` | **Required**. Nombre |
| `apellido`      | `String` | **Required**. Apellido |
| `edad`          | `int`    | **Required**. Edad |
| `genero`        | `String` | **Required**. Genero |
| `ciudad_residencia` | `String` | **Required**. Ciudad de residencia |
| `nro_libreta_universitaria` | `String` | **Required**. Numero de libreta universitaria |

```bash
curl --location 'http://localhost:8080/estudiantes/alta' \
--header 'Content-Type: application/json' \
--data '{
    "nro_documento": "44416819",
    "nombres": "Belen",
    "apellido": "Andreozzi",
    "edad": 23,
    "genero": "Female",
    "ciudad_residencia": "Tandil",
    "nro_libreta_universitaria": "44416819"
}'
```
#### B) Matricular un estudiante en una carrera

```http
  POST /inscripciones
```

| Parameter       | Type      | Description                      |
|:----------------|:----------|:---------------------------------|
| `nro_documento` | `String`  | **Required**. DNI del estudiante |
| `id_carrera`    | `Integer` | **Required**. Id de la carrera   |

```bash
curl --location 'http://localhost:8080/inscripciones' \
--header 'Content-Type: application/json' \
--data '{
    "nro_documento": "30111285",
    "id_carrera": 15
}'
```

#### C) Recuperar todos los estudiantes y especificar algún criterio de ordenamiento simple

```http
    GET /estudiantes?sort=apellido,asc
```

| Parameter   | Type      | Description                 |
|:------------|:----------|:----------------------------|
| `sort`      | `String`  | **Required**. ej `apellido` |
| `sort`      | `String`  | **Required**. ej `edad`     |

```bash
curl --location 'http://localhost:8080/estudiantes?sort=apellido%2Casc'
```

#### D) Recuperar un estudiante en base a su número de libreta universitaria

```http
    GET estudiantes/lu/{numero_libreta}
```

| Parameter        | Type     | Description             |
|:-----------------|:---------|:------------------------|
| `numero_libreta` | `String` | **Required**. ej: 51244 |

```bash
curl --location 'http://localhost:8080/estudiantes/lu/51244'
```
#### E) Recuperar todos los estudiantes, en base a su género.

```http
  GET estudiantes/genero/Female
```

| Parameter | Type     | Description          |
|:----------|:---------|:---------------------|
| `genero`  | `String` | **Required**. Genero |

```bash
curl --location 'http://localhost:8080/estudiantes/genero/Female'
```

#### F) Recuperar las cerreras con estudiantes inscriptos y ordenar por cantidad de inscriptos

```http 
  GET carreras/ordenadas-inscriptos
```
| Parameter | Type | Description |
|:----------|:-----|:------------|
| -         | -    | -           |

```bash
curl --location 'http://localhost:8080/carreras/ordenadas-inscriptos'
```


#### G) Recuperar todos los estudiantes de una determinada carrera, filtrado por ciudad

```http
   GET estudiantes/carrera/1/ciudad/Rauch
```

| Parameter              | Type      | Description                                               |
|:-----------------------|:----------|:----------------------------------------------------------|
| `nombreCarrera`        | `Integer` | **Required**. ID de la carrera                            |
| `ciudadResidencia`     | `String`  | **Required**. Filtra los estudiantes por nombre de ciudad |

```bash
curl --location 'http://localhost:8080/estudiantes/carrera/1/ciudad/Rauch'
```

#### H) Generar un reporte de las carreras

```http
       GET carreras/reporte
```

| Parameter | Type | Description |
|:----------|:-----|:------------|
| -         | -    | -           |

```bash
curl --location 'http://localhost:8080/carreras/reporte'
```

## Integrantes
- Andiarena Santiago
- Andreozzi Belén
- Ferraggine Gabriel
- Petersen Pedro
- Islas Pedro