
# Comentarios

+ El modelo de datos es sencillo, no recoge toda la información fidedignamente
+ Se usan unos simples mapas en memoria para la persistencia (por sencillez)

# TODO, mejoras
+ Añadir Seguridad, of course
+ Mas Tests
+ Usar Cucumber o Karate para hacer los test de integración
+ Logging
+ Implementar o mejorar el modelo, eg: no se persiste la información relativa a las transferencias (ver clase Transfer)
+ Mejorar formatos json, eg: fechas

# Arrancar app

```bash
gradle runShadow

# alternativa
gradle run
```

Se podra consultar la specificación de openapi en http://localhost:8080/swagger-ui/index.html
