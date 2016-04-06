/**
 * @author fabio
 * A Progress bar demo while loading a text file.
 * The progress bar is increased based on loaded bytes' number.
 */
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

public class ProgressBarDemo extends JPanel implements ActionListener, PropertyChangeListener {

	private JProgressBar progressBar;
	private JButton startButton;
	private JTextArea taskOutput;
	private CustomSwingWorker customSwingWorker;
	private String fileName = "./Sample.txt";

	class CustomSwingWorker extends SwingWorker<String, String> {

		private File file;
		private Random random = new Random();

		/**
		 * Main customSwingWorker. Executed in background thread. IN SHORT:
		 * basically this is the long task you don't want in the EDT...
		 * 
		 * @throws IOException
		 */
		@Override
		public String doInBackground() throws IOException {
			int progressPercentage = 0;
			String result = new String();
			byte[] chunks = null;
			int bytesBeenRead = 0;
			final int millisecondsOfDelay = 5; 		// milliseconds of delay before reading again
			// Initialize progress property.
			setProgress(0);

			while (progressPercentage < 100) {
				file = new File(fileName);
				double size = (double) file.length();
				System.out.println(size);
				BufferedReader br = new BufferedReader(new FileReader(file));
				StringBuilder sb = new StringBuilder();
				String line;
				try {
					line = br.readLine();
					while (line != null) {
						if (millisecondsOfDelay > 0) {
							Thread.sleep(random.nextInt(millisecondsOfDelay));
						}
						chunks = line.getBytes("UTF-8");
						bytesBeenRead += chunks.length;
						progressPercentage = (int) ((bytesBeenRead * 100) / size);
						// System.out.println(" "+chunks.length+"
						// "+bytesBeenRead+" "+progressPercentage+"\n");
						publish("bytes read: " + chunks.length + " - total bytes read:" + bytesBeenRead + " - progress: " + progressPercentage + "%");
						// publish("["+bytesBeenRead + "/" + size+"]
						// "+line+"\n");
						setProgress(progressPercentage);
						sb.append(line + System.lineSeparator());
						line = br.readLine();
					}
					result = sb.toString();
					System.out.println(sb.toString());
				} catch (InterruptedException e) {
				} catch (FileNotFoundException e) {e.printStackTrace();
				} catch (IOException e) {e.printStackTrace();
				} finally {
					br.close();
				}
			}
			return result;
		}

		/**
		 * IN SHORT: you don't call this method, it is called by publish() when
		 * you want to show intermediate results.
		 */
		@Override
		protected void process(List<String> s) {
			System.out.println(": " + s);
			// taskOutput.append(s.toString());
		}

		/**
		 * Executed in event dispatching thread. IN SHORT: basically here,
		 * you're done with the long task.
		 */
		@Override
		public void done() {
			Toolkit.getDefaultToolkit().beep();
			startButton.setEnabled(true);
			setCursor(null); // turn off the wait cursor
			taskOutput.append("Done!\n");
		}
	}

	public ProgressBarDemo() {
		super(new BorderLayout());

		// Create the demo's UI.
		startButton = new JButton("Start");
		startButton.addActionListener(this);
		startButton.setPreferredSize(new Dimension(180, 20));

		progressBar = new JProgressBar(0, 100);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		progressBar.setPreferredSize(new Dimension(400, 20));

		taskOutput = new JTextArea(15, 30);
		taskOutput.setMargin(new Insets(5, 5, 5, 5));
		taskOutput.setEditable(false);

		JPanel panel = new JPanel();
		panel.add(startButton);
		panel.add(progressBar);
		add(panel, BorderLayout.NORTH);
		add(new JScrollPane(taskOutput), BorderLayout.CENTER);
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		startLoading();
	}

	private void startLoading() {
		startButton.setEnabled(false);
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		// Instances of javax.swing.SwingWorker are not reusable, so
		// we create new instances as needed.
		customSwingWorker = new CustomSwingWorker();
		customSwingWorker.addPropertyChangeListener(this);
		customSwingWorker.execute();
	}

	/**
	 * Invoked when the user presses the start button.
	 */
	public void actionPerformed(ActionEvent evt) {
		startLoading();
	}

	/**
	 * Invoked when customSwingWorker's progress property changes.
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		if ("progress" == evt.getPropertyName()) {
			int progress = (Integer) evt.getNewValue();
			progressBar.setValue(progress);
			taskOutput.append(String.format("Completed %d%% of customSwingWorker.\n", customSwingWorker.getProgress()));
		}
	}

	/**
	 * Create the GUI and show it. As with all GUI code, this must run on the
	 * event-dispatching thread.
	 */
	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("Progress Bar Demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the content pane.
		JComponent newContentPane = new ProgressBarDemo();
		newContentPane.setOpaque(true); // content panes must be opaque
		frame.setContentPane(newContentPane);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
