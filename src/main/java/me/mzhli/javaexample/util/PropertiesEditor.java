package me.mzhli.javaexample.util;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.filechooser.FileNameExtensionFilter;

import me.mzhli.javaexample.gui.PropertiesTable;
import me.mzhli.javaexample.gui.SizedFrame;
import me.mzhli.javaexample.util.IconStore.E_IconSize;

@SuppressWarnings("serial")
public class PropertiesEditor extends SizedFrame {

	public PropertiesEditor() {
		super("Properties Editor", EDITOR_INIT_WIDTH, EDITOR_INIT_HEIGHT);

		// Initialize components in frame
		initComponents();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				PropertiesEditor editor = new PropertiesEditor();
				editor.setDefaultCloseOperation(EXIT_ON_CLOSE);
				editor.moveToScreenCenter();
				editor.setVisible(true);
			}
		});
	}

	private static final int EDITOR_INIT_WIDTH = 400;
	private static final int EDITOR_INIT_HEIGHT = 500;

	private void initComponents() {
		// Toolbar
		toolbar = new JToolBar();
		JButton btnOpenFile = new JButton(new OpenFileAction());
		toolbar.add(btnOpenFile);
		toolbar.setFloatable(false);
		add(toolbar, BorderLayout.NORTH);
		
		// Table for properties
		kvTable = new PropertiesTable();
//		kvTable.set
		add(new JScrollPane(kvTable), BorderLayout.CENTER);
	}
	
	/**
	 *	Action for "Open" button
	 */
	private class OpenFileAction extends AbstractAction {
		public OpenFileAction() {
			putValue(SMALL_ICON, IconStore.getInstance().getIcon("open", E_IconSize.ICO_SIZE_16x16));
			putValue(SHORT_DESCRIPTION, "Open File");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser();
			chooser.setFileFilter(new FileNameExtensionFilter("Properties file", "properties"));
			chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
			int result = chooser.showOpenDialog(PropertiesEditor.this);
			if (result == JFileChooser.APPROVE_OPTION) {
				try {
					kvTable.loadFile(chooser.getSelectedFile().getAbsolutePath());
				} catch (IOException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(PropertiesEditor.this, 
							"Cannot load file", "file", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	/**
	 * Components in frame
	 */
	protected PropertiesTable kvTable;
	protected JToolBar toolbar;
}
