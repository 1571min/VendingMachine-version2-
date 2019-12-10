package vendingmachine;

import java.util.GregorianCalendar;
import java.util.Locale;

import fileio.FileIO;

public class VendingMachine {
	private Beverage[] Beverage_list;
	private Coin vending_coin;
	private GregorianCalendar today;
	private int[][] DailySales;
	private int[] MonthlySale;
	private int[][][] DailySalesByDrink;
	private int[][] MonthlySalesByDrink;
	private int[] BeverageStuck;
	private FileIO fileObject;
	
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
	
	public void setDailySalesByDrink(int[][][] dailySalesByDrink) {
		DailySalesByDrink = dailySalesByDrink;
	}                           

	public VendingMachine() {
		fileObject=new FileIO();
		
		vending_coin = new Coin();
		Beverage_list = new Beverage[] { 
				new Beverage("물", 450, 3), 
				new Beverage("커피", 500, 3),
				new Beverage("이온음료", 550, 3), 
				new Beverage("프리미엄 커피", 700, 3),
				new Beverage("탄산", 750, 3) 
				};
		DailySales = new int[13][32];
		DailySalesByDrink = new int[13][32][5];
		MonthlySale = new int[13];
		MonthlySalesByDrink = new int[5][13];
		BeverageStuck=new int[5];
		
		fileObject.createFile(this);
		//fileObject.saveToFile(this);/// 뭐 이런 코드를;;;쓰냐
		

		today = new GregorianCalendar(Locale.KOREA);
	}
	
	
	// 음료의 총개수를 반환해주는 함수
	public int getTotalBeverage_count() {
		int sum = 0;
		for (int i = 0; i < Beverage_list.length; i++) {
			sum += Beverage_list[i].getCount();
		}
		return sum;
	}

	
	
	public int[] getBeverageStuck() {
		return BeverageStuck;
	}

	public void setBeverageStuck(int[] beverageStuck) {
		BeverageStuck = beverageStuck;
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

	
	//음료를 구매햇을 때 발생할 함수
	/*
	 * private int[][] DailySales;
	 * private int[] MonthlySale;
	 * private int[][][] DailySalesByDrink;
	 * private int[][] MonthlySalesByDrink;
	 * private int[] BeverageStuck;
	 * */
	public void SaleBeverage(int beverage_number) {
		DailySalesByDrink[today.get(today.MONTH)][today.get(today.DAY_OF_MONTH)-1][beverage_number]++;
		
	}
	

	
	 
}
