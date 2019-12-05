package cells;

public class Wall extends Cell{
	public Wall () {
		super("wall.png", false);
	}
	//Wall type

	@Override
	public String getCellName() {
		// TODO Auto-generated method stub
		return "wall";
	}
}
