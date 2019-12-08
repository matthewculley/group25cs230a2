package collectibles;

public class GasMask extends Collectible {

	public GasMask(int x, int y) {
		super(x, y, "gasMask.png");
	}

	public GasMask() {
		super("gasMask.png");
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getCollectibleName() {
		// TODO Auto-generated method stub
		return "gasMask";
	}

}