/**
 * Medlemmer:
 * Niclas Schilling Mølby: nicmo15
 * Jebisan Nadarajah: jenad14
 * Emil Villefrance: emvil15
 */
public class DictBinTree implements Dict {

    private Node root = null;
    private int size = 0;
    private int pointer;
    private int[] orderedArray;

    public DictBinTree() {

    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    public void printPathsRecur(Node node, int path[], int pathLen)
    {
        if (node == null)
            return;

        /* append this node to the path array */
        path[pathLen] = node.getByteValue();
        pathLen++;

        /* it's a leaf, so print the path that led to here  */
        if (node.getLeftChild() == null && node.getRightChild() == null){
            printArray(path, pathLen);
        }

        else
        {
            /* otherwise try both subtrees */
            printPathsRecur(node.getLeftChild(), path, pathLen);
            printPathsRecur(node.getRightChild(), path, pathLen);
        }
    }

    public void printArray(int ints[], int len)
    {
        int i;
        for (i = 0; i < len; i++)
        {
            System.out.print(ints[i] + " ");
        }
        System.out.println("");
    }

    /**
     *
     * @return returnerer et ordnet array af træets værdier.
     */
    public int[] orderedTraversal() {
        orderedArray = new int[size];
        pointer = 0;
        inOrderTreeWalk(root);

        return orderedArray;
    }

    /**
     * Rekursiv funktion, der først ordner det venstre undertræ,
     * dernæst det højre.
     * @param n elementet der der startes fra
     */
    private void inOrderTreeWalk(Node n){
        if(n != null) {
            inOrderTreeWalk(n.getLeftChild());
            orderedArray[pointer] = n.getByteValue();
            pointer++;
            inOrderTreeWalk(n.getRightChild());
        }
    }


}
