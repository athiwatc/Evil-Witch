package com.cg.model;

import com.cg.view.GameMenu;
import com.cg.view.StartUI;

public class Main {
	private static String TITLE = "Select Level"; // window's
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
		GameMenu wouldUi = new GameMenu(TITLE, CANVAS_WIDTH, CANVAS_HEIGHT,FPS);
		wouldUi.run();
	}
}