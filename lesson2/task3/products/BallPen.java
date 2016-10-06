package task3.products;

import task3.interfaces.IProduct;
import task3.interfaces.IProductPart;

/**
 * Created by prokop on 5.10.16.
 */
public class BallPen implements IProduct {

    private IProductPart firstPart;
    private IProductPart secondPart;
    private IProductPart thirdPart;

    @Override
    public void installFirstPart(IProductPart iProductPart) {
        setFirstPart(iProductPart);
        System.out.println(getFirstPart().getClass().getSimpleName() + " installed");
    }

    @Override
    public void installSecondPart(IProductPart iProductPart) {
        setSecondPart(iProductPart);
        System.out.println(getSecondPart().getClass().getSimpleName() + " installed");
    }

    @Override
    public void installThirdPart(IProductPart iProductPart) {
        setThirdPart(iProductPart);
        System.out.println(getThirdPart().getClass().getSimpleName() + " installed");
    }

    public IProductPart getFirstPart() {
        return firstPart;
    }

    public void setFirstPart(IProductPart firstPart) {
        this.firstPart = firstPart;
    }

    public IProductPart getSecondPart() {
        return secondPart;
    }

    public void setSecondPart(IProductPart secondPart) {
        this.secondPart = secondPart;
    }

    public IProductPart getThirdPart() {
        return thirdPart;
    }

    public void setThirdPart(IProductPart thirdPart) {
        this.thirdPart = thirdPart;
    }
}
