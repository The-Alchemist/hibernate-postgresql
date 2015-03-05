CREATE TABLE IntArrayEntity (
	id serial not null,
	integers int[],
	primary key (id)
) WITHOUT OIDS;

insert into IntArrayEntity (id, integers) VALUES (39, '{76, -5, 1001}')
