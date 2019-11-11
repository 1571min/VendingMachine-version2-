package vendingmachine;


public class Manager {
	
	public VendingMachine machine;

	
	public Manager(){
		machine=new VendingMachine();
	}
	
	
	public void init() {
		System.out.println("자판기를 초기화 하였습니다");
	}
	
}
