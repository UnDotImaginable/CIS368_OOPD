package to_use;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import to_use.ViewManager.StudentData;

public class ViewManager {

    // By placing all of this information in this class, we can freely access this information in a separate method (to separate building UI vs. handling logic)
    class StudentForm {

        public HashMap<String, TextField> studentInputs = new HashMap<>();
        public HashMap<String, TextField> phoneInputs = new HashMap<>();
        public HashMap<String, TextField> courseInputs = new HashMap<>();

        public ComboBox<String> studentStatus = new ComboBox<>();

        public Button addRecord = new Button("Add Record");
        public Button addPhoneNumber = new Button("Add Number");
        public Button addCourse = new Button("Add Course");
    }

    public static class StudentData {

        private SimpleStringProperty id;
        private SimpleStringProperty first;
        private SimpleStringProperty middle;
        private SimpleStringProperty last;
        private SimpleStringProperty streetNumber;
        private SimpleStringProperty streetName;
        private SimpleStringProperty theRest;
        private SimpleStringProperty city;
        private SimpleStringProperty state;
        private SimpleStringProperty zip;
        private SimpleStringProperty major;
        private SimpleStringProperty minor;
        private SimpleStringProperty undergradInst;
        private SimpleDoubleProperty stipend;

        public StudentData(
            String id,
            String first,
            String middle,
            String last,
            String streetNumber,
            String streetName,
            String theRest,
            String city,
            String state,
            String zip,
            String major,
            String minor,
            String undergradInst,
            double stipend
        ) {
            this.id = new SimpleStringProperty(id);
            this.first = new SimpleStringProperty(first);
            this.middle = new SimpleStringProperty(middle);
            this.last = new SimpleStringProperty(last);
            this.streetNumber = new SimpleStringProperty(streetNumber);
            this.streetName = new SimpleStringProperty(streetName);
            this.theRest = new SimpleStringProperty(theRest);
            this.city = new SimpleStringProperty(city);
            this.state = new SimpleStringProperty(state);
            this.zip = new SimpleStringProperty(zip);
            this.major = new SimpleStringProperty(major);
            this.minor = new SimpleStringProperty(minor);
            this.undergradInst = new SimpleStringProperty(undergradInst);
            this.stipend = new SimpleDoubleProperty(stipend);
        }

        public SimpleStringProperty idProperty() {
            return id;
        }

        public SimpleStringProperty firstProperty() {
            return first;
        }

        public SimpleStringProperty middleProperty() {
            return middle;
        }

        public SimpleStringProperty lastProperty() {
            return last;
        }

        public SimpleStringProperty streetNumberProperty() {
            return streetNumber;
        }

        public SimpleStringProperty streetNameProperty() {
            return streetName;
        }

        public SimpleStringProperty theRestProperty() {
            return theRest;
        }

        public SimpleStringProperty cityProperty() {
            return city;
        }

        public SimpleStringProperty stateProperty() {
            return state;
        }

        public SimpleStringProperty zipProperty() {
            return zip;
        }

        public SimpleStringProperty majorProperty() {
            return major;
        }

        public SimpleStringProperty minorProperty() {
            return minor;
        }

        public SimpleStringProperty undergradInstProperty() {
            return undergradInst;
        }

        public SimpleDoubleProperty stipendProperty() {
            return stipend;
        }
    }

    public static class PhoneNumberData {

        private SimpleIntegerProperty phoneId;
        private SimpleStringProperty areaCode;
        private SimpleStringProperty exchange;
        private SimpleStringProperty extension;
        private SimpleStringProperty id;

        public PhoneNumberData(
            int phoneId,
            String areaCode,
            String exchange,
            String extension,
            String id
        ) {
            this.phoneId = new SimpleIntegerProperty(phoneId);
            this.areaCode = new SimpleStringProperty(areaCode);
            this.exchange = new SimpleStringProperty(exchange);
            this.extension = new SimpleStringProperty(extension);
            this.id = new SimpleStringProperty(id);
        }

        public SimpleIntegerProperty phoneIdProperty() {
            return phoneId;
        }

        public SimpleStringProperty areaCodeProperty() {
            return areaCode;
        }

        public SimpleStringProperty exchangeProperty() {
            return exchange;
        }

        public SimpleStringProperty extensionProperty() {
            return extension;
        }

        public SimpleStringProperty idProperty() {
            return id;
        }
    }

    public static class CourseData {

        private SimpleIntegerProperty courseId;
        private SimpleStringProperty name;
        private SimpleStringProperty department;
        private SimpleStringProperty number;
        private SimpleDoubleProperty grade;
        private SimpleStringProperty id;

        public CourseData(
            int courseId,
            String name,
            String department,
            String number,
            double grade,
            String id
        ) {
            this.courseId = new SimpleIntegerProperty(courseId);
            this.name = new SimpleStringProperty(name);
            this.department = new SimpleStringProperty(department);
            this.number = new SimpleStringProperty(number);
            this.grade = new SimpleDoubleProperty(grade);
            this.id = new SimpleStringProperty(id);
        }

        public SimpleIntegerProperty courseIdProperty() {
            return courseId;
        }

        public SimpleStringProperty nameProperty() {
            return name;
        }

        public SimpleStringProperty departmentProperty() {
            return department;
        }

        public SimpleStringProperty numberProperty() {
            return number;
        }

        public SimpleDoubleProperty gradeProperty() {
            return grade;
        }

        public SimpleStringProperty idProperty() {
            return id;
        }
    }

    private static Stage stageRef; // stays the same for all instances
    TextField console = new TextField();
    private final String url = "jdbc:mysql://localhost:3306/student_record";
    private final String user = "root";
    private final String password = "veergaudani";

    TableView<StudentData> stuRecordTable = new TableView<StudentData>();
    TableView<PhoneNumberData> phoneNumTable = new TableView<PhoneNumberData>();
    TableView<CourseData> courseTable = new TableView<CourseData>();

    final ObservableList<StudentData> stu = FXCollections.observableArrayList();
    final ObservableList<PhoneNumberData> phone =
        FXCollections.observableArrayList();
    final ObservableList<CourseData> course =
        FXCollections.observableArrayList();

    public ViewManager() {}

    public ViewManager(Stage stageRef) {
        ViewManager.stageRef = stageRef;
    }

    public void append(String message, TextField tf) {
        tf.setText(tf.getText() + message);
    }

    public VBox addInputField(String fieldName) {
        VBox toReturn = new VBox(5);
        Label fieldLabel = new Label(fieldName);
        TextField input = new TextField();

        input.setPromptText("Enter " + fieldName);
        input.setMaxWidth(200);

        toReturn.getChildren().addAll(fieldLabel, input);
        return toReturn;
    }

    public StudentForm buildStudentForm(
        GridPane container1,
        GridPane container2,
        GridPane container3
    ) {
        container1.getChildren().clear();
        container2.getChildren().clear();
        container3.getChildren().clear();

        StudentForm form = new StudentForm();

        int row, col;

        // -------------------------------
        // STUDENT SECTION
        // -------------------------------

        container1.add(new Label("Student Status:"), 0, 0);
        form.studentStatus.getItems().addAll("Undergraduate", "Graduate");
        container1.add(form.studentStatus, 1, 0);

        String[] studentFields = {
            "Student ID",
            "First Name",
            "Middle Initial",
            "Last Name",
            "Street Number",
            "Street Name",
            "The Rest",
            "City",
            "State",
            "Zip Code",
            "Major",
            "Minor",
            "Stipend",
            "Undergrad Inst.",
        };

        row = 1;
        col = 0;

        for (String f : studentFields) {
            VBox field = addInputField(f);
            TextField tf = (TextField) field.getChildren().get(1);

            form.studentInputs.put(f, tf);

            container1.add(field, col, row);

            col++;
            if (col == 4) {
                col = 0;
                row++;
            }
        }

        container1.add(form.addRecord, 0, row + 1);

        // -------------------------------
        // PHONE SECTION
        // -------------------------------

        String[] phoneFields = {
            "Student ID",
            "Area Code",
            "Exchange",
            "Extension",
        };

        row = 0;

        for (String f : phoneFields) {
            VBox field = addInputField(f);
            TextField tf = (TextField) field.getChildren().get(1);

            form.phoneInputs.put(f, tf);

            container2.add(field, 0, row);
            row++;
        }

        container2.add(form.addPhoneNumber, 0, row + 1);

        // -------------------------------
        // COURSE SECTION
        // -------------------------------

        String[] courseFields = {
            "Student ID",
            "Course Name",
            "Department",
            "Course Number",
            "Grade",
        };

        row = 0;

        for (String f : courseFields) {
            VBox field = addInputField(f);
            TextField tf = (TextField) field.getChildren().get(1);

            form.courseInputs.put(f, tf);

            container3.add(field, 0, row);
            row++;
        }

        container3.add(form.addCourse, 0, row + 1);

        return form;
    }

    // Tables only need to be set up ONCE!
    public void setupTables() {
        TableColumn<StudentData, String> idColumn = new TableColumn<>(
            "Student ID"
        );
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<StudentData, String> firstColumn = new TableColumn<>(
            "First Name"
        );
        firstColumn.setCellValueFactory(new PropertyValueFactory<>("first"));

        TableColumn<StudentData, String> middleColumn = new TableColumn<>(
            "Middle Initial"
        );
        middleColumn.setCellValueFactory(new PropertyValueFactory<>("middle"));

        TableColumn<StudentData, String> lastColumn = new TableColumn<>(
            "Last Name"
        );
        lastColumn.setCellValueFactory(new PropertyValueFactory<>("last"));

        TableColumn<StudentData, String> streetNumberColumn = new TableColumn<>(
            "Street Number"
        );
        streetNumberColumn.setCellValueFactory(
            new PropertyValueFactory<>("streetNumber")
        );

        TableColumn<StudentData, String> streetNameColumn = new TableColumn<>(
            "Street Name"
        );
        streetNameColumn.setCellValueFactory(
            new PropertyValueFactory<>("streetName")
        );

        TableColumn<StudentData, String> theRestColumn = new TableColumn<>(
            "The Rest"
        );
        theRestColumn.setCellValueFactory(
            new PropertyValueFactory<>("theRest")
        );

        TableColumn<StudentData, String> cityColumn = new TableColumn<>("City");
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));

        TableColumn<StudentData, String> stateColumn = new TableColumn<>(
            "State"
        );
        stateColumn.setCellValueFactory(new PropertyValueFactory<>("state"));

        TableColumn<StudentData, String> zipColumn = new TableColumn<>("Zip");
        zipColumn.setCellValueFactory(new PropertyValueFactory<>("zip"));

        TableColumn<StudentData, String> majorColumn = new TableColumn<>(
            "Major"
        );
        majorColumn.setCellValueFactory(new PropertyValueFactory<>("major"));

        TableColumn<StudentData, String> minorColumn = new TableColumn<>(
            "Minor"
        );
        minorColumn.setCellValueFactory(new PropertyValueFactory<>("minor"));

        TableColumn<StudentData, String> undergradInstColumn =
            new TableColumn<>("Undergrad Institution");
        undergradInstColumn.setCellValueFactory(
            new PropertyValueFactory<>("undergradInst")
        );

        TableColumn<StudentData, Double> stipendColumn = new TableColumn<>(
            "Stipend"
        );
        stipendColumn.setCellValueFactory(
            new PropertyValueFactory<>("stipend")
        );

        TableColumn<PhoneNumberData, Integer> phoneIdColumn = new TableColumn<>(
            "Phone ID"
        );
        phoneIdColumn.setCellValueFactory(
            new PropertyValueFactory<>("phoneId")
        );

        TableColumn<PhoneNumberData, String> areaCodeColumn = new TableColumn<>(
            "Area Code"
        );
        areaCodeColumn.setCellValueFactory(
            new PropertyValueFactory<>("areaCode")
        );

        TableColumn<PhoneNumberData, String> exchangeColumn = new TableColumn<>(
            "Exchange"
        );
        exchangeColumn.setCellValueFactory(
            new PropertyValueFactory<>("exchange")
        );

        TableColumn<PhoneNumberData, String> extensionColumn =
            new TableColumn<>("Extension");
        extensionColumn.setCellValueFactory(
            new PropertyValueFactory<>("extension")
        );

        TableColumn<PhoneNumberData, String> phoneForeignColumn =
            new TableColumn<>("Student ID");
        phoneForeignColumn.setCellValueFactory(
            new PropertyValueFactory<>("id")
        );

        TableColumn<CourseData, Integer> courseIdColumn = new TableColumn<>(
            "Course ID"
        );
        courseIdColumn.setCellValueFactory(
            new PropertyValueFactory<>("courseId")
        );

        TableColumn<CourseData, String> nameColumn = new TableColumn<>(
            "Course Name"
        );
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<CourseData, String> depColumn = new TableColumn<>(
            "Department"
        );
        depColumn.setCellValueFactory(new PropertyValueFactory<>("department"));

        TableColumn<CourseData, String> numberColumn = new TableColumn<>(
            "Course Number"
        );
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));

        TableColumn<CourseData, Double> gradeColumn = new TableColumn<>(
            "Grade"
        );
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));

        TableColumn<CourseData, String> courseForeignColumn = new TableColumn<>(
            "Student ID"
        );
        courseForeignColumn.setCellValueFactory(
            new PropertyValueFactory<>("id")
        );

        stuRecordTable
            .getColumns()
            .addAll(
                idColumn,
                firstColumn,
                middleColumn,
                lastColumn,
                streetNumberColumn,
                streetNameColumn,
                theRestColumn,
                cityColumn,
                stateColumn,
                zipColumn,
                majorColumn,
                minorColumn,
                undergradInstColumn,
                stipendColumn
            );
        phoneNumTable
            .getColumns()
            .addAll(
                phoneIdColumn,
                areaCodeColumn,
                exchangeColumn,
                extensionColumn,
                phoneForeignColumn
            );
        courseTable
            .getColumns()
            .addAll(
                courseIdColumn,
                nameColumn,
                depColumn,
                numberColumn,
                gradeColumn,
                courseForeignColumn
            );
    }

    // They're then updated repeatedly as we make changes to the data within our DB
    public void updateTables() {
        stu.clear();
        phone.clear();
        course.clear();

        try (
            Connection conn = DriverManager.getConnection(url, user, password)
        ) {
            String query = "select * from Student";
            PreparedStatement st = conn.prepareStatement(query);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String id = rs.getString("id");
                String first = rs.getString("first");
                String middle = rs.getString("middle");
                String last = rs.getString("last");
                String streetNumber = rs.getString("streetNumber");
                String streetName = rs.getString("streetName");
                String theRest = rs.getString("theRest");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String zip = rs.getString("zip");
                String major = rs.getString("major");
                String minor = rs.getString("minor");
                String undergradInst = rs.getString("undergradInst");
                double stipend = rs.getDouble("stipend");

                stu.add(
                    new StudentData(
                        id,
                        first,
                        middle,
                        last,
                        streetNumber,
                        streetName,
                        theRest,
                        city,
                        state,
                        zip,
                        major,
                        minor,
                        undergradInst,
                        stipend
                    )
                );
            }
        } catch (SQLException sqe) {
            append("Inside updateTables() " + sqe.getMessage(), console);
        }

        try (
            Connection conn = DriverManager.getConnection(url, user, password)
        ) {
            String query = "select * from phoneNumber";
            PreparedStatement st = conn.prepareStatement(query);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("phoneId");
                String areaCode = rs.getString("areaCode");
                String exchange = rs.getString("exchange");
                String extension = rs.getString("extension");
                String foreignPhoneId = rs.getString("id");

                phone.add(
                    new PhoneNumberData(
                        id,
                        areaCode,
                        exchange,
                        extension,
                        foreignPhoneId
                    )
                );
            }
        } catch (SQLException sqe) {
            append("Inside updateTables() " + sqe.getMessage(), console);
        }

        try (
            Connection conn = DriverManager.getConnection(url, user, password)
        ) {
            String query = "select * from Course";
            PreparedStatement st = conn.prepareStatement(query);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("courseId");
                String name = rs.getString("name");
                String department = rs.getString("department");
                String number = rs.getString("number");
                double grade = rs.getDouble("grade");
                String foreignCourseId = rs.getString("id");

                course.add(
                    new CourseData(
                        id,
                        name,
                        department,
                        number,
                        grade,
                        foreignCourseId
                    )
                );
            }
        } catch (SQLException sqe) {
            append("Inside updateTables() " + sqe.getMessage(), console);
        }

        stuRecordTable.setItems(stu);
        phoneNumTable.setItems(phone);
        courseTable.setItems(course);

        stuRecordTable.refresh();
        phoneNumTable.refresh();
        courseTable.refresh();
    }

    public void handleAddLogic(StudentForm form) {
        form.addRecord.setOnAction(event -> {
            String query;

            if ("Undergraduate".equals(form.studentStatus.getValue())) {
                query =
                    "insert into Student (id, first, middle, last, streetNumber, streetName, theRest, city, state, zip, major, minor) " +
                    "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                try (
                    Connection conn = DriverManager.getConnection(
                        url,
                        user,
                        password
                    )
                ) {
                    PreparedStatement st = conn.prepareStatement(query);

                    st.setString(
                        1,
                        form.studentInputs.get("Student ID").getText()
                    );
                    st.setString(
                        2,
                        form.studentInputs.get("First Name").getText()
                    );
                    st.setString(
                        3,
                        form.studentInputs.get("Middle Initial").getText()
                    );
                    st.setString(
                        4,
                        form.studentInputs.get("Last Name").getText()
                    );
                    // st.setString(5, form.studentInputs.get("Street Number").getText());
                    st.setString(
                        5,
                        form.studentInputs.get("Street Number").getText()
                    );
                    st.setString(
                        6,
                        form.studentInputs.get("Street Name").getText()
                    );
                    st.setString(
                        7,
                        form.studentInputs.get("The Rest").getText()
                    );
                    st.setString(8, form.studentInputs.get("City").getText());
                    st.setString(9, form.studentInputs.get("State").getText());
                    st.setString(
                        10,
                        form.studentInputs.get("Zip Code").getText()
                    );
                    st.setString(11, form.studentInputs.get("Major").getText());
                    st.setString(12, form.studentInputs.get("Minor").getText());

                    int rowsInserted = st.executeUpdate();
                    append(
                        rowsInserted +
                            " rows inserted successfully! Updating table(s)...",
                        console
                    );

                    updateTables();
                } catch (SQLException sqe) {
                    append("Error: " + sqe.getMessage(), console);
                }
            } else {
                query =
                    "insert into Student (id, first, middle, last, streetNumber, streetName, theRest, city, state, zip, undergradInst, stipend) " +
                    "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                try (
                    Connection conn = DriverManager.getConnection(
                        url,
                        user,
                        password
                    )
                ) {
                    PreparedStatement st = conn.prepareStatement(query);

                    st.setString(
                        1,
                        form.studentInputs.get("Student ID").getText()
                    );
                    st.setString(
                        2,
                        form.studentInputs.get("First Name").getText()
                    );
                    st.setString(
                        3,
                        form.studentInputs.get("Middle Initial").getText()
                    );
                    st.setString(
                        4,
                        form.studentInputs.get("Last Name").getText()
                    );
                    st.setString(
                        5,
                        form.studentInputs.get("Street Number").getText()
                    );
                    st.setString(
                        6,
                        form.studentInputs.get("Street Name").getText()
                    );
                    st.setString(
                        7,
                        form.studentInputs.get("The Rest").getText()
                    );
                    st.setString(8, form.studentInputs.get("City").getText());
                    st.setString(9, form.studentInputs.get("State").getText());
                    st.setString(
                        10,
                        form.studentInputs.get("Zip Code").getText()
                    );
                    st.setString(
                        11,
                        form.studentInputs.get("Undergrad Inst.").getText()
                    );
                    st.setDouble(
                        12,
                        Double.parseDouble(
                            form.studentInputs.get("Stipend").getText()
                        )
                    );

                    int rowsInserted = st.executeUpdate();
                    append(
                        rowsInserted +
                            " rows inserted successfully! Updating student record table...",
                        console
                    );

                    updateTables();
                } catch (SQLException sqe) {
                    append("Error: " + sqe.getMessage(), console);
                }
            }
        });

        form.addPhoneNumber.setOnAction(event -> {
            String query =
                "insert into PhoneNumber (areaCode, exchange, extension, id) values (?, ?, ?, ?)";

            try (
                Connection conn = DriverManager.getConnection(
                    url,
                    user,
                    password
                )
            ) {
                PreparedStatement st = conn.prepareStatement(query);

                st.setString(1, form.phoneInputs.get("Area Code").getText());
                st.setString(2, form.phoneInputs.get("Exchange").getText());
                st.setString(3, form.phoneInputs.get("Extension").getText());
                st.setString(4, form.phoneInputs.get("Student ID").getText());

                int rowsInserted = st.executeUpdate();
                append(
                    rowsInserted +
                        " rows inserted successfully! Updating phone number table...",
                    console
                );

                updateTables();
            } catch (SQLException sqe) {
                append("Error: " + sqe.getMessage(), console);
            }
        });

        form.addCourse.setOnAction(event -> {
            String query =
                "insert into Course (name, department, number, grade, id) values (?, ?, ?, ?, ?)";

            try (
                Connection conn = DriverManager.getConnection(
                    url,
                    user,
                    password
                )
            ) {
                PreparedStatement st = conn.prepareStatement(query);

                st.setString(1, form.courseInputs.get("Course Name").getText());
                st.setString(2, form.courseInputs.get("Department").getText());
                st.setString(
                    3,
                    form.courseInputs.get("Course Number").getText()
                );
                st.setDouble(
                    4,
                    Double.parseDouble(form.courseInputs.get("Grade").getText())
                );
                st.setString(5, form.courseInputs.get("Student ID").getText());

                int rowsInserted = st.executeUpdate();
                append(
                    rowsInserted +
                        " rows inserted successfully! Updating course table...",
                    console
                );

                updateTables();
            } catch (SQLException sqe) {
                append("Error: " + sqe.getMessage(), console);
            }
        });
    }

    // TODO:   add code here later
    public void handleUpdateLogic(StudentForm form, Button updateButton) {
        updateButton.setOnAction(event -> {
            String id = form.studentInputs.get("Student ID").getText();

            if (id == null || id.isEmpty()) {
                append("Student ID required for update.\n", console);
                return;
            }

            String query;

            if ("Undergraduate".equals(form.studentStatus.getValue())) {
                query =
                    "UPDATE Student SET first=?, middle=?, last=?, streetNumber=?, streetName=?, theRest=?, city=?, state=?, zip=?, major=?, minor=?, undergradInst=NULL, stipend=NULL WHERE id=?";

                try (
                    Connection conn = DriverManager.getConnection(
                        url,
                        user,
                        password
                    )
                ) {
                    PreparedStatement st = conn.prepareStatement(query);

                    st.setString(
                        1,
                        form.studentInputs.get("First Name").getText()
                    );
                    st.setString(
                        2,
                        form.studentInputs.get("Middle Initial").getText()
                    );
                    st.setString(
                        3,
                        form.studentInputs.get("Last Name").getText()
                    );
                    st.setString(
                        4,
                        form.studentInputs.get("Street Number").getText()
                    );
                    st.setString(
                        5,
                        form.studentInputs.get("Street Name").getText()
                    );
                    st.setString(
                        6,
                        form.studentInputs.get("The Rest").getText()
                    );
                    st.setString(7, form.studentInputs.get("City").getText());
                    st.setString(8, form.studentInputs.get("State").getText());
                    st.setString(
                        9,
                        form.studentInputs.get("Zip Code").getText()
                    );
                    st.setString(10, form.studentInputs.get("Major").getText());
                    st.setString(11, form.studentInputs.get("Minor").getText());
                    st.setString(12, id);

                    int rows = st.executeUpdate();
                    append(rows + " student updated.\n", console);

                    updateTables();
                } catch (SQLException sqe) {
                    append("Update error: " + sqe.getMessage(), console);
                }
            } else {
                query =
                    "UPDATE Student SET first=?, middle=?, last=?, streetNumber=?, streetName=?, theRest=?, city=?, state=?, zip=?, undergradInst=?, stipend=?, major=NULL, minor=NULL WHERE id=?";

                try (
                    Connection conn = DriverManager.getConnection(
                        url,
                        user,
                        password
                    )
                ) {
                    PreparedStatement st = conn.prepareStatement(query);

                    st.setString(
                        1,
                        form.studentInputs.get("First Name").getText()
                    );
                    st.setString(
                        2,
                        form.studentInputs.get("Middle Initial").getText()
                    );
                    st.setString(
                        3,
                        form.studentInputs.get("Last Name").getText()
                    );
                    st.setString(
                        4,
                        form.studentInputs.get("Street Number").getText()
                    );
                    st.setString(
                        5,
                        form.studentInputs.get("Street Name").getText()
                    );
                    st.setString(
                        6,
                        form.studentInputs.get("The Rest").getText()
                    );
                    st.setString(7, form.studentInputs.get("City").getText());
                    st.setString(8, form.studentInputs.get("State").getText());
                    st.setString(
                        9,
                        form.studentInputs.get("Zip Code").getText()
                    );
                    st.setString(
                        10,
                        form.studentInputs.get("Undergrad Inst.").getText()
                    );
                    st.setDouble(
                        11,
                        Double.parseDouble(
                            form.studentInputs.get("Stipend").getText()
                        )
                    );
                    st.setString(12, id);

                    int rows = st.executeUpdate();
                    append(rows + " student updated.\n", console);

                    updateTables();
                } catch (SQLException sqe) {
                    append("Update error: " + sqe.getMessage(), console);
                }
            }
        });
    }

    // TODO: add code here later
    public void handleDeleteLogic(Button deleteButton) {
        deleteButton.setOnAction(event -> {
            StudentData selected = stuRecordTable
                .getSelectionModel()
                .getSelectedItem();

            if (selected == null) {
                append("No student selected.\n", console);
                return;
            }

            String studentId = selected.idProperty().get();

            try (
                Connection conn = DriverManager.getConnection(
                    url,
                    user,
                    password
                )
            ) {
                PreparedStatement deletePhones = conn.prepareStatement(
                    "DELETE FROM PhoneNumber WHERE id = ?"
                );
                deletePhones.setString(1, studentId);
                deletePhones.executeUpdate();

                PreparedStatement deleteCourses = conn.prepareStatement(
                    "DELETE FROM Course WHERE id = ?"
                );
                deleteCourses.setString(1, studentId);
                deleteCourses.executeUpdate();

                PreparedStatement deleteStudent = conn.prepareStatement(
                    "DELETE FROM Student WHERE id = ?"
                );
                deleteStudent.setString(1, studentId);

                int rowsDeleted = deleteStudent.executeUpdate();

                append(rowsDeleted + " student deleted.\n", console);

                updateTables();
            } catch (SQLException sqe) {
                append("Delete error: " + sqe.getMessage(), console);
            }
        });
    }

    // TODO: add code here later
    public void handleFindLogic(StudentForm form, Button findButton) {
        findButton.setOnAction(event -> {
            String id = form.studentInputs.get("Student ID").getText();

            if (id == null || id.isEmpty()) {
                append("Enter Student ID to search.\n", console);
                return;
            }

            try (
                Connection conn = DriverManager.getConnection(
                    url,
                    user,
                    password
                )
            ) {
                PreparedStatement st = conn.prepareStatement(
                    "SELECT * FROM Student WHERE id = ?"
                );
                st.setString(1, id);

                ResultSet rs = st.executeQuery();

                if (rs.next()) {
                    form.studentInputs
                        .get("First Name")
                        .setText(rs.getString("first"));
                    form.studentInputs
                        .get("Middle Initial")
                        .setText(rs.getString("middle"));
                    form.studentInputs
                        .get("Last Name")
                        .setText(rs.getString("last"));
                    form.studentInputs
                        .get("Street Number")
                        .setText(rs.getString("streetNumber"));
                    form.studentInputs
                        .get("Street Name")
                        .setText(rs.getString("streetName"));
                    form.studentInputs
                        .get("The Rest")
                        .setText(rs.getString("theRest"));
                    form.studentInputs
                        .get("City")
                        .setText(rs.getString("city"));
                    form.studentInputs
                        .get("State")
                        .setText(rs.getString("state"));
                    form.studentInputs
                        .get("Zip Code")
                        .setText(rs.getString("zip"));

                    form.studentInputs
                        .get("Major")
                        .setText(rs.getString("major"));
                    form.studentInputs
                        .get("Minor")
                        .setText(rs.getString("minor"));
                    form.studentInputs
                        .get("Undergrad Inst.")
                        .setText(rs.getString("undergradInst"));

                    double stipend = rs.getDouble("stipend");
                    form.studentInputs
                        .get("Stipend")
                        .setText(String.valueOf(stipend));

                    append("Student found.\n", console);
                } else {
                    append("No student found.\n", console);
                }
            } catch (SQLException sqe) {
                append("Find error: " + sqe.getMessage(), console);
            }
        });
    }

    public void homePage() {
        VBox tableContainer = new VBox(10);

        Label stuLabel = new Label("Students");
        Label phoneLabel = new Label("Phone Numbers");
        Label courseLabel = new Label("Courses");

        tableContainer
            .getChildren()
            .addAll(
                stuLabel,
                stuRecordTable,
                phoneLabel,
                phoneNumTable,
                courseLabel,
                courseTable
            );
        ScrollPane tableView = new ScrollPane(tableContainer);

        tableView.setStyle(
            "-fx-background-color: #e0e0e0; -fx-border-color: black;"
        );

        ScrollPane userInput = new ScrollPane();
        userInput.setStyle(
            "-fx-background-color: #f5f5f5; -fx-border-color: black;"
        );

        HBox centerSplit = new HBox();
        centerSplit.getChildren().addAll(tableView, userInput);

        HBox.setHgrow(tableView, javafx.scene.layout.Priority.ALWAYS);
        HBox.setHgrow(userInput, javafx.scene.layout.Priority.ALWAYS);

        BorderPane baseContainer = new BorderPane();
        baseContainer.setCenter(centerSplit);
        baseContainer.setBottom(console);

        // ------------------------------------------------     LOGIC START    ---------------------------------------------------------------------------

        /*
            Four CORE operations:
                - Add new record
                - Delete existing record
                - Update existing record
                - Find existing record
        */

        VBox tvWorkspace = new VBox(10);

        StackPane bannerHolder = new StackPane();
        Text banner = new Text("What needs to be done?");
        banner.setFont(Font.font("SF Pro Text", FontWeight.NORMAL, 18));

        bannerHolder.setPadding(new Insets(10, 20, 20, 0));

        bannerHolder.getChildren().add(banner);
        tvWorkspace.getChildren().addAll(bannerHolder);

        VBox buttonHolders = new VBox(8);

        Button addNewRecord = new Button("Add New Record"); // handleAddLogic()
        Button deleteExistingRecord = new Button("Delete Existing Record"); // handleDeleteLogic()
        Button updateExistingRecord = new Button("Update Existing Record"); // handleUpdateLogic()
        Button findExistingRecord = new Button("Find Existing Record"); // handleFindLogic

        buttonHolders
            .getChildren()
            .addAll(
                addNewRecord,
                updateExistingRecord,
                deleteExistingRecord,
                findExistingRecord
            );
        buttonHolders.setPadding(new Insets(0, 0, 0, 10));

        tvWorkspace.getChildren().addAll(buttonHolders);

        GridPane dynamicFormContainer = new GridPane();
        dynamicFormContainer.setPadding(new Insets(20));

        GridPane phoneNumberAdder = new GridPane();
        phoneNumberAdder.setPadding(new Insets(20));

        GridPane courseAdder = new GridPane();
        courseAdder.setPadding(new Insets(20));

        tvWorkspace
            .getChildren()
            .addAll(dynamicFormContainer, phoneNumberAdder, courseAdder);

        StudentForm received = buildStudentForm(
            dynamicFormContainer,
            phoneNumberAdder,
            courseAdder
        );

        handleAddLogic(received);

        handleDeleteLogic(deleteExistingRecord);

        handleFindLogic(received, findExistingRecord);

        handleUpdateLogic(received, updateExistingRecord);

        // updateExistingRecord.setOnAction(event -> {
        //     update(dynamicFormContainer);
        // });

        // findExistingRecord.setOnAction(event -> {
        //     find(dynamicFormContainer);
        // });

        userInput.setContent(tvWorkspace);

        setupTables();

        stageRef.setOnShown(e -> {
            updateTables();
        });

        // ------------------------------------------------     LOGIC END    ---------------------------------------------------------------------------

        Scene sc = new Scene(baseContainer, 1000, 900);
        stageRef.setScene(sc);
        stageRef.setTitle("Home Page");
        stageRef.show();
    }
}
