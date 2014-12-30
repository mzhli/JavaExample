package me.mzhli.javaexample.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class SizedFrame extends JFrame {

	public SizedFrame(String title) {
		this(title, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	public SizedFrame(String title, int width, int height) throws HeadlessException {
		super(title);
		this.setSize(width, height);
	}

	public void moveToScreenCenter() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension sizeScreen = kit.getScreenSize();
		this.setLocation((sizeScreen.width - this.getWidth()) / 2, 
						 (sizeScreen.height - this.getHeight()) / 2);
	}
	
	/**
	 * A subclass which defines a text label component
	 */
	private static class CenteredTextLabel extends JComponent {

		private static final long serialVersionUID = 1L;
		
		@Override
		protected void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D)g;

			// Set font
			Font font = new Font("SansSerif", Font.BOLD, 36);
			g2.setFont(font);
			
			// Calculate target text position
			String text = "abcdefghijklmnopqrstuvwxyz";
			FontRenderContext context = g2.getFontRenderContext();
			Rectangle2D textBounds = font.getStringBounds(text, context);
			double width = textBounds.getWidth();
			double height = textBounds.getHeight();
			int x = (this.getWidth() - (int)width) / 2;
			int y = (this.getHeight() - (int)height) / 2;
			int ascent = -(int)textBounds.getY();
			int baseline = y + ascent;
			// Draw rectangle
			g2.setPaint(Color.BLACK);
			g2.drawRect(x, y, (int)width, (int)height);
			// Draw text
			// NOTE: drawString accept baseline as the position, not left-corner
			g2.setPaint(Color.BLUE);
			g2.drawString(text, x, baseline);
			// Draw baseline, ascent and descent line
			g2.setPaint(Color.RED);
			g2.drawLine(x, baseline, x + (int)width, baseline);
			g2.drawLine(x, baseline - ascent, x + (int)width, baseline - ascent);
			// get descent
			LineMetrics metrics = font.getLineMetrics(text, context);
			int descent = (int)metrics.getDescent();
			g2.drawLine(x, baseline + descent, x + (int)width, baseline + descent);
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				SizedFrame frame = new SizedFrame("Hello World");
				frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
				// Create a text label in the center of frame
				frame.add(new CenteredTextLabel());
				// Move window to screen center
				frame.moveToScreenCenter();
				frame.setVisible(true);
			}
		});
	}

	private static final int DEFAULT_WIDTH = 600;
	private static final int DEFAULT_HEIGHT = 300;
}
