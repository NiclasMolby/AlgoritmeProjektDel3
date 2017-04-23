import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by niclasmolby on 20/04/2017.
 */
public class Encode {

    int[] byteArray = new int[256];
    PQ heap = new PQHeap(256);

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

    private synchronized final Element hoffman() {
        int size = byteArray.length;
        initializePQ();

        for (int i = 0; i < size; i++) {
            DictBinTree newNode = new DictBinTree();
            int leftChildFreq = heap.extractMin().key;
            int rightChildFreq = heap.extractMin().key;

            newNode.insert(leftChildFreq);
            newNode.insert(rightChildFreq);

            newNode.setFreq(leftChildFreq+rightChildFreq);

            heap.insert(new Element(leftChildFreq+rightChildFreq, newNode));



        }
        return heap.extractMin();
    }

    private void initializePQ() {
        for (int i = 0; i < byteArray.length; i++) {
            System.out.println(byteArray.length);
                Dict tree = new DictBinTree();
                tree.insert(i);
                heap.insert(new Element(byteArray[i], tree));

        }
    }
}
