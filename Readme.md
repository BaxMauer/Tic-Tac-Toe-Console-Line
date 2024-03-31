## Tic Tac Toe Project

Welcome to the Tic Tac Toe project, a Java-based implementation designed for simplicity. Built using Java 21 and
IntelliJ IDEA, this project showcases classic gameplay. It prioritizes modularity and flexibility, making it easy to
understand, extend, and maintain.

### Running the Project

To get started, you can run the project using either the provided Maven wrapper or a local Maven installation. Simply
navigate to the project directory and execute the following commands:

```bash
./mvnw clean package
java -jar target/Tic-Tac-Toe-Console-Line-1.0-SNAPSHOT-jar-with-dependencies.jar
```

The project requires Java 21 to complile and run.

### UML Class Diagram

The diagram should get you a quick overview over the implementation of the game.
The project is built with modularity in mind, extensively utilizing interfaces to promote flexibility.

![UML Class Diagram](doc%2FCodeStructure.svg))

### Project Structure

The project follows a structured layout:

- `src/main/java`: Contains the main source code.
- `src/test/java`: Holds unit tests for ensuring code quality and reliability.
- `pom.xml`: Maven configuration file defining project dependencies and settings.

### Usage

Playing the game is straightforward. Simply follow the on-screen instructions to make your moves. You can exit the game
at any time by entering 'e' or check the current game statistics by typing 'p'.<br> Please note that viewing the command
line within an IDE may present minor visual bugs due to Java-specific issues with the clear function.

### Credits

This project is brought to you by Maximilian Bauer. Contributions and feedback are always welcome!

### License

The Tic Tac Toe project is licensed under the GPLv3 License, allowing for free use, modification, and distribution.