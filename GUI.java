import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * This class handles the GUI
 * 
 *
 */
public class GUI extends JPanel implements MouseListener {

	private JFrame frame;

	/**
	 * 
	 * @param inputFile
	 *                  String from IO
	 * @throws IOException
	 *                     if there is a problem with the gui
	 */
	public static void startGUI(String inputFile) throws NullPointerException {
		// We have to check that the grid is generated before to launch the GUI
		// construction
		Runnable task = new Runnable() {
			public void run() {

				try {
					final Grid grid = Checker.buildGrid(inputFile);
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							GUI window;
							window = new GUI(grid);
							window.frame.setVisible(true);
						}
					});
				} catch (NullPointerException e) {
					throw new NullPointerException("Error with input file");
				}

			}
		};
		new Thread(task).start();

	}

	// ICI
	/**
	 * Create the application.
	 * 
	 * @throws IOException
	 */
	public GUI(Grid grid) {
		initialize(grid);
	}

	// ICI
	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws IOException
	 */
	private void initialize(Grid grid) {
		Piece[][] p = grid.getAllPieces();
		this.frame = new JFrame();
		this.frame.setTitle("Infinity Loop");
		this.frame.setSize(500, 500);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel(new GridLayout(5, 5));
		this.frame.add(panel);
		this.frame.setVisible(true);
		for (int i = 0; i < p.length; i++) {
			for (int j = 0; j < p[i].length; j++) {
				try {
					if (p[i][j] != null) {
						JLabel jl = new JLabel();
						ImageIcon img = this.getImageIcon(p[i][j]);
						jl.addMouseListener(this);
						jl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
						jl.setIcon(img);
						panel.add(jl);
						panel.validate();
					} else {
						JLabel jl = new JLabel();
						jl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
						panel.add(jl);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		// To implement:
		// creating frame, labels
		// Implementing method mouse clicked of interface MouseListener.
	}

	// ICI
	/**
	 * Display the correct image from the piece's type and orientation
	 * 
	 * @param p
	 *          the piece
	 * @return an image icon
	 */
	private ImageIcon getImageIcon(Piece p) throws IOException {
		// System.out.println(p.getType() + " | " + p.getOrientation());
		String chemin = "./ressources/";
		File f = null;
		ImageIcon i = null;
		BufferedImage bi = null;
		if (p.getType() == PieceType.ONECONN) {
			if (p.getOrientation() == Orientation.NORTH) {
				f = new File(chemin + "1.png");
				bi = ImageIO.read(f);
				i = new ImageIcon(bi);
			} else if (p.getOrientation() == Orientation.EAST) {
				f = new File(chemin + "2.png");
				bi = ImageIO.read(f);
				i = new ImageIcon(bi);
			} else if (p.getOrientation() == Orientation.SOUTH) {
				f = new File(chemin + "3.png");
				bi = ImageIO.read(f);
				i = new ImageIcon(bi);
			} else if (p.getOrientation() == Orientation.WEST) {
				f = new File(chemin + "4.png");
				bi = ImageIO.read(f);
				i = new ImageIcon(bi);
			}
		} else if (p.getType() == PieceType.BAR) {
			if (p.getOrientation() == Orientation.NORTH) {
				f = new File(chemin + "5.png");
				bi = ImageIO.read(f);
				i = new ImageIcon(bi);
			} else if (p.getOrientation() == Orientation.EAST) {
				f = new File(chemin + "6.png");
				bi = ImageIO.read(f);
				i = new ImageIcon(bi);
			} else if (p.getOrientation() == Orientation.SOUTH) {
				f = new File(chemin + "5.png");
				bi = ImageIO.read(f);
				i = new ImageIcon(bi);
			} else if (p.getOrientation() == Orientation.WEST) {
				f = new File(chemin + "6.png");
				bi = ImageIO.read(f);
				i = new ImageIcon(bi);
			}
		} else if (p.getType() == PieceType.TTYPE) {
			if (p.getOrientation() == Orientation.NORTH) {
				f = new File(chemin + "7.png");
				bi = ImageIO.read(f);
				i = new ImageIcon(bi);
			} else if (p.getOrientation() == Orientation.EAST) {
				f = new File(chemin + "8.png");
				bi = ImageIO.read(f);
				i = new ImageIcon(bi);
			} else if (p.getOrientation() == Orientation.SOUTH) {
				f = new File(chemin + "9.png");
				bi = ImageIO.read(f);
				i = new ImageIcon(bi);
			} else if (p.getOrientation() == Orientation.WEST) {
				f = new File(chemin + "10.png");
				bi = ImageIO.read(f);
				i = new ImageIcon(bi);
			}
		} else if (p.getType() == PieceType.FOURCONN) {
			if (p.getOrientation() == Orientation.NORTH) {
				f = new File(chemin + "11.png");
				bi = ImageIO.read(f);
				i = new ImageIcon(bi);
			} else if (p.getOrientation() == Orientation.EAST) {
				f = new File(chemin + "11.png");
				bi = ImageIO.read(f);
				i = new ImageIcon(bi);
			} else if (p.getOrientation() == Orientation.SOUTH) {
				f = new File(chemin + "11.png");
				bi = ImageIO.read(f);
				i = new ImageIcon(bi);
			} else if (p.getOrientation() == Orientation.WEST) {
				f = new File(chemin + "11.png");
				bi = ImageIO.read(f);
				i = new ImageIcon(bi);
			}
		} else if (p.getType() == PieceType.LTYPE) {
			if (p.getOrientation() == Orientation.NORTH) {
				f = new File(chemin + "12.png");
				bi = ImageIO.read(f);
				i = new ImageIcon(bi);
			} else if (p.getOrientation() == Orientation.EAST) {
				f = new File(chemin + "13.png");
				bi = ImageIO.read(f);
				i = new ImageIcon(bi);
			} else if (p.getOrientation() == Orientation.SOUTH) {
				f = new File(chemin + "14.png");
				bi = ImageIO.read(f);
				i = new ImageIcon(bi);
			} else if (p.getOrientation() == Orientation.WEST) {
				f = new File(chemin + "15.png");
				bi = ImageIO.read(f);
				i = new ImageIcon(bi);
			}
		} else if (p.getType() == PieceType.VOID) {
			i = new ImageIcon();
		}
		return i;
	}

	// Toutes les fonctions mouse
	@Override
	public void mouseClicked(MouseEvent e) {
		JLabel label = (JLabel) e.getSource();
		ImageIcon icon = (ImageIcon) label.getIcon();
		System.out.println(icon.toString());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// System.out.println("Pressed");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		// System.out.println("Released");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		// System.out.println("entered");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		// System.out.println("exited");
	}

}