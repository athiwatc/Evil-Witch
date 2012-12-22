package com.cg.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import jogamp.opengl.glu.mipmap.ExtractUShort;

public class StartUI extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton start;
	private JButton exit;
	private JPanel banner;
	private JLabel showDescription;
	private String description;
	private ImageIcon imgicon;
	private Image img;
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
		start = new JButton("Start");
		exit = new JButton("Exit");
		banner = new JPanel();
		imgicon = new ImageIcon(img);
		banner.setPreferredSize(new Dimension(imgicon.getIconWidth(), imgicon.getIconHeight()));
	}
}
