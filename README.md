#Tidal API - Java

#### Unofficial API for Tidal music streaming service.

This API is written as Java library. There are already other API's created
but I couldn't find any written for use with Java so I decided to create
my own.

Requirements for using this library:
- Java 1.8
- Maven 3

In order to create jar:
Run `mvn package -Dmaven.test.skip=true`
Created jar will be located under target directory

WARNING:
If you want to run unit test you will have to setup your Tidal user credentials
in [Credentials](src\test\java\com\hadas\krzysztof\testutils\Credentials.java) file because unit tests make
real calls to Tidal to check if all API calls are working correctly.

Run unit test with command `mvn test`

Example usage:
```java
TidalApi api = new TidalApiImpl();

//You have to login before using api
api.login("username", "password");

List<Track> tracks =  api.searchTrack("My track title");
String trackId = tracks.get(0).getId().toString();
api.addTrackToFavorite(trackId);

```