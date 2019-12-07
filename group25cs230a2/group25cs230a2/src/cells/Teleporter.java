package cells;

/**
 * A class that defines Teleporter.
 * @author Group25
 * @version 1.2
 */

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

	/**
 	 * Get the pariringId.
 	 * @return pairingId.
 	 */
	
	public int getPairingId() {
		return pairingId;
	}

	/**
 	 * Set the pairingId.
 	 * @param The new pairingId.
 	 */
	
	public void setPairingId(int pairingId) {
		this.pairingId = pairingId;
	}

	/**
 	 * Get the x coordinate.
 	 * @return x The x coordinate.
 	 */
	
	public int getX() {
		return x;
	}

	/**
	 * Set the x coordinate.
 	 * @param x The new x coordinate.
 	 */
	
	public void setX(int x) {
		this.x = x;
	}

	/**
 	 * Get the y coordinate.
 	 * @return y The y coordinate.
 	 */
	
	public int getY() {
		return y;
	}

	/**
 	 * Set the y coordinate.
 	 * @param y The new y coordinate.
 	 */
	
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Get the partner.
 	 * @return partner.
 	 */
	
	public Teleporter getPartner() {
		return partner;
	}

	/**
 	 * Set the partner.
 	 * @param partner The new partner.
 	 */
	
	public void setPartner(Teleporter partner) {
		this.partner = partner;
	}
	
	/**
 	 * Prints the pairingId.
 	 * @return pairingId for teleporter.
 	 */
	
	public String toString() {
		return  "teleporter:" + pairingId;
	}

	/**
 	 * Overrides the method getCellName.
 	 * @return pairingId for teleporter.
 	 */
	
	@Override
	public String getCellName() {
		return "teleporter:" + getPairingId();
	}
}
