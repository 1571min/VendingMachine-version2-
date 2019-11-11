package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import vendingmachine.Coin;
import vendingmachine.Manager;

public class VendingStartPage extends JFrame{
	//자판기 화면 구성요소 필드
	private JPanel mainPanel;
	private JPanel beveragePanel;
	


	private JPanel coinPanel;
	private JPanel beverageOutPutPanel;
	private JButton water;
	private JButton coffe;
	private JButton ion;
	private JButton premium_coffe;
	private JButton coke;
	private JButton adminbutton;
	
	//
	private Manager manager;
	private boolean logincheck;
	private int TotalUserMoney=0;		//이용자가 입력한 코인 합
	private int $1000count=0;			//천원 개수
	private Coin MachineCoin;			//자판기 코인 현황
	private static int LIMITE_SUM=5000; //
	private static int LIMITE_COUNT=3;
	
	
	public static ImageIcon button_water_image;
	public static ImageIcon button_coffee_image;
	public static ImageIcon button_ion_image;
	public static ImageIcon button_premium_coffee_image;
	public static ImageIcon button_coke_image;
	
	public static ImageIcon no_button_water_image;
	public static ImageIcon no_button_coffee_image;
	public static ImageIcon no_button_ion_image;
	public static ImageIcon no_button_premium_coffee_image;
	public static ImageIcon no_button_coke_image;
	
	
	public static ImageIcon button_10_image;
	public static ImageIcon button_50_image;
	public static ImageIcon button_100_image;
	public static ImageIcon button_500_image;
	public static ImageIcon button_1000_image;
	
	
	static {
		try {
			ImageIcon button_water_image=new ImageIcon(ImageIO.read(VendingStartPage.class.getResource("../image/water.png")));
			ImageIcon button_coffee_image=new ImageIcon(ImageIO.read(VendingStartPage.class.getResource("../image/coffee.png")));
			ImageIcon button_ion_image=new ImageIcon(ImageIO.read(VendingStartPage.class.getResource("../image/ion.png")));
			ImageIcon button_premium_coffee_image=new ImageIcon(ImageIO.read(VendingStartPage.class.getResource("../image/premium_coffee.png")));
			ImageIcon button_coke_image=new ImageIcon(ImageIO.read(VendingStartPage.class.getResource("../image/coke.png")));
			
			ImageIcon no_button_water_image=new ImageIcon(ImageIO.read(VendingStartPage.class.getResource("../image/no_water.png")));
			ImageIcon no_button_coffee_image=new ImageIcon(ImageIO.read(VendingStartPage.class.getResource("../image/no_coffee.png")));
			ImageIcon no_button_ion_image=new ImageIcon(ImageIO.read(VendingStartPage.class.getResource("../image/no_ion.png")));
			ImageIcon no_button_premium_coffee_image=new ImageIcon(ImageIO.read(VendingStartPage.class.getResource("../image/no_premium_coffee.png")));
			ImageIcon no_button_coke_image=new ImageIcon(ImageIO.read(VendingStartPage.class.getResource("../image/no_coke.png")));
			
			
			ImageIcon button_10_image=new ImageIcon(ImageIO.read(VendingStartPage.class.getResource("../image/10won.png")));
			ImageIcon button_50_image=new ImageIcon(ImageIO.read(VendingStartPage.class.getResource("../image/50won.png")));
			ImageIcon button_100_image=new ImageIcon(ImageIO.read(VendingStartPage.class.getResource("../image/100won.png")));
			ImageIcon button_500_image=new ImageIcon(ImageIO.read(VendingStartPage.class.getResource("../image/500won.png")));
			ImageIcon button_1000_image=new ImageIcon(ImageIO.read(VendingStartPage.class.getResource("../image/1000won.png")));
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
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(400,300);
		setSize(1000, 500);
		setTitle("자판기 프로그램");
		mainPanel=new JPanel();
		mainPanel.setLayout(new BorderLayout());
		//beveragepanel, coinpanel 추가
		
		setContentPane(mainPanel);
		setVisible(true);
	}
	
	public JPanel getBeveragePanel() {
		return beveragePanel;
	}

	public JPanel getCoinPanel() {
		return coinPanel;
	}

	public JPanel getBeverageOutPutPanel() {
		return beverageOutPutPanel;
	}

	public JButton getWater() {
		if(water==null) {
			
		}
		return water;
	}

	public JButton getCoffe() {
		return coffe;
	}

	public JButton getIon() {
		return ion;
	}

	public JButton getPremium_coffe() {
		return premium_coffe;
	}

	public JButton getCoke() {
		return coke;
	}

	public JButton getAdminbutton() {
		return adminbutton;
	}

	public Manager getManager() {
		return manager;
	}


}
