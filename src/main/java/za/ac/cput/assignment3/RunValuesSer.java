
package za.ac.cput.assignment3;

/**
 *
 * @Auther: Moegammad Tasreeq Adams Student Number: 216173027
 * APD Assignment3
 */
public class RunValuesSer {

    public static void main(String[] args) {
        ReadValuesSer obj = new ReadValuesSer();
        obj.openfile();
        obj.readFromFile();
        obj.sortCustomer();
        obj.sortSupplier();
        obj.writeToFileCus();
        obj.writeToFileSup();
    }
}
