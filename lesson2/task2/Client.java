public class Client implements IVisitor{
    public int value;

    public Client(boolean flag) {
        if(flag) {
            System.out.println(getClass().getSimpleName());
        }
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public void startBeClient() {

    }

    public void makeAccount() {

    }

    public void performOperation() {

    }
}