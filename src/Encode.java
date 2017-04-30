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
            scanInputBytesForFrequency(args[0]);

            initializePQ();
            tree.generateHuffmanTree(heap, byteArray.length);

            encodeHuffmanTree();

            writeCodeToOutput(args[0], args[1]);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Encode(args);
    }

    private void scanInputBytesForFrequency(String args) throws IOException {
        FileInputStream firstScan = new FileInputStream(args);
        int firstByteScan;
        while ((firstByteScan = firstScan.read()) != -1) {
            byteArray[firstByteScan]++;
        }
    }

    private void writeCodeToOutput(String input, String output) throws IOException {
        FileInputStream secondScan = new FileInputStream(input);
        BitOutputStream out = new BitOutputStream(new FileOutputStream(output));

        for(int byteFreq : byteArray) {
            out.writeInt(byteFreq);
        }

        int secondByteScan;
        while ((secondByteScan = secondScan.read()) != -1) {
            String[] codeCharArray = codewordArray[secondByteScan].split("");
            for (String c : codeCharArray) {
                out.writeBit(Integer.valueOf(c));
            }
        }

        out.writeBit(0);
        out.writeBit(1);
        out.close();
    }

    private void encodeHuffmanTree() {
        huffmanTreeWalk(tree.getRoot(), "", "");
    }

    private void huffmanTreeWalk(Node n, String pathSoFar, String childDirection) {
        String[] codewordArray = new String[256];
        if(n != null) {
            pathSoFar = pathSoFar+childDirection;
            huffmanTreeWalk(n.getLeftChild(), pathSoFar, "0");
            huffmanTreeWalk(n.getRightChild(), pathSoFar, "1");
            if(n.isLeaf()) {
                codewordArray[n.getByteValue()] = pathSoFar;
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
