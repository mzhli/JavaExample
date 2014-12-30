package me.mzhli.javaexample.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class IconStore {
	
	/**
	 * Enum of ico size
	 */
	public static enum E_IconSize {
		ICO_SIZE_16x16("16x16"),
		ICO_SIZE_24x24("24x24"),
		ICO_SIZE_32x32("32x32"),
		ICO_SIZE_48x48("48x48");
		
		public String desp;
		
		private E_IconSize(String desp) {
			this.desp = desp;
		}
	}
	
	public static IconStore getInstance() {
		return singleton;
	}
	
	/**
	 * Get the icon by name of target size
	 * @param name icon name
	 * @param size any valid value in E_IconSize
	 * @return object of icon if success, otherwise return null
	 */
	public Icon getIcon(String name, E_IconSize size) {
		String icoName = name.toLowerCase() + "@" + size.desp;
		Icon ico = mapIcon.get(icoName);
		return ico;
	}
	
	/**
	 * List all the icon names in the format of 'iconame@size'
	 * @return array containing all the icon names
	 */
	public String[] listIconNames() {
		return mapIcon.keySet().toArray(new String[0]);
	}
	
	/**
	 * Constructor which will load all the icons registered in the properties file
	 */
	private IconStore() {
		InputStream istream = getClass().getResourceAsStream(ICON_INVENTORY_LIST_PATH);
		if (istream != null) {
			Properties prop = new Properties();
			try {
				prop.load(istream);
			} catch (IOException e) {
				System.err.printf("[Error] Cannot load icon inventory list file '%s'\n", ICON_INVENTORY_LIST_PATH);
				return;
			}
			
			for (Entry<Object, Object> entry : prop.entrySet()) {
				URL urlIcon = getClass().getResource(RESOURCE_ICON_BASEDIR + ((String)entry.getValue()).replace("\"", ""));
				if (urlIcon != null) {
					try {
						Icon ico = new ImageIcon(ImageIO.read(urlIcon));
						mapIcon.put(((String)entry.getKey()).toLowerCase(), ico);
					} catch (IOException e) {
						System.err.printf("[Error] Failed to load icon '%s'\n", urlIcon);
					}
				}
			}
		}
	}
	
	private static final String RESOURCE_ICON_BASEDIR = "/images/icons/";
	private static final String ICON_INVENTORY_LIST_PATH = "/images/IconInventoryList.properties";
	private static final IconStore singleton = new IconStore();
	
	private Map<String, Icon> mapIcon = new HashMap<String, Icon>();
	
	
	public static void main(String[] args) {
		IconStore is = IconStore.getInstance();
		System.out.println(Arrays.toString(is.listIconNames()));
	}
}
