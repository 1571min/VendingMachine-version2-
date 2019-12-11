package ui;

import java.awt.EventQueue;

import vendingmachine.VendingMachine;

public class VmMain {

	static AdminFrame adminPage;
	static VmStartFrame startPage;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					VmMain main=new VmMain();
					startPage=new VmStartFrame();
					startPage.setMain(main);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void resetMachine(VendingMachine machine) {
		startPage.manager.machine=machine;
	}


}
