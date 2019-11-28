package fileio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

import vendingmachine.VendingMachine;


public class FileIO {
	static String StringForSave = "";

	public FileIO() { // ������

	}

	public static void SetFileVendingInfo(String sfilename) { // ���� �̸��� �޾� �� �ش��ϴ� ����Ʈ
		BufferedReader read = null;
		
		
		try {
			read = new BufferedReader(new FileReader(sfilename));
			//���� �����ؾߵ�
			// ����,�Ϻ� �ؽ�Ʈ ���� �����ؾߵ�
			// �� ���Ằ ����,�Ϻ� �ؽ�Ʈ���� ����
			//
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				read.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void SaveToFile(String sfilename) {
		
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(sfilename));
			writer.write(StringForSave);
			StringForSave = ""; // �ʱ�ȭ
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
	
	public static void initFile(String sfilename,VendingMachine machine) {
		
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(sfilename));
			
			
			writer.write(StringForSave);
			StringForSave = ""; // �ʱ�ȭ
			
			
			if (sfilename.equals("DailySales.txt")) {
				//���Ǳ� ���ϸ� ������ ���� �ʿ�
				int data[][]=machine.getDailySales();
				
			
				for(int i=0;i<13;i++) {
					writer.write(Integer.toString(i+1)+"\n");
					for(int j=0;j<31;j++) {
						writer.write(Integer.toString(data[i][j])+" ");
					}
					writer.write("\n");
				}
				
			} 
			else if (sfilename.equals("MonthlySale.txt")) {
				int data[]=machine.getMonthlySale();
				
				
				for(int i=0;i<13;i++) {	
					writer.write(Integer.toString(data[i])+" ");
					writer.write("\n");
				}
			}
			else if (sfilename.equals("DailySalesByDrink.txt")) {
				int data[][][]=machine.getDailySalesByDrink();
				
				
				for(int i=0;i<5;i++) {
					writer.write(Integer.toString(i+1)+"\n");
					for(int j=0;j<13;j++) {
						for(int k=0;k<31;k++) {
							writer.write(Integer.toString(data[i][j][k])+" ");
						}
						writer.write("\n");
					}
					writer.write("\n");
				}
			}
			else if (sfilename.equals("MonthlySalesByDrink.txt")) {
				int data[][]=machine.getMonthlySalesByDrink();
				
				
				for(int i=0;i<5;i++) {
					writer.write(Integer.toString(i+1)+"\n");
					for(int j=0;j<13;j++) {
						writer.write(Integer.toString(data[i][j])+" ");
					}
					writer.write("\n");
				}
			}
			else if (sfilename.equals("BeverageStuck.txt")) {
				int data[]=machine.getBeverageStuck();
				
				
				for(int i=0;i<5;i++) {	
					writer.write(Integer.toString(data[i])+" ");
					writer.write("\n");
				}	
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

	
	public void createFile(String sfilename) {
		
	        //absolute file name with path
	      
	        File file = new File(sfilename);
	        try {
				if(file.createNewFile()){
				    System.out.println(file.getName()+" ������ �����Ǿ����ϴ�");
				}else System.out.println("File "+file.getName()+" �̹� �����ϴ� ���� �Դϴ�.");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        
	}
	
	
	public void saveFile(String sfilename,VendingMachine machine) {
		/*
		 * �ؽ�Ʈ ���� ���
		 * DailySales 
		 * MonthlySale 
		 * DailySalesByDrink 
		 * MonthlySalesByDrink
		 * BeverageStuck
		 *
		 * 
		 */
		if (sfilename.equals("DailySales.txt")) {

		} 
		else if (sfilename.equals("MonthlySale.txt")) {
			
		}
		else if (sfilename.equals("DailySalesByDrink.txt")) {
			
		}
		else if (sfilename.equals("MonthlySalesByDrink.txt")) {
		
		}
		else if (sfilename.equals("BeverageStuck.txt")) {
		
		}
		
	}
	
	public void readFile(String sfilename) {
		
	}

}