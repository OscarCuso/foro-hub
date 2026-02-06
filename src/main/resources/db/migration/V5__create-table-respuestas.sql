create table respuestas(

    id bigint not null auto_increment,
    mensaje varchar(100) not null,
    topico_id bigint not null,
    fecha_creacion datetime not null,
    autor_id bigint not null,
    solucion tinyint not null,

    primary key(id),

    FOREIGN KEY (topico_id)
    REFERENCES topicos(id),

    FOREIGN KEY (autor_id)
    REFERENCES usuarios(id)
);