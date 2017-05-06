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

    /**
     * Getter metode til nodens venstre barn
     * @return den node der er det venstre barn
     */
    public Node getLeftChild() {
        return LeftChild;
    }

    /**
     * Setter metode til nodens venstre barn
     * @param leftChild den node, der skal være det venstre barn
     */
    public void setLeftChild(Node leftChild) {
        LeftChild = leftChild;
    }

    /**
     * Getter metode til nodens højre barn
     * @return den node der er det højre barn
     */
    public Node getRightChild() {
        return rightChild;
    }

    /**
     * Setter metode til nodens højre barn
     * @param rightChild den node der skal være det højre barn
     */
    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    /**
     * Getter metode på byteValue
     * @return byteValuen
     */
    public int getByteValue() {
        return byteValue;
    }

    /**
     * Setter metode på byteValue
     * @param byteValue den value som bytevalue skal have
     */
    public void setByteValue(int byteValue) {
        this.byteValue = byteValue;
    }

    /**
     * Metode der tjekker om en node er et blad
     * @return en boolean der siger, om noden er et blad
     */
    public boolean isLeaf() {
        return getLeftChild() == null && getRightChild() == null;
    }
}
