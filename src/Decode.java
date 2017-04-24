import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by niclasmolby on 24/04/2017.
 */
public class Decode {
    private int[] freqArray = new int[256];
    private PQ heap = new PQHeap(256);
    private Node root;
    private int totalBytes = 0;

    public Decode() {
        try {
            BitInputStream in = new BitInputStream(new FileInputStream("out.txt"));

            for(int i = 0; i < freqArray.length; i++) {
                int bytes = in.readInt();
                //System.out.println(bytes);
                freqArray[i] = bytes;
                totalBytes += bytes;
            }

            root = (Node) huffman().data;
            System.out.println(root.getRightChild().getRightChild().getLeftChild().getRightChild().getRightChild().getByteValue());
            //System.out.println(totalBytes);
            Node temp = root;
            int bytesRead = 0;

            int bit;
            while ((bit = in.readBit()) != -1 && bytesRead != totalBytes) {
                System.out.print(bit);
                if(temp.isLeaf()) {
                    System.out.println(" "+temp.getByteValue());
                    temp = root;
                    bytesRead++;
                }
                else if (bit == 0) {
                        temp = temp.getLeftChild();
                }
                else if (bit == 1) {
                        temp = temp.getRightChild();
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Decode();
    }

    private Element huffman() {
        initializePQ();

        for (int i = 0; i < freqArray.length-1; i++) {
            Node newTreeNode = new Node();

            Element leftChild = heap.extractMin();
            Element rightChild = heap.extractMin();

            newTreeNode.setLeftChild((Node) leftChild.data);
            newTreeNode.setRightChild((Node) rightChild.data);

            heap.insert(new Element(leftChild.frequency + rightChild.frequency, newTreeNode));
        }
        return heap.extractMin();
    }

    private void decodeHuffman() {

    }

    private void initializePQ() {
        for (int i = 0; i < freqArray.length; i++) {
            Node newNode = new Node();
            newNode.setByteValue(i);
            heap.insert(new Element(freqArray[i], newNode));
        }
    }
}
