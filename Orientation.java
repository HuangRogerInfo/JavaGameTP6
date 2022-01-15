import java.io.IOException;
import java.util.HashMap;

/**
 * 
 * Orientation of the piece enum
 * 
 */
public enum Orientation {
	NORTH, EAST, SOUTH, WEST;

	// ICI
	public static int[] getOpposedPieceCoordinates(Piece p) {
		int[] opposed_values = { -p.getPosX(), -p.getPosY() };
		return opposed_values;
	}

	/*
	 * Implement all the possible orientations and
	 * required methods to rotate
	 */

	// ICI
	public Orientation getOpposedOrientation() {
		switch (this) {
			case NORTH:
				return Orientation.SOUTH;
			case SOUTH:
				return Orientation.NORTH;
			case EAST:
				return Orientation.WEST;
			case WEST:
				return Orientation.EAST;
			default:
				throw new IllegalArgumentException("Orientation doesn't exist : " + this);
		}
	}

	// ICI
	public static Orientation getOrifromValue(int orientationValue) {
		switch (orientationValue) {
			case 0:
				return Orientation.NORTH;
			case 1:
				return Orientation.EAST;
			case 2:
				return Orientation.SOUTH;
			case 3:
				return Orientation.WEST;
			default:
				throw new IllegalArgumentException(
						"D�sol�, cela ne correspond � aucune des orientation : " + orientationValue);
		}
	}

	public static int getValuefromOri(Orientation orientation) {
		switch (orientation) {
			case NORTH:
				return 0;
			case EAST:
				return 1;
			case SOUTH:
				return 2;
			case WEST:
				return 3;
			default:
				throw new IllegalArgumentException("D�sol�, cela ne correspond � aucune valeur : " + orientation);
		}
	}

	// ICI
	public Orientation turn90() {
		switch (this) {
			case NORTH:
				return Orientation.EAST;
			case EAST:
				return Orientation.SOUTH;
			case SOUTH:
				return Orientation.WEST;
			case WEST:
				return Orientation.NORTH;
			default:
				throw new IllegalArgumentException("Orientation doesn't exist :  " + this);
		}
	}

}
