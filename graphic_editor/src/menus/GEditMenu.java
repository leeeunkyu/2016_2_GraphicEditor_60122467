package menus;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import contant.GConstants;
import contant.GConstants.EEditMenuItem;
import frame.GDrawingPanel;
import shapes.GGroupManager;
import shapes.GShape;

public class GEditMenu extends JMenu {
	private static final long serialVersionUID = 1L;
	// association
	private GDrawingPanel drawingPanel;
	private Vector<GShape> saves;
	private Vector<GShape> temps;
	private GGroupManager currentGroup;
	private Vector<GGroupManager> groups;


	public GEditMenu() {
		super(GConstants.EDITMENU_TITLE);
		ActionHandler actionHandler = new ActionHandler();
		saves = new Vector<GShape>();
		temps = new Vector<GShape>();
		currentGroup=null;
		groups = new Vector<GGroupManager>();
		for (GConstants.EEditMenuItem eMenuItem: GConstants.EEditMenuItem.values()) {
			JMenuItem menuItem = new JMenuItem(eMenuItem.getText());
			menuItem.addActionListener(actionHandler);
			menuItem.setActionCommand(eMenuItem.name());
			this.add(menuItem);
		}
	}
	public void initialize(GDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;		
	}
	private void undo() {
		// TODO Auto-generated method stub
		System.out.println("undotest");

		drawingPanel.undo();

	}

	private void redo() {
		// TODO Auto-generated method stub
		drawingPanel.redo();

	}
	public void cut() {
		saves.clear();
		for(GShape s : this.drawingPanel.getShapeVector()){
			temps.add(s);
		}
		for(GShape s : temps) {
			if(s.isSelected()) {
				try {
					saves.add((GShape)s.clone2());
					this.drawingPanel.getShapeVector().remove(s);
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	      }
	      drawingPanel.repaint();
	}
	private void cpoy() {
		// TODO Auto-generated method stub
		System.out.println("test");
		saves.clear();
		int i=0;
		for(GShape s : this.drawingPanel.getShapeVector()){
	//	GShape s=this.drawingPanel.currentShape;
//		System.out.println(this.drawingPanel.currentShape+"test");
			if(s.isSelected()) {
				try {
					saves.add(s.clone2());
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i++;
			}
		}
	}

	private void select() {
		// TODO Auto-generated method stub
		drawingPanel.resetSelected();
		for(GShape s : this.drawingPanel.getShapeVector()){
			s.setSelected(true);
		}
		repaint();
	
	}
	private void paste() {
		// TODO Auto-generated method stub
		System.out.println("test2");
		drawingPanel.resetSelected();
		for(GShape s : saves) {
			s.setSelected(true);
			try {
				this.drawingPanel.shapeVector.add(s.clone2());
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		drawingPanel.repaint();
	}	
	private void group() {
		// TODO Auto-generated method stub
		this.drawingPanel.group(new GGroupManager());

	}

	private class ActionHandler implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (event.getActionCommand().equals(EEditMenuItem.paste.name())) {
				paste();
			} else if (event.getActionCommand().equals(EEditMenuItem.copy.name())) {
				cpoy();
				
			} else if(event.getActionCommand().equals(EEditMenuItem.redo.name())){
				redo();
			} else if(event.getActionCommand().equals(EEditMenuItem.undo.name())){
				undo();
			}else if(event.getActionCommand().equals(EEditMenuItem.select.name())){
				select();
			}
			else if(event.getActionCommand().equals(EEditMenuItem.cut.name())){
				cut();
			}else if(event.getActionCommand().equals(EEditMenuItem.group.name())){
				group();
			}

		}

	}
}
