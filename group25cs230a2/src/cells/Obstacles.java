package cells;

public class Obstacles extends Cell{

	//private inventory itemReq;
	
	public void checkItem(boolean item) {
		if(item == true) {
			open();
		}
	}
	
	private void open() {
		//Change into ground tile
	}
	
}
