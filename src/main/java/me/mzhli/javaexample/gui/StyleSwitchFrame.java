package me.mzhli.javaexample.gui;

import java.awt.EventQueue;
import java.awt.HeadlessException;

@SuppressWarnings("serial")
public class StyleSwitchFrame extends SizedFrame {

	public StyleSwitchFrame(String title) throws HeadlessException {
		super(title, FRAME_INIT_WIDTH, FRAME_INIT_HEIGHT);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				StyleSwitchFrame frame = new StyleSwitchFrame("Style Switch Frame");
				frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
				frame.moveToScreenCenter();
				frame.setVisible(true);
			}
		});
	}
	
	private static final int FRAME_INIT_WIDTH = 400;
	private static final int FRAME_INIT_HEIGHT = 300;
}
