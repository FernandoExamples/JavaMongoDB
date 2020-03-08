## MongoDBDemo 

Esta es una demo con un ejemplo sencillo de una conexion a la base de datos MongoDB.
Esta hecha en Maven. 
La estructura consiste en Problemas que tienen un titulo, una descripcion y el id de la categoria a la que pertenecen. 
- Cada problema se almacena en una determinada categoria. 
- Las categorias solo tienen un nombre

Para más informaciin acerca del uso de mongoDB en Java click [aqui](https://docs.mongodb.com/manual/crud/ "MongoDB CRUD Operations")

Para mas informacion acerca del uso del update en Java Mongo click [aqui](https://www.mongodb.com/blog/post/quick-start-java-and-mongodb--update-operations "Quick Start: Java and MongoDB - Update Operations")

Es importante resaltar la parte de los import static
- import static com.mongodb.client.model.Filters.and;
- import static com.mongodb.client.model.Filters.eq;
- import static com.mongodb.client.model.Updates.*;

Los cuales permiten utilizar de forma sencilla los metodos mas comunes para formar querys. 
