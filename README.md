# HTMLSimilarityAnalysis

This project is my own pat project for making example to parse HTML and check similarity between exist processed HTML

## Choice of technologies
- Java: the most popular programming language
- Spring Boot: Framework to build web application with embedded web application server easily. Also easy to deploy to well-known PaaS environment like Heroku, PCF, Bluemix, etc
- Spring Web: For Rest Controller, Build RESTfull web service with high-level abstraction code
- Spring Data Redis: For handling Article Store, Easy to access redis using JPA(Java Persistence API)
- Spring Test: Provide spring context for unit testing
- Jsoup: For article processing. Checking url validity and paring HTML with same dom tree structure
- Redis: For article store, easy to store key/value in memory based database with scalability under distributed environment. Also easy to use on PaaS
- jsGrid: For display of processed articles, JavaScript grid library based on jQuery
