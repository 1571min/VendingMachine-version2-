package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import fileio.FileIO;
import vendingmachine.Manager;

public class AdminFrame extends JFrame{
	private VmMain main;
	
	private JPanel mainPanel;
	private JPanel chartPanel;
	private JPanel menuPanel;
	private JPanel SalesRatePanel;
	
	private JButton SalesRateButton;
	private JButton BeverageButton;
	private JButton CollectButton;
	private JButton StockButton;
	private JButton CoinButton;
	private JButton PWChangeButton;
	private JButton ReturnButton;
	
	private Manager manager2;
	private FileIO fileIO;

	
	public AdminFrame() {
		/*
		 * ���Ͽ��� ������ �о��( ���ϰ�ü �ʿ�(�б� �Լ� �ʿ�)))
		 * manager�� ����
		 * ������ manager�� ������ ������ ������ ���� ���
		 * */
		
		//������ �ʱ�ȭ
		mainPanel=new JPanel();
		mainPanel.setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(400,300);
		setSize(700, 400);
		setTitle("���Ǳ� ���α׷�");
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
			chartPanel.setBorder(new EmptyBorder(5,5,5,5));
			chartPanel.setPreferredSize(new Dimension(500,500));
			
			int[][][] test=manager2.machine.getDailySalesByDrink();
			Integer[][][] boxedArray = new Integer[13][32][5];
			String []a = {
					"��"
					,"Ŀ��"
					,"�̿�"
					,"���Ŀ��"
					,"�ݶ�"
			};
			
			for (int i = 0; i < 13; i++) {
				for (int j = 0; j < 32; j++) {
					for (int j2 = 0; j2 <5; j2++) {
						boxedArray[i][j][j2] = Integer.valueOf(test[i][j][j2]);
					}
				}
			}
			
			
			JTable jTable=new JTable(boxedArray[11],a);
			JScrollPane jScrollPane=new JScrollPane(jTable);
			jScrollPane.setPreferredSize(new Dimension(300,300));
			chartPanel.add(jScrollPane);
			chartPanel.setSize(500, 500);
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
		
		SalesRateButton=new JButton("���� ���ⷮ");
		return SalesRateButton;
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
		fileIO.readFile(manager2.machine);
	}

	public void setMain(VmMain main) {
		// TODO Auto-generated method stub
		this.main=main;
	}
}
