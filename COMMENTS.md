# Que es esto

Esto es un documento en el que yo expongo mis pensamientos y decisiones sobre el reto a resolver.
No todos los puntos han de ser importantes o implementados finalmente.

# Primeras decisiones

Como primeros puntos sobre el projecto a realizar:

- Voy a utilizar TDD para el proyecto, aunque solamente los primeros commits voy a mostrar los stages red, green y
  refactor. Después de esto hare commits a medida que vaya implementando funcionalidades a la API.
- Obviamente, voy a seguir los requerimientos de Spring 2.7, Java 11 y usar H2 como base de datos.
- Para documentar la API del proyecto voy a usar swagger, ya que es la herramienta con la que estoy más familiarizado.
- No sé muy bien como hacer la integración de Spring Security para la seguridad de la API sin un sistema de login o
  autentificación, poor lo tanto no se si implementare este punto finalmente.
- Ya que no se me da una estructura que ha de tener el unico modelo requerido en la API, que es el superheroe, va a ser
  lo más simple posible incluyendo unicamente el ID y el nombre del superheroe.
- Ya que se busca que sea una API extensible y con una buena mantenibilidad voy a crear una clase padre de la cual
  herede el superheroe, ya que si en un futuro se quiere incluir super villanos, ayudantes de super heroes, etc. va a
  ser lo más simple posible, seguimos los principios SOLID de extensibilidad y eliminamos el boilerplate.

# Comenzando con el TDD

Voy a crear primero los test para los controladores de nuestra API, ya que deberían ser los puntos de entrada.
Para los paths voy a usar el path api/ seguido del nombre del controlador, ya que no se especifica nada.