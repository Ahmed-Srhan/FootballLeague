# Football League Application
The Football League Application is a simple Android app designed to display information about football competitions. It consists of two screens: a list of competitions and details about each competition.

## Features
1. List of Competitions Screen: Display a list of competitions showing competition name, area name, and competition emblem.
2. Competition Details Screen: Display detailed information about a selected competition.
3. Offline Support: The app caches data retrieved from APIs in secure storage, allowing users to access the app offline if there is no internet connection.
4. Security Measures:
   - Prevent users from taking screenshots or recording the screen while the app is open.
   - Prevent Man-in-the-Middle (MITM) cyberattacks.
       - Use HTTPS : encrypts the data transmitted between the client and server
       - Certificate pinning in Retrofit: This helps prevent attackers from using fake certificates to intercept network
       - Use Network Security Configuration: allows to specify which certificates the app trusts
       - Use ProGuard and minify code: it makes it harder for attackers to analyze and temper with the app
   - Prevent common reverse engineering
       - Putting base URL and API key in gradle build config
  
## APIs
The Football League application uses the following APIs from https://www.football-data.org/:

* Competition API: http://api.football-data.org/v4/competitions/

To use these APIs, you need to create an account and generate an API token.

## Architecture
The app follows the MVVM architecture pattern for better separation of concerns and easier maintenance.

## Technology Used
- Clean Architecture
- Dagger-hilt to handle dependency injection.
- Co-routines to deal with threads.
- Retrofit and OkHttp: to handle apis requests and making secure network.
- Room DataBase for secure data storage.
- Flow & StateFlow
- Navigation Component : one activity contains multiple fragments instead of creating multiple activites.
- view binding and data binding instead of inflating views manually view binding will take care of that.
- Coil to load images from internet.


