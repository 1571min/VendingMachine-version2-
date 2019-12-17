package ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;

import java.awt.Dimension;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;


import fileio.FileIO;
import vendingmachine.Coin;
import vendingmachine.Manager;

public class AdminFrame extends JFrame{
	private VmMain main;
	private CardLayout cards = new CardLayout();

	
	private JPanel mainPanel;
	private JPanel chartPanel;
	private JPanel menuPanel;
	
	private JPanel SalesRatePanel;
	private JPanel BeverageStuckPanel;
	private JPanel CoinStuckPanel;
	private JPanel PWChangePanel;
	
	
	private JButton SalesRateButton;
	private JButton BeverageButton;
	private JButton CoinButton;
	private JButton PWChangeButton;
	private JButton ReturnButton;
	
	private Manager manager2;
	private FileIO fileIO;
	DefaultTableModel SalesModel;
	DefaultTableModel BeverageModel;
	String password;
	
	public AdminFrame() {

		//프레임 초기화
		mainPanel=new JPanel();
		mainPanel.setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(400,300);
		setSize(800, 400);
		setTitle("자판기 프로그램");
		fileIO=new FileIO();
		manager2=new Manager();
		fileIO.readFile(manager2.machine);
		
		mainPanel.add(getMenuPanel(),BorderLayout.EAST);
		mainPanel.add(getChartPanel(),BorderLayout.CENTER);
		setContentPane(mainPanel);
		
		setVisible(true);
		
				
	}

	public JPanel getChartPanel() {
		if(chartPanel==null) {
			chartPanel=new JPanel();
			//차트 판넬의 레이아웃을 cardlayout으로 설정해서 
			//버튼 이벤트에 따라서 각각의 판넬을 호출한다
			chartPanel.setLayout(cards);
			chartPanel.add(getSalesRatePanel(),"Sales");
			chartPanel.add(getBeverageStuckPanel(),"Beverage");
			chartPanel.add(getCoinStuckPanel(),"Coin");
			chartPanel.add(getPWChangePanel(),"PW");
		}
		
		return chartPanel;
	}

	public JPanel getMenuPanel() {
		if(chartPanel==null) {
			menuPanel=new JPanel();
			menuPanel.setBorder(new EmptyBorder(5,5,5,5));
			menuPanel.setPreferredSize(new Dimension(300,300));
			menuPanel.setLayout(new GridLayout(8,1));
			menuPanel.add(getDayButton());
			menuPanel.add(getBeverageButton());
			menuPanel.add(getCoinButton());
			menuPanel.add(getPWChangeButton());
			menuPanel.add(getReturnButton());
		}
		return menuPanel;
	}

	public JPanel getSalesRatePanel() {
		if(SalesRatePanel==null) {
			SalesRatePanel=new JPanel();
			SalesRatePanel.setBorder(new EmptyBorder(5,5,5,5));
			SalesRatePanel.setPreferredSize(new Dimension(500,500));
			JLabel monthSalesMoney=new JLabel("총 판매량: ");
			JTable SalesTable=new JTable();
			String[] monthListName= {"1월","2월","3월","4월","5월","6월",
					         "7월","8월","9월","10월","11월","12월"};
			String []columnNames = {
					"날짜","물","커피"
					,"이온","고급커피","콜라"};
			
			
			//month 스크롤 바 파트
			JList<String> monthList=new JList<String>();
			monthList.setListData(monthListName);
			monthList.setSelectionBackground(Color.YELLOW);
			monthList.setSelectionForeground(Color.RED);
			JScrollPane monthScroll=new JScrollPane(monthList);
			SalesRatePanel.add(monthScroll);
			
			
			
			//판매량 데이터를 모델에 넣기 위해 String으로 넣고 있다
			int[][][] machineSalesArray=manager2.machine.getDailySalesByDrink();
			String[][][] boxedArray = new String[13][32][5];
			
			for (int i = 0; i < 13; i++) {
				for (int j = 0; j < 32; j++) {
					for (int j2 = 0; j2 <5; j2++) {
						boxedArray[i][j][j2] = Integer.toString(machineSalesArray[i][j][j2]);
					}
				}
			}
			
			/*
			 * 월별 선택 이벤트 리스너
			 * */
			monthList.addListSelectionListener(new ListSelectionListener() {	
				@Override
				public void valueChanged(ListSelectionEvent e) {
					// TODO Auto-generated method stub
					int index = monthList.getSelectedIndex();
					SalesModel=new DefaultTableModel(columnNames,0);
					
					//관리자가 선택한 인덱스에 따라서 가져오는 배열의 정보가 달라진다
					for (int j = 0; j < 31; j++) {
						String DayName=Integer.toString(j+1)+"일";
						String[] rowdata={DayName,
								boxedArray[index][j][0],
								boxedArray[index][j][1],
								boxedArray[index][j][2],
								boxedArray[index][j][3],
								boxedArray[index][j][4],};
						SalesModel.addRow(rowdata);
						
					}
					int sum=manager2.machine.getSalesMoneyByMonth(index);
					monthSalesMoney.setText((index+1)+"월 판매 금액: "+ sum+"원"); //월별 총 판매 금액 
					SalesTable.setModel(SalesModel);
				}
			});

			JScrollPane jScrollPane=new JScrollPane(SalesTable);
			jScrollPane.setPreferredSize(new Dimension(300,300));
			SalesRatePanel.add(jScrollPane);
			SalesRatePanel.add(monthSalesMoney);
			SalesRatePanel.setSize(500, 500);
		}
		
		
		return SalesRatePanel;
	}

	public JPanel getBeverageStuckPanel() {
		if(BeverageStuckPanel==null) {
			BeverageStuckPanel=new JPanel();
			BeverageStuckPanel.setBorder(new EmptyBorder(5,5,5,5));
			BeverageStuckPanel.setPreferredSize(new Dimension(500,500));
			
			JTable BeverageTable=new JTable();
			String []columnNames = {
					"물","커피"
					,"이온","고급커피","콜라"};
			
			BeverageModel=new DefaultTableModel(columnNames,0);
			
			//음료 재고 데이터를 모델에 넣기 위해 String으로 넣고 있다
			int[] machineBeverageArray=manager2.machine.getBeverageStuck();
			String[] boxedArray = new String[5];
			for(int i=0;i<5;i++) {
				boxedArray[i]=Integer.toString(machineBeverageArray[i]);
			}
			
			String[] rowdata={
					boxedArray[0],
					boxedArray[1],
					boxedArray[2],
					boxedArray[3],
					boxedArray[4],};
			BeverageModel.addRow(rowdata);
			BeverageTable.setModel(BeverageModel);
			
			
			JScrollPane jScrollPane=new JScrollPane(BeverageTable);
			jScrollPane.setPreferredSize(new Dimension(300,300));
			BeverageStuckPanel.add(jScrollPane);
			BeverageStuckPanel.setSize(500, 500);
		}
		return BeverageStuckPanel;
	}

	public JPanel getCoinStuckPanel() {
		if(CoinStuckPanel==null) {
			CoinStuckPanel=new JPanel();
			CoinStuckPanel.setBorder(new EmptyBorder(5,5,5,5));
			CoinStuckPanel.setPreferredSize(new Dimension(500,500));
			JLabel totalCoin;
			JTable BeverageTable=new JTable();
			String []columnNames = {
					"10원","50원"
					,"100원","500원","1000원"};
			
			BeverageModel=new DefaultTableModel(columnNames,0);
			
			//화폐 재고 데이터를 모델에 넣기 위해 String으로 넣고 있다
			Coin machineCoin=manager2.machine.getCoin();
			String[] boxedArray = new String[5];
			totalCoin=new JLabel("총 금액: "+machineCoin.getTotal()+"원");
			boxedArray[0]=Integer.toString(machineCoin.get_10won());
			boxedArray[1]=Integer.toString(machineCoin.get_50won());
			boxedArray[2]=Integer.toString(machineCoin.get_100won());
			boxedArray[3]=Integer.toString(machineCoin.get_500won());
			boxedArray[4]=Integer.toString(machineCoin.get_1000won());
			
			String[] rowdata={
					boxedArray[0],
					boxedArray[1],
					boxedArray[2],
					boxedArray[3],
					boxedArray[4],};
			BeverageModel.addRow(rowdata);
			BeverageTable.setModel(BeverageModel);
			
			
			JScrollPane jScrollPane=new JScrollPane(BeverageTable);
			jScrollPane.setPreferredSize(new Dimension(300,300));
			CoinStuckPanel.add(jScrollPane);
			CoinStuckPanel.add(totalCoin);
			CoinStuckPanel.setSize(500, 500);
		}
		return CoinStuckPanel;
	}

	public JPanel getPWChangePanel() {
		if(PWChangePanel==null) {
			PWChangePanel=new JPanel();
			PWChangePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			PWChangePanel.setBorder(new EmptyBorder(5,5,5,5));
			PWChangePanel.setPreferredSize(new Dimension(200,150));
			JButton change=new JButton("바꾸기");
			//비밀번호 조건을 검사하기 위한 정규식이다
			String pwPattern = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,20}$";

			JPasswordField value = new JPasswordField(25);
			value.setSize(200, 50);
		    JLabel l1=new JLabel("Password((영문(대소문자 구분), 숫자, 특수문자 조합, 8이상) :");    

		    PWChangePanel.add(l1);
		    PWChangePanel.add(value);
		    PWChangePanel.add(change);
    
		    
		    /*
		     * 바꾸기 버튼을 눌렀을 때 이벤트 리스너
		     * */
		    change.addMouseListener(new MouseAdapter() {

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mousePressed(e);
					password=new String(value.getPassword());
					Matcher match = Pattern.compile(pwPattern).matcher(password);
					
					
				    if(match.find()){
				        manager2.machine.setPassword(password);
				        JOptionPane.showMessageDialog(null,"비밀번호가 변경되었습니다.","자판기 알림",JOptionPane.INFORMATION_MESSAGE);
				        fileIO.saveToFile(manager2.machine);
				    }
				    else {
				    	JOptionPane.showMessageDialog(null,"비밀번호는 영문(대소문자 구분), 숫자, 특수문자 조합, 8자리 이상입니다","자판기 경고",JOptionPane.WARNING_MESSAGE);
				    }
				}
		    	
			});
		
		}
		return PWChangePanel;
	}

	public JButton getDayButton() {
		if(SalesRateButton==null) {
			SalesRateButton=new JButton("월별 매출량");
			
			SalesRateButton.addMouseListener(new MouseAdapter() {

				@Override
				public void mousePressed(MouseEvent e) {
					
					super.mousePressed(e);
					
					cards.show(chartPanel,"Sales");
					
				}
				
			});
		

			
		}
		
		return SalesRateButton;
	}

	public JButton getBeverageButton() {
		if(BeverageButton==null) {
			BeverageButton=new JButton("음료");
			
			BeverageButton.addMouseListener(new MouseAdapter() {

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mousePressed(e);
					cards.show(chartPanel,"Beverage");
				}});
		}
		return BeverageButton;
	}

	public JButton getCoinButton() {
		if(CoinButton==null) {
			CoinButton=new JButton("화폐재고");
			
			CoinButton.addMouseListener(new MouseAdapter() {

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mousePressed(e);
					cards.show(chartPanel,"Coin");
				}});
		}
		return CoinButton;
	}

	public JButton getPWChangeButton() {
		if(PWChangeButton==null) {
			PWChangeButton=new JButton("비밀번호 변경");
			
			PWChangeButton.addMouseListener(new MouseAdapter() {

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mousePressed(e);
					cards.show(chartPanel,"PW");
				}});
		}
		return PWChangeButton;
	}

	public JButton getReturnButton() {
		if(ReturnButton==null) {
			JFrame container=this;
			ReturnButton=new JButton("돌아가기");
			ReturnButton.addMouseListener(new MouseAdapter() {

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO 파일 저장 구현해야됨
					super.mousePressed(e);
					main.resetMachine(manager2.machine);
					
					container.dispose();
				}
				
			});
		}
		return ReturnButton;
	}


	public void readadmin() {
		fileIO.readFile(manager2.machine);
	}

	public void setMain(VmMain main) {
		// TODO Auto-generated method stub
		this.main=main;
	}
}
