package vendingmachine;

import java.util.GregorianCalendar;
import java.util.Locale;

import fileio.FileIO;

public class VendingMachine {
	private Beverage[] Beverage_list;
	private Coin vending_coin;
	private GregorianCalendar today;
	private int[][][] DailySalesByDrink;
	private int[] BeverageStuck;
	private FileIO fileObject;
	private String password;

	public VendingMachine() {
		fileObject=new FileIO();
		password="1";
		vending_coin = new Coin();
		Beverage_list = new Beverage[] { 
				new Beverage("��", 450, 3), 
				new Beverage("Ŀ��", 500, 3),
				new Beverage("�̿�����", 550, 3), 
				new Beverage("�����̾� Ŀ��", 700, 3),
				new Beverage("ź��", 750, 3) 
				};
		
		DailySalesByDrink = new int[13][32][5];
		BeverageStuck=new int[] {3,3,3,3,3};
		fileObject.createFile(this);
		today = new GregorianCalendar(Locale.KOREA);
	}
	
	// getter
	public Coin getVending_coin() {
		return vending_coin;
	}

	public int[][][] getDailySalesByDrink() {
		return DailySalesByDrink;
	}
	
	public int[] getBeverageStuck() {
		return BeverageStuck;
	}

	public Beverage[] getBeverage_list() {
		return Beverage_list;
	}

	public Coin getCoin() {
		return vending_coin;
	}

	public String getPassword() {
		return password;
	}
	
	
	// setter
	public void setDailySalesByDrink(int[][][] dailySalesByDrink) {
		DailySalesByDrink = dailySalesByDrink;
	}  

	public void setBeverageStuck(int[] beverageStuck) {
		BeverageStuck = beverageStuck;
	}
	
	public void setBeverage_list(Beverage[] Beverage_list) {
		this.Beverage_list = Beverage_list;
	}
	
	public void setCoin(Coin coin) {
		this.vending_coin = coin;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	/*
	 * �з�      : �Լ�
	 * ��ȯ��    : void
	 * �Ű� ���� : ������ �ε���
	 * ���      : ������� ������ �� ���� �Ǹŷ��� �������ִ� �Լ��̴�
	 * */
	public void SaleBeverage(int beverage_number) {
		DailySalesByDrink[today.get(today.MONTH)][today.get(today.DAY_OF_MONTH)-1][beverage_number]++;
		BeverageStuck[beverage_number]--;
		
	}
	/*
	 * �з�      : �Լ�
	 * ��ȯ��    : int
	 * �Ű� ���� : month
	 * ���      : �� ���� �Ǹ� �ݾ��� ��ȯ�Ѵ�
	 * */
	public int getSalesMoneyByMonth(int month) {
		int sum=0;
		for(int i=0;i<32;i++) {
			for(int j=0;j<5;j++) {
				sum+=DailySalesByDrink[month][i][j]*Beverage_list[j].getPrice();
			}
		}
		
		return sum;
	}

	 
}
