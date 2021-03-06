package me.mzhli.javaexample.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import me.mzhli.javaexample.util.IconStore;
import me.mzhli.javaexample.util.IconStore.E_IconSize;

@SuppressWarnings("serial")
public final class GridLayoutFrame extends SizedFrame {
	
	/**
	 * Application logo
	 */
	private static final Icon ICON_LOGO = IconStore.getInstance().getIcon("logo", E_IconSize.ICO_SIZE_48x48);
	
	private static final int FRAME_HEIGHT = 400;
	private static final int FRAME_WIDTH = 400;
	
	/**
	 * Components 
	 */
	private JButton btnSearch;
	private JButton btnSetting;
	private JTextField txtInput;
	private JComboBox<String> cbCategory;
	private JScrollPane scrollPane;
	private JList<String> listResult;
	private JToolBar toolBar;
	
	/**
	 * Actions shared by Menu, ToolBar and PopupMenu
	 */
	private Action copyAction;
	private Action pasteAction;
	private Action delAction;
	private Action toggleMenuAction;
	
	/**
	 * Internal states or values for components
	 */
	private String selectedResults = "";
	private boolean isMenuVisible = true;

	{
		// Initialize all components
		initComponents();
		
		// initialize actions
		initActions();
		
		// create menu bar
		createMenu();
		
		// create tool bar
		createToolbar();
	}

	private void initComponents() {
		Icon icoSearch = IconStore.getInstance().getIcon("search", E_IconSize.ICO_SIZE_24x24);
		Icon icoSetting = IconStore.getInstance().getIcon("setting", E_IconSize.ICO_SIZE_24x24);
		
		btnSearch = new JButton("Search", icoSearch);
;		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnSetting = new JButton("Setting", icoSetting);
		btnSetting.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(GridLayoutFrame.this, "Setting updated", "Setting", 
						JOptionPane.WARNING_MESSAGE, ICON_LOGO);
			}
		});
		
		txtInput = new JTextField();
		
		cbCategory = new JComboBox<String>(new String[]{
				"Category 1", "Category 2", "Category 3"
		});
		
		listResult = new JList<String>(new String[]{
				"Result 1",
				"Result 2",
				"Result 3",
				"Result 4",
				"Result 5",
				"Result 6",
				"Result 7",
				"Result 8",
				"Result 9"
		});
		listResult.setBorder(BorderFactory.createLoweredBevelBorder());
		scrollPane = new JScrollPane(listResult);
		listResult.addListSelectionListener(new ListSelectionListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void valueChanged(ListSelectionEvent event) {
				if (! event.getValueIsAdjusting()) {
					JList<String> list = (JList<String>)event.getSource();
					List<String> selections = list.getSelectedValuesList();
					StringBuilder sb = new StringBuilder();
					for (String selected : selections) {
						sb.append(selected);
						sb.append(" ");
					}
					updateInput(sb.toString());
				}
			}
		});
	}

	public GridLayoutFrame(String title)
			throws HeadlessException {
		super(title, FRAME_WIDTH, FRAME_HEIGHT);
		setIconImage(((ImageIcon)ICON_LOGO).getImage());
	}
	
	/**
	 * Initialize all actions
	 */
	private void initActions() {
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
		toggleMenuAction = new AbstractAction("Show Menu") {
			@Override
			public void actionPerformed(ActionEvent e) {
				JToggleButton btn = (JToggleButton)e.getSource();
				updateMenu(btn.isSelected());
			}
		};
		
		copyAction.putValue(Action.SMALL_ICON, IconStore.getInstance().getIcon("copy", E_IconSize.ICO_SIZE_16x16));
		copyAction.putValue(Action.LARGE_ICON_KEY, IconStore.getInstance().getIcon("copy", E_IconSize.ICO_SIZE_24x24));
		pasteAction.putValue(Action.SMALL_ICON, IconStore.getInstance().getIcon("paste", E_IconSize.ICO_SIZE_16x16));
		pasteAction.putValue(Action.LARGE_ICON_KEY, IconStore.getInstance().getIcon("paste", E_IconSize.ICO_SIZE_24x24));
		delAction.putValue(Action.SMALL_ICON, IconStore.getInstance().getIcon("delete", E_IconSize.ICO_SIZE_16x16));
		delAction.putValue(Action.LARGE_ICON_KEY, IconStore.getInstance().getIcon("delete", E_IconSize.ICO_SIZE_24x24));
		toggleMenuAction.putValue(Action.LARGE_ICON_KEY, IconStore.getInstance().getIcon("togglemenu", E_IconSize.ICO_SIZE_24x24));
	}
	
	protected void createMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu editMenu = new JMenu("Edit");
		editMenu.add(new JMenuItem(copyAction));
		editMenu.add(new JMenuItem(pasteAction));
		editMenu.add(new JMenuItem(delAction));
		menuBar.add(editMenu);
		setJMenuBar(menuBar);
		
		// popup menu
		JPopupMenu popupMenu = new JPopupMenu();
		popupMenu.add(new JMenuItem(copyAction));
		popupMenu.add(new JMenuItem(pasteAction));
		popupMenu.add(new JMenuItem(delAction));
		listResult.setComponentPopupMenu(popupMenu);
	}
	
	protected void createToolbar() {
		toolBar = new JToolBar("Edit");
		toolBar.add(copyAction);
		toolBar.add(pasteAction);
		toolBar.add(delAction);
		toolBar.addSeparator();
		JToggleButton btnToggle = new JToggleButton(toggleMenuAction); 
		btnToggle.setSelected(true);
		toolBar.add(btnToggle);
	}
	
	public void applyLayout() {
		add(toolBar, BorderLayout.NORTH);
		GridBagLayout layout = new GridBagLayout();
		JPanel panel = new JPanel();
		panel.setLayout(layout);
		panel.add(btnSearch, new GBC(1, 1, 1, 1).setWeight(0, 0).setFill(GBC.BOTH).setInsets(1));
		panel.add(btnSetting, new GBC(2, 1, 1, 1).setWeight(0, 0).setFill(GBC.BOTH).setInsets(1));
		panel.add(txtInput, new GBC(0, 0, 3, 1).setWeight(100, 0).setFill(GBC.BOTH).setInsets(1));
		panel.add(cbCategory, new GBC(0, 1, 1, 1).setWeight(100, 0).setFill(GBC.BOTH).setInsets(1));
		panel.add(scrollPane, new GBC(0, 4, 4, 4).setWeight(100, 100).setFill(GBC.BOTH).setInsets(2));
		add(panel, BorderLayout.CENTER);
	}
	
	/**
	 * Enforce all components reload the latest values
	 */
	public void refreshAll() {
		refreshInput();
		refreshMenu();
	}
	
	/**
	 * Update input text box display
	 * @param newInput the new input text
	 */
	protected void updateInput(String newInput) {
		selectedResults = newInput;
		refreshInput();
	}
	
	private void refreshInput() {
		txtInput.setText(selectedResults);
	}
	
	/**
	 * Update menu display
	 * @param isVisible the visibility of menu
	 */
	protected void updateMenu(boolean isVisible) {
		isMenuVisible = isVisible;
		refreshMenu();
	}
	
	private void refreshMenu() {
		getJMenuBar().setVisible(isMenuVisible);
	}

	
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
