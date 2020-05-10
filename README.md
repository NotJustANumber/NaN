# NaN (Not just a Number) 

Dashboard for all your test results.

## Welcome page:

![Home page Design](https://raw.githubusercontent.com/abishekram/NaN/master/screenshots/nan-welcome.png)

If you are maintaing  multiple versions of an application, to view status of test results is a detious process. 

NaN can be your single point of all your test results.

You can quickly get started with docker:

> docker run -p 8080:8080 nanserver/nan

Wanna check demo with prepopulated data Try : 

> docker run -p 8080:8080 nanserver/nan-demo 


Server will be available in http://localhost:8080

Currently supports exporting test results from Gradle.

Future implementation include:

  1. Maven
  2. Jest
  3. Angular.
