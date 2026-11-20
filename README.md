# MasterRestAssuredFramework
REST Assured API test automation framework using Java + Maven + TestNG, written against the Spotify Web API.
Framework follows many of the industry best practices.

Technologies/Tools used in building the framework
=================================================
- Rest Assured
- TestNG
- Java 17+
- Maven
- Allure Reports
- Hamcrest
- Jackson API
- Lombok
- JavaFaker (test data generation)
- dotenv-java (secrets from a local `.env` file)
- IntelliJ
- GitHub
- Jenkins

Framework implements below best practices
=========================================
- Scalable and extensible
- Reusable Rest Assured specifications
- Reusable Rest Assured API requests
- Separation of API layer from test layer
- POJOs for Serialization and Deserialization
- Singleton Design Pattern
- Lombok for reducing Boilerplate code
- Builder pattern for Setter methods in POJOs
- Robust reporting and logging using Allure
- Automate positive and negative scenarios
- Support parallel execution
- Automated access token renewal
- Secrets kept out of source control (`.env`)
- Maven command line execution
- Integration with Git
- Integration with Jenkins

Prerequisites
=============
- JDK 17 or later
- Maven 3.9+
- A Spotify app ([Spotify Developer Dashboard](https://developer.spotify.com/dashboard)) and a refresh token
  obtained via the Authorization Code flow with the scopes `playlist-read-private`,
  `playlist-modify-public` and `playlist-modify-private`

Setup
=====
1. Create a `.env` file in the project root with the following keys:

   | Key | Required | Description |
   |---|---|---|
   | `CLIENT_ID` | Yes | Spotify app client ID |
   | `CLIENT_SECRET` | Yes | Spotify app client secret |
   | `REFRESH_TOKEN` | Yes | Refresh token, exchanged for an access token automatically |
   | `USER_ID` | Yes | Spotify user ID that owns the playlists |
   | `GRANT_TYPE` | No | Defaults to `refresh_token` |
   | `BASE_URI` | No | Defaults to `https://api.spotify.com` |
   | `ACCOUNT_BASE_URI` | No | Defaults to `https://accounts.spotify.com` |

2. Set existing playlist IDs (owned by `USER_ID`) in `src/test/resources/data.properties`:
   ```properties
   get_playlist_id=<playlist id>
   update_playlist_id=<playlist id>
   ```

Running the tests
=================
```bash
# all tests (runs in parallel, 10 threads)
mvn clean test

# a single test
mvn test -Dtest=PlaylistTests#ShouldBeAbleToGetAPlaylist

# override the base URIs (takes precedence over .env)
mvn test -DBASE_URI=https://api.spotify.com -DACCOUNT_BASE_URI=https://accounts.spotify.com
```

Allure report
=============
Results are written to `target/allure-results` (configured in `src/test/resources/allure.properties`).
With the [Allure CLI](https://allurereport.org/docs/install/) installed:
```bash
allure serve target/allure-results
```
