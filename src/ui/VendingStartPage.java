package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import fileio.FileIO;
import vendingmachine.Beverage;
import vendingmachine.Coin;
import vendingmachine.Manager;

public class VendingStartPage extends JFrame{
	//자판기 화면 구성요소 필드
	private FileIO fileIO;
	private Main main;
	
	//자판기 메인 화면 판넬
	private JPanel mainPanel;
	private JPanel beveragePanel;
	private JPanel coinPanel;
	/*
	 * todo 
	 * 판넬 별로 클래스 만들어서 쪼개기
	 * 메인 메소드로 쪼개서 만들기 
	 * 컴포넌트를 가져오고 판넬을 붙여야한다
	 * */
	
	//자판기 음료버튼
	private JButton buttonWater;
	private JButton buttonCoffee;
	private JButton buttonIon;
	private JButton buttonPremiumCoffee;
	private JButton buttonCoke;
	
	//자판기 동전입력 버튼
	private JButton button10won;
	private JButton button50won;
	private JButton button100won;
	private JButton button500won;
	private JButton button1000won;
	
	//자판기 사용자 입력 현황판
	private JButton buttonCashOutput;   //반환버튼
	private JLabel labelBalance;        //잔액 string label
	private JLabel labelBeverageOut;    //음료 출력 화면
	private JTextField fieldCashInput;  //잔액 표시창
	private JButton buttonAdmin;        //관리자 페이지 전환 버튼
	
	//기타 자판기 객체
	private Manager manager;            //자판기 매니저 객체
	private Coin MachineCoin;			//자판기 코인 객체
	private Beverage[] Blist;			//자판기 음료 객체
	private int TotalUserMoney=0;		//사용자가 입력한 코인 합
	private static int LIMITE_SUM=5000; //자판기 입력 상한선
	private int $1000count=0;			//천원 개수
	private static int LIMITE_COUNT=3;  //천원 개수 상한선
	
	
	
	////이미지 객체
	
	//구매가능 이미지
	public static ImageIcon button_water_image;
	public static ImageIcon button_coffee_image;
	public static ImageIcon button_ion_image;
	public static ImageIcon button_premium_coffee_image;
	public static ImageIcon button_coke_image;
	
	//구매 불가능 이미지
	public static ImageIcon no_button_water_image;
	public static ImageIcon no_button_coffee_image;
	public static ImageIcon no_button_ion_image;
	public static ImageIcon no_button_premium_coffee_image;
	public static ImageIcon no_button_coke_image;
	
	//동전 이미지
	public static ImageIcon button_10_image;
	public static ImageIcon button_50_image;
	public static ImageIcon button_100_image;
	public static ImageIcon button_500_image;
	public static ImageIcon button_1000_image;
	
	///이미지 객체 할당
	static {
		try {
			button_water_image=new ImageIcon(ImageIO.read(VendingStartPage.class.getResource("../image/water.png")));
			button_coffee_image=new ImageIcon(ImageIO.read(VendingStartPage.class.getResource("../image/coffee.png")));
			button_ion_image=new ImageIcon(ImageIO.read(VendingStartPage.class.getResource("../image/ion.png")));
			button_premium_coffee_image=new ImageIcon(ImageIO.read(VendingStartPage.class.getResource("../image/premium_coffee.png")));
			button_coke_image=new ImageIcon(ImageIO.read(VendingStartPage.class.getResource("../image/coke.png")));
			
			no_button_water_image=new ImageIcon(ImageIO.read(VendingStartPage.class.getResource("../image/no_water.png")));
			no_button_coffee_image=new ImageIcon(ImageIO.read(VendingStartPage.class.getResource("../image/no_coffee.png")));
			no_button_ion_image=new ImageIcon(ImageIO.read(VendingStartPage.class.getResource("../image/no_ion.png")));
			no_button_premium_coffee_image=new ImageIcon(ImageIO.read(VendingStartPage.class.getResource("../image/no_premium_coffee.png")));
			no_button_coke_image=new ImageIcon(ImageIO.read(VendingStartPage.class.getResource("../image/no_coke.png")));
			
			
			button_10_image=new ImageIcon(ImageIO.read(VendingStartPage.class.getResource("../image/10won.png")));
			button_50_image=new ImageIcon(ImageIO.read(VendingStartPage.class.getResource("../image/50won.png")));
			button_100_image=new ImageIcon(ImageIO.read(VendingStartPage.class.getResource("../image/100won.png")));
			button_500_image=new ImageIcon(ImageIO.read(VendingStartPage.class.getResource("../image/500won.png")));
			button_1000_image=new ImageIcon(ImageIO.read(VendingStartPage.class.getResource("../image/1000won.png")));
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					VendingStartPage startpage=new VendingStartPage();
					startpage.setVisible(true);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public VendingStartPage() {
		//프레임 초기화
		fileIO=new FileIO();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(400,300);
		setSize(1000, 500);
		setTitle("자판기 프로그램");
		mainPanel=new JPanel();
		mainPanel.setLayout(new BorderLayout());
		manager=new Manager();
		Blist=manager.machine.getBeverage_list();
		MachineCoin=manager.machine.getCoin();
		//beveragepanel, coinpanel 추가
		mainPanel.add(getBeveragePanel(), BorderLayout.CENTER);
		mainPanel.add(getCoinPanel(), BorderLayout.EAST);
		setContentPane(mainPanel);
		setVisible(true);
	}
	
	public JPanel getBeveragePanel() {
		if(beveragePanel==null) {
			beveragePanel=new JPanel();
			beveragePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			beveragePanel.setLayout(new GridLayout(3,2));
			
			beveragePanel.add(getButtonWater());
			beveragePanel.add(getButtonCoffee());
			beveragePanel.add(getButtonIon());
			beveragePanel.add(getButtonPremiumCoffee());
			beveragePanel.add(getButtonCoke());
		}
		return beveragePanel;
	}

	public JPanel getCoinPanel() {
		if(coinPanel==null) {
			coinPanel=new JPanel();
			coinPanel.setPreferredSize(new Dimension(300,500));
			coinPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			
			coinPanel.add(getLabelBalance());
			coinPanel.add(getFieldCashInput());
			
			coinPanel.add(getButton10won());
			coinPanel.add(getButton50won());
			coinPanel.add(getButton100won());
			coinPanel.add(getButton500won());
			coinPanel.add(getButton1000won());
			coinPanel.add(getButtonCashOutput());
			coinPanel.add(getButtonAdmin());
			
			coinPanel.add(getLabelBeverageOut());
			
			
		}
		return coinPanel;
	}

	public Manager getManager() {
		return manager;
	}

	
	/*
	 * 음료 버튼,
	 * 음료 반환 라벨
	 * 각 음료버튼에 대한 이벤트 처리 함수
	*/
	public JButton getButtonWater() {
		if(buttonWater==null) {
			buttonWater=new JButton(Blist[0].getName()+
					Blist[0].getPrice()+"원",no_button_water_image);
			buttonWater.addMouseListener(new MouseAdapter() {
					
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mousePressed(e);
					if(TotalUserMoney>=Blist[0].getPrice()){
						manager.machine.SaleBeverage(0);    // 음료 판매기록 자판기 기록
						TotalUserMoney-=Blist[0].getPrice();                       //현재 입력 돈 업데이트
						fieldCashInput.setText(Integer.toString(TotalUserMoney));  // 텍스트 필드 업데이트
						labelBeverageOut.setIcon(button_water_image);
					}
					else{
						JOptionPane.showMessageDialog(null,"잔액이 부족합니다","자판기 경고",JOptionPane.WARNING_MESSAGE);
					}
					cheakbeverage(TotalUserMoney,Blist);
				}
				
			});
		}
		return buttonWater;
	}

	public JButton getButtonCoffee() {
		if(buttonCoffee==null) {
			buttonCoffee=new JButton(Blist[1].getName()+
					Blist[1].getPrice()+"원",no_button_coffee_image);
			buttonCoffee.addMouseListener(new MouseAdapter() {
					
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mousePressed(e);
					if(TotalUserMoney>=Blist[1].getPrice()){
						manager.machine.SaleBeverage(1);    // 음료 판매기록 자판기 기록
						TotalUserMoney-=Blist[1].getPrice();                       //현재 입력 돈 업데이트
						fieldCashInput.setText(Integer.toString(TotalUserMoney));  // 텍스트 필드 업데이트
						labelBeverageOut.setIcon(button_coffee_image);
					}
					else{
						JOptionPane.showMessageDialog(null,"잔액이 부족합니다","자판기 경고",JOptionPane.WARNING_MESSAGE);
					}
					cheakbeverage(TotalUserMoney,Blist);
				}
				
			});
		}
		
		return buttonCoffee;
	}

	public JButton getButtonIon() {
		if(buttonIon==null) {
			buttonIon=new JButton(Blist[2].getName()+
					Blist[2].getPrice()+"원",no_button_ion_image);
			buttonIon.addMouseListener(new MouseAdapter() {
					
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mousePressed(e);
					if(TotalUserMoney>=Blist[2].getPrice()){
						manager.machine.SaleBeverage(2);    // 음료 판매기록 자판기 기록
						TotalUserMoney-=Blist[2].getPrice();                       //현재 입력 돈 업데이트
						fieldCashInput.setText(Integer.toString(TotalUserMoney));  // 텍스트 필드 업데이트
						labelBeverageOut.setIcon(button_ion_image);
					}
					else{
						JOptionPane.showMessageDialog(null,"잔액이 부족합니다","자판기 경고",JOptionPane.WARNING_MESSAGE);
					}
					cheakbeverage(TotalUserMoney,Blist);
				}
				
			});
		}
		return buttonIon;
	}

	public JButton getButtonPremiumCoffee() {
		if(buttonPremiumCoffee==null) {
			buttonPremiumCoffee=new JButton(Blist[3].getName()+
					Blist[3].getPrice()+"원",no_button_premium_coffee_image);
			buttonPremiumCoffee.addMouseListener(new MouseAdapter() {
					
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mousePressed(e);
					if(TotalUserMoney>=Blist[3].getPrice()){
						manager.machine.SaleBeverage(3);    // 음료 판매기록 자판기 기록
						TotalUserMoney-=Blist[3].getPrice();                       //현재 입력 돈 업데이트
						fieldCashInput.setText(Integer.toString(TotalUserMoney));  // 텍스트 필드 업데이트
						labelBeverageOut.setIcon(button_premium_coffee_image);
					}
					else{
						JOptionPane.showMessageDialog(null,"잔액이 부족합니다","자판기 경고",JOptionPane.WARNING_MESSAGE);
					}
					cheakbeverage(TotalUserMoney,Blist);
				}
				
			});
		}
		return buttonPremiumCoffee;
	}

	public JButton getButtonCoke() {
		if(buttonCoke==null) {
			buttonCoke=new JButton(Blist[4].getName()+
					Blist[4].getPrice()+"원",no_button_coke_image);
			buttonCoke.addMouseListener(new MouseAdapter() {
					
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mousePressed(e);
					if(TotalUserMoney>=Blist[4].getPrice()){
						manager.machine.SaleBeverage(4);    // 음료 판매기록 자판기 기록
						TotalUserMoney-=Blist[4].getPrice();                       //현재 입력 돈 업데이트
						fieldCashInput.setText(Integer.toString(TotalUserMoney));  // 텍스트 필드 업데이트
						labelBeverageOut.setIcon(button_coke_image);
					}
					else{
						JOptionPane.showMessageDialog(null,"잔액이 부족합니다","자판기 경고",JOptionPane.WARNING_MESSAGE);
					}
					cheakbeverage(TotalUserMoney,Blist);
				}
				
			});
		}
		return buttonCoke;
	}

	public JLabel getLabelBeverageOut() {
		if(labelBeverageOut==null) {
			labelBeverageOut=new JLabel("");
		}
		return labelBeverageOut;
	}

	
	
	/*
	 * 동전 버튼,
	 * 잔액 라벨, 
	 * 동전 잔액 표시 필드
	 * 각 동전 버튼에 대한 이벤트 처리 함수
	*/
	public JButton getButton10won() {
		if(button10won==null) {
			button10won=new JButton(button_10_image);
			button10won.setPreferredSize(new Dimension(35,35));
			
			button10won.addMouseListener(new MouseAdapter() {

				@Override
				public void mousePressed(MouseEvent e) {
					if(TotalUserMoney<LIMITE_SUM) {
						super.mousePressed(e);
					    TotalUserMoney+=10;
					    MachineCoin.countup10won();
					    fieldCashInput.setText(Integer.toString(TotalUserMoney));
					}
					else {
						JOptionPane.showMessageDialog(null,"입력은 오천원까지만 가능합니다 ","자판기 경고",JOptionPane.WARNING_MESSAGE);
					}
					cheakbeverage(TotalUserMoney,Blist);
				}
				
			});
		}
		return button10won;
	}

	public JButton getButton50won() {
		if(button50won==null) {
			button50won=new JButton(button_50_image);
			button50won.setPreferredSize(new Dimension(35,35));
			
			button50won.addMouseListener(new MouseAdapter() {

				@Override
				public void mousePressed(MouseEvent e) {
					if(TotalUserMoney<LIMITE_SUM) {
						super.mousePressed(e);
					    TotalUserMoney+=50;
					    MachineCoin.countup50won();
					    fieldCashInput.setText(Integer.toString(TotalUserMoney));
					}
					else {
						JOptionPane.showMessageDialog(null,"입력은 오천원까지만 가능합니다 ","자판기 경고",JOptionPane.WARNING_MESSAGE);
					}
					cheakbeverage(TotalUserMoney,Blist);
				}
				
			});
		}
		return button50won;
	}

	public JButton getButton100won() {
		if(button100won==null) {
			button100won=new JButton(button_100_image);
			button100won.setPreferredSize(new Dimension(35,35));
			
			button100won.addMouseListener(new MouseAdapter() {

				@Override
				public void mousePressed(MouseEvent e) {
					if(TotalUserMoney<LIMITE_SUM) {
						super.mousePressed(e);
					    TotalUserMoney+=100;
					    MachineCoin.countup100won();
					    fieldCashInput.setText(Integer.toString(TotalUserMoney));
					}
					else {
						JOptionPane.showMessageDialog(null,"입력은 오천원까지만 가능합니다 ","자판기 경고",JOptionPane.WARNING_MESSAGE);
					} 
					cheakbeverage(TotalUserMoney,Blist);
				}
				
			});
		}
		return button100won;
	}

	public JButton getButton500won() {
		if(button500won==null) {
			button500won=new JButton(button_500_image);
			button500won.setPreferredSize(new Dimension(35,35));
			
			button500won.addMouseListener(new MouseAdapter() {

				@Override
				public void mousePressed(MouseEvent e) {
					if(TotalUserMoney<LIMITE_SUM) {
						super.mousePressed(e);
					    TotalUserMoney+=500;
					    MachineCoin.countup500won();
					    fieldCashInput.setText(Integer.toString(TotalUserMoney));
					}
					else {
						JOptionPane.showMessageDialog(null,"입력은 오천원까지만 가능합니다 ","자판기 경고",JOptionPane.WARNING_MESSAGE);
					} 
					cheakbeverage(TotalUserMoney,Blist);
				}
				
			});
		}
		return button500won;
	}

	public JButton getButton1000won() {
		if(button1000won==null) {
			button1000won=new JButton(button_1000_image);
			button1000won.setPreferredSize(new Dimension(65,35));
			
			button1000won.addMouseListener(new MouseAdapter() {

				@Override
				public void mousePressed(MouseEvent e) {
						if($1000count<LIMITE_COUNT) {
								if(TotalUserMoney<LIMITE_SUM) {
									super.mousePressed(e);
								    TotalUserMoney+=1000;
								    MachineCoin.countup1000won();
								    fieldCashInput.setText(Integer.toString(TotalUserMoney));
								    $1000count++;
								}
								else {
									JOptionPane.showMessageDialog(null,"입력은 오천원까지만 가능합니다 ","자판기 경고",JOptionPane.WARNING_MESSAGE);
								} 
								
							}
						else {
							JOptionPane.showMessageDialog(null,"천원은 3장 까지만 입력 가능합니다 ","자판기 경고",JOptionPane.WARNING_MESSAGE);
						}
						cheakbeverage(TotalUserMoney,Blist);
					}
				
			});
		}
		return button1000won;
	}
	
	public JLabel getLabelBalance() {
		if(labelBalance==null) {
			labelBalance=new JLabel("잔액");
			labelBalance.setSize(new Dimension(75,25));
		}
		return labelBalance;
	}
	
	public JTextField getFieldCashInput() {
		if(fieldCashInput==null) {
			fieldCashInput=new JTextField(25);
		}
		return fieldCashInput;
	}

	
	/*
	 * 동전반환, 관리자 메뉴 전환 함수
	 * 각 음료버튼에 대한 이벤트 처리 함수
	*/
	public JButton getButtonCashOutput() {
		if(buttonCashOutput==null) {
			buttonCashOutput=new JButton("반환");
			int[] countarr=new int[] {0,0,0,0,0};
			buttonCashOutput.addMouseListener(new MouseAdapter() {

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					if(ischangeok(TotalUserMoney, MachineCoin,countarr)==true) {
						
						super.mousePressed(e);
						JOptionPane.showMessageDialog(null, Integer.toString(TotalUserMoney)+"원("+"1000원 : "+countarr[0]+"개"+
																							"500원 : "+countarr[1]+"개"+
																							"100원 : "+countarr[2]+"개"+
																							"50원 : "+countarr[3]+"개"+
																							"10원 : "+countarr[4]+"개"+
														")이 반환 되었습니다","잔액반환",JOptionPane.INFORMATION_MESSAGE);
						TotalUserMoney-=TotalUserMoney;
						fieldCashInput.setText(Integer.toString(TotalUserMoney));
						MachineCoin.set_1000won(MachineCoin.get_1000won()-countarr[0]);
						MachineCoin.set_500won(MachineCoin.get_500won()-countarr[1]);
						MachineCoin.set_100won(MachineCoin.get_100won()-countarr[2]);
						MachineCoin.set_50won(MachineCoin.get_50won()-countarr[3]);
						MachineCoin.set_10won(MachineCoin.get_10won()-countarr[4]);
						
						manager.machine.initInfo();
						
						for(int i=0;i<5;i++)
							countarr[i]=0;
						System.out.println(MachineCoin.get_10won()+" 개,"+MachineCoin.get_50won()+
								" 개,"+MachineCoin.get_100won()+" 개,"+MachineCoin.get_500won()+" 개,"+MachineCoin.get_1000won()+" 개");
						}
						else {
							JOptionPane.showMessageDialog(null,"거스름돈이 부족합니다","자판기 경고",JOptionPane.WARNING_MESSAGE);

						}
						
				}
				
			});
		}
		return buttonCashOutput;
	}
	
	public JButton getButtonAdmin() {
		if(buttonAdmin==null) {
			buttonAdmin=new JButton("관리자 페이지");
			buttonAdmin.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					
					super.mousePressed(e);
					String password=JOptionPane.showInputDialog("비밀번호를 입력해주세요");
					if(password.equals("1234")) {
						//화면 전환 이벤트 처리
						
						
					}
					else {
						JOptionPane.showMessageDialog(null, "잘못된 비밀번호입니다","경고",1);
						
						
					}
				}
			});
		}
		return buttonAdmin;
	}

	/*
	 * 분류      : 함수
	 * 반환형    : void
	 * 매게 변수 : money(사용자가 입력한 돈),음료 리스트
	 * 기능      : 음료수가 현재 구매가능한 상태인지 체크
	 *             구매가능 -> 컬러, 구매 불가능 -> 흑백
	 * */
	public void cheakbeverage(int money,Beverage[] blist) {
		//water
		if(blist[0].getCount()>0) {
			if(blist[0].getPrice()<=money) {
				buttonWater.setIcon(button_water_image);
			}
			else {
				buttonWater.setIcon(no_button_water_image);
			}
		}
		else {
			buttonWater.setIcon(no_button_water_image);
		}
		//coffee
		if(blist[1].getCount()>0) {
			if(blist[1].getPrice()<=money) {
				buttonCoffee.setIcon(button_coffee_image);
			}
			else {
				buttonCoffee.setIcon(no_button_coffee_image);
			}
		}
		else {
			buttonCoffee.setIcon(no_button_coffee_image);
		}
		//ion
		if(blist[2].getCount()>0) {
			if(blist[2].getPrice()<=money) {
				buttonIon.setIcon(button_ion_image);
			}
			else {
				buttonIon.setIcon(no_button_ion_image);
			}
		}
		else {
			buttonIon.setIcon(no_button_ion_image);
		}
		
		//premium_coffee
		if(blist[3].getCount()>0) {
			if(blist[3].getPrice()<=money) {
				buttonPremiumCoffee.setIcon(button_premium_coffee_image);
			}
			else {
				buttonPremiumCoffee.setIcon(no_button_premium_coffee_image);
			}
		}
		else {
			buttonPremiumCoffee.setIcon(no_button_premium_coffee_image);
		}
		
		//coke
		if(blist[4].getCount()>0) {
			if(blist[4].getPrice()<=money) {
				buttonCoke.setIcon(button_coke_image);
			}
			else {
				buttonCoke.setIcon(no_button_coke_image);
			}
		}
		else {
			buttonCoke.setIcon(no_button_coke_image);
		}
		
	}
	
	
	/*
	 * 분류      : 함수
	 * 반환형    : boolean
	 * 매게 변수 : changemoney(사용자가 입력한 돈),temp_coin(현재 자판기 돈),countarr(얼마를 거슬러 줄지 기록할 배열)
	 * 기능      : 자판기가 거스름돈을 거슬러 줄 수 있는 지 체크
	 * */
	
	public boolean ischangeok(int changemoney,Coin temp_coin,int[] countarr) {
		int count=0;
	
		//Coin temp_coin=m.machine.getCoin();
		if(changemoney>=1000) {
			count=changemoney/1000;
			if(temp_coin.get_1000won()>=count) {
				changemoney-=(count*1000);
				countarr[0]=count;
			}
			
			count=0;
		}
		if(changemoney>=500) {
			count=changemoney/500;
			if(temp_coin.get_500won()>=count) {
				changemoney-=(count*500);
				countarr[1]=count;
			}
			
			count=0;
		}
		if(changemoney>=100) {
			count=changemoney/100;
			if(temp_coin.get_100won()>=count) {
				changemoney-=(count*100);
				countarr[2]=count;
			}
			
			count=0;
		}
		if(changemoney>=50) {
			count=changemoney/50;
			if(temp_coin.get_50won()>=count) {
				changemoney-=(count*50);
				countarr[3]=count;
			}
			
			count=0;
		}
		if(changemoney>=10) {
			count=changemoney/10;
			if(temp_coin.get_10won()>=count) {
				changemoney-=(count*10);
				countarr[4]=count;
			}
			
			count=0;
		}
		int sum=countarr[0]*1000+countarr[1]*500+countarr[2]*100+countarr[3]*50+countarr[4]*10;
		if(changemoney==0) return true;
		else return false;
	}

	
	public void setMain(Main main) {
		// TODO Auto-generated method stub
		this.main=main;
	}
	
}
