@TypeDefs(
{
    @TypeDef(name = "circle", typeClass = com.github.thealchemist.pg_hibernate.CircleType.class),
    @TypeDef(name = "hstore", typeClass = com.github.thealchemist.pg_hibernate.HstoreType.class),
    @TypeDef(name = "linesegment", typeClass = com.github.thealchemist.pg_hibernate.LineSegmentType.class),
    @TypeDef(name = "point", typeClass = com.github.thealchemist.pg_hibernate.PointType.class),
    @TypeDef(name = "polygon", typeClass = com.github.thealchemist.pg_hibernate.PolygonType.class),
    @TypeDef(name = "stringarray", typeClass = com.github.thealchemist.pg_hibernate.StringArrayType.class),
    @TypeDef(name = "inet", typeClass = com.github.thealchemist.pg_hibernate.Inet4AddressType.class)
})

package com.github.thealchemist.pg_hibernate.spring;

import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

