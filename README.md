This is a demo project for [Remote Compose](https://developer.android.com/jetpack/androidx/releases/compose-remote).
Remote Compose allows you to run a Compose UI on a remote server and display it on a client device, such as an Android phone or a desktop computer.
This project includes a Ktor server application that serves the Compose UI and two client applications: one for Android and one for desktop (JVM).

## Project structure
* [/composeApp](./composeApp/src) the Compose Multiplatform client applications for desktop and Android
* [/server](./server/src/main/kotlin) is for the Ktor server application
* [/shared](./shared/src) is for the code that will be shared between all targets in the project

### Build and Run Android Application

To build and run the development version of the Android app, use the run configuration from the run
widget
in your IDE’s toolbar or build it directly from the terminal:

- on macOS/Linux
  ```shell
  ./gradlew :composeApp:assembleDebug
  ```
- on Windows
  ```shell
  .\gradlew.bat :composeApp:assembleDebug
  ```

### Build and Run Desktop (JVM) Application

To build and run the development version of the desktop app, use the run configuration from the run
widget
in your IDE’s toolbar or run it directly from the terminal:

- on macOS/Linux
  ```shell
  ./gradlew :composeApp:run
  ```
- on Windows
  ```shell
  .\gradlew.bat :composeApp:run
  ```

### Build and Run Server

To build and run the development version of the server, use the run configuration from the run
widget
in your IDE’s toolbar or run it directly from the terminal:

- on macOS/Linux
  ```shell
  ./gradlew :server:run
  ```
- on Windows
  ```shell
  .\gradlew.bat :server:run
  ```

## License
The project is licensed by the [Apache 2 license](LICENSE).