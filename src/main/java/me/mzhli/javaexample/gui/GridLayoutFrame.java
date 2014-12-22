package me.mzhli.javaexample.gui;

import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

@SuppressWarnings("serial")
public final class GridLayoutFrame extends SizedFrame {
	
	private JButton btnOK;
	private JButton btnCancel;
	private JTextField txtInput;
	private JComboBox<String> cbCategory;
	private JList<String> listResult;

	{
		// Initialize all components
		final JFrame thisFrame = this;
		btnOK = new JButton("Ok");
		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				thisFrame.dispatchEvent(new WindowEvent(thisFrame, WindowEvent.WINDOW_CLOSING));
			}
		});
		
		txtInput = new JTextField();
		
		cbCategory = new JComboBox<String>(new String[]{
				"Category 1", "Category 2", "Category 3"
		});
		
		listResult = new JList<String>(new String[]{
				"Result 1",
				"Result 2",
				"Result 3"
		});
		listResult.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
	}

	public GridLayoutFrame(String title)
			throws HeadlessException {
		super(title, FRAME_WIDTH, FRAME_HEIGHT);
	}
	
	public void applyLayout() {
		GridBagLayout layout = new GridBagLayout();
		JPanel panel = new JPanel();
		panel.setLayout(layout);
		panel.add(btnOK, new GBC(2, 0, 1, 1).setWeight(0, 0).setFill(GBC.BOTH).setInsets(1));
		panel.add(btnCancel, new GBC(2, 1, 1, 1).setWeight(0, 0).setFill(GBC.BOTH).setInsets(1));
		panel.add(txtInput, new GBC(0, 0, 2, 1).setWeight(100, 0).setFill(GBC.BOTH).setInsets(1));
		panel.add(cbCategory, new GBC(0, 1, 2, 1).setWeight(100, 0).setFill(GBC.BOTH).setInsets(1));
		panel.add(listResult, new GBC(0, 2, 3, 3).setWeight(100, 100).setFill(GBC.BOTH).setInsets(1));
		add(panel);
	}

	private static final int FRAME_HEIGHT = 300;
	private static final int FRAME_WIDTH = 400;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				GridLayoutFrame frame = new GridLayoutFrame("Grid Layout Frame");
				frame.applyLayout();
				frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
				frame.moveToScreenCenter();
				frame.setVisible(true);
			}
		});
	}

}
