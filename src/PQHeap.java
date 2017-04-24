import java.util.ArrayList;
import java.util.Collections;

/**
 * Medlemmer:
 * Niclas Schilling Mølby: nicmo15
 * Jebisan Nadarajah: jenad14
 * Emil Villefrance: emvil15
 */
public class PQHeap implements PQ {

    private ArrayList<Element> elements;


    PQHeap(int maxElms){
        elements = new ArrayList<>(maxElms);
        elements.add(new Element(0,null));
    }

    /**
     *
     * @return returnerer det element der har den mindste frequency værdi
     */
    @Override
    public Element extractMin() {
        Element min = elements.get(1);
        Collections.swap(elements, 1, elements.size()-1);
        elements.remove(elements.size()-1);

        minHeapify(1);
        return min;
    }

    /**
     *
     * @param i positionen i array'et, hvor heapify skal starte fra
     */
    private void minHeapify(int i) {
        int left = getLeftChild(i);
        int right = getRightChild(i);
        int min;

        // Tjekker om den nuværende positions venstre barn har en mindre frequency værdi
        if(left < elements.size() && elements.get(left).frequency < elements.get(i).frequency){
            min = left;
        }
        else {
            min = i;
        }

        // Tjekker om den nuværene positions højre barn har en mindre frequency værdi
        if(right < elements.size() && elements.get(right).frequency < elements.get(min).frequency) {
            min = right;
        }

        /*
            Tjekker om forældren har den mindste frequency værdi
            hvis ikke skal den byttes ud med den midnste værdi og heapify skal køres igen
        */
        if(min != i){
            Collections.swap(elements, i, min);
            minHeapify(min);
        }
    }

    /**
     *
     * @param e tilføjer et element til sidst i heapen og ordner heapen, så det nye element kommer hen på den korrekte plads.
     */
    @Override
    public void insert(Element e) {
        elements.add(e);
        int i = elements.size() - 1;
        while(i > 1 && elements.get(getParent(i)).frequency > e.frequency){
            Collections.swap(elements, i, getParent(i));
            i = getParent(i);
        }
    }

    @Override
    public int getSize() {
        return elements.size();
    }

    /**
     *
     * @param index det index hvis forældre skal findes
     * @return returnerer det index der er forældre til det indtastede index
     */
    private int getParent(int index) {
        return index / 2;
    }

    /**
     *
     * @param index det index hvis venstre barn skal findes
     * @return returnerer det venstre barn til indexet
     */
    private int getLeftChild(int index) {
        return 2 * index;
    }

    /**
     *
     * @param index det index hvis højre barn skal findes
     * @return returnerer det højre barn til indexet
     */
    private int getRightChild(int index) {
        return 2 * index + 1;
    }
}
