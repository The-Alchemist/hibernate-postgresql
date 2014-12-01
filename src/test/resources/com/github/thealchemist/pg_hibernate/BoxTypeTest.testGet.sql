CREATE TABLE BoxTypeEntity (
    id serial not null,
    my_box box,
    primary key (id)
);

insert into BoxTypeEntity (id, my_box) VALUES (37, '(1, 1), (5,5)');