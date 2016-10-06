package task3.product.parts;

import task3.interfaces.IProductPart;

/**
 * Created by prokop on 6.10.16.
 */
public class Spring implements IProductPart{
    public Spring() {
        System.out.println(getClass().getSimpleName() + " created");
    }
}
