<img src="https://github.com/a-vishniavetski/Java-SudokuFromScratch/assets/132013288/465ccd99-e6d1-4073-a5cb-94b0cbd88973" align="right" width="300"></img>


# Feature-full Java Sudoku Game
> A Java game developed from scratch. It utilizes the [JavaFX](https://github.com/openjdk/jfx) library for GUI. The game provides users with an experience of solving Sudoku puzzles, offering features such as checking if a position is solved, saving and loading game states, and is localized in English and Polish, with a possibility to add more languages with resourse bundles. All necessary functionality, like a possibility of a database connection, error handling, and test coverage are implemented. The codebase follows object-oriented programming (OOP) principles, ensuring maintainability and extensibility.

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

## Navigation

- [Overview](#overview)
- [License](#license)

## Overview

Key aspects of the implementation include:

- **Graphical User Interface with [_JavaFX_](https://github.com/openjdk/jfx):** Configured using .fxml files.
<p align="center">
  <img src="https://github.com/a-vishniavetski/Java-SudokuFromScratch/assets/132013288/97a69bb9-b47b-4c85-9733-9db1f4bca375" alt="GUI" width="400">
</p>


- **Localization and Internationalization**: App, logging, and exception handling are all localized and internationalized using ```Resource Bundles```.

Polish Version            |  English Version
:-------------------------:|:-------------------------:
![PL](https://github.com/a-vishniavetski/Java-SudokuFromScratch/assets/132013288/19339f9a-c658-40cc-9fb9-c459d0dbd854)  |  ![ENG](https://github.com/a-vishniavetski/Java-SudokuFromScratch/assets/132013288/20ef4be0-1e69-4d48-878e-5478a89a1634)


- **Database Connection:** Uses ```jdbc``` to connect to a MySQL database to save and load games.

Load Games            |  Save a game
:-------------------------:|:-------------------------:
![Load](https://github.com/a-vishniavetski/Java-SudokuFromScratch/assets/132013288/577d7815-56d3-47c3-b990-c423e7e7619c) | ![Save](https://github.com/a-vishniavetski/Java-SudokuFromScratch/assets/132013288/fa333195-522b-4504-8cb7-0eec225c306a)

- **Logging:** In-game operations like save/load and exceptions are logged using ```java.util.logging.Logger```

- **Unit Testing:** **98% code coverage** with ```JUnit4``` tests to validate the correctness of the implementation and ensure robustness.

- **Maven as a Build-Tool:** The project is built and managed using ```Maven```, ensuring dependency management and project structure consistency.
  
- **Game Serialization:** It's also possible to save/load games locally.
  
- **Exception Handling:** All Java exceptions are wrapped into a custom exception hierarchy.
  
As well as fundamental Java concepts like Collections, Inheritance, Serialization, Interfaces, Decorators, etc.

## License
The application is licensed under the terms of the MIT Open Source license.
