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

	public FileIO() { // 생성자

	}

	public static void SetFileVendingInfo(String sfilename) { // 파일 이름을 받아 그 해당하는 리스트
		BufferedReader read = null;
		
		
		try {
			read = new BufferedReader(new FileReader(sfilename));
			//파일 저장해야됨
			// 월별,일별 텍스트 파일 저장해야됨
			// 각 음료별 월별,일별 텍스트파일 저장
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
			StringForSave = ""; // 초기화
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
			StringForSave = ""; // 초기화
			
			
			if (sfilename.equals("DailySales.txt")) {
				//자판기 데일리 세일즈 정보 필요
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
				    System.out.println(file.getName()+" 파일이 생성되었습니다");
				}else System.out.println("File "+file.getName()+" 이미 존재하는 파일 입니다.");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        
	}
	
	
	public void saveFile(String sfilename,VendingMachine machine) {
		/*
		 * 텍스트 파일 목록
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