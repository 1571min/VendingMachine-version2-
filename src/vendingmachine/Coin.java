package vendingmachine;

public class Coin {
	
	private int _10won;
	private int _50won;
	private int _100won;
	private int _500won;
	private int _1000won;
	
	
	//동전 개수 5개로 초기화
	public Coin() {
		this._10won = 5;
		this._50won = 5;
		this._100won = 5;
		this._500won = 5;
		this._1000won = 5;
	}
	
	
	// 동전 개수 getter
	public int getTotal() {
		return _10won*10+_50won*50+_100won*100+_500won*500+_1000won*1000;
	}
	public int get_10won() {
		return _10won;
	}
	public int get_50won() {
		return _50won;
	}
	public int get_100won() {
		return _100won;
	}
	public int get_500won() {
		return _500won;
	}
	public int get_1000won() {
		return _1000won;
	}

	
	//동전 개수 setter

	public void set_10won(int _10won) {
		this._10won = _10won;
	}



	public void set_50won(int _50won) {
		this._50won = _50won;
	}



	public void set_100won(int _100won) {
		this._100won = _100won;
	}



	public void set_500won(int _500won) {
		this._500won = _500won;
	}



	public void set_1000won(int _1000won) {
		this._1000won = _1000won;
	}
	
	
	
	
	
	
	
}
