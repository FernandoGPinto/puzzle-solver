package algo;

import javafx.scene.control.Alert;
import view.Main;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Fernando on 04/10/2017.
 */
public class ExactCoverSolver implements Solver {

    private ArrayList<Integer> solutionSet = new ArrayList<>();
    private ExactCoverMatrix puzzle;
    private Main main;
    private int size;

    public ExactCoverSolver(ExactCoverMatrix puzzle, int clue, int size, Main main) throws Exception {
        this.puzzle = puzzle;
        this.main = main;
        this.size = size;
        loadClue(clue);
    }

    @Override
    public void loadClue(int clue) throws Exception {

        solutionSet.add(clue);
        DancingLinksHeader header;
        DancingLinksNode node = null;

        for(header = (DancingLinksHeader)puzzle.HEAD.getEast(); header != puzzle.HEAD; header = (DancingLinksHeader)header.getEast()){
            for(DancingLinksNode row = header.getSouth(); row != header; row = row.getSouth()){
                if(row.getNodeId() == clue){
                    node = row;
                    break;
                }
            }
        }
        cover(puzzle.getColumnHeader(node));

        for(DancingLinksNode next = node.getEast(); next != node; next = next.getEast()){
            cover(puzzle.getColumnHeader(next));
        }
        search();
    }

    @Override
    public void search() throws Exception {

        DancingLinksHeader header = puzzle.chooseNextColumn();

        if(header == puzzle.HEAD) {
            Collections.sort(solutionSet);
            main.showSolution(solutionSet, size);
            return;
        }

        DancingLinksNode node = header.getSouth(); // NEED TO HANDLE HEADERS WITH NO NODES (ie. call chooseNextColumn())
        DancingLinksNode next;
        pushToSolutionSet(node.getNodeId());
        cover(header);
        //covering removes links between nodes and headers in the matrix and those to be discarded
        //either because they are now part of the solution or conflict with it
        //however removed nodes/headers remain linked to each other and to nodes within the matrix allowing us to uncover (ie. backtrack)
        for(next = node.getEast(); next != node; next = next.getEast()){
            cover(puzzle.getColumnHeader(next));
        }
        try {
            search();
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(this.main.getPrimaryStage());
            alert.setTitle("No Solution Found");
            alert.setHeaderText("Please try again.");
            alert.showAndWait();
        }
        //backtrack
        popFromSolutionSet();

        for(DancingLinksNode previous = next.getWest(); previous != next; previous = previous.getWest()){
            uncover(puzzle.getColumnHeader(previous));
        }
        uncover(header);
        // * uncovering will not uncover cover performed by loadClues() *
    }

    @Override
    public void cover(DancingLinksHeader header) {

        header.getEast().setWest(header.getWest());
        header.getWest().setEast(header.getEast());

        for(DancingLinksNode current = header.getSouth(); current != header; current = current.getSouth()){
            for(DancingLinksNode row = current.getEast(); row != current; row = row.getEast()){
                row.getNorth().setSouth(row.getSouth());
                row.getSouth().setNorth(row.getNorth());
                puzzle.decreaseHeaderCounter(row);
            }
        }
    }

    @Override
    public void uncover(DancingLinksHeader header) {

        for(DancingLinksNode current = header.getNorth(); current != header; current = current.getNorth()){
            for(DancingLinksNode row = current.getWest(); row != current; row = row.getWest()){
                row.getNorth().setSouth(row);
                row.getSouth().setNorth(row);
                puzzle.increaseHeaderCounter(row);
            }
        }
        header.getEast().setWest(header);
        header.getWest().setEast(header);
    }

    @Override
    public void pushToSolutionSet(int nodeId) {

        solutionSet.add(nodeId);
    }

    @Override
    public void popFromSolutionSet() {

        solutionSet.remove(solutionSet.size()-1);
    }
}