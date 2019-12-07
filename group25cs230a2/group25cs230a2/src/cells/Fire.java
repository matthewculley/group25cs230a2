package cells;
public class Fire extends Cell {
	public Fire() {
		super("fire.png", true);
	}

	@Override
	public String getCellName() {
		return "fire";
	}
}
