CREATE TABLE InetAddressEntity (
    id serial not null,
    address inet,
    primary key (id)
);

insert into InetAddressEntity (id, address) VALUES (37, inet '2001:db8::1')
