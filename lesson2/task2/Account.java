public class Account {
	public int number;

	public Account() {
		System.out.println(getClass().getSimpleName());
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
}