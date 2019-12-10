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
		JANUARY, //1월 
		FEBRUARY, //2월 
		MARCH, //3월 
		APRIL, //4월 
		MAY, //5월 
		JUNE, //6월 
		JULY, //7월
		AUGUST, //8월 
		SEPTEMBER, //9월 
		OCTOBER, //10월 
		NOVEMBER, //11월 
		DECEMBER //12월 
		
	}
	
	static String StringForSave = "";
	private GregorianCalendar today;
	
	
	public FileIO() { // 생성자

	}
	
	
	/*
	 * 분류      : 함수
	 * 반환형    : void
	 * 매게 변수 : VendingMachine
	 * 기능      : 자판기를 매개변수로 받아 자판기의 정보를 저장한다
	 * */
	public void saveToFile(VendingMachine machine) {
		
		BufferedWriter writer = null;
		try {
				//자판기 데일리 세일즈 정보 필요
				int data[][][]=machine.getDailySalesByDrink();
				for (Month temp: Month.values()) {
					writer = new BufferedWriter(new FileWriter(temp.name()+".txt"));
					writer.write(StringForSave);
					StringForSave = ""; // 초기화
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
	 * 분류      : 함수
	 * 반환형    : void
	 * 매게 변수 : x
	 * 기능      : 자판기 생성 초기에 파일을 생성해 주는 함수이다
	 *             이미 존재하는 파일이라면 생성하지 않는다
	 * */
	public void createFile(VendingMachine machine) {
		
        //absolute file name with path
      for(Month temp:Month.values()) {
    	  
        File file = new File("C:\\\\Astudy\\\\학교\\\\2학년 2학기\\\\JavaTerm\\\\VendingMacine(수정본)\\\\"+temp.name()+".txt");
        try {
			if(file.createNewFile()){
			    System.out.println(file.getName()+" 파일이 생성되었습니다");
			}else {
				System.out.println("File "+file.getName()+" 이미 존재하는 파일 입니다.");
				readFile(machine);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
      }
}


	/*
	 * 분류      : 함수
	 * 반환형    : void
	 * 매게 변수 : VendingMachine
	 * 기능      : 자판기 객체를 받아서 그 객체에 데이터를 저장한다
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
						while (row.hasMoreTokens()) {// 리턴할 다음 토큰이 있는지 여부 확인
							//if(i<5&&daily<32) {
								_dailySalesByDrink[temp.ordinal()][daily][i++] = Integer.parseInt(row.nextToken()); // 배열에 각 값을 담기
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