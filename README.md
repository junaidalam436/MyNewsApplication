News App : 
News App is an Android application that provides daily news articles sourced from NewsAPI. It features biometric authentication for secure access, offline storage using ROOM database, reactive programming with Kotlin Flow, dependency injection with Dagger Hilt, and a modern user interface built using Jetpack Compose.

Table of Contents:-
Features
Technologies Used
Setup Instructions
API Key Setup
Build and Run
Project Structure
Testing
Contributing



Features
Biometric Authentication: Secure access to the app using pattern, password, fingerprint or face recognition.
News Categories: Horizontal scroll to browse news articles by categories.
Offline Storage: Persist news articles locally using ROOM database.
Reactive Programming: Utilize Kotlin Flow for asynchronous data handling.
Dependency Injection: Manage dependencies efficiently with Dagger Hilt.
Modern UI: Build UI components with Jetpack Compose for a declarative and responsive user interface.


Technologies Used
Kotlin
Android SDK (minSdkVersion 31)
Android Jetpack components:
ViewModel
LiveData
Room
Biometric
Retrofit for network requests
Dagger Hilt for dependency injection
Jetpack Compose for UI
Kotlin Coroutines and Flow for asynchronous programming
Setup Instructions
API Key Setup
To run the app, you need to obtain a free API key from NewsAPI. Once you have the API key, do the following:

Create a constant file in your project.

(replace YOUR_API_KEY with your actual API key):
 
properties
Copy code
NEWS_API_KEY=YOUR_API_KEY
Build and Run
Clone the repository:

Build and run the app on an Android emulator or physical device.

Project Structure
The project follows a modular structure to separate concerns and improve maintainability:

database : Room database specific module
di: Dependency Injection setup using Dagger Hilt.
model: model layer handling data sources (local and remote), repositories, and models.
network: Network layer with Retrofit setup for API communication.
repository : used for creating object 
ui : ui layer
utils : Utility classes and extensions.
viewmodel : news app view model
test: Unit tests for repositories, ViewModels, database
Testing
The project includes unit tests using JUnit and Mockito for repositories, ViewModels, and database. To run tests:

Right-click on the test directory.
Select "Run tests in 'test'" to execute all unit tests.

Notes:
Replace placeholders like YOUR_API_KEY with actual values in your README file.
Provide clear and concise instructions for setting up and running the project.
