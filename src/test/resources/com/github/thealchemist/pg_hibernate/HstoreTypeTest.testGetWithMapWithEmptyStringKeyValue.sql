CREATE TABLE HstoreTypeEntity (
    id serial not null,
    map hstore,
    primary key (id)
);

insert into HstoreTypeEntity (id, map) VALUES (37, '"" => ""')
