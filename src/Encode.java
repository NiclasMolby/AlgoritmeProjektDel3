import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by niclasmolby on 20/04/2017.
 */
public class Encode {

    private int[] byteArray = new int[256];
    private String[] codewordArray = new String[256];
    private PQ heap = new PQHeap(256);
    private DictBinTree tree = new DictBinTree();

    public Encode(String[] args) {
        try {
            //BitInputStream in = new BitInputStream(new FileInputStream(args[0]));
            FileInputStream file = new FileInputStream(args[0]);
            FileInputStream file2 = new FileInputStream(args[0]);
            BitOutputStream out = new BitOutputStream(new FileOutputStream("out.txt"));

            int bytes;
            while ((bytes = file.read()) != -1) {
                byteArray[bytes]++;
            }

            tree.setRoot((Node) hoffman().data);
            Node temp = tree.getRoot().getRightChild().getLeftChild();
            System.out.println("Value: " + temp.getByteValue() + " // ");
            encodeHoffmanTree();

            for(int byteFreq : byteArray) {
                out.writeInt(byteFreq);
            }

            int bytes2;
            while ((bytes2 = file2.read()) != -1) {
                String codeCharArray = codewordArray[bytes2];
                String[] codeCharArray2 = codewordArray[bytes2].split("");
                System.out.println(codeCharArray + " " + bytes2);
                //System.out.println(codeCharArray);
                for (String c : codeCharArray2) {
                    out.writeBit(Integer.valueOf(c));
                    //System.out.println(c);
                }
            }

            out.writeBit(0);
            out.writeBit(1);
            out.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Encode(args);
    }


    private Element hoffman() {
        initializePQ();

        for (int i = 0; i < byteArray.length-1; i++) {
            Node newTreeNode = new Node();

            Element leftChild = heap.extractMin();
            Element rightChild = heap.extractMin();

            newTreeNode.setLeftChild((Node) leftChild.data);
            newTreeNode.setRightChild((Node) rightChild.data);

            heap.insert(new Element(leftChild.frequency + rightChild.frequency, newTreeNode));
        }
        return heap.extractMin();
    }

    private void encodeHoffmanTree() {
        hoffmanTreeWalk(tree.getRoot(), "", "");
    }

    private void hoffmanTreeWalk(Node n, String pathSoFar, String childDirection){
        if(n != null) {
            pathSoFar = pathSoFar+childDirection;
            hoffmanTreeWalk(n.getLeftChild(), pathSoFar, "0");
            hoffmanTreeWalk(n.getRightChild(), pathSoFar, "1");
            if(n.isLeaf()) {
                codewordArray[n.getByteValue()] = pathSoFar;
                //System.out.println(n.getByteValue() + " " + pathSoFar);
            }
        }
    }


    private void initializePQ() {
        for (int i = 0; i < byteArray.length; i++) {
                Node newNode = new Node();
                newNode.setByteValue(i);
                heap.insert(new Element(byteArray[i], newNode));
        }
    }
}
