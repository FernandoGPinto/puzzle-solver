package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by Fernando on 12/09/2017.
 */

public class Main extends Application {

    Stage primaryStage;

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        this.primaryStage = primaryStage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("UserInput.fxml"));
        Parent root = loader.load();
        UserInputController controller = loader.getController();
        controller.setMain(this);
        primaryStage.setTitle("Puzzle Solver");
        primaryStage.setScene(new Scene(root, 385, 325));
        primaryStage.show();
    }

    public void showSolution(ArrayList<Integer> solution, int size) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("ShowSolution.fxml"));
        Parent root = loader.load();
        ShowSolutionController controller = loader.getController();
        controller.updateGridPane(solution, size);
        primaryStage.setTitle("Solution");
        double sideLength = 150 * Math.pow(size, 0.5) > 1000 ? 1000 : 150 * Math.pow(size, 0.5);
        primaryStage.setScene(new Scene(root, sideLength, sideLength));
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
