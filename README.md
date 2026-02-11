# Foro Hub

Este es un proyecto interactivo en el que se puede realizar:
1) Registrar y eliminar Usuarios, a lo que genera un token de seguridad para la autenticacion
2) Registrar, actualizar, eliminar y listar cursos, como poder listarlos todos o solo por categoria 
4) Registrar, listar, actualizar y eliminar topicos, como también listarlos por fecha y nombre de curso 
5) Registrar, listar, actualizar y eliminar respuestas, como también maracarlas cuando están resueltas y cerradas

## Instalación

Para instalar el proyecto se necesita clonar el repositorio

```bash
  git clone https://github.com/OscarCuso/foro-hub
  cd foro-hub
```

## Uso del proyecto

Para iniciar el proyecto es necesario abrirlo desde un IDE, una vez abierto el proyecto lo podemos iniciar

Una vez iniciando el proyecto podemos hacer las pruebas o consultas en insomnia

Primero obtenemos una contraseña encriptada para mayor seguridad

![imagen1](img/imagen1.jpg)

Después hacemos el registro de usuario donde nos piden los campos obligatorios de nombre, correo electronico, contraseña y perfil(solo es un campo como de nombre de perfil)

![imagen2](img/imagen2.jpg)

Procedemos a iniciar sesion donde nos pedira el nombre y la contraseña y nos generara un token con el que podemos hacer las consultas

![imagen3](img/imagen3.jpg)

También podemos agregar los cursos donde ingresamos el nombre del curso y la categoria siendo solo 3 opciones(BACKEND, FRONTEND, DATABASE)
donde en cada consulta que hagamos tenemos que agregar el token generado en Auth, luego en Bearer Token y pegamos el token que nos dio cuando iniciamos sesion

![imagen4](img/imagen4.jpg)

![imagen5](img/imagen5.jpg)

También podemos listar los cursos ingresados, y también listarlos por categoria 

![imagen6](img/imagen6.jpg)

![imagen7](img/imagen7.jpg)

De igual manera podemos actualizar un curso ya sea como cambiar el nombre o la categoria, como también eliminarlo

![imagen8](img/imagen8.jpg)

![imagen9](img/imagen9.jpg)

En los topicos igual podemos registrar, listar, actualizar, ver detalle de un topico y eliminar 

En registrar tenemos que ingresar un título y un mensaje como también él, id del curso al cual hace referencia 

![imagen10](img/imagen10.jpg)

En listar nos mostrará los topicos creados, como también podemos listarlos por año de creacion y nombre de curso 

![imagen11](img/imagen11.jpg)

![imagen12](img/imagen12.jpg)

![imagen13](img/imagen13.jpg)

También podemos actualizar el topico en el título o mensaje o ambos seleccionando él, id del topico, como también mostar el detalle del topico y también eliminarlo

![imagen14](img/imagen14.jpg)

![imagen15](img/imagen15.jpg)

![imagen16](img/imagen16.jpg)

En las respuestas podemos registrarlas con él, id del topico al que hacemos referencia, y colocando el mensaje de la supuesta solucion

Donde la respuesta nos mostrara él, id de la respuesta el mensaje, la fecha y el autor, como los datos del topico como también los datos del curso

![imagen17](img/imagen17.jpg)

También podemos eliminar una respuesta 

![imagen18](img/imagen18.jpg)

También podemos cambiar la solucion del topico si es que le sirvio la respuesta, pasaria de abierto ha resuelto, el que puede hacer ese cambio es solo el autor del topico

Donde la url debemos poner él, id del topico él, id de la respuesta y solucion ejemplo: (http://localhost:8080/topicos/24/respuestas/7/solucion)

![imagen19](img/imagen19.jpg)

![imagen20](img/imagen20.jpg)

Si intentamos cerrar o cambiar el status con un usuario que no sea el que creo el topico no nos dejara 

![imagen21](img/imagen21.jpg)

Y también podemos cambiar el status del topico a cerrado para que ya no se puedan anadir más respuestas a ese topico 

Solo necesitamos iniciar sesion con el usuario del topico y en la url añadir el topico y cerrar, ejemplo: (http://localhost:8080/topicos/24/cerrar)

![imagen22](img/imagen22.jpg)

![imagen23](img/imagen23.jpg)

## Autores

- [@OscarCuso](https://github.com/OscarCuso)