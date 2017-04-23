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

    /**
     *
     * @param k værdien der skal indsættes i træet
     */
    public void insert(int k) {
        Node currentNode = null;
        Node rootNode = root;
        Node newNode = new Node();
        newNode.setKey(k);
        newNode.setLeftChild(null);
        newNode.setRightChild(null);

        // Så længe at den nuværende rod ikke er null, køres følgende while-loop:
        while(rootNode != null){
            currentNode = rootNode;
			/*
			Hvis værdien af det nye element k, er mindre end den nuværende rods værdi,
			sættes den nuværende rod til at være sit venstre barn. Ellers sættes den
			til at være det højre barn.
			*/
            if(newNode.getKey() < rootNode.getKey()) {
                rootNode = rootNode.getLeftChild();
            }
            else {
                rootNode = rootNode.getRightChild();
            }
        }
		/*
		Hvis den nuværende node er null når vi kommer ud af while-loopet, betyder det at
		den nye værdi skal indsættes her. Ellers hvis værdien af den nye node er mindre end
		værdien af den nuværende node, sættes den nye node til at være den nuværende nodes
		venstre barn. Hvis ingen af disse ting er tilfældet, sættes den til at være det højre barn.
		*/
        if(currentNode == null) {
            root = newNode;
        }
        else if(newNode.getKey() < currentNode.getKey()) {
            currentNode.setLeftChild(newNode);
        }
        else {
            currentNode.setRightChild(newNode);
        }

        size++;
    }

    public void setFreq(int freq) {
        root.setKey(freq);
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
            orderedArray[pointer] = n.getKey();
            pointer++;
            inOrderTreeWalk(n.getRightChild());
        }
    }

    /**
     *
     * @param k elementet der søges efter (et heltal)
     * @return returnerer true hvis k blev fundet i træet, ellers returneres false.
     */
    public boolean search(int k) {
        Node current = root;
		/*
		Så længe den nuværende rod ikke er null og den nuværende rods key-værdi ikke er
		den vi leder efter, køres følgende while-loop:
		*/
        while(current != null && current.getKey() != k) {
			/*
			Hvis k er mindre end den nuværende rods key-værdi, sættes den nuværende rod
			til at være sit venstre barn. Ellers sættes den til at være det højre barn.
			*/
            if (k < current.getKey()) {
                current = current.getLeftChild();
            }
            else {
                current = current.getRightChild();
            }
        }

		/*
		Hvis den nuværende rod ikke er null og den nuværende rods key-værdi er
		den vi leder efter, returneres true. Ellers returneres false,
		da værdien k så ikke findes i træet.
		*/
        if(current != null && current.getKey() == k) {
            return true;
        }

        return false;
    }
}
