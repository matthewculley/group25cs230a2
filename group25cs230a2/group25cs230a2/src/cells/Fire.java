package cells;
public class Fire extends Cell {
	public Fire() {
		super("fire.png", false);
	}

	@Override
	public String getCellName() {
		return "fire";
	}
}
