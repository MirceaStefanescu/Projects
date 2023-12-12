Prerequisites: Make sure you have the following installed on your system:

Java Development Kit (JDK) 8 or later
Maven 3.2+ (if you’re using Maven)
Gradle 4+ (if you’re using Gradle)
Build the Application:

If you’re using Maven, navigate to the project directory in your terminal and run the following command to build the
application:
mvn clean install

If you’re using Gradle, use the following command instead:
gradle build

Run the Application:

If you’re using Maven, you can run the application using the following command:
mvn spring-boot:run

If you’re using Gradle, use the following command instead:
gradle bootRun

Access the Application: Once your application is running, you can access it by opening a web browser and navigating
to http://localhost:8080, or whatever port you’ve configured your application to run on.

Access the Endpoints: Your application has the following endpoints:

/countries/population-density: This endpoint returns a list of all countries, each with its population density. You can
access this endpoint by navigating to http://localhost:8080/countries/population-density in your web browser.

/countries/asia/most-borders: This endpoint returns the country in Asia with the most bordering countries of a different
region. You can access this endpoint by navigating to http://localhost:8080/countries/asia/most-borders in your web
browser.

Test the Endpoints: You can test these endpoints using a tool like Postman or curl. Here’s how you can do it with curl:

To test the /countries/population-density endpoint:
curl http://localhost:8080/countries/population-density

To test the /countries/asia/most-borders endpoint:
curl http://localhost:8080/countries/asia/most-borders