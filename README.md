# Revolut Code Challenge

Code task for Revolut

## Getting Started

Clone the repository from Github, start the project in Android Studio and run it. Or just install the apk from ..\RevolutTask\app\build\outputs\apk\debug

## Description

Android app that gets a list of rates and currencies and show them updated every second. API: https://hiring.revolut.codes/api/android/latest?base=EUR I also used another API for country information: https://restcountries.eu/rest/v2/currency/eur

## Notes on implementation

- Design architecture MVVM for presentation layer along with Clean architecture adapted design
- Dagger 2 for dependency injection
- Written in Kotlin
- Coil for image loading
- Unit tests (Mockito)
- Data binding
- State manager (loading, error, success)
- Coroutines. I have always worked with RxJava but since coroutines are relatively new and promising I wanted to give them a try.
- LiveData and ViewModel from Architecture Components
- Retrofit
- Added some extension functions
- Mappers to map network objects to domain and domain to presentation
- I have used chuck to test the api calls (along with a logging interceptor in Retrofit)
- Shimmer effect for loading indicator

### Improvements

- An offline approach can be implemented and improve the app and save the countries info.
- Architecture is simplified it can be improved (adding datasource layer i.e.)
- I could have chosen to have the country flags and info locally in the app but I discovered this API and I decided to use it
- Add more functionality like conversion, search currency, add custom currencies, filters, etc... 
- Architecture implementation is quite simple so in a real project (depend on the project) more complexity can be added but I stuck with simplicity and focus on the separation of concerns.


## Libraries

- [Coil](https://github.com/coil-kt/coil)
- [Shimmer](https://facebook.github.io/shimmer-android/)
- [Chuck](https://github.com/jgilfelt/chuck)

## Authors

* **Raúl Suárez Rodríguez**
