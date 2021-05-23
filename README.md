# Silent-passenger project

## Build and run
First we need to create the .env file in root direktory and add the following information
```
DB_URL=<jdbc url to database>
DB_USER=<database username>
DB_PASSWORD=<database password>
RAPID_API_GEOCODE_KEY=<rapid API key>
RAPID_API_GEOCODE_HOST=forward-reverse-geocoding.p.rapidapi.com
```
we used postgresql database.

To be able to generate JWT tokens we need to create private and publikc openss key with following commands
```
openssl genrsa -out rsaPrivateKey.pem 2048
openssl rsa -pubout -in rsaPrivateKey.pem -out public.pem
openssl pkcs8 -topk8 -nocrypt -inform pem -in rsaPrivateKey.pem -outform pem -out private.pem
```
then we copy the public.pem and private.pem into src/main/resources

Now we can build the project `mvn clean package`.
To run the application call `java -jar target/quarkus-app/*.jar`

The application should be available `http://localhost:8080`.

## About project

We decided to create an application that will greatly benefit every driver with extra space in their car and at the same time do something good for our planet. 
The idea was to match people who are in a desperate need to deliver a package with someone who wants to earn a quick buck and happens to drive past the delivery destination.
It is important to mention that most of the time shipping via transfer companies just isn’t fast enough. That is why our platform is going to allow users to search through available rides and find someone who is going to deliver their package instead of them. 

We used leaflet for displaying start/end location and routs. To help with displaying routs we integrated an API that allowed us to konvert address and post information into longitude and latitude which we forwareded to another API to get the total distance and lots of coordinates which helped us with drawing the route.


