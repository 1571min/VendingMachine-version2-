package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fileio.FileIO;
import vendingmachine.Manager;

public class AdminPage extends JFrame{
	private Main main;
	
	private JPanel mainPanel;
	private JPanel chartPanel;
	private JPanel menuPanel;
	
	private JButton DayButton;
	private JButton BeverageButton;
	private JButton CollectButton;
	private JButton StockButton;
	private JButton CoinButton;
	private JButton PWChangeButton;
	private JButton ReturnButton;
	
	private Manager manager;
	private FileIO fileIO;
	
	
	public AdminPage() {
		/*
		 * 파일에서 데이터 읽어옴( 파일객체 필요(읽기 함수 필요)))
		 * manager에 대입
		 * 대입한 manager를 가지고 관리자 페이지 정보 출력
		 * */
		
		//프레임 초기화
		mainPanel=new JPanel();
		mainPanel.setLayout(new FlowLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(400,300);
		setSize(1000, 500);
		setTitle("자판기 프로그램");
		mainPanel.add(getMenuPanel());
		mainPanel.add(getChartPanel());
		setContentPane(mainPanel);
		fileIO=new FileIO();
		manager=new Manager();
		setVisible(true);
		
				
	}
	
	

	public JPanel getChartPanel() {
		if(chartPanel==null) {
			chartPanel=new JPanel();
			chartPanel.setBorder(new EmptyBorder(5,5,5,5));
			chartPanel.setPreferredSize(new Dimension(500,500));
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
			menuPanel.add(getCollectButton());
			menuPanel.add(getStockButton());
			menuPanel.add(getCoinButton());
			menuPanel.add(getPWChangeButton());
			menuPanel.add(getReturnButton());
		}
		return menuPanel;
	}




	public JButton getDayButton() {
		
		DayButton=new JButton("월별 매출량");
		return DayButton;
	}




	public JButton getBeverageButton() {
		BeverageButton=new JButton("음료별");
		return BeverageButton;
	}




	public JButton getCollectButton() {
		CollectButton =new JButton("수금");
		return CollectButton;
	}




	public JButton getStockButton() {
		StockButton=new JButton("재고");
		return StockButton;
	}




	public JButton getCoinButton() {
		CoinButton=new JButton("화폐");
		return CoinButton;
	}




	public JButton getPWChangeButton() {
		PWChangeButton=new JButton("비밀번호 변경");
		return PWChangeButton;
	}




	public JButton getReturnButton() {
		ReturnButton=new JButton("자판기 페이지");
		return ReturnButton;
	}


	public void readadmin() {
		fileIO.readFile(manager.machine);
	}

	public void setMain(Main main) {
		// TODO Auto-generated method stub
		this.main=main;
	}
}
