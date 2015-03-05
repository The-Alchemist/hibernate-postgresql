DROP TABLE IF EXISTS IntegerArrayEntity;

CREATE TABLE IntegerArrayEntity (
	id serial not null,
	integers int[],
	primary key (id)
);

insert into IntegerArrayEntity (id, integers) VALUES (37, '{10, 22, 37}')
