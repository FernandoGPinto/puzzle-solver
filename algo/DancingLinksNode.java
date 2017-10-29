package algo;

/**
 * Created by Fernando on 04/10/2017.
 */
public class DancingLinksNode {

    private int nodeId;
    private DancingLinksNode north;
    private DancingLinksNode south;
    private DancingLinksNode west;
    private DancingLinksNode east;

    public DancingLinksNode() {}

    public DancingLinksNode(int id){
        this.nodeId = id;
    }

    public int getNodeId () {return nodeId;}
    public void setNodeId (int id) {this.nodeId = id;}
    public DancingLinksNode getNorth () {return north;}
    public void setNorth (DancingLinksNode north) {this.north = north;}
    public DancingLinksNode getSouth () {return south;}
    public void setSouth (DancingLinksNode south) {this.south = south;}
    public DancingLinksNode getWest () {return west;}
    public void setWest (DancingLinksNode west) {this.west = west;}
    public DancingLinksNode getEast () {return east;}
    public void setEast (DancingLinksNode east) {this.east = east;}

}