/**
 * Medlemmer:
 * Niclas Schilling Mølby: nicmo15
 * Jebisan Nadarajah: jenad14
 * Emil Villefrance: emvil15
 */
public class Node {

    private Node LeftChild;
    private Node rightChild;
    private int key;

    public Node getLeftChild() {
        return LeftChild;
    }

    public void setLeftChild(Node leftChild) {
        LeftChild = leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
}
