CREATE TABLE IntArrayEntity (
	id serial not null,
	integers int[],
	primary key (id)
);

insert into IntArrayEntity (id, integers) VALUES (37, '{10, 22, 37}')
