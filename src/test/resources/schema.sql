DROP TABLE IF EXISTS PointTypeEntity;
DROP TABLE IF EXISTS LineSegmentTypeEntity;
DROP TABLE IF EXISTS CircleTypeEntity;
DROP TABLE IF EXISTS RectangleTypeEntity;
DROP TABLE IF EXISTS PolygonTypeEntity;
DROP TABLE IF EXISTS Inet4AddressTypeEntity;
DROP TABLE IF EXISTS NumericTypeEntity;
DROP TABLE IF EXISTS StringArrayTypeEntity;
drop sequence IF EXISTS hibernate_sequence;


create sequence hibernate_sequence;
create extension if not exists hstore;