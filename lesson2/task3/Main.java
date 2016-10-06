package task3;

import task3.collectors.Collector;
import task3.interfaces.IProduct;
import task3.linesteps.BodyBuilder;
import task3.linesteps.KernelBuilder;
import task3.linesteps.SpringBuilder;
import task3.products.BallPen;

/**
 * Created by prokop on 6.10.16.
 */
public class Main {

    public static void main(String[] args) {
        Collector collectorBallPen = new Collector(new BodyBuilder(), new SpringBuilder(), new KernelBuilder());
        IProduct ballPen = collectorBallPen.assembleProduct(new BallPen());
    }

}
