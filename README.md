# Tavoitemittari
[Tavoitemittari](http://tavoitemittari.herokuapp.com/login)

Application for tracking the learning of elementary and high school children. 

The upcoming national core curriculum (OPS2016) emphasizes the importance of constant feedback and assessment of the student. This is contrary to the traditional model of assessing a student's performance only at the end, using a test or  course essay.
This is a web application in which students (and teachers) can report and receive feedback on their progress during the entire course. The application allows students to register to the system, be signed up for courses and then input information on which course content they feel they have learned and which things they have yet to learn.
The application has or will have features for teachers including but not limited to the creation of a course and its goals, required skills and exercises.

The application is programmed in Java, HTML and JavaScript.

# Installation and Usage

### Standalone

An executable jar with an in-memory database and embedded tomcat server can be found the google drive link below. If you want to make changes and build your own, just change the pom to create jar files and build the jar file with maven.

### Using your postgresql database
this setup has been tested with tomcat 8 and postgresql versions 9.3.9 and 9.4.1

clone the database ready version with

```
git clone -b dataBaseReady git@github.com:TeamTavoitemittari/Tavoitemittari.git
```

Open DevProfile.java found inside the path src/main/java/wadp/profiles/ with a text editor
and change the values specified in the code block below.


```java
@Bean
public DataSource dataSource() throws URISyntaxException {

    BasicDataSource basicDataSource = new BasicDataSource();
    basicDataSource.setDriverClassName("org.postgresql.Driver");
    //change the values inside the quotes to match your database setup
    basicDataSource.setUrl("jdbc:postgresql://localhost/postgres");
    basicDataSource.setUsername("postgres");
    basicDataSource.setPassword("testpg");

    return basicDataSource;
}
```

Save your changes and build the package with the "-Dmaven.test.skip=true" flag
On bash this should work
```
mvn clean -Dmaven.test.skip=true install
```
# Development information

[Google Drive - dokumentaatio ja luonnokset](https://drive.google.com/folderview?id=0Bx5J5FlNtXT7fkFiaU5LUlQyMzVnT3RCekU2OGZWNFRkNE1tLW51c2VCUmtnZmhjS2Nka0k&usp=sharing_eid&invite=CKftxeIE)

[Trello - Tavoitemittari Scrum Board](https://trello.com/b/frSvLOGr/tavoitemittari-scrum-board)

[![Build Status](https://travis-ci.org/TeamTavoitemittari/Tavoitemittari.svg?branch=master)](https://travis-ci.org/TeamTavoitemittari/Tavoitemittari)

[![Coverage Status](https://coveralls.io/repos/TeamTavoitemittari/Tavoitemittari/badge.svg?branch=master)](https://coveralls.io/r/TeamTavoitemittari/Tavoitemittari?branch=master)
