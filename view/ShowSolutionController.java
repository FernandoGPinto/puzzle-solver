package view;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

import java.util.ArrayList;

/**
 * Created by Fernando on 12/09/2017.
 */

public class ShowSolutionController {

    @FXML
    private GridPane matrix;

    public void updateGridPane(ArrayList<Integer> solution, int size) {

        for(int j = 0; j<size; j++){
            ColumnConstraints column = new ColumnConstraints();
            column.setHgrow(Priority.ALWAYS);
            matrix.getColumnConstraints().add(column);
            matrix.getRowConstraints().add(new RowConstraints(10, 20, 60, Priority.ALWAYS, VPos.CENTER, true));
        }

        for(int i = 0; i<Math.pow(size, 3); i++){
            if(solution.contains(i)) {
                HBox hbox = new HBox();
                int digit = (i % size) + 1;
                int col = i / (size*size);
                int row = (i / size) - (col*size);
                Label node = new Label(String.valueOf(digit));
                hbox.getStyleClass().add("matrix");
                hbox.getChildren().add(node);
                hbox.setAlignment(Pos.CENTER);
                matrix.add(hbox, col, row);
            }
        }
    }

    @FXML public void handleTryAgain(){

    }
}