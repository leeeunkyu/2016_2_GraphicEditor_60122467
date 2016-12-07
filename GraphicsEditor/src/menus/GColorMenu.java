package menus;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import contant.GConstants;
import contant.GConstants.EColorMenuItem;
import contant.GConstants.EFileMenuItem;
import frame.GDrawingPanel;
import frame.GToolBar.ActionHandler;

public class GColorMenu extends JMenu {
	private GDrawingPanel drawingPanel;
	public GColorMenu(){
		super(GConstants.COLORMENU_TITLE);
		ActionHandler actionHandler = new ActionHandler();
		for (GConstants.EColorMenuItem eColorItem: GConstants.EColorMenuItem.values()) {
			JMenuItem menuItem = new JMenuItem(eColorItem.getText());
			menuItem.addActionListener(actionHandler);
			menuItem.setActionCommand(eColorItem.name());
			this.add(menuItem);
		}
	}
	public void initialize(GDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;		
	}
	private class ActionHandler implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (event.getActionCommand().equals(EColorMenuItem.fill.name())) {
				setfillcolor();
			} else if (event.getActionCommand().equals(EColorMenuItem.line.name())) {
				setlinecolor();
				
			} 
		}

		private void setlinecolor() {
			// TODO Auto-generated method stub
			Color lineColor = JColorChooser.showDialog(null, "Select line color", null);
			drawingPanel.setLineColor(lineColor);

		}

		private void setfillcolor() {
			// TODO Auto-generated method stub
			Color fillColor = JColorChooser.showDialog(null,"Select fill color", null);
			System.out.println(fillColor);
			drawingPanel.setFillColor(fillColor);

		}		
	}
}
