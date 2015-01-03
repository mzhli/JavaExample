package me.mzhli.javaexample.gui;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class PropertiesTable extends JTable {

	public PropertiesTable() {
		setModel(new PropertiesTableModel());
		setFillsViewportHeight(true);
	}

	public void loadFile(String path) throws IOException {
		// Create object associated to properties file
		FileReader reader = new FileReader(new File(path));
		Properties prop = new Properties();
		try {
			prop.load(reader);	
		} finally {
			reader.close();
		}
		// Model parses the data
		((PropertiesTableModel)getModel()).loadProperties(prop);
		// Refresh view
		this.revalidate();
	}
	
	public void saveFile() throws IOException {
		
	}
	
	public void load(InputStream in) throws IOException {
		
	}
	
	public void save(OutputStream out) throws IOException {
		
	}
	
	public void saveAs(String newfile) throws IOException {
		
	}
	
	/**
	 * Data model for Properties table class
	 */
	private static class PropertiesTableModel extends AbstractTableModel {
		
		public PropertiesTableModel() {
			kvList = new ArrayList<KVPair>(10);
			isModified = false;
		}
	
		public void loadProperties(Properties prop) {
			this.prop = prop;
			reload();
		}

		@Override
		public int getRowCount() {
			return kvList.size();
		}

		@Override
		public int getColumnCount() {
			return 2;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			if (rowIndex < kvList.size()) {
				switch(columnIndex) {
				case 0: // "Key"
					return kvList.get(rowIndex).getKey();
				case 1: // "Value"
					return kvList.get(rowIndex).getVal();
				}
			}
			return null;
		}

		/* (non-Javadoc)
		 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
		 */
		@Override
		public String getColumnName(int column) {
			switch (column) {
			case 0:
				return "KEY";
			case 1:
				return "VALUE";
			default:
				return "";
			}
		}
		
		/* (non-Javadoc)
		 * @see javax.swing.table.AbstractTableModel#isCellEditable(int, int)
		 */
		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return columnIndex == 1;
		}
	
		/* (non-Javadoc)
		 * @see javax.swing.table.AbstractTableModel#setValueAt(java.lang.Object, int, int)
		 */
		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
//			assert columnIndex == 1;
			String oldVal = (String)getValueAt(rowIndex, columnIndex);
			if (! oldVal.equals(aValue)) {
				String key = (String)getValueAt(rowIndex, 0);
				prop.setProperty(key, (String)aValue);
				kvList.set(rowIndex, new KVPair(key, aValue));
				isModified = true; // mark as modified
			}
		}

		protected void reload() {
			assert prop != null;
			kvList.clear();
			Enumeration<Object> enumKeys = prop.keys();
			while (enumKeys.hasMoreElements()) {
				String key = (String)enumKeys.nextElement();
				String val = (String)prop.getProperty(key);
				kvList.add(new KVPair(key, val));
			}
		}
		
		/**
		 * The element which is stored in key list for quick access
		 */
		private static class KVPair {
			public KVPair(Object k, Object v) {
				key = k;
				val = v;
			}

			/**
			 * @return the key
			 */
			public Object getKey() {
				return key;
			}
			/**
			 * @return the val
			 */
			public Object getVal() {
				return val;
			}
			
			private Object key;
			private Object val;
		}
		
		private Properties prop;
		private ArrayList<KVPair> kvList;
		boolean isModified;
	}
	
}
