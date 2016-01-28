package com.fabio.system.properties;

/*
 * Shows system properties. 
 * */

import java.awt.*;
import javax.swing.*;
import java.util.*;

/** ain program. */
public class SysPropList {
	public static void main(String[] args) {
		JFrame window = new JFrame("System Properties");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setContentPane(new SysPropListGUI());
		window.pack();
		window.setVisible(true);
	}
}

/** Panel to display the GUI intereface. */
class SysPropListGUI extends JPanel {
	JTextArea propertiesTextArea = new JTextArea(20, 50);

	/** Constructor sets layout, adds components and sets values */
	public SysPropListGUI() {
		this.setLayout(new BorderLayout());
		this.add(new JScrollPane(propertiesTextArea), BorderLayout.CENTER);

		// Add property list data to text area.
		Properties pr = System.getProperties();
		// A TreeSet sorts its keys
		TreeSet propKeys = new TreeSet(pr.keySet());
		for (Iterator it = propKeys.iterator(); it.hasNext();) {
			String key = (String) it.next();
			propertiesTextArea.append(key + "=" + pr.get(key) + "\n");
			// System.out.print(key + "=" + pr.get(key) + "\n");
		}
	}
}
