# Android Mobile App Registration

## Overview

Welcome to the Android Mobile App Registration README! This mobile app simplifies the registration process right from the convenience of your Android device. This app will help you manage and track registrations efficiently.

## Features

1. **Personal Data:** Users can input their personal information, national ID, fullname, bank account, education, and date of birth.

2. **Address Data:** Contains input data from user address information, domicile, housing type, no, domicile

3. **Review Page:** Show all data that user input, contains personal data and address data

## Tech Stack

This Android mobile app is built using modern technologies.

- **Programming Language:**
  - **Kotlin:** A versatile and widely-used programming language for Android app development.

- **Dependency Injection:**
  - **Dagger 2:** A powerful dependency injection framework that enhances code maintainability and scalability.

- **Architectural Pattern:**
  - **MVVM (Model-View-ViewModel):** A design pattern that promotes separation of concerns and maintainable code architecture.

- **Reactive Programming:**
  - **Coroutine Flow:** Utilizes Kotlin coroutines and Flow to manage asynchronous operations and handle reactive data streams.

- **Modularization:** Promotes a modular app architecture, enhancing code reusability and scalability.

- **Network Communication:**
  - **Retrofit:** A robust library for making HTTP requests and handling API communication.

- **Unit Testing:**
  - **JUnit4 and MockK:** Ensures code quality and reliability through unit tests, using JUnit4 for test structure and MockK for mocking dependencies.


- **Development Environment:**
  - **Android Studio:** The official Integrated Development Environment (IDE) for Android app development.

## Getting Started
### How to setup this app description

Fetch api province using this [API Province](https://goapi.id/docs/#/Regional%20Indonesia/get_regional_provinsi)

API GET https://api.goapi.id/v1/regional/provinsi

To secure manage my api_key i using com.google.android.libraries.mapsplatform.secrets-gradle-plugin , and store my api_key to *local.properties*

So, please if you want to build this app, generate api_key from  [GO API](https://goapi.id), register, login you will get the api key

and then setup your *local.properties* like this 

<img width="374" alt="image" src="https://github.com/vionavio/Registration-Page/assets/62820688/44f86105-a835-4ba1-99c6-974096ed0e9f">

## APK Sample
You can download the app here [Register App](https://drive.google.com/file/d/1XqEGRHI48S8ZNfTDfy2U1wwF3XFnjmTZ/view?usp=sharing)

## Contributors

- [Viona](https://github.com/vionavio)

## Contact

If you have any questions, suggestions, or need assistance, feel free to contact me at [vionamahdiya@gmail.com](mailto:vionamahdiya@gmail.com).

We hope this Android Mobile App Registration simplifies your event registration process and enhances your user experience. Enjoy using it!
