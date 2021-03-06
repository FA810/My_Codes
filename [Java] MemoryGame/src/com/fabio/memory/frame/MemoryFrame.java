package com.fabio.memory.frame;

import javax.swing.*;

import com.fabio.memory.Board;
import com.fabio.memory.Disposition;
import com.fabio.memory.Settings;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

public class MemoryFrame extends JFrame {

	/**
	 * Creates new form New JFrame
	 */

	List<Integer> move = new ArrayList<Integer>();
	Disposition disposition;
	Board board;

	public MemoryFrame() {
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Memory Game");
		setResizable(true);
		setPreferredSize(new Dimension(Settings.preferredButtonSizeInGUI*8+10,Settings.preferredWindowSizeInGUI));
		setMinimumSize(new Dimension(Settings.preferredButtonSizeInGUI*8+10,Settings.preferredWindowSizeInGUI));

		disposition = new Disposition();
		if(Settings.shuffleDispositionForTheGame){
			disposition.shuffleDisposition();
		}
		board = new Board(disposition);
		GridBagLayout layout = new GridBagLayout();
		for (int i = 0; i < board.getBoardTotalSquares(); i++) {
			jButtons[i] = new JButton();
			jButtons[i].setText("-");
			jButtons[i].setName(i + "");
			jButtons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					jButton1ActionPerformed(evt);
				}
			});
			jButtons[i].setPreferredSize(new Dimension(Settings.preferredButtonSizeInGUI,Settings.preferredButtonSizeInGUI));
		}
		
		
		GridBagConstraints c = new GridBagConstraints();
		getContentPane().setLayout(layout);

		c.weighty = 0D;
		for(int i = 0; i<board.getBoardTotalSquares();i++ ){
			c = new GridBagConstraints();
			c.gridx = i%board.getLineSize();
			c.gridy = i/board.getLineSize();		
			getContentPane().add(jButtons[i], c);
		}
		
		
		jProgressBar1 = new JProgressBar();
		jProgressBar1.setMinimum(0);
		jProgressBar1.setMaximum(board.getBoardTotalSquares());
		jProgressBar1.setPreferredSize(new Dimension(Settings.preferredButtonSizeInGUI*8,Settings.preferredProgressBarSizeInGUI));
		c = new GridBagConstraints();
		c.weighty = 0D;
		c.gridwidth = 8;
		c.gridy = 5;		
		getContentPane().add(jProgressBar1, c);
		
		
		jScrollPane1 = new JScrollPane();
		jTextArea1 = new JTextArea();

		jTextArea1.setColumns(25);
		jTextArea1.setRows(30);
		jScrollPane1.setViewportView(jTextArea1);	
		
		c = new GridBagConstraints();
		c.weighty = 0D;
		c.gridwidth = 8;
		c.gridy = 6;		
		jScrollPane1.setPreferredSize(new Dimension(Settings.preferredButtonSizeInGUI*8,Settings.preferredTextAreaSizeInGUI));
		getContentPane().add(jScrollPane1, c);

		pack();
	}// </editor-fold>

	private void jButton1ActionPerformed(ActionEvent evt) {
		JButton button = (JButton) evt.getSource();
		int square = Integer.valueOf(button.getName());
		char chosen = board.getValueAt(square);
		//System.out.println("selected " + square + " - " + chosen);
		if (move.size() == 2) {
			resetButtons(new int[]{move.get(0),move.get(1)});
			move.clear();
		}
		if (move.size() == 0) {
			move.add(square);
			jButtons[square].setText("" + chosen);
		} else if ((move.size() == 1)&&(square!=move.get(0))) {
			move.add(square);
			Character previous = board.getValueAt(move.get(0));
			if (board.setMove(square,move.get(0))) {
				board.printMatchMoves();
				//System.out.println("found: " + board.getFoundSquares());
				jProgressBar1.setValue(board.getFoundSquares());
				if (board.isBoardComplete()) {
					jTextArea1.setText(jTextArea1.getText() + "The board is now complete.");
					resetButtons(new int[]{move.get(0),move.get(1)});
					board.printMatchMoves();
				} else {
					jTextArea1.setText(jTextArea1.getText() + previous + " and " + chosen + " -> match found."+ "\n");
				}
			} else {
				jTextArea1.setText(jTextArea1.getText() + previous + " and " + chosen + ", try again."+ "\n");
			}
			jButtons[square].setText("" + chosen);
		}

	}

	public void resetButtons(int[] cards) {
		for (int i = 0; i < cards.length; i++) {
			if (!board.getFound(cards[i])) {
				jButtons[cards[i]].setText("-");
			} else {
				jButtons[cards[i]].setEnabled(false);
			}
		}
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed"
		// desc=" Look and feel setting code (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the
		 * default look and feel. For details see
		 * http://download.oracle.com/javase
		 * /tutorial/uiswing/lookandfeel/plaf.html
		*/ 
		try {
			for (UIManager.LookAndFeelInfo info : UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName()) && Settings.useNimbusLookAndFeel) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(MemoryFrame.class.getName()).log(Level.SEVERE,
					null, ex);
		} catch (InstantiationException ex) {
			Logger.getLogger(MemoryFrame.class.getName()).log(Level.SEVERE,
					null, ex);
		} catch (IllegalAccessException ex) {
			Logger.getLogger(MemoryFrame.class.getName()).log(Level.SEVERE,
					null, ex);
		} catch (UnsupportedLookAndFeelException ex) {
			Logger.getLogger(MemoryFrame.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MemoryFrame().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify
	private JButton[] jButtons = new JButton[32];

	private JProgressBar jProgressBar1;
	private JScrollPane jScrollPane1;
	private JTextArea jTextArea1;
	// End of variables declaration
}
