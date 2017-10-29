package algo;

/**
 * Created by Fernando on 04/10/2017.
 */
public class DancingLinksHeader extends DancingLinksNode {

    private int headerId;
    private int count;

    public DancingLinksHeader(int id){
        super(-1);
        this.headerId = id;
    }

    public int getHeaderId () {return headerId;}
    public void setHeaderId (int headerId) {this.headerId = headerId;}
    public int getCount () {return count;}
    public void setCount (int count) {this.count += count;}
}