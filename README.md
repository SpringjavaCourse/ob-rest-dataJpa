
## Spring Boot

Proyecto Spring Boot con las siguientes dependencias / starters:

Starters para persistencia:
* H2
* Spring Data JPA

Starters para web:
* Spring Boot Dev tools
* Spring Web

Aplicación  API REST con acceso a base de datos H2 para persistir la información.

El acceso se puede realizar desde Postman o Navegador.

## Entidad Book

1. Book
2. BookRepository
3. BookController
   1. Buscar todos los libros
   2. Buscar un solo libro
   3. Crear un nuevo libro
   4. Actualizar un libro existente
   5. Borrar un libro
   6. Borrar todos los libros

## Documentación API

Se aplica los siguientes pasos para poder agregar la dependencia de swagger y asi documentar la api:
1. Se accede al siguiente [LINK](https://mvnrepository.com/search?q=springfox-boot-starter).
2. se ingresa **springfox-boot-starter**
3. Descargamos la dependencia. 
4. En el siguiente [LINK](https://springfox.github.io/springfox/docs/current/) detalla el paso a paso para la implementacion 