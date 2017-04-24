/**
 * Created by niclasmolby on 28/02/2017.
 */
public interface PQ {
    Element extractMin();
    void insert(Element e);
    int getSize();
}
