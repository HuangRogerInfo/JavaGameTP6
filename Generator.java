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

			// On remplit la grille de pieces aleatoires
			for (int i = 0; i < inputGrid.getWidth(); i++) {
				for (int j = 0; j < inputGrid.getHeight(); j++) {
					Piece p_courante = inputGrid.getPiece(i, j);
					HashSet<PieceType> PossibleTypes = new HashSet<PieceType>();

					if (inputGrid.isCorner(i, j)) {
						for (PieceType type : PieceType.values()) {
							if ((type == PieceType.ONECONN || type == PieceType.LTYPE)) {
								PossibleTypes.add(type);
							}
						}
					}

					else if (inputGrid.isBorderLine(i, j)) {

						for (PieceType type : PieceType.values()) {
							if (type != PieceType.FOURCONN) {
								PossibleTypes.add(type);
							}
						}
					}

					else if (inputGrid.isBorderColumn(i, j)) {
						for (PieceType type : PieceType.values()) {
							if (type != PieceType.FOURCONN) {
								PossibleTypes.add(type);
							}
						}
					}

					else {
						for (PieceType type : PieceType.values()) {
								PossibleTypes.add(type);
						}
					}
			
					ArrayList<PieceType> types = new ArrayList<PieceType>(PossibleTypes);
					int index = r.nextInt(types.size());

					Piece p = new Piece(i, j, types.get(index), Orientation.getOrifromValue(r.nextInt(4)));
					inputGrid.setPiece(i, j, p);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// On arrange pour que la grille soit resolvable
		try {
			Random r = new Random();

			for (int i = 0; i < inputGrid.getWidth(); i++) {
				for (int j = 0; j < inputGrid.getHeight(); j++) {

					Piece p_courante = inputGrid.getPiece(i, j);
					HashSet<Piece> voisins = new HashSet<Piece>();
					HashSet<PieceType> PossibleTypes = new HashSet<PieceType>();

					System.out.println(i + "/" + j);

					if (inputGrid.existNeighbour(p_courante)) {

						if (inputGrid.isCorner(i, j)) {
							// Top left corner
							if (i == 0 && j == 0) {
								voisins.add(inputGrid.rightNeighbor(p_courante));
								voisins.add(inputGrid.bottomNeighbor(p_courante));
							}

							// Top right corner
							if (i == 0 && j == inputGrid.getWidth() - 1) {
								voisins.add(inputGrid.leftNeighbor(p_courante));
								voisins.add(inputGrid.bottomNeighbor(p_courante));
							}

							// Bot left corner
							if (i == inputGrid.getHeight() - 1 && j == 0) {
								voisins.add(inputGrid.rightNeighbor(p_courante));
								voisins.add(inputGrid.topNeighbor(p_courante));
							}

							// Bot right corner
							if (i == inputGrid.getHeight() - 1 && j == inputGrid.getWidth() - 1) {
								voisins.add(inputGrid.leftNeighbor(p_courante));
								voisins.add(inputGrid.topNeighbor(p_courante));
							}

							int nb_connectors = voisins.size();

							System.out.println("nb=" + nb_connectors);

							for (PieceType type : PieceType.values()) {
								if (type.getNbConnectors() == nb_connectors
										&& (type == PieceType.ONECONN || type == PieceType.LTYPE)) {
									PossibleTypes.add(type);
								}
							}
						}

						else if (inputGrid.isBorderLine(i, j)) {
							voisins.add(inputGrid.bottomNeighbor(p_courante));
							voisins.add(inputGrid.topNeighbor(p_courante));

							if (i == 0) {
								voisins.add(inputGrid.rightNeighbor(p_courante));
							} else {
								voisins.add(inputGrid.leftNeighbor(p_courante));
							}

							int nb_connectors = voisins.size();

							for (PieceType type : PieceType.values()) {
								if (type.getNbConnectors() == nb_connectors && type != PieceType.FOURCONN) {
									PossibleTypes.add(type);
								}
							}
						}

						else if (inputGrid.isBorderColumn(i, j)) {
							voisins.add(inputGrid.leftNeighbor(p_courante));
							voisins.add(inputGrid.rightNeighbor(p_courante));

							if (j == 0) {
								voisins.add(inputGrid.bottomNeighbor(p_courante));
							} else {
								voisins.add(inputGrid.topNeighbor(p_courante));
							}

							int nb_connectors = voisins.size();

							for (PieceType type : PieceType.values()) {
								if (type.getNbConnectors() == nb_connectors && type != PieceType.FOURCONN) {
									PossibleTypes.add(type);
								}
							}
						}

						else {
							voisins.add(inputGrid.leftNeighbor(p_courante));
							voisins.add(inputGrid.rightNeighbor(p_courante));
							voisins.add(inputGrid.topNeighbor(p_courante));
							voisins.add(inputGrid.bottomNeighbor(p_courante));

							int nb_connectors = voisins.size();

							for (PieceType type : PieceType.values()) {
								if (type.getNbConnectors() == nb_connectors) {
									PossibleTypes.add(type);
								}
							}
						}

					} else {
						PossibleTypes.add(PieceType.VOID);
					}

					System.out.println(voisins.size());

					ArrayList<PieceType> types = new ArrayList<PieceType>(PossibleTypes);
					int index = r.nextInt(types.size());

					Piece p = new Piece(i, j, types.get(index), Orientation.getOrifromValue(r.nextInt(4)));
					inputGrid.setPiece(i, j, p);
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