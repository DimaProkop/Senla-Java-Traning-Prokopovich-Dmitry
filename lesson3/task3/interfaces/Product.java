package task3.interfaces;

import task3.enums.ProductTypes;

/**
 * Created by prokop on 9.10.16.
 */
public interface Product {

    ProductTypes getType();

    double getWeight();

    String getName();
}
