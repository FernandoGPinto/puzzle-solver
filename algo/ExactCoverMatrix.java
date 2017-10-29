package algo;

import java.util.ArrayList;
import java.util.function.BiFunction;

/**
 * Created by Fernando on 04/10/2017.
 */
public class ExactCoverMatrix {

    static final DancingLinksHeader HEAD = new DancingLinksHeader(0);

    public ExactCoverMatrix(int size){
        setUpMatrix(size);
    }

    public void setUpMatrix(int size) {

        int n = size;
        BiFunction<Integer, Integer, Integer> first = (x, y) -> x / y;
        BiFunction<Integer, Integer, Integer> second = (x, y) -> (y * y) + (y * (x % y)) + (x / y) - (y * (x / (y * y)));
        BiFunction<Integer, Integer, Integer> third = (x, y) -> (y * y * 2) + (y * (x % y)) + 2 * (x / (y * y)) - ((x / (y * y)));

        DancingLinksHeader current = HEAD;
        ArrayList<DancingLinksHeader> headers = new ArrayList<>();
        for (int i = 1; i <= n * n * 3; i++) {
            headers.add((i - 1), new DancingLinksHeader(i));
            current.setEast(headers.get(i - 1));
            headers.get(i - 1).setWest(current);
            current = headers.get(i - 1);
        }
        HEAD.setWest(current);
        current.setEast(HEAD);

        //add rows

        DancingLinksNode firstHeader;
        DancingLinksNode secondHeader;
        DancingLinksNode thirdHeader;

        for (int i = 0; i < Math.pow(n, 3); i++) {
            firstHeader = headers.get(first.apply(i, n));
            secondHeader = headers.get(second.apply(i, n));
            thirdHeader = headers.get(third.apply(i, n));

            while (firstHeader.getSouth() != null) {
                firstHeader = firstHeader.getSouth();
            }
            while (secondHeader.getSouth() != null){
                secondHeader = secondHeader.getSouth();
            }
            while (thirdHeader.getSouth() != null){
                thirdHeader = thirdHeader.getSouth();
            }

            DancingLinksNode firstNode = new DancingLinksNode(i);
            firstNode.setNorth(firstHeader);
            firstHeader.setSouth(firstNode);
            increaseHeaderCounter(firstNode);

            DancingLinksNode secondNode = new DancingLinksNode(i);
            secondNode.setNorth(secondHeader);
            secondHeader.setSouth(secondNode);
            firstNode.setEast(secondNode);
            secondNode.setWest(firstNode);
            increaseHeaderCounter(secondNode);

            DancingLinksNode thirdNode = new DancingLinksNode(i);
            thirdNode.setNorth(thirdHeader);
            thirdHeader.setSouth(thirdNode);
            secondNode.setEast(thirdNode);
            thirdNode.setWest(secondNode);
            firstNode.setWest(thirdNode);
            thirdNode.setEast(firstNode);
            increaseHeaderCounter(thirdNode);

            if (getColumnHeader(firstNode).getCount() == n) {
                getColumnHeader(firstNode).setNorth(firstNode);
                firstNode.setSouth(getColumnHeader(firstNode));
            }
            if (getColumnHeader(secondNode).getCount() == n) {
                getColumnHeader(secondNode).setNorth(secondNode);
                secondNode.setSouth(getColumnHeader(secondNode));
            }
            if (getColumnHeader(thirdNode).getCount() == n) {
                getColumnHeader(thirdNode).setNorth(thirdNode);
                thirdNode.setSouth(getColumnHeader(thirdNode));
            }
        }
    }

    public DancingLinksHeader chooseNextColumn(){

        DancingLinksHeader current = (DancingLinksHeader) HEAD.getEast();
        DancingLinksHeader next = (DancingLinksHeader) current.getEast();

        while(next != HEAD) {
            if (current.getCount() > next.getCount()) {
                current = next;
            }
            next = (DancingLinksHeader) next.getEast();
        }
        return current;
    }

    public DancingLinksHeader getColumnHeader(DancingLinksNode node){

        DancingLinksHeader header = null;

        for(DancingLinksNode current = node.getNorth(); current != node; current = current.getNorth()){
            if(current.getNodeId() == -1){
                header = (DancingLinksHeader) current;
                break;
            }
        }
        return header; //check for null when receiving value from this method
    }

    public void increaseHeaderCounter(DancingLinksNode node){

        while(node.getNodeId() != -1){
            node = node.getNorth();
        }
        DancingLinksHeader header = (DancingLinksHeader) node;
        header.setCount(1);
    }

    public void decreaseHeaderCounter(DancingLinksNode node){

        while(node.getNodeId() != -1){
            node = node.getNorth();
        }
        DancingLinksHeader header = (DancingLinksHeader) node;
        header.setCount(-1);
    }
}