package me.mzhli.javaexample.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class StyleSwitchFrame extends SizedFrame {

	public StyleSwitchFrame(String title) throws HeadlessException {
		super(title, FRAME_INIT_WIDTH, FRAME_INIT_HEIGHT);
		addButtons();
	}
	
	private void addButtons() {
		panel = new JPanel();
		try {
			panel.add(new JButton(new ColorAction("Blue", Color.BLUE, 
					new ImageIcon(ImageIO.read(this.getClass().getResource("/images/icons/blue.png"))))));
			panel.add(new JButton(new ColorAction("Red", Color.RED, 
					new ImageIcon(ImageIO.read(this.getClass().getResource("/images/icons/red.png"))))));
			panel.add(new JButton(new ColorAction("Green", Color.GREEN, 
					new ImageIcon(ImageIO.read(this.getClass().getResource("/images/icons/green.png"))))));
		} catch (IOException err) {
			err.printStackTrace();
		}
		add(panel);
	}
	
	/**
	 * Action for changing panel color
	 */
	private class ColorAction extends AbstractAction {
		
		public ColorAction(String name, Color color, Icon icon) {
			super();
			putValue(NAME, name);
			putValue(SMALL_ICON, icon);
			// Set to default window color if color is not specified
			if (color != null) {
				putValue("color", color);
				putValue(SHORT_DESCRIPTION, "Set panel color to " + name.toLowerCase());
			} else {
				putValue("color", SystemColor.WINDOW);
				putValue(SHORT_DESCRIPTION, "Set panel color to default window color");
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Color color = (Color)getValue("color");
			panel.setBackground(color);
		}
		
	}
	
	private JPanel panel;
	
	private static final int FRAME_INIT_WIDTH = 400;
	private static final int FRAME_INIT_HEIGHT = 300;

	
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
	
}
