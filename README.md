# Tarot Android App

An Android application for Tarot card readings, featuring various spreads and a local database for card information.

## Features

- **Multiple Spreads**: Choose between three different reading types, each designed for different levels of insight:
    - **One Card Spread**: Focus on a specific question or situation to get the essence of your inquiry or the energy surrounding your situation.
    - **Three Card Spread**: Offers holistic insights, illuminating past influences, present circumstances, and potential future outcomes.
    - **Five Card Spread**: Delves into the complexities of your situation, providing guidance for navigating challenges, making decisions, and learning from experiences.
- **Card Database**: Uses the **Room Persistence Library** to manage tarot card data.
- **Dynamic Seeding**: The database is automatically seeded from a CSV asset file on first run.
- **Interactive UI**: Clean interface built with Material Design components.

## Screenshots

*(Add screenshots here)*

## Getting Started

### Prerequisites

- Android Studio Flamingo or newer.
- Android SDK 34.
- Minimum SDK: 24 (Android 7.0 Nougat).

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/Tarot.git
   ```
2. Open the project in Android Studio.
3. Sync the project with Gradle files.
4. Run the app on an emulator or a physical device.

## Technologies Used

- **Language**: Java
- **Architecture**: MVVM-ish pattern with `CardRepository` and Room.
- **Database**: [Room Persistence Library](https://developer.android.com/training/data-storage/room)
- **UI Components**: 
    - ConstraintLayout
    - Material Design
    - View Binding
- **Lifecycle**: ViewModel and LiveData (via ktx libraries)

## Project Structure

- `com.example.tarot`: Main package containing activities and logic.
- `Card.java`: Entity class for tarot cards.
- `CardDao.java`: Data Access Object for Room.
- `CardsDatabase.java`: Main database configuration.
- `CardRepository.java`: Abstracted data access layer.
- `CardUtil.java`: Utility for seeding the database from `assets/cards.csv`.

## License

This project is licensed under the MIT License - see the LICENSE file for details.
