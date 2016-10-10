package task3.classes;

import task3.enums.ProductTypes;
import task3.interfaces.Product;

/**
 * Created by prokop on 9.10.16.
 */
public class ProductImpl implements Product{
    private ProductTypes type;
    private double weight;
    private String name;

    public ProductImpl() {
    }

    public ProductImpl(ProductTypes type, double weight, String name) {
        this.type = type;
        this.weight = weight;
        this.name = name;
    }

    public ProductTypes getType() {
        return type;
    }

    public void setType(ProductTypes type) {
        this.type = type;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProductImpl{" +
                "type=" + type +
                ", weight=" + weight +
                ", name='" + name + '\'' +
                '}';
    }
}
