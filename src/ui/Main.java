package ui;

import java.awt.EventQueue;

public class Main {

	static AdminPage adminPage;
	static VendingStartPage startPage;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Main main=new Main();
					startPage=new VendingStartPage();
					startPage.setMain(main);
					adminPage=new AdminPage();
					adminPage.setMain(main);
					adminPage.setVisible(false);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public void showLoginFrame() {
		startPage.setVisible(false);
		adminPage.readadmin();
		adminPage.setVisible(true);
		
	}
	public void showVendingMachineFrame() {
		startPage.setVisible(true);
		adminPage.setVisible(false);
	}

}
