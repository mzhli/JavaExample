package me.mzhli.javaexample.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

@SuppressWarnings("serial")
public final class GridLayoutFrame extends SizedFrame {
	
	private JButton btnOK;
	private JButton btnCancel;
	private JTextField txtInput;
	private JComboBox<String> cbCategory;
	private JList<String> listResult;
	private JToolBar toolBar;
	
	private Action copyAction;
	private Action pasteAction;
	private Action delAction;

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
		listResult.setBorder(BorderFactory.createLoweredBevelBorder());
		
		// copy,paste,del action
		copyAction = new AbstractAction("Copy") {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		};
		pasteAction = new AbstractAction("Paste") {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		};
		delAction = new AbstractAction("Delete") {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		};
		
		Icon icoCopy16 = null;
		Icon icoCopy32 = null;
		Icon icoPaste16 = null;
		Icon icoPaste32 = null;
		Icon icoDelete16 = null;
		Icon icoDelete32 = null;
		try {
			icoCopy16 = new ImageIcon(ImageIO.read(this.getClass().getResource("/images/icons/copy@16x16.png")));
			icoCopy32 = new ImageIcon(ImageIO.read(this.getClass().getResource("/images/icons/copy@32x32.png")));
			icoPaste16 = new ImageIcon(ImageIO.read(this.getClass().getResource("/images/icons/paste@16x16.png")));
			icoPaste32 = new ImageIcon(ImageIO.read(this.getClass().getResource("/images/icons/paste@32x32.png")));
			icoDelete16 = new ImageIcon(ImageIO.read(this.getClass().getResource("/images/icons/delete@16x16.png")));
			icoDelete32 = new ImageIcon(ImageIO.read(this.getClass().getResource("/images/icons/delete@32x32.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		copyAction.putValue(Action.SMALL_ICON, icoCopy16);
//		copyAction.putValue(Action.LARGE_ICON_KEY, icoCopy32);
		pasteAction.putValue(Action.SMALL_ICON, icoPaste16);
//		pasteAction.putValue(Action.LARGE_ICON_KEY, icoPaste32);
		delAction.putValue(Action.SMALL_ICON, icoDelete16);
//		delAction.putValue(Action.LARGE_ICON_KEY, icoDelete32);
		
		// menu bar
		JMenuBar menuBar = new JMenuBar();
		JMenu editMenu = new JMenu("Edit");
		editMenu.add(new JMenuItem(copyAction));
		editMenu.add(new JMenuItem(pasteAction));
		editMenu.add(new JMenuItem(delAction));
		menuBar.add(editMenu);
		setJMenuBar(menuBar);
		
		// tool bar
		toolBar = new JToolBar("Edit");
		toolBar.add(copyAction);
		toolBar.add(pasteAction);
		toolBar.add(delAction);
		
		// popup menu
		JPopupMenu popupMenu = new JPopupMenu();
		popupMenu.add(new JMenuItem(copyAction));
		popupMenu.add(new JMenuItem(pasteAction));
		popupMenu.add(new JMenuItem(delAction));
		listResult.setComponentPopupMenu(popupMenu);
	}

	public GridLayoutFrame(String title)
			throws HeadlessException {
		super(title, FRAME_WIDTH, FRAME_HEIGHT);
		
	}
	
	public void applyLayout() {
		add(toolBar, BorderLayout.NORTH);
		GridBagLayout layout = new GridBagLayout();
		JPanel panel = new JPanel();
		panel.setLayout(layout);
		panel.add(btnOK, new GBC(2, 0, 1, 1).setWeight(0, 0).setFill(GBC.BOTH).setInsets(1));
		panel.add(btnCancel, new GBC(2, 1, 1, 1).setWeight(0, 0).setFill(GBC.BOTH).setInsets(1));
		panel.add(txtInput, new GBC(0, 0, 2, 1).setWeight(100, 0).setFill(GBC.BOTH).setInsets(1));
		panel.add(cbCategory, new GBC(0, 1, 2, 1).setWeight(100, 0).setFill(GBC.BOTH).setInsets(1));
		panel.add(listResult, new GBC(0, 2, 3, 3).setWeight(100, 100).setFill(GBC.BOTH).setInsets(2));
		add(panel, BorderLayout.CENTER);
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
