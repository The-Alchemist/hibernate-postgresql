CREATE TABLE CircleTypeEntity (
    id serial not null,
    my_circle circle,
    primary key (id)
);

insert into CircleTypeEntity (id, my_circle) VALUES (37, '( (1, 1), 37 )')
