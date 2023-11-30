This is a sample app to simulate the RealEstate listing and details page.

### Objective
The main objective of this app is to demonstrate the use of the following:
- Kotlin
- MVVM architecture
- Jetpack Compose
- Navigation Component
- Test Driven Development
- Dependency Injection (using Koin)
- Unit testing (using Kotest and Mockk)
- Coroutines
- Retrofit

### App Architecture
- Single activity architecture with Multiple fragments and Jetpack Compose.
- Navigation between fragments is done using Navigation Component.
- MVVM architecture for the fragments.
- Koin for dependency injection.
- Retrofit for network calls.
- Coroutines for asynchronous calls.
- Kotest and Mockk for unit testing.
- Shot library for screenshot testing.
- I followed Test Driven Development (TDD) approach for development.

### App Features
- Listing page
  - Display list of real estate properties
- Details page
  - Display details of the selected property

### Observations
- There are 2 services provided by the API:
  - Listing service
    - https://gsl-apps-technical-test.dignp.com/listings.json
  - Details service
    - https://gsl-apps-technical-test.dignp.com/listings/{id}.json
- The response of second service is already covered in the first service. So, I have used the first service only.
- Few of the properties are present in one property that are not present in other properties. So, I ignored some of the properties that doesn't exist in all the properties.
- The property "offerType" looks like a enum, and the explanation of the enum is not provided. So, I ignored the property.

### Note
- Since the main focus is on the usage of Kotlin, the UI and error scenarios are handled with limited scope.

### Screenshot testing
- I have used the following library for screenshot testing:
  - Shot (https://github.com/pedrovgs/Shot)
- Use the following command to capture the screenshots (Goldens)):
  - `./gradlew executeScreenshotTests -Pandroid.testInstrumentationRunnerArguments.class=com.realestate.propertyweb.list.PropertyListScreenShotTest -Precord`  
  - `./gradlew executeScreenshotTests -Pandroid.testInstrumentationRunnerArguments.class=com.realestate.propertyweb.details.PropertyDetailsScreenshotTest -Precord`
- Use the following command to compare the screenshots with the recorded screenshots (Goldens):
  - `./gradlew executeScreenshotTests -Pandroid.testInstrumentationRunnerArguments.class=com.realestate.propertyweb.list.PropertyListScreenShotTest`
  - `./gradlew executeScreenshotTests -Pandroid.testInstrumentationRunnerArguments.class=com.realestate.propertyweb.details.PropertyDetailsScreenshotTest`

### Unit testing
- I have used the following libraries for unit testing:
  - Kotest (https://kotest.io/)
  - Mockk (https://mockk.io/)
- To run the Kotest tests inside the Android Studio, install the Kotest plugin
    - https://plugins.jetbrains.com/plugin/14080-kotest

### Screenshots
| Listing page | Details page|
|--------------| -------------|
|![listing_page.png](app%2Fscreenshots%2Fcaptured%2Flisting_page.png)|![details_screen.png](app%2Fscreenshots%2Fcaptured%2Fdetails_screen.png)|
