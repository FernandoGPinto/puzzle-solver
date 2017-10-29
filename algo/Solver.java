package algo;

/**
 * Created by Fernando on 04/10/2017.
 */
public interface Solver {

    void loadClue(int clue) throws Exception;
    void search() throws Exception;
    void cover(DancingLinksHeader header);
    void uncover(DancingLinksHeader header);
    void pushToSolutionSet(int nodeID);
    void popFromSolutionSet();
}
