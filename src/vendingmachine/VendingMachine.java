package vendingmachine;

import java.util.GregorianCalendar;
import java.util.Locale;

public class VendingMachine {
	private Beverage[] Beverage_list;
	private Coin vending_coin;
	private GregorianCalendar today;
	private int[][] DailySales;
	private int[] MonthlySale;
	private int[][][] DailySalesByDrink;
	private int[][] MonthlySalesByDrink;
	
	public Coin getVending_coin() {
		return vending_coin;
	}

	public int[][] getDailySales() {
		return DailySales;
	}

	public int[] getMonthlySale() {
		return MonthlySale;
	}

	public int[][][] getDailySalesByDrink() {
		return DailySalesByDrink;
	}

	public int[][] getMonthlySalesByDrink() {
		return MonthlySalesByDrink;
	}
	
	public VendingMachine() {
		vending_coin = new Coin();
		Beverage_list = new Beverage[] { 
				new Beverage("��", 450, 3), 
				new Beverage("Ŀ��", 500, 3),
				new Beverage("�̿�����", 550, 3), 
				new Beverage("�����̾� Ŀ��", 700, 3),
				new Beverage("ź��", 750, 3) 
				};
		DailySales = new int[13][32];
		MonthlySale = new int[13];
		DailySalesByDrink = new int[5][13][32];
		MonthlySalesByDrink = new int[5][32];
		today = new GregorianCalendar(Locale.KOREA);
	}
	
	
	// ������ �Ѱ����� ��ȯ���ִ� �Լ�
	public int getTotalBeverage_count() {
		int sum = 0;
		for (int i = 0; i < Beverage_list.length; i++) {
			sum += Beverage_list[i].getCount();
		}
		return sum;
	}

	public Beverage[] getBeverage_list() {
		return Beverage_list;
	}

	public void setBeverage_list(Beverage[] Beverage_list) {
		this.Beverage_list = Beverage_list;
	}

	public Coin getCoin() {
		return vending_coin;
	}

	public void setCoin(Coin coin) {
		this.vending_coin = coin;
	}

	
	//���Ḧ �������� �� �߻��� �Լ�
	public void SaleBeverage(int beverage_number) {
		Beverage_list[beverage_number].countdown();
		DailySales[today.get(today.MONTH)][today.get(today.DAY_OF_MONTH)]++;
		DailySalesByDrink[beverage_number][today.get(today.MONTH)][today.get(today.DAY_OF_MONTH)]++;

	}
}