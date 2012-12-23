package com.cg.view;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

import com.cg.model.DrawWorld;
import com.jogamp.opengl.util.FPSAnimator;

public class GameWorld {
	private JFrame frame;
	private GLCanvas canvas;
	private int fPS;

	public GameWorld(String title, int width, int height, int FPS) {
		fPS = FPS;
		frame = new JFrame(title);
		canvas = new DrawWorld();
		// Create the OpenGL rendering canvas
		canvas.setPreferredSize(new Dimension(width, height));
		frame.getContentPane().add(canvas);
		frame.pack();
	}

	public void run() {

		// Create a animator that drives canvas' display() at the specified FPS.
		final FPSAnimator animator = new FPSAnimator(canvas, fPS, true);

		// Create the top-level container
		frame.getContentPane().add(canvas);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// Use a dedicate thread to run the stop() to ensure that the
				// animator stops before program exits.
				new Thread() {
					@Override
					public void run() {
						if (animator.isStarted())
							animator.stop();
						System.exit(0);
					}
				}.start();
			}
		});
		frame.setVisible(true);
		animator.start();

	}
}
