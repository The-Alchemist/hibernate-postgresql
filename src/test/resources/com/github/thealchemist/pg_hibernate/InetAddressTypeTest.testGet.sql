CREATE TABLE InetAddressEntity (
    id serial not null,
    address inet,
    primary key (id)
);

insert into InetAddressEntity (id, address) VALUES (37, inet '192.168.1.137')
