<img src="https://github.com/a-vishniavetski/Java-SudokuFromScratch/assets/132013288/465ccd99-e6d1-4073-a5cb-94b0cbd88973" align="right" width="300"></img>


# Feature-full Java Sudoku Game
> This Java Sudoku application is a Java game developed from scratch. It utilizes the [JavaFX](https://github.com/openjdk/jfx) library for GUI, ensuring cross-platform compatibility. The game provides users with an experience of solving Sudoku puzzles, offering features such as checking if a position is solved, saving and loading game states, and choosing between different languages for localization. All necessary functionality, like a possibility of a database connection, error handling, and test coverage are implemented. The codebase follows object-oriented programming (OOP) principles, ensuring maintainability and extensibility.

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

## Navigation

- [Overview](#overview)
- [Installation and Usage](#installation-and-usage)
- [License](#license)

## Overview

The Java Sudoku game is implemented with a variety of features and techniques to ensure robustness and functionality. Some of the key aspects of the implementation include:

- **Graphical User Interface with JavaFX:** Configured using .xml files to create an intuitive and user-friendly graphical interface for interacting with the Sudoku game.


- **Database Connection:** Implemented a mechanism for saving and loading Sudoku games using a database connection, allowing for persistent storage of game states.

- **Game Serialization:** Save and load your chess games at any point. Serialization for the saving system was written from scratch.
- 
- **Localization and Internationalization**: App, logging, and exception handling are all localized and internationalized using Resource Bundles, making the game accessible to users from different linguistic backgrounds.

- **Custom Exception Handling:** Implemented custom exception handling to gracefully handle errors and unexpected situations during gameplay.

- **Maven as a Build-Tool:** The project is built and managed using Maven, ensuring dependency management and project structure consistency.

- **Unit Testing:** Achieved 98% code coverage with JUnit tests to validate the correctness of the implementation and ensure robustness.

- **Object-Oriented Programming (OOP):** The project strictly follows OOP principles, providing clean, maintainable, and well-organized code.
  
Collections, Inheritance, Serialization, Interfaces, and Decorators: Utilized for efficient data manipulation, creating a hierarchy of classes, saving and loading game states, providing standard functionality, and adding additional functionalities or behaviors to existing Sudoku puzzle objects without modifying their structure, respectively.

## Installation and Usage

1. **Prerequisites:**
   - C++ compiler
   - CMake
   - Make
   - [Boost library](https://www.boost.org/)
   - [wxWidgets library](https://github.com/wxWidgets/wxWidgets)

2. **Clone the Repository:**
 ```shell
 git clone https://github.com/a-vishniavetski/CPP-ChessFromScratch.git
```

3. **Compile the Application:**

In the project folder:
```bash
mkdir build
cd build
cmake ..
make
```

4. **Run the Application:**

```bash
./main
```

5. **Gameplay:**

  - Choose the game mode (Two-Player or Against Bot).
  - Use the GUI to make moves.
  - Save and load your games at any time.

## License
The application is licensed under the terms of the MIT Open Source license and is available for free for any purposes.
