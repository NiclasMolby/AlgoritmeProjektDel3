import java.util.Scanner;

/**
 * Medlemmer:
 * Niclas Schilling Mølby: nicmo15
 * Jebisan Nadarajah: jenad14
 * Emil Villefrance: emvil15
 */
public class Treesort {

    public static void main(String args[]){
        Dict dbt = new DictBinTree();
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextInt()) {
            dbt.insert(scanner.nextInt());
        }
        System.out.println();
        for(int i : dbt.orderedTraversal()){
            System.out.println(i);
        }
    }
}
