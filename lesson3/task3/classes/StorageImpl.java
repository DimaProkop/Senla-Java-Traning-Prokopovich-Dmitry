package task3.classes;

import task3.enums.ExceptionMessages;
import task3.exceptions.StorageException;
import task3.interfaces.Product;
import task3.interfaces.Storage;

/**
 * Created by prokop on 9.10.16.
 */
public class StorageImpl implements Storage {
    private double maxWeight;
    private Product[] products;

    public StorageImpl(int maxWeight) {
        this.products = new Product[10];
        this.maxWeight = maxWeight;
    }

    @Override
    public double getTotalWeight() {
        double currentWeight = 0;
        for(Product product: products) {
            if(product != null) {
                currentWeight += product.getWeight();
            }
        }
        return currentWeight;
    }

    @Override
    public void addProduct(Product product) throws StorageException {
        if(getTotalWeight() + product.getWeight() > getMaxWeight()){
            throw new StorageException(ExceptionMessages.STORAGE_IS_FULL.getMessage());
        }

        if(isFull()){
            this.products = extendPlace(this.products);
        }

        for(int i = 0; i < products.length; i++){

            if(products[i] == null){
                products[i] = product;
                return;
            }
        }
    }

    @Override
    public void deleteProduct(Product product) throws StorageException {
        if(this.products == null || this.products.length == 0){
            throw new StorageException(ExceptionMessages.STORAGE_IS_EMPTY.getMessage());
        }

        for(int i = 0; i < this.products.length; i++){

            if(this.products != null && product.equals(this.products[i])){

                this.products[i] = null;
                return;
            }
        }
    }

    private boolean isFull(){

        for(Product p : this.products){

            if(p == null){
                return false;
            }
        }

        return true;
    }

    private Product[] extendPlace(Product[] products){

        Product[] newPlace = new Product[products.length + 10];

        System.arraycopy(products, 0, newPlace, 0, products.length);

        return newPlace;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }
}
