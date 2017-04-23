import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by niclasmolby on 20/04/2017.
 */
public class Encode {

    private int[] byteArray = new int[256];
    private PQ heap = new PQHeap(256);
    private Dict tree = new DictBinTree();

    public static void main(String[] args) {
        new Encode(args);
    }

    public Encode(String[] args) {
        try {
            //BitInputStream in = new BitInputStream(new FileInputStream(args[0]));
            FileInputStream file = new FileInputStream(args[0]);

            int bytes;
            while ( (bytes = file.read()) != -1 ) {
                byteArray[bytes]++;
            }
            Element elementalShaman = hoffman();
            int[] i = ((Dict) elementalShaman.data).orderedTraversal();
            System.out.println(Arrays.toString(i));


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Element hoffman() {
        int size = byteArray.length;
        initializePQ();

        for (int i = 0; i < size; i++) {
            Node newTreeNode = new Node();

            Element leftChild = heap.extractMin();
            Element rightChild = heap.extractMin();

            newTreeNode.setLeftChild((Node) leftChild.data);
            newTreeNode.setRightChild((Node) rightChild.data);

            newTreeNode.setKey(leftChild.key+rightChild.key);

            heap.insert(new Element(newTreeNode.getKey(), newTreeNode));



        }
        return heap.extractMin();
    }

    private void initializePQ() {
        for (int i = 0; i < byteArray.length; i++) {
            System.out.println(byteArray.length);
                Node newNode = new Node();
                newNode.setKey(i);
                heap.insert(new Element(byteArray[i], newNode));

        }
    }
}
