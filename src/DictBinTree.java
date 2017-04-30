/**
 * Medlemmer:
 * Niclas Schilling Mølby: nicmo15
 * Jebisan Nadarajah: jenad14
 * Emil Villefrance: emvil15
 */
public class DictBinTree implements Dict {

    private Node root = null;

    public DictBinTree() {

    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

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
