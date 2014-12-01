CREATE TABLE StringArrayEntity (
    id serial not null,
    strings text[],
    primary key (id)
);

insert into StringArrayEntity (id, strings) VALUES (37, '{"a", "b", "c"}')
