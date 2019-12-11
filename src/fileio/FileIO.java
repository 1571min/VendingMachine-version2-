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
		
		//동전 음료 재고 초기화 부분
		
		try {
			
			int BeverageStuck[]=machine.getBeverageStuck();
			Coin saveCoin=machine.getCoin();
			
			writer = new BufferedWriter(new FileWriter("CoinAndBeverage.txt"));
			writer.write(StringForSave);
			StringForSave = ""; // 초기화
			
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
		
		
		//비밀번호 입력저장 란
		try {
			
			String password=machine.getPassword();
			
			writer = new BufferedWriter(new FileWriter("password.txt"));
			writer.write(StringForSave);
			StringForSave = ""; // 초기화
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
	 * 분류      : 함수
	 * 반환형    : void
	 * 매게 변수 : x
	 * 기능      : 자판기 생성 초기에 파일을 생성해 주는 함수이다
	 *             이미 존재하는 파일이라면 생성하지 않는다
	 * */
	public void createFile(VendingMachine machine) {
		
        //absolute file name with path
      
        try {
        	File file;
        	for(Month temp:Month.values()) {
	                file = new File("C:\\\\Astudy\\\\학교\\\\2학년 2학기\\\\JavaTerm\\\\VendingMacine(수정본)\\\\"+temp.name()+".txt");
				if(file.createNewFile()){
				    System.out.println(file.getName()+" 파일이 생성되었습니다");
				}else {
					System.out.println("File "+file.getName()+" 이미 존재하는 파일 입니다.");
					
				}
        	}
        	File file2;
        	file2 = new File("C:\\\\Astudy\\\\학교\\\\2학년 2학기\\\\JavaTerm\\\\VendingMacine(수정본)\\\\CoinAndBeverage.txt");
        	if(file2.createNewFile()){
			    System.out.println(file2.getName()+" 파일이 생성되었습니다");
			}else {
				System.out.println("File "+file2.getName()+" 이미 존재하는 파일 입니다.");
				
			}
        	File file3;
        	file3 = new File("C:\\\\Astudy\\\\학교\\\\2학년 2학기\\\\JavaTerm\\\\VendingMacine(수정본)\\\\password.txt");
        	if(file3.createNewFile()){
			    System.out.println(file3.getName()+" 파일이 생성되었습니다");
			}else {
				System.out.println("File "+file3.getName()+" 이미 존재하는 파일 입니다.");
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
							if(count<5) {// 리턴할 다음 토큰이 있는지 여부 확인
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
		
		//월별 데이터 읽어오기
		try {
			for (Month temp: Month.values()) {
				try {
					read = new BufferedReader(new FileReader(temp.name()+".txt"));
					int daily=0;
					while ((s = read.readLine()) != null) {
						StringTokenizer row = new StringTokenizer(s, ",");
						i=0;
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
		
		
		//비밀번호 읽어오기
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