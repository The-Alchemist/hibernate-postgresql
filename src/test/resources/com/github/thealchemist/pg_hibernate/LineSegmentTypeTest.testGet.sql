CREATE TABLE LineSegmentTypeEntity (
    id serial not null,
    line lseg,
    primary key (id)
);

insert into LineSegmentTypeEntity (id, line) VALUES (37, '(1, 1), (5, 5)');
insert into LineSegmentTypeEntity (id, line) VALUES (38, '[ (6, 7), (8, 9) ]');
