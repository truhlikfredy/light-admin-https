# LightAdmin - CRUD data administration library for Java web applications.

##Warrning##

This is just a fork of the lightadmin tool to add some missing features. To be honest probably it was mistake to choose this tool. Many aspects look promising, but lot of them are not. This tool has some potential and it could be awesome, but there are lot of problem which makes it terrible. It could be pretty late if you realise the drawbacks.

Here are some issues I encotered:

* It was made for Tomcat8 (but it will work happyli on tomcat7 as well). So this is not bad.
* It might use different version of jackson json librabry which will create havok in your dependancies. You might end up making duplicate project just to have this thing running (not always this easy drop in way will work). Actually lot of the promises are not so great as they look.
* It supports only Spring boot 1.2.x (will not work with 1.3.x)
* The original has no support for HTTPS (this one has dodgy support for it, it will work on localhost over http and over everything else it will replace http with https)
* It support only hibernate 4 instead of 5. Which has different default naming stragety for fields.
* Writting custom naming stragety will be partialy ignored as well. So part of the tool work and part will not.
* It partialy ignores the @Column annotations, for model validation it will adhere to anotations and yet later will do method calls which ignore the annotations. (it means that your whole project and model needs to use naming shceme which suits the lightadmin, which is the default naming stragey of Hibernate 4). If you cannot afford renaming your whole schema and model and deal with all the consequences it brings you might have hard time with this tool. Probably only time this will work for you without any issues when you will use Hibernate 4 default naming strategy and never used custom annotations.
* It partialy ignores @JsonProperty the backend can be forced to adhere to it, but frontend will send JSON back to backend while ignoring the @JsonProperty annotations.
* It doesn't support primitive fields (so long needs to become Long). Which is not bad.
* It might not support your field types, there is limited list of supported types. (For example Date is supported, but Calendar isn't)
* There is no way to have custom handlers for unsuported fileds (so you need to change the type, or you will have problem). So even when you are willing to write your own handlers still there is no support for custom type handlers.
* If you will need to add static text to the form. Spacing or any html.
* The fields are the same for create and edit forms. If you need slightly different forms for different actions then you can't do it with this tool.
* It needs to recreate whole object from the signle JSON, if you have some specific way how you setup your model and maybe use @JsonIgnore and fill some data in Java code, here it will fail. It needs to get all the information only from single JSON. It's possibly you will have hard time with this.
* If you use number like long as foreigner keys and you use it JSON to create your parent/child relations this tool will send String URLs instead of IDs.
* (this is problem of jackson) No way to have two different @JsonCreate constructors with different arguments (not documented properly documented issue)
* If you need 2 different ways of annotations for some class. There is no way to have Json Mixins, you would need to access the ObjectMapper which is inside lightadmin set as protected.
* Buttons like export and templates are not implemented and show you only the "Not implemented" message. Yet you have no power to disable them.
* Everybody has acces to everything, you don't have option to disable edit/delete button. Everybody can do everything. There is no proper way to stop create/save event if your code needs to reject / stop this event. (only dodgy way to stop save/create is to create invalid SQL query and hope the database will ge tyou error which will stop the event). Even the form needs to have at least on field (which needs to exist). I tried empty forms, returning nulls or breaking the even somehow, either it was ignored or it crashed whole webapp. Only the SQL error does without crashing whole webapp.
* As I metioned before every field in the form needs to exist in database, so if you want have something different (like type password twice), so you would take the value only when both of them match can't be done without dirty workarounds.
* There are no event listeners for Delete.
* Getting the tool to keep some fields in their original state (not affecting them) is pretty painfull workaround where you load your original entry and field by field you need to set the values back.
* If you have predicates and you will add empty whitespace on the end of text label it will work as "always true" predicate without telling you why.
* Lot of stuff in the end I achived with dirty and dodgy workarounds, the tool does the administration the only way it wants to and most of the time you have no power to affect how it's done.

And probably this is the most frustrating on the tool, what works looks pretty decent and promising. If it would be afwaul from begining to end it woudn't be as such loss. But this way you see what it could be if it woudn't be so uncooperative.

And here is the original text describling how awesome this tool is:


[Light Admin](http://lightadmin.org) makes it possible to <b>focus on the stuff that matters</b> instead of spending time on auxiliary functionality.

## Features ##

* <b>DSL configurations</b>: Allows developers to easily configure their administration user interface
* <b>Displaying persistent entities</b>: Customizable Listing & Quick Views with paging & sorting capabilities
* <b>CRUD operations</b>: Complete entities manipulation support (including their associations)
* <b>Automatic Validation</b>: JSR-303 annotation-based validation rules support
* <b>Search</b>: Allows users to search entities by text fields, dates, numeric values & associations
* <b>Filtering Scopes</b>: Use scopes to filter data by predefined criteria
* <b>Pluggable Security</b>: Authentication based on [Spring Security](http://www.springsource.org/spring-security)
* <b>REST API</b>: Enriching your application with REST API based on [Spring Data REST](http://www.springsource.org/spring-data/rest)
* <b>Easy integration</b>: Servlet 2.5/3.0 web applications supported

## Integration examples ##

* [LightAdmin and Spring Boot](https://github.com/la-team/lightadmin-springboot)
* [LightAdmin and JHipster](https://github.com/la-team/lightadmin-jhipster)
* [LightAdmin running on Heroku](https://github.com/la-team/lightadmin-heroku)

## Documentation & Support ##

* Web site: [lightadmin.org](http://lightadmin.org)
* Documentation & Guides: [lightadmin.org/getting-started/](http://lightadmin.org/getting-started/)
* Wiki: [github.com/la-team/light-admin/wiki](http://github.com/la-team/light-admin/wiki)
* Live demo: [lightadmin.org/demo](http://lightadmin.org/demo)
* CI Server: [lightadmin.org/jenkins](http://lightadmin.org/jenkins)
* Use Google Groups for posting questions: [groups.google.com/group/lightadmin](http://groups.google.com/group/lightadmin)
* Use Stack Overflow for posting questions with <b>lightadmin</b> tag
* Contact LightAdmin Team directly on Twitter: <b>@lightadm_team</b>

## Bug Reports ##

* Bug Reports: [github.com/la-team/light-admin/issues](http://github.com/la-team/light-admin/issues)

## License ##

* <b>LightAdmin</b> is released under version 2.0 of the Apache License.

## Contribute ##

You're interested in contributing to LightAdmin? AWESOME. Here are the basic steps:

* Fork <b>LightAdmin</b> from here: http://github.com/la-team/light-admin
* Clone your fork
* Hack away
* If necessary, rebase your commits into logical chunks, without errors
* Verify your code by running the test suite, and adding additional tests if able
* Push the branch up to GitHub
* Send a pull request to the <b>la-team/light-admin</b> project

We'll do our best to get your changes in!

## Getting started ##

Declare maven dependency for using with Spring 4.0.X directly from Maven Central

```xml
<dependency>
  <groupId>org.lightadmin</groupId>
  <artifactId>lightadmin</artifactId>
  <version>1.2.0.RC1</version>
</dependency> 
```

or

```xml
<dependency>
  <groupId>org.lightadmin</groupId>
  <artifactId>lightadmin</artifactId>
  <version>1.2.0.BUILD-SNAPSHOT</version>
</dependency> 
```

For snapshots and LightAdmin compatible with Spring 3.2.X, please declare LA Nexus repositories:

```xml
<repositories>
  <repository>
    <id>lightadmin-nexus-releases</id>
    <url>http://lightadmin.org/nexus/content/repositories/releases</url>
    <releases>
      <enabled>true</enabled>
      <updatePolicy>always</updatePolicy>
    </releases>
  </repository>
  <repository>
    <id>lightadmin-nexus-snapshots</id>
    <url>http://lightadmin.org/nexus/content/repositories/snapshots</url>
    <snapshots>
      <enabled>true</enabled>
      <updatePolicy>always</updatePolicy>
    </snapshots>
  </repository>  
</repositories>
```

And dependency

```xml
<dependency>
  <groupId>org.lightadmin</groupId>
  <artifactId>lightadmin</artifactId>
  <version>1.0.0.M2</version>
</dependency> 
```



### Enable LightAdmin web-module in your <b>web.xml</b> if you have one: ###

```xml
<context-param>
  <param-name>light:administration:base-url</param-name>
  <param-value>/admin</param-value>
</context-param>

<context-param>
  <param-name>light:administration:security</param-name>
  <param-value>true</param-value>
</context-param>

<context-param>
  <param-name>light:administration:base-package</param-name>
  <param-value>[package with @Administration configurations, ex.: org.lightadmin.demo.config]</param-value>
</context-param>
```

### Or enable LightAdmin web-module in your <b>WebApplicationInitializer</b>: ###

```java
@Override
public void onStartup(ServletContext servletContext) throws ServletException {
  servletContext.setInitParameter(LIGHT_ADMINISTRATION_BASE_URL, "/admin");
  servletContext.setInitParameter(LIGHT_ADMINISTRATION_BACK_TO_SITE_URL, "http://lightadmin.org");
  servletContext.setInitParameter(LIGHT_ADMINISTRATION_BASE_PACKAGE, "org.lightadmin.administration");

  super.onStartup(servletContext);
}
```


### Include your JPA persistence provider of choice (Hibernate, EclipseLink, OpenJpa) and setup basic <b>Spring JPA</b> configuration. ###

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:jdbc="http://www.springframework.org/schema/jdbc"
       xmlns:jpa="http://www.springframework.org/schema/data/jpa"
       xsi:schemaLocation="http://www.springframework.org/schema/jdbc 
                           http://www.springframework.org/schema/jdbc/spring-jdbc.xsd
                           http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/data/jpa
                           http://www.springframework.org/schema/data/jpa/spring-jpa.xsd">
  
  <bean id="entityManagerFactory" class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
    <property name="dataSource" ref="dataSource" />
    <property name="jpaVendorAdapter">
      <bean class="org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter" />
    </property>
  </bean>

  <bean id="transactionManager" class="org.springframework.orm.jpa.JpaTransactionManager">
    <property name="entityManagerFactory" ref="entityManagerFactory" />
  </bean>

</beans>
```

### Create an entity: ###

```java
@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String firstname;
  private String lastname;
       
  // Getters and setters
}
```
### Create an <b>@Administration configuration</b> in the package defined in <b>web.xml</b> previously: ###

```java
public class UserAdministration extends AdministrationConfiguration<User> {

  public EntityMetadataConfigurationUnit configuration( EntityMetadataConfigurationUnitBuilder configurationBuilder ) {
    return configurationBuilder.nameField( "firstname" ).build();
  }

  public ScreenContextConfigurationUnit screenContext( ScreenContextConfigurationUnitBuilder screenContextBuilder ) {
    return screenContextBuilder
      .screenName( "Users Administration" )
      .menuName( "Users" )
      .build();
  }

  public FieldSetConfigurationUnit listView( final FieldSetConfigurationUnitBuilder fragmentBuilder ) {
    return fragmentBuilder
      .field( "firstname" ).caption( "First Name" )
      .field( "lastname" ).caption( "Last Name" )
      .build();
  }

```

Voila! You have a brand new LightAdmin back-end configured.

## Check Out and Build from Source

1. Clone the repository from GitHub:

		$ git clone git://github.com/la-team/light-admin.git

2. Navigate into the cloned repository directory:

		$ cd light-admin

3. The project uses [Maven](http://maven.apache.org/) to build:

		$ mvn clean install

## Running from the Command Line

By default, the app will run in 'embedded' mode which does not require any external setup. The Tomcat 7 Maven plugin is configured for you in the POM file.

1. Navigate into demo application directory:

		$ cd lightadmin-sandbox

2. Launch Tomcat from the command line:

		$ mvn tomcat7:run

3. Access the deployed webapp at 

		http://localhost:8080/lightadmin-sandbox

## LightAdmin integration example

We prepared an example how easily you can integrate LightAdmin back-end to existing web application.

It's based on [Spring Travel](https://github.com/SpringSource/spring-webflow-samples/tree/master/booking-mvc) reference application.

1. Clone the repository from GitHub:

		$ git clone git://github.com/la-team/lightadmin-spring-travel.git

2. Navigate into the cloned repository directory:

		$ cd lightadmin-spring-travel

3. The project uses [Maven](http://maven.apache.org/) to build:

		$ mvn clean install
			
4. Launch Tomcat from the command line:

		$ mvn tomcat7:run

5. Access the deployed webapp at 

		http://localhost:8080/booking-mvc
		
## Screenshots

<b>Login to LightAdmin:</b>

![Login view](https://github.com/la-team/light-admin/raw/master/screenshots/login.png "login view")

<b>Dashboard:</b>

![Dashboard view](https://github.com/la-team/light-admin/raw/master/screenshots/dashboard.png "dashboard view")

<b>List of persistent entities configured:</b>

![List view](https://github.com/la-team/light-admin/raw/master/screenshots/list-view.png "list view")

<b>Search entities by criteria:</b>

![List view & Filtering](https://github.com/la-team/light-admin/raw/master/screenshots/search.png "list view & filtering")

<b>Quick view for particular entity:</b>

![Quick view](https://github.com/la-team/light-admin/raw/master/screenshots/quick-view.png "quick view")

<b>Editing entity:</b>

![Form view](https://github.com/la-team/light-admin/raw/master/screenshots/form-view-validation.png "form view")

<b>Show entity with all fields:</b>

![Show view](https://github.com/la-team/light-admin/raw/master/screenshots/show-view.png "show view")
