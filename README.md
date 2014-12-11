hibernate-postgresql
====================

Extra type mapping for PostgreSQL-specific types such as hstore and inet for Hibernate.

I haven't written most of the code: I've just scoured the web for code that's already been written and tried to put it in one place.

Sources include:
* https://github.com/jamesward/spring_hibernate_hstore_demo
* https://hibernate.atlassian.net/browse/HB-450
* https://github.com/Canadensys/canadensys-data-access

## How To Use
#### Java Source
```java
@TypeDefs(value={
    @TypeDef(name = "hstore", typeClass = com.github.thealchemist.pg_hibernate.HstoreType.class),
    @TypeDef(name = "inet", typeClass = com.github.thealchemist.pg_hibernate.InetAddressType.class)
})
public class LoggedAction implements Serializable {
  @Type(type = "hstore")
  @Column(name="row_data", columnDefinition="hstore")
  private Map<String, String> rowData;
	
  @Column(name="client_addr", columnDefinition="inet")
  @Type(type="inet")
  private InetAddress clientAddr;
```

#### Maven Configuration (pom.xml)

```xml

        <dependency>
            <groupId>com.github.the-alchemist</groupId>
            <artifactId>hibernate-postgresql</artifactId>
            <version>1.0.8</version>
        </dependency>
```

## Full List of Supported Types
* box
* circle
* hstore
* inet
* point
* lineseg
* polygon
* string[]

## Contributions Welcome!
