DROP TABLE IF EXISTS PointTypeEntity;
DROP TABLE IF EXISTS LineSegmentTypeEntity;
DROP TABLE IF EXISTS CircleTypeEntity;
DROP TABLE IF EXISTS RectangleTypeEntity;
DROP TABLE IF EXISTS PolygonTypeEntity;
DROP TABLE IF EXISTS Inet4AddressTypeEntity;
DROP TABLE IF EXISTS NumericTypeEntity;
DROP TABLE IF EXISTS StringArrayTypeEntity;
drop sequence IF EXISTS hibernate_sequence;

CREATE TABLE PointTypeEntity (
	id serial not null,
	point point,
	primary key (id)
) WITHOUT OIDS;

CREATE TABLE LineSegmentTypeEntity (
	id serial not null,
	line lseg,
	primary key (id)
) WITHOUT OIDS;

CREATE TABLE CircleTypeEntity (
	id serial not null,
	circle circle,
	primary key (id)
) WITHOUT OIDS;

CREATE TABLE RectangleTypeEntity (
	id serial not null,
	rect box,
	primary key (id)
) WITHOUT OIDS;

CREATE TABLE PolygonTypeEntity (
	id serial not null,
	my_polygon polygon,
	primary key (id)
) WITHOUT OIDS;

CREATE TABLE Inet4AddressTypeEntity (
	id serial not null,
	address inet,
	primary key (id)
) WITHOUT OIDS;

CREATE TABLE NumericTypeEntity (
	id serial not null,
	value numeric,
	primary key (id)
) WITHOUT OIDS;

CREATE TABLE StringArrayTypeEntity (
	id serial not null,
	strings varchar(50)[],
	primary key (id)
) WITHOUT OIDS;

CREATE TABLE HstoreTypeEntity (
    id serial not null,
    map hstore,
    primary key (id)
) WITHOUT OIDS;

create sequence hibernate_sequence;
