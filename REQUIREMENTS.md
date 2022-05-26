## Requerimientos de la prueba

Desarrollar, utilizando Spring Boot 2 y Java 11, una API que permita hacer un mantenimiento de súper
héroes.

##### Este mantenimiento debe permitir:
- Consultar todos los súper héroes.
- Consultar un único súper héroe por id.
- Consultar todos los súper héroes que contienen, en su nombre, el valor de un parámetro
  enviado en la petición. Por ejemplo, si enviamos “man” devolverá “Spiderman”, “Superman”,
  “Manolito el fuerte”, etc.
- Modificar un súper héroe.
- Eliminar un súper héroe.
- Test unitarios de algún servicio.

##### Puntos a tener en cuenta:
- Los súper héroes se deben guardar en una base de datos H2 en memoria.
- La prueba se debe presentar en un repositorio de Git. No hace falta que esté publicado. Se
  puede pasar comprimido en un único archivo.

##### Puntos opcionales de mejora:
- Utilizar alguna librería que facilite el mantenimiento de los scripts DDL de base de datos.
- Implementar una anotación personalizada que sirva para medir cuánto tarda en ejecutarse.
  una petición. Se podría anotar alguno o todos los métodos de la API con esa anotación.
  Funcionaría de forma similar al @Timed de Spring, pero imprimiendo la duración en un log.
- Gestión centralizada de excepciones.
- Test de integración.
- Presentar la aplicación dockerizada.
- Poder cachear peticiones.
- Documentación de la API.
- Seguridad del API.

##### Se valorará positivamente:
- El uso de TDD. En caso de subir la práctica a un repositorio, se pueden utilizar los commits
  para ver el proceso.
- Aplicación de los principios SOLID.
