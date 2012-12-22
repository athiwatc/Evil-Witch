package com.cg.model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

	private BufferedImage image;

	public ImagePanel(String path) {
		try {
			image = ImageIO.read(new File(path));
		} catch (IOException ex) {
			// handle exception...
			JOptionPane.showMessageDialog(null, "File Doesn't Existing",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null); // see javadoc for more info on the
										// parameters
	}
}