### CIS 368 Group Project (Spring 2026)

- This repository contains the code for our group project in a class called CIS 368 (Object Oriented Programming and Design)

- Group members:
    - Ethan Pedrick
    - Carver Ossoff
    - Abhi Siri
    - Veer Gaudani
    - Nabeel Mesleh

- Instructor: Dr. Jackie Woldering

- Group Contribution:
    - Ethan Pedrick - 20% - Part 4, README, general help
    - Carver Ossoff - 20% - ..., general help
    - Abhi Siri - 20% - ..., general help
    - Veer Gaudani - 20% - ..., general help
    - Nabeel Mesleh - 20% ..., general help
 
### Compiling Instruction and Execution Sequence:
- Requirements:
    - Java JDK (17+ is recommended)
    - Maven installed
    - MySQL installed
 
- Setup Steps:
  1. Clone the repository:
      'git clone <github.com/UnDotImaginable/CIS368_OOPD'
      'cd CIS368_OOPD'
  2. Create the database:
      'CREATE DATABASE student_record;'
  3. Run the SQL files in order:
      - Student.sql
      - PhoneNumber.sql
      - Course.sql
  4. Update the database credentials if necessary in 'ViewManager.java'
  5. Compile:
      'mvn clean compile'
  6. Run the program:
      'mvn javafx:run'

### Sample Test Run
  1. Launch the program -> the login screen appears
  2. Login using one of the following roles:
        - 'r' read-only access
        - 'rw' read and write access
        - 'ov' admin (user and data management) 
  3. After login:
        - View students in table
        - Add a new student (graduate or undergraduate)
        - Update an existing student by ID
        - Delete a student by ID
        - Find a student by ID
  4. Save/Load functionality:
        - Enter file path -> click Save or Load

#### All parts function as intended by the instructions.


### To test parts of the program prior to Pt5 (if needed):
- Install **Maven**.
- Clone this repository.
- Navigate to the project root (where this file is located).
- Type `mvn clean javafx:run`.
    - On first bootup, maven will take some time to install the necessary dependencies.
- The file that's executed is determined by the following line inside the **pom.xml** file:
    - `<mainClass>to_use.Test_class</mainClass>`
- To run any `.class` file, specify the relative path to the file and **don't** include the `.class` extension.
