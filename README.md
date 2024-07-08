# Stick Hero Game

## Overview
This is an enhanced version of the classic Stick Hero game built using JavaFX. The game includes additional features like sound effects, multiple cherry collections, high score retrieval, improved graphics, and more. Object-Oriented Programming (OOP) principles such as Serialization and Inheritance have been utilized to structure the code efficiently.

## Features
- **Sound Effects**: Experience real-time sound effects for actions like walking and increasing stick length.
- **Multiple Cherry Collection**: Collect multiple cherries between buildings to increase your points.
- **High Score Retrieval**: Save and view high scores through a settings menu.
- **Automatic Revival**: The player is revived automatically if sufficient points are available, at the cost of two cherry points.
- **Game Over**: The game ends when the player’s score reaches zero.

## Game Mechanics
1. **Starting the Game**:
    - Click on the PlayButton to start the game.
2. **Playing the Game**:
    - Press and hold on the stick to increase its length. The stick length increases as long as the mouse is pressed.
    - Aim to bridge the gap between buildings and collect cherries to increase your score.
    - The player is revived automatically if there are sufficient points, costing two cherry points per revival.
    - The score decreases by one each time the player falls into the abyss.
    - The game ends when the player’s score reaches zero.

## Technical Details
- **OOP Principles**: The game utilizes Serialization and Inheritance.
    - A `HighScore` class is used to store game data. When the save button is clicked on the main game screen, the `HighScore` object is serialized and saved to `savedstate.txt`. The current game score can be viewed in settings under "See User Stats".
- **Multithreading**: The game uses JavaFX's `Timeline` objects, which are essentially JavaFX threads.
    - Multiple threads are created for different actions:
        - `rotateStickTimeline()`: Rotates the stick.
        - `moveCharacterTimeline()`: Moves the character from one building to another.

## Installation
1. **Clone the repository**:
    ```sh
    git clone https://github.com/rishabh0022/StickHero_Game2.git
    ```
2. **Navigate to the project directory**:
    ```sh
    cd stick-hero-game
    ```
3. **Open the project in your preferred IDE (e.g., IntelliJ IDEA, Eclipse) and run the `HelloApplication` file inside the `src` folder to start the game**.

## Usage
- **Start Game**: Click the PlayButton.
- **Control Stick**: Press and hold the mouse button to increase the stick’s length.
- **Save High Score**: Click the save button on the main game screen to save the current score.
- **View High Score**: Go to settings and click on "See User Stats" to view the saved high score.

## Contributing
If you want to contribute to this project, please fork the repository and create a pull request with your changes.

## Acknowledgements
- Original Stick Hero game for the inspiration.
- JavaFX community for the resources and documentation.

Enjoy playing and feel free to enhance the game further with your creative ideas!

