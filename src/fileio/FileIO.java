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
      for(Month temp:Month.values()) {
    	  
        File file = new File("C:\\\\Astudy\\\\�б�\\\\2�г� 2�б�\\\\JavaTerm\\\\VendingMacine(������)\\\\"+temp.name()+".txt");
        try {
			if(file.createNewFile()){
			    System.out.println(file.getName()+" ������ �����Ǿ����ϴ�");
			}else {
				System.out.println("File "+file.getName()+" �̹� �����ϴ� ���� �Դϴ�.");
				readFile(machine);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
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
		int[][][] _dailySalesByDrink=new int[13][32][5];
		try {
			for (Month temp: Month.values()) {
				try {
					read = new BufferedReader(new FileReader(temp.name()+".txt"));
					int daily=0;
					while ((s = read.readLine()) != null) {
						StringTokenizer row = new StringTokenizer(s, ",");
						int i=0;
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
	}

}