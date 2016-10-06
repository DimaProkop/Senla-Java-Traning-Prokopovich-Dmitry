public class Main {
    public static void main(String[] args) {
        Client client = new Client(true);
        Individual individual = new Individual();
        LegalEntity legalEntity = new LegalEntity();
        Account account = new Account();
        Operation operation = new Operation(account);
    }
}
