package me.mzhli.javaexample.util;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;

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
		JButton btnOpenFile = new JButton(IconStore.getInstance().getIcon(
				"open", E_IconSize.ICO_SIZE_16x16));
		btnOpenFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(PropertiesEditor.this,
						"Clicked 'OpenFile' button", "Message",
						JOptionPane.PLAIN_MESSAGE);
			}
		});
		toolbar.add(btnOpenFile);
		toolbar.setFloatable(false);
		add(toolbar, BorderLayout.NORTH);
		
		// Table with 2 columns
		kvTable = new JTable(new String[][]{{ "Key1", "Value1"}}, new String[]{ "Key", "Value" });
		add(new JScrollPane(kvTable), BorderLayout.CENTER);
	}

	/**
	 * Components in frame
	 */
	protected JTable kvTable;
	protected JToolBar toolbar;
}
