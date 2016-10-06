package task3.linesteps;

import task3.interfaces.ILineStep;
import task3.interfaces.IProductPart;
import task3.product.parts.Kernel;

/**
 * Created by prokop on 6.10.16.
 */
public class KernelBuilder implements ILineStep{

    @Override
    public IProductPart buildProductPart() {
        return new Kernel();
    }
}
