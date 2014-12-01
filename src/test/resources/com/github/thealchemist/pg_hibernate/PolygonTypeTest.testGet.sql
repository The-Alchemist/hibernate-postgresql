CREATE TABLE PolygonTypeEntity (
    id serial not null,
    my_polygon Polygon,
    primary key (id)
);

insert into PolygonTypeEntity (id, my_polygon) VALUES (37, '( (0,0), (0, 1), (1,1), (1, 0) )')
