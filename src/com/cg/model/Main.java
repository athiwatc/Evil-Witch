package com.cg.model;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.cg.view.StartUI;
import com.jogamp.opengl.util.FPSAnimator;

public class Main {
	private static String TITLE = "Rotating 3D Shaps (GLCanvas)"; // window's
																	// title
	private static final int CANVAS_WIDTH = 800; // width of the drawable
	private static final int CANVAS_HEIGHT = 600; // height of the drawable
	private static final int FPS = 60; // animator's target frames per second

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StartUI ui = new StartUI();
		ui.run();
		while (!ui.isStarted()) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		GLCanvas canvas = new DrawWorld();
		JFrame frame = new JFrame();         // or AWT's Frame
		frame.getContentPane().add(canvas);  // add Component
		frame.pack();
		frame.setVisible(true);
	}
}