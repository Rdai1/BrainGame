package Game;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class GameGui extends java.awt.Frame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5675893142391763512L;
	Checkbox b1, b2, b3, b4;
	Label l1, l2, l3;
	int intScore = 0;
	int intLife = 1;
	Label Score;
	Label Life;
	CheckboxGroup cbg1 = new CheckboxGroup();
	ImageIcon puzzle;
	Button btnCheck;
	Panel controlPanel;

	public GameGui() {
		setTitle("Puzzle Game");
		Dialog GOS = new Dialog(this, "Game Over", true);
		addWindowListener(new WindowListener() { // making the close button actually close the frame

			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub

			}
		});
		setSize(900, 600);
		setLayout(new FlowLayout());
		setLocationRelativeTo(null); // frame always opens at center of screen

		Life = new Label("Life: " + intLife);
		Score = new Label("Score: " + intScore);

		btnCheck = new Button("Check Answer");
		btnCheck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (b2.getState()) {
					l3.setText("You are correct!");
					intScore += 1;
					intLife += 1;
					Score.setText("Score: " + intScore);
					Life.setText("Life: " + intLife);
				} else {
					l3.setText("Wrong");
					intLife -= 1;
					Score.setText("Score: " + intScore);
					Life.setText("Life: " + intLife);
				}
				if (intLife <= 0) {
					GOS.setSize(300, 300);
					GOS.setLocationRelativeTo(null);
					GOS.addWindowListener(new WindowListener() {
						public void windowClosing(WindowEvent e) {
							GOS.dispose();
						}

						@Override
						public void windowOpened(WindowEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowClosed(WindowEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowIconified(WindowEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowDeiconified(WindowEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowActivated(WindowEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowDeactivated(WindowEvent e) {
							// TODO Auto-generated method stub

						}
					});
					GOS.add(new Label("You have lost all your lives, please start over"));
					GOS.setVisible(true);
					b1.setEnabled(false);
					b2.setEnabled(false);
					b3.setEnabled(false);
					b4.setEnabled(false);
					btnCheck.setEnabled(false);
				}
			}
		});

		l1 = new Label("Selection");
		l2 = new Label("                                                 ");
		l3 = new Label("                                                 ");

		b1 = new Checkbox("A", cbg1, false);
		b2 = new Checkbox("B", cbg1, false);
		b3 = new Checkbox("C", cbg1, false);
		b4 = new Checkbox("D", cbg1, false);

		// add label
		add(l1);
		add(l2);
		add(l3);
		add(Score);
		add(Life);

		// add radio buttons
		add(b1);
		add(b2);
		add(b3);
		add(b4);
		// add buttons
		add(btnCheck);

		b1.addItemListener(new radButtonActive());
		b3.addItemListener(new radButtonActive());
		b4.addItemListener(new radButtonActive());
		b2.addItemListener(new radButtonActive());

		setVisible(true);
		setResizable(false);
	}

	public void showImage() {
		add(new ImageComponent("pasted_image_0.png"));
		setVisible(true);
	}

	class ImageComponent extends Component {
		/**
		 * 
		 */
		private static final long serialVersionUID = 2328750917033391215L;
		/**
		 * 
		 */
		/**
		 * 
		 */
		BufferedImage img;

		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, null);
		}

		public ImageComponent(String path) {
			try {
				img = ImageIO.read(new File(path));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public Dimension getPreferredSize() {
			if (img == null) {
				return new Dimension(100, 100);
			} else {
				return new Dimension(img.getWidth(), img.getHeight());
			}
		}
	}

	class radButtonActive implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			Object obj = e.getItemSelectable();
			Checkbox cb = (Checkbox) obj;
			if (cb.getState()) {
				l2.setText("You HAVE SELECTED : " + cb.getLabel());
			} else {
				l2.setText("You HAVE SELECTED NONE");
			}

		}

	}
	
	public static void main(String[] args) {
		GameGui Game = new GameGui(); 
		Game.showImage();
		
	}
	

}
