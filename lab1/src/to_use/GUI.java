package to_use;

import javafx.application.Application;
import javafx.application.Platform;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swt.FXCanvas;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        } catch (Exception ex) 
        {
            ex.printStackTrace();
        }
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

        // bind columns to function outputs

        // bind ID
        idColumn.setCellValueFactory(cellData ->
        // wizardry to cast id (int) to Integer
        new SimpleIntegerProperty(cellData.getValue().getId()).asObject());

        // bind name
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentName()));

        // bind student type
        studentTypeColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getClass().getSimpleName()));

        // bind phone
        phoneColumn
                .setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentPhoneString()));

        // bind address
        addrColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentAddress()));

        // bind transcript
        transcriptColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getStudentTranscript().toString()));

        recordView.getColumns().addAll(idColumn, nameColumn, studentTypeColumn, phoneColumn, addrColumn,
                transcriptColumn);
        recordView.setItems(obsStudentList);
        recordView.refresh();

        Label saveLabel = new Label("Save : ");
        Button saveButton = new Button("Save");

        Label loadLabel = new Label("Load: ");
        Button loadButton = new Button("Load");

        savePathField.setPromptText("Enter a full path");
        loadPathField.setPromptText("Enter a full path");

        HBox saveHBox = new HBox(saveLabel, savePathField, saveButton);
        HBox loadHBox = new HBox(loadLabel, loadPathField, loadButton);

        saveHBox.setAlignment(Pos.CENTER);
        loadHBox.setAlignment(Pos.CENTER);

        VBox vstack = new VBox(recordView, saveHBox, loadHBox);
        vstack.setSpacing(5);
        vstack.setAlignment(Pos.CENTER);

        StackPane mainbox = new StackPane(vstack);
        mainbox.setPadding(new Insets(10, 10, 10, 10));

        //bind event handlers
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
