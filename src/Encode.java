import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Medlemmer:
 * Niclas Schilling Mølby: nicmo15
 * Jebisan Nadarajah: jenad14
 * Emil Villefrance: emvil15
 */
public class Encode {

    private int[] byteArray = new int[256];
    private String[] codewordArray = new String[256];
    private PQ heap = new PQHeap(256);
    private DictBinTree tree = new DictBinTree();

    /**
     *
     * @param args argumenterne fra main metoden til skrivning og læsning af ind og output filer
     */
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

    /**
     * Metode der scanner input bytes og gemmer deres frequency i et int array
     * @param input filnavnet på input filen, som metoden skal læse og oprette frequency tabellen af
     * @throws IOException
     */
    private void scanInputBytesForFrequency(String input) throws IOException {
        FileInputStream firstScan = new FileInputStream(input);
        int firstByteScan;
        while ((firstByteScan = firstScan.read()) != -1) {
            byteArray[firstByteScan]++;
        }
    }

    /**
     * Metode der skriver frequency og koderne til output filen. Skriver et @ til sidst, for at indikere at skrivningen er slut og
     * at mændgen af bits passer.
     * @param input filnavnet på input filen, så metoden kan læse de bytes den skal skrive koden for
     * @param output filnavnet på output filen, så metoden ved, hvilken fil den skal skrive frequency og koden i
     * @throws IOException
     */
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

    /**
     * Metode der kalder treeWalk metoden
     */
    private void encodeHuffmanTree() {
        huffmanTreeWalk(tree.getRoot(), "", "");
    }

    /**
     * En rekursiv metode der foretager et treewalk på huffman træet, og sørger for, at skrive sti i træet, til den rigtige byte
     * @param n træ noden på den node, som treewalken skal starte fra
     * @param pathSoFar en streng som løbende holder stien under det rekursive kald. Skal være en tom streng under første kald.
     * @param childDirection en streng der indikere om der bliver gået til højre eller venstre under treewalk, så koden kan genereres korrekt. Skal være en tom streng under første kald.
     */
    private void huffmanTreeWalk(Node n, String pathSoFar, String childDirection) {
        if(n != null) {
            pathSoFar = pathSoFar+childDirection;
            huffmanTreeWalk(n.getLeftChild(), pathSoFar, "0");
            huffmanTreeWalk(n.getRightChild(), pathSoFar, "1");
            if(n.isLeaf()) {
                codewordArray[n.getByteValue()] = pathSoFar;
            }
        }
    }

    /**
     * Metode der sørger for at oprette den priotetskø, der bruges til at oprette huffman træet
     */
    private void initializePQ() {
        for (int i = 0; i < byteArray.length; i++) {
                Node newNode = new Node();
                newNode.setByteValue(i);
                heap.insert(new Element(byteArray[i], newNode));
        }
    }
}
