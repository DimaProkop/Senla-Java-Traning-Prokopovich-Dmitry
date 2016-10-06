package task3.product.parts;

import task3.interfaces.IProductPart;

/**
 * Created by prokop on 6.10.16.
 */
public class Body implements IProductPart{
    public Body() {
        System.out.println(getClass().getSimpleName() + " created");
    }
}
