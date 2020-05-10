# NaN (Not just a Number) 

Dashboard for all your test results.

## Welcome page:

![Home page Design](https://raw.githubusercontent.com/abishekram/NaN/master/screenshots/nan-welcome.png)

Viewing over status of tests from various version of application , requires custom implementation.

NaN can be your single point of all your test results.

You can quickly get started with docker:

> docker run -p 8080:8080 nanserver/nan

Wanna check demo with prepopulated data Try : 

> docker run -p 8080:8080 nanserver/nan-demo 


Server will be available in http://localhost:8080

### Gradle:

Currently supports exporting test results from Gradle. 
  
More can be find at  [NaN Gradle plugin](https://github.com/NotJustANumber/nan-gradle-plugin)

### Will work on other adapters includes:

  1. Maven
  2. Jest
  3. Angular.
