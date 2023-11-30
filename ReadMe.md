This is a simple app to simulate the RealEstate property listing and details page.

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
- Listing Property page
  - Display the list of real estate properties
- Property details page
  - Display the details of the selected property

### Observations / Assumptions (based on my self-refinement)
- There were 2 web services given in the requirement:
  - Property listing service
    - https://gsl-apps-technical-test.dignp.com/listings.json
  - Property details service
    - https://gsl-apps-technical-test.dignp.com/listings/{id}.json
- The response of Property details service was already covered in the Property listing service. So, I have used the listing service only to develop both the screens.
- Though the input json has different sets of attributes for each property, So, I have incorporated the same in the UI screen except the attribute "URL" as I have used some random images from internet for URl.
- The attribute "offerType" looks like an enum, and the explanation of the enum [ENUM value] is not provided. So, I have ignored that attribute to display.

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
| Property listing page                                                | Property details page                                                    |
|----------------------------------------------------------------------|--------------------------------------------------------------------------|
| ![listing_page.png](app%2Fscreenshots%2Fcaptured%2Flisting_page.png) | ![details_screen.png](app%2Fscreenshots%2Fcaptured%2Fdetails_screen.png) |
