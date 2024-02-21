<img src="https://github.com/a-vishniavetski/CPP-ChessFromScratch/assets/132013288/00e0cc43-3d73-4e50-a24b-62ec82186296" align="right" width="400"></img>

# Feature-full Java Sudoku Game
> Written from scratch by students of Politechnika Łódzka as a mid-term assignment.

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

## Navigation

- [Overview](#overview)
- [Features](#features)
- [Installation and Usage](#installation-and-usage)
- [License](#license)

## Overview

This Java Sudoku application is a Java game developed from scratch. It utilizes the [JavaFX](https://github.com/openjdk/jfx) library for GUI, ensuring cross-platform compatibility. The game provides users with an immersive experience for solving Sudoku puzzles, offering features such as checking if a puzzle position is solved, saving and loading game states, choosing between different languages for localization, and handling exceptions gracefully. The codebase follows object-oriented programming (OOP) principles, ensuring maintainability and extensibility.

## Features

- **Graphical User Interface (GUI):** The application features a GUI created using the wxWidgets library, ensuring cross-platform compatibility for Windows, macOS, and Linux.

- **Game Serialization:** Save and load your chess games at any point. Serialization for the saving system was written from scratch.
  
- **Different Game Modes:** Challenge your friends or colleagues for a classic chess game. The application allows two humans to play against each other. For solo players, test your skills against a computer-controlled bot.

- **Object-Oriented Programming (OOP):** The project strictly follows OOP principles, providing clean, maintainable, and well-organized code.

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
