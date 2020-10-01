# Cheatsheet

REST   | Indempotent | Safe
------ | ----------- | -----
GET    | X           | X
POST   |             | 
PUT    | X           |  
DELETE | X           |
                     
`Indempotent`: Kann mehrmals aufgerufen werden, ohne dass das Ergebnis anders ist
`Safe`: Methoden die die Ressource nicht ändern

`POJO`: Plain Old Java Object
`JavaBean`: Standard für Klassen, properties sind private, no Argument Constructor, Serializable ist implementiert

**Annotationen**: 
* **Klassenannotationen**:
	* `@Controller`: Web Controller für APIs die angeboten werden sollen. Durch die Annotation routet Spring automatisch
	* `@Service`: In diesen Klassen wird die Businesslogik implementiert. 
	* `@ResponseBody`: Sagt Spring dass es Objekte automatisch in JSON umwandeln soll
	* `@RestController`: Fasst @Controller und @ResponseBody zusammen 
	* `@RequestMapping`: Definiert die Route unter der die API abrufbar ist.
	
* **Funktionsannotationen**:
	* `@RequestMapping`: Definiert die Route unter der die API abrufbar ist. Als Parameter kann das HTTP Verb/Methode angegeben werden
	    * Alternativ: z.b. @GetMapping(/cart/items) 
	* `@RequestParam`: http://localhost:8080/api/foos?id=abc
	    * Beispiel: public String getFoos(@RequestParam String id)
	    * kann auch direkt gemappt werden z.B. @RequestParam(„id") String userId)
	* `@PathVariable`:  http://localhost:8080/cart/items/3/details
        * Beispiel: public String getItemDetail (@Pathvariable long id)
		* Auch hier direkt ein Mapping nötig
			
`@Autowired`: Annotation für Dependency Injektion

Controller —> Service —> Repository

## application.properties

### spring.jpa.hibernate.ddl-auto 

The values create, create-drop, validate, and update basically influence how the schema tool management will manipulate the database schema at startup.

For example, the update operation will query the JDBC driver's API to get the database metadata and then Hibernate compares the object model it creates based on reading your annotated classes or HBM XML mappings and will attempt to adjust the schema on-the-fly.

The update operation for example will attempt to add new columns, constraints, etc but will never remove a column or constraint that may have existed previously but no longer does as part of the object model from a prior run.

Typically in test case scenarios, you'll likely use create-drop so that you create your schema, your test case adds some mock data, you run your tests, and then during the test case cleanup, the schema objects are dropped, leaving an empty database.

In development, it's often common to see developers use update to automatically modify the schema to add new additions upon restart. But again understand, this does not remove a column or constraint that may exist from previous executions that is no longer necessary.

In production, it's often highly recommended you use none or simply don't specify this property. That is because it's common practice for DBAs to review migration scripts for database changes, particularly if your database is shared across multiple services and applications.


https://docs.spring.io/spring-boot/docs/current/reference/html/using-spring-boot.html#using-boot-structuring-your-code