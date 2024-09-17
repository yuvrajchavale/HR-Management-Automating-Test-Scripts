# HR Management Automation Test Scripts

<p align="center">
  <img src="https://img.shields.io/badge/Automation-Selenium-green" alt="Automation: Selenium">
  <img src="https://img.shields.io/badge/Language-Java-orange" alt="Language: Java">
  <img src="https://img.shields.io/badge/Build-Maven-blue" alt="Build: Maven">
  <img src="https://img.shields.io/badge/Test-NG-red" alt="Test Framework: TestNG">
</p>

## Table of Contents
- [Overview](#overview)
- [Project Structure](#project-structure)
- [Installation](#installation)
- [How to Run](#how-to-run)
- [Test Reports](#test-reports)
- [Technology Stack](#technology-stack)
- [Contribution](#contribution)
- [License](#license)

## Overview

This project automates the testing of an **HR Management** system using **Selenium WebDriver** with **Java**. It is built around a **data-driven approach** using Excel files to input test data, allowing you to manage multiple test cases with ease.

The framework incorporates reusable components, a modular structure, and automated reporting to ensure efficiency and flexibility.

## Project Structure

```html
HrmLoginwithExcel/
├── src/main/java/                         <!-- Core application code -->
├── src/main/resources/                    <!-- Resource files -->
│
├── src/test/java/                         <!-- Test Automation Scripts -->
│   ├── commonFunctions/
│   │   └── FunctionLibrary.java           <!-- Contains reusable functions -->
│   ├── driverFactory/
│   │   └── AppTest.java                   <!-- Test execution logic -->
│   │   └── DriverScript.java              <!-- Executes tests using Excel -->
│   ├── utilities/
│   │   └── ExcelFileUtil.java             <!-- Excel file handling utility -->
│
├── FileInput/
│   └── InputControllers.xlsx              <!-- Test input data in Excel format -->
├── FileOutput/
│   └── HRMRResults.xlsx                   <!-- Excel file containing test results -->
│
├── PropertyFiles/                         <!-- Configuration and properties files -->
├── target/                                <!-- Generated files and reports -->
│   ├── Reports/                           <!-- Test execution reports -->
│   ├── screenshot/                        <!-- Captures of failed test case screenshots -->
│
└── pom.xml                                <!-- Maven dependencies and build configuration -->
```

## Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/yuvrajchavale/HR-Management-Automating-Test-Scripts.git
   ```
2. **Open the Project** in your favorite IDE (e.g., IntelliJ IDEA, Eclipse).
3. **Install Maven Dependencies**:
   ```bash
   mvn clean install
   ```

## How to Run

1. **Pre-requisites**:  
   Make sure you have **JDK 1.8+** and **Maven** installed.
   
2. **Configure Test Data**:  
   Modify `InputControllers.xlsx` located in the `FileInput/` folder to match the test cases.

3. **Run the Test Cases**:
   You can run the test cases via Maven:
   ```bash
   mvn test
   ```

4. **Test Execution Output**:  
   - Execution results will be written into the `FileOutput/HRMRResults.xlsx`.
   - Screenshots will be captured for failed tests and stored in the `target/screenshot/` directory.

## Test Reports

- TestNG HTML Reports will be available in the `target/Reports/` folder once the tests have completed.
- A screenshot of any failures will also be stored in the `target/screenshot/` folder, useful for debugging purposes.

## Technology Stack

| Technology         | Description                                        |
|--------------------|----------------------------------------------------|
| **Java**           | Main programming language                          |
| **Selenium WebDriver** | Browser automation for test execution            |
| **TestNG**         | Framework for running and managing tests           |
| **Apache POI**     | Excel integration for reading/writing test data     |
| **Maven**          | Dependency management and project build            |
| **Jenkins**        | (Optional) For continuous integration               |

## Contribution

Contributions are always welcome! Feel free to fork the repository and submit a pull request.

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Commit your changes (`git commit -m 'Add some feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Open a pull request.

---

## License

Distributed under the MIT License. See [LICENSE](LICENSE) for more information.

```

---

Feel free to customize it further to fit the specific details of your repository!
