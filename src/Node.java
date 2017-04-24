/**
 * Medlemmer:
 * Niclas Schilling Mølby: nicmo15
 * Jebisan Nadarajah: jenad14
 * Emil Villefrance: emvil15
 */
public class Node {

    private Node LeftChild = null;
    private Node rightChild = null;
    private int byteValue;

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

    public int getByteValue() {
        return byteValue;
    }

    public void setByteValue(int byteValue) {
        this.byteValue = byteValue;
    }

    public boolean isLeaf() {
        return getLeftChild() == null && getRightChild() == null;
    }
}
