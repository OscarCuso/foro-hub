create table topicos(

    id bigint not null auto_increment,
    titulo varchar(100) not null,
    mensaje varchar(100) not null unique,
    fecha_creacion datetime not null,
    status tinyint,
    autor varchar(100) not null,
    nombre_curso varchar(20) not null,

    primary key(id)

);