public class Operation {
    public int number;
    public Account account;

    public Operation(Account account) {
        this.setAccount(account);
        System.out.println(getClass().getSimpleName());
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}