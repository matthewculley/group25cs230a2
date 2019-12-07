import java.util.Scanner;

public class IntegerInput {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		//System.out.println(plainIntBetweenRange(in));
		//System.out.println(plainInt(in));
		
	}
	
	//plain integer, only verification is that it is integer
	public static int plainInt(Scanner in) {
		
			boolean valid = false;
			int input = 0;
			String inputMessage = "Enter an integer: ";
			String invalidInput = "Ensure an integer is entered";

			while (!valid) {
				System.out.print(inputMessage);
				if (in.hasNextInt()) {
					input = in.nextInt();
					valid = true;
				} else {
					System.out.println(invalidInput);
					in.nextLine();
				}
			}
			return input;
	}
	
	//integer within a certain range, can change operatives on labelled line to adjust behaviour
	public static int plainIntBetweenRange(Scanner in) {
		
		boolean valid = false;
		int input = 0;
		int min = 0;
		int max = 100;
		
		String inputMessage = "Enter an integer within range: ";
		String invalidInput = "Ensure an integer is entered";

		while (!valid) {
			System.out.print(inputMessage);
			if (in.hasNextInt() && (input = in.nextInt()) >= min && input <= max) { //can adjust on this line ( >= , <= )
				valid = true;
			} else {
				System.out.println(invalidInput);
				in.nextLine();
			}
		}
		return input;
	}
	
	
}
