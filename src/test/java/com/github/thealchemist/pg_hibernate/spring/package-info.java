@TypeDefs(
{
    @TypeDef(name = "circle", typeClass = com.github.thealchemist.pg_hibernate.CircleType.class),
    @TypeDef(name = "hstore", typeClass = com.github.thealchemist.pg_hibernate.HstoreType.class),
    @TypeDef(name = "lseg", typeClass = com.github.thealchemist.pg_hibernate.LineSegmentType.class),
    @TypeDef(name = "point", typeClass = com.github.thealchemist.pg_hibernate.PointType.class),
    @TypeDef(name = "box", typeClass = com.github.thealchemist.pg_hibernate.BoxType.class),
    @TypeDef(name = "polygon", typeClass = com.github.thealchemist.pg_hibernate.PolygonType.class),
    @TypeDef(name = "stringarray", typeClass = com.github.thealchemist.pg_hibernate.StringArrayType.class),
    @TypeDef(name = "inet", typeClass = com.github.thealchemist.pg_hibernate.InetAddressType.class),
    @TypeDef(name = "intarray", typeClass = com.github.thealchemist.pg_hibernate.IntegerArrayType.class)
})

package com.github.thealchemist.pg_hibernate.spring;

import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

