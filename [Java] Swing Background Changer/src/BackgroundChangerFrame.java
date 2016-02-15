import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	int loaded = 0;
	BufferedImage image[];
	TiledImage backgroundImage;
	Loader[] loaders = new Loader[maxFiles];
	String path = "./images/";
	String prefix = "0";
	String extension = ".jpg";	

	BackgroundChangerFrame() throws IOException {
		frame = new JFrame("Background Changer");
		// frame.setUndecorated(true);
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 200);
		frame.setLocation(50, 50);
		initializeElements();
		System.out.println("loaded value: "+loaded);
		try {
			initializeFiles();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	
	public synchronized int getLoaded(){
		return this.loaded;
	}
	
	public synchronized void setLoaded(){
		this.loaded = this.loaded + 1;
		System.out.println(" - currently loaded: "+getLoaded() );
		notify();
	}

	class Loader extends Thread {
		int index;

		Loader(int index) {
			this.index = index;
		}

		public void run() {
			File file = new File(path+prefix+index+extension);
			try {				
					System.out.println("reading "+index);
					image[index] = ImageIO.read(file);
					System.out.print("notifying "+index);
					setLoaded();
									
			} catch (IOException e) {e.printStackTrace();}
		}
	}
	
	class TiledImage extends JPanel {
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

	public synchronized void initializeFiles() throws IOException {
		System.out.println("initialization...");
		image = new BufferedImage[maxFiles];
		long start = System.currentTimeMillis();
		for (int i = 0; i < maxFiles; i++) {
			synchronized(this){
				loaders[i] = new Loader(i);
				loaders[i].start();
			}
			
		}
		while(getLoaded() < maxFiles) {
	        try {
	        	System.out.println("wait..."+getLoaded());
	            this.wait();
	        } catch (InterruptedException e) {}
	    }		
		long stop = System.currentTimeMillis();
		System.out.println("... done in " + (stop - start) + " milliseconds");
		setBackgroundHere(backGroundNumber);
	}

	public void setBackgroundHere(int i) {
		System.out.println("changing background... "+i);
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
			} else {backGroundNumber = maxFiles - 1;}
			setBackgroundHere(backGroundNumber);
		}
	};

	public static void main(String[] args) throws IOException {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					BackgroundChangerFrame bcf = new BackgroundChangerFrame();
					bcf.fillFrame();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
}

