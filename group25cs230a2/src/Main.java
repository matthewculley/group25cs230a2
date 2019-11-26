
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map map = new Map("test.csv");
		System.out.println(map.toString());
		map.listEnemies();
		
		System.out.println(map.getAt(2,2));
		
//		
//		for (int i=0; i < 25; i++) {
//			int y = i % 5;    // % is the "modulo operator", the remainder of i / width;
//			int x = i / 5;    // where "/" is an integer division
//				
//			System.out.println("Index: " + i + " Coords: " + "(" + x + "," + y + ")" + " Item: " + map.getAt(x, y));
//		}
//		
		
		
	}

}
