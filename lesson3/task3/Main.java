package task3;

import task3.classes.ProductImpl;
import task3.classes.StorageImpl;
import task3.enums.ProductTypes;
import task3.exceptions.StorageException;
import task3.interfaces.Product;
import task3.interfaces.Storage;

/**
 * Created by prokop on 9.10.16.
 */
public class Main {

    public static void main(String[] args) throws StorageException {

        Storage storage = new StorageImpl(100);

        Product product1 = new ProductImpl(ProductTypes.EDIBLE, 15, "qwerty1");
        Product product2 = new ProductImpl(ProductTypes.INEDIBLE, 65, "qwerty2");
        Product product3 = new ProductImpl(ProductTypes.EDIBLE, 45, "qwerty3");

        try {
            storage.addProduct(product1);
            storage.addProduct(product2);
            storage.addProduct(product3);
        }
        catch (StorageException e){
            System.out.println(e.getMessage());
        }

        try {
            storage.deleteProduct(product2);
        }
        catch (StorageException e){
            System.out.println(e.getMessage());
        }

        System.out.println(storage.getTotalWeight());
    }
}
