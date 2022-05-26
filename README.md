# SuperHeroesSpringAPI

### Que es esto

Esto es un documento en el que yo expongo mis pensamientos y decisiones sobre el reto a resolver.
No todos los puntos han de ser importantes o implementados finalmente.

### Primeras decisiones

Como primeros puntos sobre el projecto a realizar:

- Voy a utilizar TDD para el proyecto, aunque solamente los primeros commits voy a mostrar los stages red, green y
  refactor. Después de esto hare commits a medida que vaya implementando funcionalidades a la API.
- Obviamente, voy a seguir los requerimientos de Spring 2.7, Java 11 y usar H2 como base de datos.
- Para documentar la API del proyecto voy a usar swagger, ya que es la herramienta con la que estoy más familiarizado.
- No sé muy bien como hacer la integración de Spring Security para la seguridad de la API sin un sistema de login o
  autentificación, por lo tanto no sé si implementare este punto finalmente.
- Ya que no se me da una estructura que ha de tener el unico modelo requerido en la API, que es el superheroe, va a ser
  lo más simple posible incluyendo únicamente el ID y el nombre del superheroe.

### Comenzando con el TDD

Voy a crear primero los test para los controladores de nuestra API, ya que deberían ser los puntos de entrada.
Para los paths voy a usar el path api/superheroes , puesto que no se especifica nada.

### Seguridad
Como he comentado antes no se como usar Spring Security sin un sistema de autenticacion, lo cual me llevaria un tiempo y no creo que sea lo mas importante para esta prueba tecnica

### Cache
Se ha implementado que todas las peticiones puedan ser almacenadas en cache ya sea por id, por el nombre o simplemente para oobtener todos los super heroes

### Documentacion
La aplicacion esta totalmente documentada usando la herramienta swagger.
Pueden acceder a ella a traves del path <url-proyecto>/swagger-ui/index.html

### Anotacion de tiempo
Se ha añadido la anotacion que te muestra un loog con el tiempo que tarda un metodo en ejecutarse.
La anotacion es @ExecutionTime

### Docker y make
La aplicacion esta dockerizada y para que sea mas facil de usar he incluido un archivo make para una mejor integracion con todos los comandos de Docker.
Estan todos en el archivo Makefile

### Sonar
También he incluido el uso de sonar para mejorar notablemente algun posible problema.
Para usarlo hay que usar el comando
```bash
docker-compose up -d
```

en la carpeta sonarqube. Esto levantara un contenedor de sonar en local.
Y para subir nuestro codigo a sonar bastara con hace un 

```bash
mvn clean install sonar:sonar
```

Tambien pueden utilizar el comando de make 

```bash
make sonar
```