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
		 * ���Ͽ��� ������ �о��( ���ϰ�ü �ʿ�(�б� �Լ� �ʿ�)))
		 * manager�� ����
		 * ������ manager�� ������ ������ ������ ���� ���
		 * */
		
		//������ �ʱ�ȭ
		mainPanel=new JPanel();
		mainPanel.setLayout(new FlowLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(400,300);
		setSize(1000, 500);
		setTitle("���Ǳ� ���α׷�");
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
		
		DayButton=new JButton("���� ���ⷮ");
		return DayButton;
	}




	public JButton getBeverageButton() {
		BeverageButton=new JButton("���Ằ");
		return BeverageButton;
	}




	public JButton getCollectButton() {
		CollectButton =new JButton("����");
		return CollectButton;
	}




	public JButton getStockButton() {
		StockButton=new JButton("���");
		return StockButton;
	}




	public JButton getCoinButton() {
		CoinButton=new JButton("ȭ��");
		return CoinButton;
	}




	public JButton getPWChangeButton() {
		PWChangeButton=new JButton("��й�ȣ ����");
		return PWChangeButton;
	}




	public JButton getReturnButton() {
		ReturnButton=new JButton("���Ǳ� ������");
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
