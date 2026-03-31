//Group 16 - 3/19/26

/**
GUI.java
This class runs the GUI for the application. 
It allows you to populate a TableView with StudentRecord objects, save to a binary file, and load from a binary file.
*/

package to_use;

import javafx.application.Application;
import javafx.application.Platform;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import javafx.embed.swt.FXCanvas;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.BufferedWriter;

public class GUI extends Application {
    // table view
    TableView<StudentRecord> recordView;

    // list of students
    static ObservableList<StudentRecord> obsStudentList = FXCollections.observableArrayList();

    TextField savePathField = new TextField();
    TextField loadPathField = new TextField();

    TextField idField = new TextField();
    TextField firstField = new TextField();
    TextField middleField = new TextField();
    TextField lastField = new TextField();

    TextField streetNumField = new TextField();
    TextField streetNameField = new TextField();
    TextField restField = new TextField();
    TextField cityField = new TextField();
    TextField stateField = new TextField();
    TextField zipField = new TextField();

    TextField areaCodeField = new TextField();
    TextField exchangeField = new TextField();
    TextField extensionField = new TextField();

    TextField majorField = new TextField();
    TextField minorField = new TextField();
    TextField undergradInstField = new TextField();
    TextField stipendField = new TextField();

    ComboBox<String> studentTypeBox = new ComboBox<>();

    // update the observable list with an arraylist passed in
    public static void updateListFromArrayList(ArrayList<StudentRecord> in) {
        obsStudentList.setAll(in);
        System.out.println(obsStudentList.toString());
    }

    private void exportStudentList() {
        Path savePath = Path.of(savePathField.getText());

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(savePath.toFile())))
        {
            StudentArray arr = new StudentArray();
            arr.setStudentList(new ArrayList<>(obsStudentList));

            oos.writeObject(arr);

            System.out.println("Saved to " + savePath);
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    private void importStudentList() {
        Path loadPath = Path.of(loadPathField.getText());

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(loadPath.toFile()))) 
        {
            StudentArray loadedArr = (StudentArray) ois.readObject();

            obsStudentList.setAll(loadedArr.getStudentList());

            System.out.println("Loaded from " + loadPath);
            recordView.refresh();
        } catch (Exception ex) 
        {
            ex.printStackTrace();
            showError(ex.getMessage());
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private String getMajor(StudentRecord s) {
        if (s instanceof Undergraduate) {
            return ((Undergraduate) s).getMajor();
        }
        return "";
    }

    private String getMinor(StudentRecord s) {
        if (s instanceof Undergraduate) {
            return ((Undergraduate) s).getMinor();
        }
        return "";
    }

    private String getUndergradInst(StudentRecord s) {
        if (s instanceof Graduate) {
            return ((Graduate) s).getUndergradInst();
        }
        return "";
    }

    private String getStipend(StudentRecord s) {
        if (s instanceof Graduate) {
            return String.valueOf(((Graduate) s).getStipend());
        }
        return "";
    }

    private StudentRecord buildStudentFromFields() {
        Name name = new Name(firstField.getText(), middleField.getText(), lastField.getText());

        Address address = new Address(
                Integer.parseInt(streetNumField.getText()),
                streetNameField.getText(),
                restField.getText(),
                cityField.getText(),
                stateField.getText(),
                zipField.getText());

        PhoneList phoneList = new PhoneList();
        PersonInfo personInfo = new PersonInfo(name, address, phoneList);

        if (!areaCodeField.getText().isBlank()
                && !exchangeField.getText().isBlank()
                && !extensionField.getText().isBlank()) {
            personInfo.addNewNumber(areaCodeField.getText(), exchangeField.getText(), extensionField.getText());
        }

        if ("Graduate".equals(studentTypeBox.getValue())) {
            return new Graduate(
                    idField.getText(),
                    personInfo,
                    undergradInstField.getText(),
                    Double.parseDouble(stipendField.getText()));
        } else {
            return new Undergraduate(
                    idField.getText(),
                    personInfo,
                    majorField.getText(),
                    minorField.getText());
        }
    }

    private void clearFields() {
        idField.clear();
        firstField.clear();
        middleField.clear();
        lastField.clear();

        streetNumField.clear();
        streetNameField.clear();
        restField.clear();
        cityField.clear();
        stateField.clear();
        zipField.clear();

        areaCodeField.clear();
        exchangeField.clear();
        extensionField.clear();

        majorField.clear();
        minorField.clear();
        undergradInstField.clear();
        stipendField.clear();
    }

    private void updateTypeFieldState() {
        boolean isGrad = "Graduate".equals(studentTypeBox.getValue());

        majorField.setDisable(isGrad);
        minorField.setDisable(isGrad);

        undergradInstField.setDisable(!isGrad);
        stipendField.setDisable(!isGrad);
    }

    @Override
    public void start(Stage primaryStage) {
        recordView = new TableView<>();

        // create columns
        TableColumn<StudentRecord, Integer> idColumn = new TableColumn<>("ID");
        TableColumn<StudentRecord, String> nameColumn = new TableColumn<>("Full Name");
        TableColumn<StudentRecord, String> studentTypeColumn = new TableColumn<>("Student Type");
        TableColumn<StudentRecord, String> phoneColumn = new TableColumn<>("Phone Numbers");
        TableColumn<StudentRecord, String> addrColumn = new TableColumn<>("Address");
        TableColumn<StudentRecord, String> transcriptColumn = new TableColumn<>("Transcript");

        TableColumn<StudentRecord, String> majorColumn = new TableColumn<>("Major");
        TableColumn<StudentRecord, String> minorColumn = new TableColumn<>("Minor");
        TableColumn<StudentRecord, String> undergradInstColumn = new TableColumn<>("Undergrad Inst");
        TableColumn<StudentRecord, String> stipendColumn = new TableColumn<>("Stipend");

        // bind columns to function outputs

        // bind ID
        // wizardry to cast id (int) to Integer
        idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());

        // bind name
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentName()));

        // bind student type
        studentTypeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClass().getSimpleName()));

        // bind phone
        phoneColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentPhoneString()));

        // bind address
        addrColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentAddress()));

        // bind transcript
        transcriptColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentTranscript().toString()));

        // bind major
        majorColumn.setCellValueFactory(cellData -> new SimpleStringProperty(getMajor(cellData.getValue())));

        // bind minor
        minorColumn.setCellValueFactory(cellData -> new SimpleStringProperty(getMinor(cellData.getValue())));

        // bind undergradInst
        undergradInstColumn.setCellValueFactory(cellData -> new SimpleStringProperty(getUndergradInst(cellData.getValue())));

        // bind stipend
        stipendColumn.setCellValueFactory(cellData -> new SimpleStringProperty(getStipend(cellData.getValue())));


        recordView.getColumns().addAll(idColumn, nameColumn, studentTypeColumn, phoneColumn, addrColumn, transcriptColumn, majorColumn, minorColumn, undergradInstColumn, stipendColumn);
        recordView.setItems(obsStudentList);
        recordView.refresh();

        Button addButton = new Button("Add");
        Button deleteButton = new Button("Delete by ID");
        Button updateButton = new Button("Update by ID");
        Button findButton = new Button("Find by ID");

        Label saveLabel = new Label("Save : ");
        Button saveButton = new Button("Save");

        Label loadLabel = new Label("Load: ");
        Button loadButton = new Button("Load");


        studentTypeBox.getItems().addAll("Undergraduate", "Graduate");
        studentTypeBox.setValue("Undergraduate");

        idField.setPromptText("Student ID");
        firstField.setPromptText("First");
        middleField.setPromptText("Middle Initial");
        lastField.setPromptText("Last");

        streetNumField.setPromptText("Street Number");
        streetNameField.setPromptText("Street Name");
        restField.setPromptText("Apt / Suite");
        cityField.setPromptText("City");
        stateField.setPromptText("State");
        zipField.setPromptText("ZIP");

        areaCodeField.setPromptText("Area");
        exchangeField.setPromptText("Exchange");
        extensionField.setPromptText("Extension");

        majorField.setPromptText("Major");
        minorField.setPromptText("Minor");
        undergradInstField.setPromptText("Undergrad Institution");
        stipendField.setPromptText("Stipend");

        savePathField.setPromptText("Enter a full path");
        loadPathField.setPromptText("Enter a full path");

        updateTypeFieldState();

        HBox typeRow = new HBox(5, new Label("Type:"), studentTypeBox, new Label("ID:"), idField);
        HBox nameRow = new HBox(5, firstField, middleField, lastField);
        HBox addrRow1 = new HBox(5, streetNumField, streetNameField, restField);
        HBox addrRow2 = new HBox(5, cityField, stateField, zipField);
        HBox phoneRow = new HBox(5, areaCodeField, exchangeField, extensionField);
        HBox ugRow = new HBox(5, majorField, minorField);
        HBox gradRow = new HBox(5, undergradInstField, stipendField);
        HBox actionRow = new HBox(5, addButton, deleteButton, updateButton, findButton);
        HBox saveHBox = new HBox(saveLabel, savePathField, saveButton);
        HBox loadHBox = new HBox(loadLabel, loadPathField, loadButton);

        typeRow.setAlignment(Pos.CENTER);
        nameRow.setAlignment(Pos.CENTER);
        addrRow1.setAlignment(Pos.CENTER);
        addrRow2.setAlignment(Pos.CENTER);
        phoneRow.setAlignment(Pos.CENTER);
        ugRow.setAlignment(Pos.CENTER);
        gradRow.setAlignment(Pos.CENTER);
        actionRow.setAlignment(Pos.CENTER);
        saveHBox.setAlignment(Pos.CENTER);
        loadHBox.setAlignment(Pos.CENTER);

        VBox vstack = new VBox(recordView, typeRow, nameRow, addrRow1, addrRow2, phoneRow, ugRow, actionRow, saveHBox, loadHBox);
        vstack.setSpacing(5);
        vstack.setAlignment(Pos.CENTER);

        StackPane mainbox = new StackPane(vstack);
        mainbox.setPadding(new Insets(10, 10, 10, 10));

        //bind event handlers
        studentTypeBox.setOnAction(e -> updateTypeFieldState());

        addButton.setOnAction(e -> {
            try {
                StudentRecord student = buildStudentFromFields();
                obsStudentList.add(student);
                recordView.refresh();
                clearFields();
            } catch (Exception ex) {
                ex.printStackTrace();
                showError(ex.getMessage());
            }
        });

        deleteButton.setOnAction(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                StudentRecord found = null;

                for (StudentRecord s : obsStudentList) {
                    if (s.getId() == id) {
                        found = s;
                        break;
                    }
                }

                if (found != null) {
                    obsStudentList.remove(found);
                    recordView.refresh();
                } else {
                    showError("Student ID not found.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                showError(ex.getMessage());
            }
        });

        findButton.setOnAction(e -> {
            try {
                int id = Integer.parseInt(idField.getText());

                for (StudentRecord s : obsStudentList) {
                    if (s.getId() == id) {
                        recordView.getSelectionModel().select(s);
                        recordView.scrollTo(s);
                        return;
                    }
                }

                showError("Student ID not found.");
            } catch (Exception ex) {
                ex.printStackTrace();
                showError(ex.getMessage());
            }
        });

        updateButton.setOnAction(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                StudentRecord updated = buildStudentFromFields();

                for (int i = 0; i < obsStudentList.size(); i++) {
                    if (obsStudentList.get(i).getId() == id) {
                        obsStudentList.set(i, updated);
                        recordView.refresh();
                        clearFields();
                        return;
                    }
                }

                showError("Student ID not found.");
            } catch (Exception ex) {
                ex.printStackTrace();
                showError(ex.getMessage());
            }
        });

        saveButton.setOnAction(e -> exportStudentList());
        loadButton.setOnAction(e -> importStudentList());

        Scene scene = new Scene(mainbox, 700, 400);
        primaryStage.setTitle("View Students"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        System.out.println("Showed!");
    }

    public static void main(String[] args) {
        launch();
    }
}
