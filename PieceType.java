import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * 
 * Type of the piece enum
 * 
 */
public enum PieceType {
	ONECONN, BAR, TTYPE, FOURCONN, LTYPE, VOID;

	// ICI
	public int getNbConnectors() {
		switch (this) {
			case ONECONN:
				return 1;
			case BAR:
				return 2;
			case TTYPE:
				return 3;
			case FOURCONN:
				return 4;
			case LTYPE:
				return 2;
			case VOID:
				return 0;
			default:
				throw new IllegalArgumentException("Connector doesn't exist : " + this);
		}
	}
	// Each Type has a number of connectors and a specific value

	// ICI
	public LinkedList<Orientation> setConnectorsList(Orientation orientation) {
		LinkedList<Orientation> list = new LinkedList<Orientation>();

		switch (this) {
			case ONECONN:
				list.add(orientation);
				break;
			case BAR:
				list.add(orientation);
				list.add(orientation.getOpposedOrientation());
				break;
			case TTYPE:
				list.add(orientation);
				list.add(orientation.turn90());
				list.add(orientation.turn90().getOpposedOrientation());
				break;
			case FOURCONN:
				for (Orientation o : Orientation.values()) {
					list.add(o);
				}
				break;
			case LTYPE:
				list.add(orientation);
				list.add(orientation.turn90());
				break;
			case VOID:
				break;
		}
		return list;
	}

	// ICI
	public Orientation getOrientation(Orientation o) {
		return o;
	}

	// ICI
	public static PieceType getTypefromValue(int typeValue) {
		PieceType p = null;
		try {
			if (typeValue == 1) {
				p = ONECONN;
			} else if (typeValue == 2) {
				p = BAR;
			} else if (typeValue == 3) {
				p = TTYPE;
			} else if (typeValue == 4) {
				p = FOURCONN;
			} else if (typeValue == 5) {
				p = LTYPE;
			} else if (typeValue == 0) {
				p = VOID;
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return p;
	}

	// ICI
	public ArrayList<Orientation> getListOfPossibleOri() {
		ArrayList<Orientation> o = new ArrayList<Orientation>();
		o.add(Orientation.NORTH);
		o.add(Orientation.EAST);
		o.add(Orientation.SOUTH);
		o.add(Orientation.WEST);
		return o;
	}

}
