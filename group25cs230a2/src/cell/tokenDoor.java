package cell;

public class tokenDoor extends door{

	private inventory inv;
	private int tokenReq = 2;
	//How many tokens required to open??
	
	public void checkTokenCount() {
		if(inv.tokenNum == tokenReq) {
			super.open();
		}
	}
}
