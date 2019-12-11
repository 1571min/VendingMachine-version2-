package fileio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

import vendingmachine.Coin;
import vendingmachine.VendingMachine;


public class FileIO {
	
	public enum Month { 
		JANUARY, //1�� 
		FEBRUARY, //2�� 
		MARCH, //3�� 
		APRIL, //4�� 
		MAY, //5�� 
		JUNE, //6�� 
		JULY, //7��
		AUGUST, //8�� 
		SEPTEMBER, //9�� 
		OCTOBER, //10�� 
		NOVEMBER, //11�� 
		DECEMBER //12�� 
		
	}
	
	static String StringForSave = "";
	private GregorianCalendar today;
	
	
	public FileIO() { // ������

	}
	
	
	/*
	 * �з�      : �Լ�
	 * ��ȯ��    : void
	 * �Ű� ���� : VendingMachine
	 * ���      : ���Ǳ⸦ �Ű������� �޾� ���Ǳ��� ������ �����Ѵ�
	 * */
	public void saveToFile(VendingMachine machine) {
		
		BufferedWriter writer = null;
		try {
				//���Ǳ� ���ϸ� ������ ���� �ʿ�
				int data[][][]=machine.getDailySalesByDrink();
				for (Month temp: Month.values()) {
					writer = new BufferedWriter(new FileWriter(temp.name()+".txt"));
					writer.write(StringForSave);
					StringForSave = ""; // �ʱ�ȭ
					for(int i=0;i<32;i++) {
						for(int j=0;j<5;j++) {
							writer.write(Integer.toString(data[temp.ordinal()][i][j])+",");
						}
						writer.write("\n");
					}
					writer.close();
				}
				
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//���� ���� ��� �ʱ�ȭ �κ�
		
		try {
			
			int BeverageStuck[]=machine.getBeverageStuck();
			Coin saveCoin=machine.getCoin();
			
			writer = new BufferedWriter(new FileWriter("CoinAndBeverage.txt"));
			writer.write(StringForSave);
			StringForSave = ""; // �ʱ�ȭ
			
			for(int j=0;j<5;j++) {
				writer.write(Integer.toString(BeverageStuck[j])+",");
			}
			writer.write("\n");
			writer.write(Integer.toString(saveCoin.get_10won())+",");
			writer.write(Integer.toString(saveCoin.get_50won())+",");
			writer.write(Integer.toString(saveCoin.get_100won())+",");
			writer.write(Integer.toString(saveCoin.get_500won())+",");
			writer.write(Integer.toString(saveCoin.get_1000won())+",");
			
			writer.close();
		
			
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		//��й�ȣ �Է����� ��
		try {
			
			String password=machine.getPassword();
			
			writer = new BufferedWriter(new FileWriter("password.txt"));
			writer.write(StringForSave);
			StringForSave = ""; // �ʱ�ȭ
			writer.write(password);

			writer.close();
		
			
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
	/*
	 * �з�      : �Լ�
	 * ��ȯ��    : void
	 * �Ű� ���� : x
	 * ���      : ���Ǳ� ���� �ʱ⿡ ������ ������ �ִ� �Լ��̴�
	 *             �̹� �����ϴ� �����̶�� �������� �ʴ´�
	 * */
	public void createFile(VendingMachine machine) {
		
        //absolute file name with path
      
        try {
        	File file;
        	for(Month temp:Month.values()) {
	                file = new File("C:\\\\Astudy\\\\�б�\\\\2�г� 2�б�\\\\JavaTerm\\\\VendingMacine(������)\\\\"+temp.name()+".txt");
				if(file.createNewFile()){
				    System.out.println(file.getName()+" ������ �����Ǿ����ϴ�");
				}else {
					System.out.println("File "+file.getName()+" �̹� �����ϴ� ���� �Դϴ�.");
					
				}
        	}
        	File file2;
        	file2 = new File("C:\\\\Astudy\\\\�б�\\\\2�г� 2�б�\\\\JavaTerm\\\\VendingMacine(������)\\\\CoinAndBeverage.txt");
        	if(file2.createNewFile()){
			    System.out.println(file2.getName()+" ������ �����Ǿ����ϴ�");
			}else {
				System.out.println("File "+file2.getName()+" �̹� �����ϴ� ���� �Դϴ�.");
				
			}
        	File file3;
        	file3 = new File("C:\\\\Astudy\\\\�б�\\\\2�г� 2�б�\\\\JavaTerm\\\\VendingMacine(������)\\\\password.txt");
        	if(file3.createNewFile()){
			    System.out.println(file3.getName()+" ������ �����Ǿ����ϴ�");
			}else {
				System.out.println("File "+file3.getName()+" �̹� �����ϴ� ���� �Դϴ�.");
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

  
}


	/*
	 * �з�      : �Լ�
	 * ��ȯ��    : void
	 * �Ű� ���� : VendingMachine
	 * ���      : ���Ǳ� ��ü�� �޾Ƽ� �� ��ü�� �����͸� �����Ѵ�
	 * */
	public void readFile(VendingMachine machine) {
		BufferedReader read = null;
		String s = null;
		int[] BeverageStuck=new int[5];
		int[] readingCoinArray=new int[5];
		int[][][] _dailySalesByDrink=new int[13][32][5];
		String password;
		Coin readingCoin=new Coin();
		int i=0;
		int count=0;
		try {
				try {
					read = new BufferedReader(new FileReader("CoinAndBeverage.txt"));
					while ((s = read.readLine()) != null) {
						StringTokenizer row = new StringTokenizer(s, ",");
						i=0;
						while (row.hasMoreTokens()) {
							if(count<5) {// ������ ���� ��ū�� �ִ��� ���� Ȯ��
								BeverageStuck[i++] = Integer.parseInt(row.nextToken());
								count++;
							}
							else {
								readingCoinArray[i++] = Integer.parseInt(row.nextToken());
							}
						}
					}
					
					readingCoin.set_10won(readingCoinArray[0]);
					readingCoin.set_50won(readingCoinArray[1]);
					readingCoin.set_100won(readingCoinArray[2]);
					readingCoin.set_500won(readingCoinArray[3]);
					readingCoin.set_1000won(readingCoinArray[4]);

					read.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
			
			machine.setBeverageStuck(BeverageStuck);
			machine.setCoin(readingCoin);
		} finally {
			try {
				read.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//���� ������ �о����
		try {
			for (Month temp: Month.values()) {
				try {
					read = new BufferedReader(new FileReader(temp.name()+".txt"));
					int daily=0;
					while ((s = read.readLine()) != null) {
						StringTokenizer row = new StringTokenizer(s, ",");
						i=0;
						while (row.hasMoreTokens()) {// ������ ���� ��ū�� �ִ��� ���� Ȯ��
							//if(i<5&&daily<32) {
								_dailySalesByDrink[temp.ordinal()][daily][i++] = Integer.parseInt(row.nextToken()); // �迭�� �� ���� ���
							//}
						}
						daily++;
					}

					read.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
			}
			machine.setDailySalesByDrink(_dailySalesByDrink);
		} finally {
			try {
				read.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		//��й�ȣ �о����
		try {
				try {
					read = new BufferedReader(new FileReader("password.txt"));
					password=new String();
					while ((s = read.readLine()) != null) {
						password=s;
					}
					machine.setPassword(password);
					read.close();
				} catch (IOException e) {
					e.printStackTrace();

			}
		} finally {
			try {
				read.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	

}