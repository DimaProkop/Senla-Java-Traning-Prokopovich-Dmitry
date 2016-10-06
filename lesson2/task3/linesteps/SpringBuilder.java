package task3.linesteps;

import task3.interfaces.ILineStep;
import task3.interfaces.IProductPart;
import task3.product.parts.Spring;

/**
 * Created by prokop on 6.10.16.
 */
public class SpringBuilder implements ILineStep{

    @Override
    public IProductPart buildProductPart() {
        return new Spring();
    }
}
