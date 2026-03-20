This is a demo project for [Remote Compose](https://developer.android.com/jetpack/androidx/releases/compose-remote).
Remote Compose allows you to run a Compose UI on a remote server and display it on a client device. 

This is a proof-of-concept project that demonstrates how to use Remote Compose.
- Currently, the library supports only Android. Nevertheless, the project includes a desktop client to implement it when it's supported.
- This project includes a Ktor server application that serves the Compose UI.
- WIP: The Compose UI is created on the server programmatically, because `remote-creation-compose` dependency isn't available on JVM

## Video


## Project structure
* [/composeApp](./composeApp/src) the Compose Multiplatform client applications for desktop and Android.
* [/server](./server/src/main/kotlin) is for the Ktor server application
* [/shared](./shared/src) is for the code that will be shared between all targets in the project

## Build and Run Server

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