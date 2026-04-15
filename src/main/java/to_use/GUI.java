package to_use;

import javafx.application.Application;
import javafx.stage.Stage;

public class GUI extends Application {

    public void start(Stage primaryStage) {
        ViewManager vm = new ViewManager(primaryStage);

        vm.homePage();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
