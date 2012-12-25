package com.cg.view;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.cg.model.DrawMenu;
import com.jogamp.opengl.util.FPSAnimator;

public class GameMenu {
	private JFrame frame;
	private GLCanvas canvas;
	private int fPS;
	private String level = "None";
	private int prevMouseX;
	private int prevMouseY;
	private boolean isClicked = false;
	private static String EARTH_TITLE = "Save The Earth";
	private static final int CANVAS_WIDTH = 800; // width of the drawable
	private static final int CANVAS_HEIGHT = 600; // height of the drawable
	private static final int FPS = 60;

	public GameMenu(String title, int width, int height, int FPS) {
		fPS = FPS;
		frame = new JFrame(title);
		canvas = new DrawMenu();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Create the OpenGL rendering canvas
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.addMouseListener(new SelectMenuListener());
		canvas.addMouseMotionListener(new MotionListener());
		frame.getContentPane().add(canvas);
		frame.pack();
	}

	public void run() {

		// Create a animator that drives canvas' display() at the specified FPS.
		final FPSAnimator animator = new FPSAnimator(canvas, fPS, true);

		// Create the top-level container
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

	public String getLevel() {
		return level;
	}

	public boolean isClicked() {
		return isClicked;
	}

	public boolean isBetween(int a, int b, int c) {
		return b > a ? c > a && c < b : c > b && c < a;
	}

	class SelectMenuListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if (isBetween(148, 306, e.getX()) && isBetween(99, 255, e.getY())) {
				isClicked = true;
				level = "Earth";
				frame.dispose();
				EarthLevel earthLevel = new EarthLevel(EARTH_TITLE,
						CANVAS_WIDTH, CANVAS_HEIGHT, FPS);
				earthLevel.run();
			} else if (isBetween(488, 628, e.getX())
					&& isBetween(282, 426, e.getY())) {
				isClicked = true;
				level = "Coming Soon";
				JOptionPane.showMessageDialog(frame, "It is coming soon");
			} else {
				isClicked = false;
				level = "None";
				JOptionPane.showMessageDialog(frame, "Select on Planet");
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	}

	class MotionListener implements MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			// System.out.println(e.getX() + " " + e.getY() + "\n");
		}
	}
}