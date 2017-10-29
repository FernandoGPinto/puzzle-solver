package view;

import algo.ExactCoverMatrix;
import algo.ExactCoverSolver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * Created by Fernando on 12/09/2017.
 */

public class UserInputController {

    Main main;

    @FXML private TextField size;

    @FXML private TextField row;

    @FXML private TextField col;

    @FXML private TextField dig;

    public void setMain(Main main) {
        this.main = main;
    }

    @FXML protected void handleRun() throws Exception {
        if(isInputValid()) {
            int side = Integer.parseInt(size.getText());
            ExactCoverMatrix puzzle = new ExactCoverMatrix(side);
            int clue = ((Integer.parseInt(col.getText())-1)*(side*side)) + ((Integer.parseInt(row.getText())-1)*side)
                    + (Integer.parseInt(dig.getText())-1);
            System.out.println(clue);
            new ExactCoverSolver(puzzle, clue, side, main);
        }
    }

    public boolean isInputValid(){

        String errorMessage = "";

        if(this.size.getText() != null && this.size.getText().length() != 0) {
            try {
                if(Integer.parseInt(this.size.getText()) < 2 || Integer.parseInt(this.size.getText()) > 50) {
                    errorMessage += "Size must be larger than 2 and smaller than 50.\n";
                }
            } catch (NumberFormatException e) {
                errorMessage += "Size must be an integer!\n";
            }
        } else {
            errorMessage += "Please enter a size!\n";
        }

        if(this.row.getText() != null && this.col.getText() != null && this.dig.getText() != null
                && this.row.getText().length() != 0 && this.col.getText().length() != 0 && this.dig.getText().length() != 0
                && !this.row.getText().equals("row") && !this.col.getText().equals("column") && !this.dig.getText().equals("digit")) {
            try {
                if(Integer.parseInt(this.row.getText()) > Integer.parseInt(size.getText())
                        || Integer.parseInt(this.row.getText()) < 1) {
                    errorMessage += "Row must be from 1 to size.\n";
                }
                if(Integer.parseInt(this.col.getText()) > Integer.parseInt(size.getText())
                        || Integer.parseInt(this.col.getText()) < 1) {
                    errorMessage += "Column must be from 1 to size.\n";
                }
                if(Integer.parseInt(this.dig.getText()) > Integer.parseInt(size.getText())
                        || Integer.parseInt(this.dig.getText()) < 1) {
                    errorMessage += "Digit must be from 1 to size.\n";
                }
            } catch (NumberFormatException e) {
                errorMessage += "Please enter integers only!\n";
            }
        } else {
            errorMessage += "Please enter a clue!\n";
        }

        if(errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(this.main.getPrimaryStage());
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }

    public void clearDig(MouseEvent mouseEvent) {

        if(dig.getText().equals("digit")) dig.clear();
    }

    public void clearCol(MouseEvent mouseEvent) {

        if(col.getText().equals("column")) col.clear();
    }

    public void clearRow(MouseEvent mouseEvent) {

        if(row.getText().equals("row")) row.clear();
    }

    public void handleInstructions(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(this.main.getPrimaryStage());
        alert.setTitle("Instructions");
        alert.setHeaderText("Puzzle Solver Instructions");
        alert.setContentText("The puzzle solver was designed to solve sudoku style square matrices with three constraints:\n " +
                "1- There must be a digit in every cell between zero and the size of the puzzle (inclusive)\n" +
                "2- Digits cannot be repeated in each row\n" +
                "3- Digits cannot be repeated in each column\n\n" +
                "The size of the puzzle corresponds to the number of cells in each side (ie. number of rows or number of columns)" +
                "and the puzzle solver accepts a number between 2 and 50.\n\n" +
                "The user must enter a clue in the form of a digit in a selected cell, by indicating the column and row where it lies.\n" +
                "Eg. Size: 5, Clue: column=3 row=2 digit=4");
        alert.showAndWait();
    }
}