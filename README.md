# Search Application

The **Search Application** is a Java program that allows users to search for data within a specified file. It employs an inverted index data structure to efficiently search and retrieve information.

## Table of Contents

- [Features](#features)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Usage](#usage)
- [Usage Examples](#usage-examples)
- [Contributing](#contributing)
- [License](#license)

## Features

- Data search using different strategies: ALL, ANY, NONE.
- Loading data from a file and creating an inverted index for efficient search.
- Interactive command-line interface for user interaction.
- Printing all available data.
- Graceful exit with proper resource cleanup.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) installed
- A text file containing the data to be searched

### Usage

1. Clone this repository to your local machine:

   ```sh
   git clone https://github.com/your-username/search-application.git
    ```

2. Navigate to the project directory:
   ```sh
   cd search-application
    ```

3. Compile the Java code:
   ```sh
   javac search/Main.java
    ```

4. Run the application:
   ```sh
   java search.Main --data your-data-file.txt
    ```

Replace `your-data-file.txt` with the path to the file containing the data to be loaded and searched.

## Usage Examples

### Adding Data

The application supports adding data from a file:
```sh
java search.Main --data your-data-file.txt
```

### Searching Data

You can search for data using different strategies (ALL, ANY, NONE):
```sh
Select a matching strategy: ALL, ANY, NONE
ALL
Enter data to search people:
John Doe
```

### Printing All Data

You can print all available data:
```sh
2 people found:
John Doe
Jane Smith
```

### Exiting the Application
```sh
0
```
To exit the application, use the `exit` command:


### Exiting the Application

To exit the application, use the `exit` command:


## Contributing

Contributions are welcome! If you find a bug or have suggestions for improvements, please create an issue or a pull request.

## License

This project is licensed under the MIT License.
