import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

/**
 * Generate a solution, number of connexe composant is not finished
 *
 */

public class Generator {

	private static Grid filledGrid;

	/**
	 * @param output
	 *               file name
	 * @throws IOException
	 *                     - if an I/O error occurs.
	 * @return a File that contains a grid filled with pieces (a level)
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public static void generateLevel(String fileName, Grid inputGrid) {

		try {

			Random r = new Random();
			for (int i = 0; i < inputGrid.getWidth(); i++) {
				for (int j = 0; j < inputGrid.getHeight(); j++) {

					// On pose la premiere piece
					if (i == 0 && j == 0) {
						inputGrid.setPiece(i, j, new Piece(i, j, PieceType.ONECONN, Orientation.EAST));
						j++;
					}

					Piece p_courante = new Piece(i, j);
					HashSet<PieceType> PossibleTypes = new HashSet<PieceType>();
					HashSet<Orientation> contraintes = new HashSet<Orientation>();
					HashSet<Orientation> notContainsContraintes = new HashSet<Orientation>();
					ArrayList<Piece> PossiblePiece = new ArrayList<Piece>();

					if (inputGrid.isCorner(i, j)) {
						// Type possible
						for (PieceType type : PieceType.values()) {
							if ((type == PieceType.ONECONN || type == PieceType.LTYPE)) {
								PossibleTypes.add(type);
							}
						}

						// Contraintes de connecteurs
						if (i == 0 && j == 0) {
							// Top left corner
							System.out.println("corner_top_left");

							notContainsContraintes.add(Orientation.NORTH);
							notContainsContraintes.add(Orientation.WEST);

							if (inputGrid.rightNeighbor(p_courante) != null) {
								if (inputGrid.rightNeighbor(p_courante).getConnectors().contains(Orientation.WEST)) {
									contraintes.add(Orientation.EAST);
								} else {
									notContainsContraintes.add(Orientation.EAST);
								}
							}

							if (inputGrid.bottomNeighbor(p_courante) != null) {
								if (inputGrid.bottomNeighbor(p_courante).getConnectors().contains(Orientation.NORTH)) {
									contraintes.add(Orientation.SOUTH);
								} else {
									notContainsContraintes.add(Orientation.SOUTH);
								}
							}
						}

						if (i == 0 && j == inputGrid.getWidth() - 1) {
							// Top right corner
							System.out.println("corner_top_right");

							notContainsContraintes.add(Orientation.NORTH);
							notContainsContraintes.add(Orientation.EAST);

							if (inputGrid.leftNeighbor(p_courante) != null) {
								if (inputGrid.leftNeighbor(p_courante).getConnectors().contains(Orientation.EAST)) {
									contraintes.add(Orientation.WEST);
								} else {
									notContainsContraintes.add(Orientation.WEST);
								}
							}

							if (inputGrid.bottomNeighbor(p_courante) != null) {
								if (inputGrid.bottomNeighbor(p_courante).getConnectors().contains(Orientation.NORTH)) {
									contraintes.add(Orientation.SOUTH);
								} else {
									notContainsContraintes.add(Orientation.SOUTH);
								}
							}
						}

						if (i == inputGrid.getHeight() - 1 && j == 0) {
							// Bot left corner
							System.out.println("corner_bot_left");

							notContainsContraintes.add(Orientation.WEST);
							notContainsContraintes.add(Orientation.SOUTH);

							if (inputGrid.rightNeighbor(p_courante) != null) {
								if (inputGrid.rightNeighbor(p_courante).getConnectors().contains(Orientation.WEST)) {
									contraintes.add(Orientation.EAST);
								} else {
									notContainsContraintes.add(Orientation.EAST);
								}
							}

							if (inputGrid.topNeighbor(p_courante) != null) {
								if (inputGrid.topNeighbor(p_courante).getConnectors().contains(Orientation.SOUTH)) {
									contraintes.add(Orientation.NORTH);
								} else {
									notContainsContraintes.add(Orientation.NORTH);
								}
							}

						}

						if (i == inputGrid.getHeight() - 1 && j == inputGrid.getWidth() - 1) {
							// Bot right corner
							System.out.println("corner_bot_right");

							notContainsContraintes.add(Orientation.EAST);
							notContainsContraintes.add(Orientation.SOUTH);

							if (inputGrid.leftNeighbor(p_courante) != null) {
								if (inputGrid.leftNeighbor(p_courante).getConnectors().contains(Orientation.EAST)) {
									contraintes.add(Orientation.WEST);
								} else {
									notContainsContraintes.add(Orientation.WEST);
								}
							}

							if (inputGrid.topNeighbor(p_courante) != null) {
								if (inputGrid.topNeighbor(p_courante).getConnectors().contains(Orientation.SOUTH)) {
									contraintes.add(Orientation.NORTH);
								} else {
									notContainsContraintes.add(Orientation.NORTH);
								}
							}
						}
					}

					else if (inputGrid.isBorderLine(i, j)) {

						// Type possible
						for (PieceType type : PieceType.values()) {
							if (type != PieceType.FOURCONN) {
								PossibleTypes.add(type);
							}
						}

						// Contraintes de connecteurs
						if (i == 0) {
							// Top
							System.out.println("borderline_top");

							notContainsContraintes.add(Orientation.NORTH);

							if (inputGrid.rightNeighbor(p_courante) != null) {
								if (inputGrid.rightNeighbor(p_courante).getConnectors().contains(Orientation.WEST)) {
									contraintes.add(Orientation.EAST);
								} else {
									notContainsContraintes.add(Orientation.EAST);
								}
							}

							if (inputGrid.leftNeighbor(p_courante) != null) {
								if (inputGrid.leftNeighbor(p_courante).getConnectors().contains(Orientation.EAST)) {
									contraintes.add(Orientation.WEST);
								} else {
									notContainsContraintes.add(Orientation.WEST);
								}
							}

							if (inputGrid.bottomNeighbor(p_courante) != null) {
								if (inputGrid.bottomNeighbor(p_courante).getConnectors().contains(Orientation.NORTH)) {
									contraintes.add(Orientation.SOUTH);
								} else {
									notContainsContraintes.add(Orientation.SOUTH);
								}
							}
						}

						else {
							// Bot
							System.out.println("borderline_bot");

							notContainsContraintes.add(Orientation.SOUTH);

							if (inputGrid.rightNeighbor(p_courante) != null) {
								if (inputGrid.rightNeighbor(p_courante).getConnectors().contains(Orientation.WEST)) {
									contraintes.add(Orientation.EAST);
								} else {
									notContainsContraintes.add(Orientation.EAST);
								}
							}

							if (inputGrid.leftNeighbor(p_courante) != null) {
								if (inputGrid.leftNeighbor(p_courante).getConnectors().contains(Orientation.EAST)) {
									contraintes.add(Orientation.WEST);
								} else {
									notContainsContraintes.add(Orientation.WEST);
								}
							}

							if (inputGrid.topNeighbor(p_courante) != null) {
								if (inputGrid.topNeighbor(p_courante).getConnectors().contains(Orientation.SOUTH)) {
									contraintes.add(Orientation.NORTH);
								} else {
									notContainsContraintes.add(Orientation.NORTH);
								}
							}
						}
					}

					else if (inputGrid.isBorderColumn(i, j)) {
						// Type possible
						for (PieceType type : PieceType.values()) {
							if (type != PieceType.FOURCONN) {
								PossibleTypes.add(type);
							}
						}

						// Contraintes de connecteurs
						if (j == 0) {
							// Left
							System.out.println("bordercolum_left");

							notContainsContraintes.add(Orientation.WEST);

							if (inputGrid.topNeighbor(p_courante) != null) {
								if (inputGrid.topNeighbor(p_courante).getConnectors().contains(Orientation.SOUTH)) {
									contraintes.add(Orientation.NORTH);
								} else {
									notContainsContraintes.add(Orientation.NORTH);
								}
							}

							if (inputGrid.bottomNeighbor(p_courante) != null) {
								if (inputGrid.bottomNeighbor(p_courante).getConnectors().contains(Orientation.NORTH)) {
									contraintes.add(Orientation.SOUTH);
								} else {
									notContainsContraintes.add(Orientation.SOUTH);
								}
							}

							if (inputGrid.rightNeighbor(p_courante) != null) {
								if (inputGrid.rightNeighbor(p_courante).getConnectors().contains(Orientation.WEST)) {
									contraintes.add(Orientation.EAST);
								} else {
									notContainsContraintes.add(Orientation.EAST);
								}
							}
						} else {
							// Right
							System.out.println("bordercolum_right");

							notContainsContraintes.add(Orientation.EAST);

							if (inputGrid.topNeighbor(p_courante) != null) {
								if (inputGrid.topNeighbor(p_courante).getConnectors().contains(Orientation.SOUTH)) {
									contraintes.add(Orientation.NORTH);
								} else {
									notContainsContraintes.add(Orientation.NORTH);
								}
							}

							if (inputGrid.bottomNeighbor(p_courante) != null) {
								if (inputGrid.bottomNeighbor(p_courante).getConnectors().contains(Orientation.NORTH)) {
									contraintes.add(Orientation.SOUTH);
								} else {
									notContainsContraintes.add(Orientation.SOUTH);
								}
							}

							if (inputGrid.leftNeighbor(p_courante) != null) {
								if (inputGrid.leftNeighbor(p_courante).getConnectors().contains(Orientation.EAST)) {
									contraintes.add(Orientation.WEST);
								} else {
									notContainsContraintes.add(Orientation.WEST);
								}
							}
						}
					}

					else {
						System.out.println("Inside");
						// Type possible
						for (PieceType type : PieceType.values()) {
							PossibleTypes.add(type);
						}

						// Contraintes de connecteurs
						if (inputGrid.topNeighbor(p_courante) != null) {
							if (inputGrid.topNeighbor(p_courante).getConnectors().contains(Orientation.SOUTH)) {
								contraintes.add(Orientation.NORTH);
							} else {
								notContainsContraintes.add(Orientation.NORTH);
							}
						}

						if (inputGrid.bottomNeighbor(p_courante) != null) {
							if (inputGrid.bottomNeighbor(p_courante).getConnectors().contains(Orientation.NORTH)) {
								contraintes.add(Orientation.SOUTH);
							} else {
								notContainsContraintes.add(Orientation.SOUTH);
							}
						}

						if (inputGrid.leftNeighbor(p_courante) != null) {
							if (inputGrid.leftNeighbor(p_courante).getConnectors().contains(Orientation.EAST)) {
								contraintes.add(Orientation.WEST);
							} else {
								notContainsContraintes.add(Orientation.WEST);
							}
						}

						if (inputGrid.rightNeighbor(p_courante) != null) {
							if (inputGrid.rightNeighbor(p_courante).getConnectors().contains(Orientation.WEST)) {
								contraintes.add(Orientation.EAST);
							} else {
								notContainsContraintes.add(Orientation.EAST);
							}
						}
					}

					System.out.println("types = " + PossibleTypes);
					System.out.println("Contains = " + contraintes);
					System.out.println("NotContains = " + notContainsContraintes);

					// Piece Possibles en fonction des contraintes de connecteurs
					for (PieceType type : PossibleTypes) {
						for (Orientation orientation : Orientation.values()) {
							Piece p = new Piece(i, j, type, orientation);

							HashSet<Orientation> connecteurs = new HashSet<>();
							for (Orientation o : p.getConnectors()) {
								connecteurs.add(o);
							}

							if (contraintes.size() != 0) {
								if (!(p.getType().equals(PieceType.VOID))) {
									if (connecteurs.containsAll(contraintes)) {
										PossiblePiece.add(p);
									}
								}
							} else {
								PossiblePiece.add(p);
							}
						}
					}

					for (Orientation forbiddenConnector : notContainsContraintes) {
						PossiblePiece.removeIf(p -> p.getConnectors().contains(forbiddenConnector));
					}

					// System.out.println("PossiblePiece = " + PossiblePiece);

					if (PossiblePiece.size() == 0) {
						inputGrid.setPiece(i, j, new Piece(i, j));
					} else {
						int index = r.nextInt(PossiblePiece.size());
						inputGrid.setPiece(i, j, PossiblePiece.get(index));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int[] copyGrid(Grid filledGrid, Grid inputGrid, int i, int j) {
		Piece p;
		int hmax = inputGrid.getHeight();
		int wmax = inputGrid.getWidth();

		if (inputGrid.getHeight() != filledGrid.getHeight())
			hmax = filledGrid.getHeight() + i; // we must adjust hmax to have the height of the original grid
		if (inputGrid.getWidth() != filledGrid.getWidth())
			wmax = filledGrid.getWidth() + j;

		int tmpi = 0;// temporary variable to stock the last index
		int tmpj = 0;

		// DEBUG System.out.println("copyGrid : i =" + i + " & j = " + j);
		// DEBUG System.out.println("hmax = " + hmax + " - wmax = " + wmax);
		for (int x = i; x < hmax; x++) {
			for (int y = j; y < wmax; y++) {
				// DEBUG System.out.println("x = " + x + " - y = " + y);
				p = filledGrid.getPiece(x - i, y - j);
				// DEBUG System.out.println("x = " + x + " - y = " +
				// y);System.out.println(p);
				inputGrid.setPiece(x, y, new Piece(x, y, p.getType(), p.getOrientation()));
				// DEBUG System.out.println("x = " + x + " - y = " +
				// y);System.out.println(inputGrid.getPiece(x, y));
				tmpj = y;
			}
			tmpi = x;
		}
		// DEBUGSystem.out.println("tmpi =" + tmpi + " & tmpj = " + tmpj);
		return new int[] { tmpi, tmpj };
	}
}