package vendingmachine;

public class Beverage {
	private String name;
	private int price;
	private int count;
	
	public Beverage(String name, int price, int count) {
		this.name = name;
		this.price = price;
		this.count = count;
	}
	
	//�̸� ���� getter, setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getCount() {
		return count;
	}
	
	// ���� ���Ž� ��� ������Ʈ �̿��Լ�
	public void countdown() {
		count--;
	}
	public void countup() {
		count++;
	}

}
