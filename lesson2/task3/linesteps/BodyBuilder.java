package task3.linesteps;

import task3.interfaces.ILineStep;
import task3.interfaces.IProductPart;
import task3.product.parts.Body;

/**
 * Created by prokop on 6.10.16.
 */
public class BodyBuilder implements ILineStep{

    @Override
    public IProductPart buildProductPart() {
        return new Body();
    }
}
