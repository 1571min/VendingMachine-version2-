package ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AdminPage extends JFrame{
	private Main main;
	
	private static JPanel mainPanel;
	private static JPanel chartPanel;
	private static JPanel menuPanel;
	
	private JButton DayButton;
	private JButton BeverageButton;
	private JButton CollectButton;
	private JButton StockButton;
	private JButton CoinButton;
	private JButton PWChangeButton;
	private JButton ReturnButton;
	
	
	public AdminPage() {
		/*
		 * ���Ͽ��� ������ �о��( ���ϰ�ü �ʿ�(�б� �Լ� �ʿ�)))
		 * manager�� ����
		 * ������ manager�� ������ ������ ������ ���� ���
		 * */
	}
	
	
	
	
	public static JPanel getMainPanel() {
		return mainPanel;
	}




	public static JPanel getChartPanel() {
		return chartPanel;
	}




	public static JPanel getMenuPanel() {
		return menuPanel;
	}




	public JButton getDayButton() {
		return DayButton;
	}




	public JButton getBeverageButton() {
		return BeverageButton;
	}




	public JButton getCollectButton() {
		return CollectButton;
	}




	public JButton getStockButton() {
		return StockButton;
	}




	public JButton getCoinButton() {
		return CoinButton;
	}




	public JButton getPWChangeButton() {
		return PWChangeButton;
	}




	public JButton getReturnButton() {
		return ReturnButton;
	}




	public void setMain(Main main) {
		// TODO Auto-generated method stub
		this.main=main;
	}
}
