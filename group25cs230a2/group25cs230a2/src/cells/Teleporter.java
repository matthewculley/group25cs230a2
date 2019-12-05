package cells;

public class Teleporter extends Cell{
	private int pairingId;
	private Teleporter partner;
	private int x;
	private int y;

	public Teleporter(int x, int y, int pairing) {
		super("teleporter.png", true);
		setPairingId(pairing);
		setX(x);
		setY(y);
		
	}
	
	public Teleporter() {
		super("teleporter.png", true);
	}

	public int getPairingId() {
		return pairingId;
	}

	public void setPairingId(int pairingId) {
		this.pairingId = pairingId;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Teleporter getPartner() {
		return partner;
	}

	public void setPartner(Teleporter partner) {
		this.partner = partner;
	}
	
	public String toString() {
		return  "teleporter:" + pairingId;
	}

	@Override
	public String getCellName() {
		// TODO Auto-generated method stub
		return "teleporter:" + getPairingId();
	}

}
