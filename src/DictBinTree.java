/**
 * Medlemmer:
 * Niclas Schilling Mølby: nicmo15
 * Jebisan Nadarajah: jenad14
 * Emil Villefrance: emvil15
 */
public class DictBinTree implements Dict {

    private Node root;

    public DictBinTree() {

        root = null;
    }

    /**
     * Setter metode til træets rod
     * @param root den node der skal sættes til træets rod
     */
    public void setRoot(Node root) {
        this.root = root;
    }

    /**
     * Getter metode til træets rod
     * @return den node der er træets rod
     */
    public Node getRoot() {
        return root;
    }

    /**
     * Metode der skal generere huffman træet
     * @param heap den priotets kø, der skal bruges til, at generere træet med
     * @param length længden på arrayet der indeholder frequency og derfor det antal bytes der skal indsættes i træet
     */
    public void generateHuffmanTree(PQ heap, int length) {
        for (int i = 0; i < length-1; i++) {
            Node newTreeNode = new Node();

            Element leftChild = heap.extractMin();
            Element rightChild = heap.extractMin();

            newTreeNode.setLeftChild((Node) leftChild.data);
            newTreeNode.setRightChild((Node) rightChild.data);

            heap.insert(new Element(leftChild.frequency + rightChild.frequency, newTreeNode));
        }

        root = (Node) heap.extractMin().data;
    }

}
