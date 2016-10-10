package task3.interfaces;

import task3.exceptions.StorageException;

/**
 * Created by prokop on 9.10.16.
 */
public interface Storage {

    double getTotalWeight();

    double getMaxWeight();

    Product[] getProducts();

    void addProduct(Product product) throws StorageException;

    void deleteProduct(Product product) throws StorageException;
}
