# EV Station App README
## Table of Contents
- Overview
- Features
- Installation
- Usage
- Technologies
- Contributing
- License
- Acknowledgements
## Overview
The EV Station App is a mobile application designed to help electric vehicle (EV) owners find and locate EV charging stations in Brussels. The app provides detailed information about each charging station, such as power output, connector types, number of charging points, and precise location, allowing users to determine the suitability of a charging station for their needs.

## Features
- Locate EV Charging Stations: Find EV charging stations in Brussels with ease.
- Detailed Station Information: Access information on power output, connector types, number of charging points, and location.
- Offline Access: Use the app even without an internet connection, thanks to local data storage.
- Navigation Assistance: Integrate with Google Maps to navigate to charging stations from your current location.
- User-friendly Interface: Enjoy a seamless experience with easy navigation between main and details views.
## Installation
To run the EV Station App on your local machine, follow these steps:

1. Clone the Repository:

bash
Copy code
git clone https://github.com/Assaad-Assaad/ev-station-app.git
cd ev-station-app
2. Open the Project in Android Studio:

- Open Android Studio.
- Select "Open an existing Android Studio project".
- Navigate to the cloned repository folder and select it.
3. Build the Project:

- Sync the project with Gradle files.
- Build the project to ensure all dependencies are installed.
4. Run the App:

- Connect an Android device or start an emulator.
- Run the app from Android Studio.
## Usage
1. Launch the App: Open the EV Station App on your device.
2. Main View: Browse the list of EV charging stations in Brussels.
3. Select a Station: Tap on a station to view detailed information.
4. Details View: See comprehensive details and a map of the station's location.
5. Navigate: Click on the map to open Google Maps and get directions to the station.
6. Back Navigation: Use the back button to return to the main list of stations.
## Technologies
- Kotlin: Programming language used for the app.
- Retrofit: HTTP client for fetching data from the Brussels Open Data API.
- Room: Persistence library for offline data storage.
- Parcelable: Interface for serializing and deserializing objects.
- Google Maps: Integration for navigation to charging stations.
## Contributing
We welcome contributions to the EV Station App! To contribute:

1. Fork the repository.
2. Create a new branch for your feature or bugfix.
3. Make your changes and commit them.
4. Push your changes to your fork.
5. Open a pull request with a description of your changes.
## License
This project is licensed under the MIT License. See the LICENSE file for details.

## Acknowledgements
- Brussels Open Data: For providing the data on EV charging stations.
- Retrofit: For making API calls easy.
- Room: For enabling offline access.
- Google Maps: For navigation integration.
Thank you for using the EV Station App! We hope it makes your EV charging experience in Brussels easier and more convenient. If you have any questions or feedback, please don't hesitate to reach out.