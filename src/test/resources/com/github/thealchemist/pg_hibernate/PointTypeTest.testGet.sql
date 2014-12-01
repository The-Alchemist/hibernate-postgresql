CREATE TABLE PointTypeEntity (
    id serial not null,
    my_point Point,
    primary key (id)
);

insert into PointTypeEntity (id, my_Point) VALUES (101, '(38, 39.0)')
