import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class BackgroundChangerFrame extends JFrame {

	JFrame frame;
	private JButton buttonLeft;
	private JButton buttonRight;
	private JLabel change;
	private int backGroundNumber = 5;
	private int maxFiles = 8;
	File file;
	BufferedImage image[];
	TiledImage backgroundImage;

	BackgroundChangerFrame() throws IOException {

		frame = new JFrame("Background Changer");
		//frame.setUndecorated(true);
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 200);
		frame.setLocation(50, 50);
		initializeElements();
		try {
			initializeFiles();
		} catch (IOException e) {e.printStackTrace();}
		fillFrame();
		frame.setVisible(true);
	}
		
	public void initializeElements() {
		change = new JLabel("Change Background");
		change.setSize(100, 40);
		buttonLeft = new JButton("<");
		buttonRight = new JButton(">");
		buttonLeft.setPreferredSize(new Dimension(100, 25));
		buttonLeft.addActionListener(decreaseBackGround);
		buttonRight.setPreferredSize(new Dimension(100, 25));
		buttonRight.addActionListener(increaseBackGround);
	}

	public void fillFrame() {
		frame.add(change);
		frame.add(buttonLeft);
		frame.add(buttonRight);
	}

	public void initializeFiles() throws IOException {
		System.out.println("initialization...");
		image = new BufferedImage[maxFiles];
		long start = System.currentTimeMillis();
		for (int i = 0; i < maxFiles; i++) {
			new Thread(i + "") {
				public void run() {
					file = new File("./images/0" + this.getName() + ".jpg");
					// file = new File("0" + i + ".jpg");
					try {
						image[Integer.valueOf(this.getName())] = ImageIO.read(file);
						// image[i] = ImageIO.read(file);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}.run();
		}
		long stop = System.currentTimeMillis();
		System.out.println("... done in " + (stop - start) + " milliseconds");
		setBackgroundHere(backGroundNumber);
	}

	public void setBackgroundHere(int i) {
		System.out.println("changing background...");
		backgroundImage = new TiledImage(image[i]);
		if (i == 5) {
			change.setForeground(Color.WHITE);
		} else {
			change.setForeground(Color.BLACK);
		}
		frame.setContentPane(backgroundImage);
		fillFrame();
		frame.setVisible(true);
	}

	ActionListener increaseBackGround = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			backGroundNumber = ((backGroundNumber + 1) % maxFiles);
			setBackgroundHere(backGroundNumber);
		}
	};

	ActionListener decreaseBackGround = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (backGroundNumber > 0) {
				backGroundNumber = ((backGroundNumber - 1) % maxFiles);
			} else {
				backGroundNumber = maxFiles - 1;
			}
			setBackgroundHere(backGroundNumber);
		}
	};

	public static void main(String[] args) throws IOException {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					BackgroundChangerFrame bcf = new BackgroundChangerFrame();
					bcf.fillFrame();
					//bcf.setLocation(200, 20);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

	}

	public class TiledImage extends JPanel {
		BufferedImage tileImage;

		public TiledImage(BufferedImage image) {
			tileImage = image;
		}

		protected void paintComponent(Graphics g) {
			int width = getWidth();
			int height = getHeight();
			for (int x = 0; x < width; x += tileImage.getWidth()) {
				for (int y = 0; y < height; y += tileImage.getHeight()) {
					g.drawImage(tileImage, x, y, this);
				}
			}
		}

	}

}
