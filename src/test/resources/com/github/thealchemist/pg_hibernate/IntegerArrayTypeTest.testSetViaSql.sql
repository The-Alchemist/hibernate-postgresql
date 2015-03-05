CREATE TABLE IntegerArrayEntity (
	id serial not null,
	integers int[],
	primary key (id)
) ;

insert into IntegerArrayEntity (id, integers) VALUES (39, '{76, -5, 1001}')
