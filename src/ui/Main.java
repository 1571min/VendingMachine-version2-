package ui;

public class Main {

	static AdminPage adminPage;
	static VendingStartPage startPage;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main main=new Main();
		startPage=new VendingStartPage();
		startPage.setMain(main);
		adminPage=new AdminPage();
		adminPage.setMain(main);
		adminPage.setVisible(false);
		
		
	}
	
	
	public void showLoginFrame() {
		startPage.setVisible(false);
		adminPage.setVisible(true);
	}
	public void showVendingMachineFrame() {
		startPage.setVisible(true);
		adminPage.setVisible(false);
	}

}
