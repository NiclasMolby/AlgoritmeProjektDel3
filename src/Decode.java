import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Medlemmer:
 * Niclas Schilling Mølby: nicmo15
 * Jebisan Nadarajah: jenad14
 * Emil Villefrance: emvil15
 */
public class Decode {
    private int[] freqArray = new int[256];
    private PQ heap = new PQHeap(256);
    private DictBinTree tree = new DictBinTree();
    private int totalBytes = 0;
    private BitInputStream in;
    private FileOutputStream out;

    /**
     *
     * @param args argumenterne fra main metoden til skrivning og læsning af ind og output filer
     */
    public Decode(String[] args) {
        try {
            out = new FileOutputStream(args[1]);
            in = new BitInputStream(new FileInputStream(args[0]));

            readFrequencyBytes();

            initializePQ();
            tree.generateHuffmanTree(heap, freqArray.length);

            decodeHuffman();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Decode(args);
    }

    /**
     * Metode der læser frequencyen på bytesne fra inputfilen, samt tæller hvor mange bytes der er.
     * @throws IOException
     */
    private void readFrequencyBytes() throws IOException {
        for(int i = 0; i < freqArray.length; i++) {
            int bytes = in.readInt();
            freqArray[i] = bytes;
            totalBytes += bytes;
        }
    }

    /**
     * Metode der decoder huffman træet. Metoden kører inputfilen igennem og læser bitsne og bruger dem, til at finde stien
     * til den rigtige byte og skriver den ud i output filen.
     * @throws IOException
     */
    private void decodeHuffman() throws IOException {
        Node temp = tree.getRoot();
        int bytesRead = 0;

        int bit;
        while (bytesRead != totalBytes) {
            if(temp.isLeaf()) {
                out.write(temp.getByteValue());
                temp = tree.getRoot();
                bytesRead++;
            }
            else {
                bit = in.readBit();
                if (bit == 0) {
                    temp = temp.getLeftChild();
                } else if (bit == 1) {
                    temp = temp.getRightChild();
                }
            }
        }
    }

    /**
     * Metode der sørger for at oprette den priotetskø, der bruges til at oprette huffman træet
     */
    private void initializePQ() {
        for (int i = 0; i < freqArray.length; i++) {
            Node newNode = new Node();
            newNode.setByteValue(i);
            heap.insert(new Element(freqArray[i], newNode));
        }
    }
}
