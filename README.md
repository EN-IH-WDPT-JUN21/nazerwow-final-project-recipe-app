# The Recipe Box

## Table of Contents

1. [**About**](#About)
1. [**Getting started**](#Getting-started)
2. [**Diagrams**](#Diagrams)


<br><br>

## About

At the recipe box our aim is to provide a simple and clean recipe app, so you can spend less time scrolling and more 
time eating!

The Recipe Box allows you to: 

* Search Recipes by Name, Diet or Cuisine

* Add Recipes to your favourites 

* Rate Recipes

* Create / Edit / Delete your own recipes 

* Our page will also show your our highest rated and most favourited recipes 

The project has been created with a microservices structure to aid with future scalability and the addition of new feature. 
Our microservices include: 

- Recipe-Service
- Favourites-Service
- Ratings-Service
- User-Service
- 
## Getting started

### Running The Recipe Box App
<br>

1. Download ZIP file / Clone the project:
```
https://github.com/EN-IH-WDPT-JUN21/nazerwow-final-project-recipe-app.git
```

2. Create mySQL database at the standard localhost port 3306 called "recipeapp"

```
Or use the below commands to Set up database and the same login details as us to save changing this in all microservices 

CREATE DATABASE pokeapp;
CREATE USER 'ironhack-user1'@'localhost' IDENTIFIED BY 'ironhack-user1';
GRANT ALL PRIVILEGES ON *.* TO 'ironhack-user1'@'localhost';
FLUSH PRIVILEGES;

```

3. Set Up & Run The Microservices - (Best Running Order - > Discovery, Gateway, Recipe, User, Favourite, Rating)
```
OPTION 1
=== Using Terminal === 
Create a JAR file for each service using Maven package
Navigate to the target folder for that microservice
Run each Jar File by using JAVA -jar .\{File-name}
eg. JAVA -jar .\recipe-service-0.0.1-SNAPSHOT.jar

=============================

OPTION 2
=== Using Intellij ===
Run Each Microservice Application
```

4. Load Project in Visual Studio enter the following into terminal:

  ```
  cd .\recipe-app-frontend\
  npm install 
  ng serve 
  ```

5. Enjoy at http://localhost:4200/
