package com.cg.view;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.cg.model.DrawMenu;
import com.jogamp.opengl.util.FPSAnimator;

public class GameMenu {
	private JFrame frame;
	private GLCanvas canvas;
	private int fPS;
	private String level;
	private int prevMouseX;
	private int prevMouseY;
	private boolean mouseRButtonDown;
	private boolean mouseLButtonDown;

	public GameMenu(String title, int width, int height, int FPS) {
		fPS = FPS;
		frame = new JFrame(title);
		canvas = new DrawMenu();
		// Create the OpenGL rendering canvas
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.addMouseListener( new SelectMenuListener() );
		canvas.addMouseMotionListener( new MotionListener());
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

	class SelectMenuListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

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
			prevMouseX = e.getX();
			prevMouseY = e.getY();
			if ((prevMouseX <= 306 || prevMouseX >= 148)
					&& (prevMouseY <= 255 || prevMouseY >= 99)) {
				mouseLButtonDown = true;
				level = "Earth";
				frame.dispose();
			} else if ((prevMouseX <= 628 || prevMouseX >= 488)
					&& (prevMouseY <= 426 || prevMouseY >= 282)) {
				mouseLButtonDown = true;
				level = "Coming Soon";
				frame.dispose();
			}
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
			int x = e.getX();
			int y = e.getY();
			System.out.println("x : " + x + " y : " + y + "\n");
		}
	}
}