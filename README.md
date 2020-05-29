# Number Neighbors International

## Description
This is a demo app showcasing the `numverify` [API](https://numverify.com/documentation) to find number neighbors across different countries. It selects 5 countries at random and verifies a given phone number against the numverify API and displays the results.

Fetched country results are stored in a Room database.

This project uses MVVM, Dagger, & clean architecture principles.

## Usage
Use mockDebug/mockRelease to test with mock data instead of consuming `numverify` API quota.

For the prod variant, add your API key in build.gradle (or better yet, from your environment or elsewhere).

## Attribution
- [numverify](https://numverify.com) - [Terms & Conditions](https://numverify.com/terms)
- Retrofit - [License](https://github.com/square/retrofit/blob/master/LICENSE.txt)
- Moshi - [License](https://github.com/square/moshi/blob/master/LICENSE.txt)
- Square - [License](https://github.com/square/leakcanary/blob/master/LICENSE.txt)
