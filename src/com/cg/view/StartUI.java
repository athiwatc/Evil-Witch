package com.cg.view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartUI extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton start;
	private JButton Exit;
	private JPanel banner;
	private JLabel description;
	
	public StartUI () {
		this.setTitle("Evil Witch");
		initComponents();
		this.setSize(this.getContentPane().getPreferredSize());
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent ev) {
				System.exit(0);
			}
		});
		this.pack();
	}
	public void run() {
		// TODO Auto-generated method stub
		this.setVisible(true);
	}
	public void initComponents() {
		
	}
}
