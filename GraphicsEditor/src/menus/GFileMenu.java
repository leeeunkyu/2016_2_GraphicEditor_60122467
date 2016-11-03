package menus;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import contant.GConstants;

public class GFileMenu extends JMenu {
	
	private static final long serialVersionUID = 1L;
	
	public GFileMenu(){
	
		super(GConstants.FILEMENU_TITLE);
	
		for(GConstants.EFileMenuItem eMenuItem: GConstants.EFileMenuItem.values()){
			JMenuItem menuItem = new JMenuItem(eMenuItem.getText());
			this.add(menuItem);
		}
		
	}

	
}
