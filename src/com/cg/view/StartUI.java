package com.cg.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.cg.model.DrawMenu;
import com.cg.model.ImagePanel;
import com.sun.xml.internal.ws.org.objectweb.asm.Label;

import jogamp.opengl.glu.mipmap.ExtractUShort;

public class StartUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton start;
	private JButton exit;
	private JLabel showDescription;
	private String description;
	private JPanel desJPanel;
	// private ImagePanel imgPanel;
	private JLabel label;
	// private ButtonGroup btg;
	private JPanel buttonPanel;
	private BufferedImage image;
	private String path = "/pic/wicked-witch.jpg";
	private boolean started;
	private JFrame frame;

	public StartUI() {
		frame = new JFrame();
		frame.setTitle("Evil Witch");
		frame.setLayout(new BorderLayout());
		initComponents();
		frame.setSize(800, 600);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent ev) {
				System.exit(0);
			}
		});
		frame.pack();
	}

	public void run() {
		// TODO Auto-generated method stub
		frame.setVisible(true);
	}

	public void initComponents() {
		start = new JButton("Start");
		exit = new JButton("Exit");
		buttonPanel = new JPanel();
		desJPanel = new JPanel();
		description = "This is Evil Witch Game Created by Athiwat and Wasupol Team";
		showDescription = new JLabel(description);
		try {
//			System.out.println();
			image = ImageIO.read(this.getClass().getResource(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		label = new JLabel(new ImageIcon(image));
		// btg = new ButtonGroup();
		desJPanel.add(showDescription);
		// btg.add(start);
		// btg.add(exit);
		start.addActionListener( new StartListener());
		exit.addActionListener(new ExitListener());
		buttonPanel.add(start);
		buttonPanel.add(exit);
		frame.add(label, BorderLayout.PAGE_START);
		frame.add(desJPanel, BorderLayout.CENTER);
		frame.add(buttonPanel, BorderLayout.PAGE_END);
	}

	public boolean isStarted() {
		return started;
	}

	class StartListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			started = true;
			frame.dispose();
		}
	}

	class ExitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			started = false;
			System.exit(0);
		}

	}
}
