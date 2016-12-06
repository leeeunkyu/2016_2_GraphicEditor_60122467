package menus;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import contant.GConstants;
import contant.GConstants.EFileMenuItem;
import frame.GDrawingPanel;
import shapes.GShape;

public class GFileMenu extends JMenu {
	private static final long serialVersionUID = 1L;
	// association
	private GDrawingPanel drawingPanel;
	File file;
	public GFileMenu() {
		super(GConstants.FILEMENU_TITLE);
		ActionHandler actionHandler = new ActionHandler();
		for (EFileMenuItem eMenuItem: EFileMenuItem.values()) {
			JMenuItem menuItem = new JMenuItem(eMenuItem.getText());
			menuItem.addActionListener(actionHandler);
			menuItem.setActionCommand(eMenuItem.name());
			this.add(menuItem);
		}
	}
	public void initialize(GDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
	}
	private int showOpenDialog() {
		JFileChooser fileChooser = new JFileChooser(new File("."));
		int reply;
		FileNameExtensionFilter filter = new FileNameExtensionFilter("GraphicEditor", "gps");
		fileChooser.setFileFilter(filter);
		reply = fileChooser.showOpenDialog(null);
		file = fileChooser.getSelectedFile();
		return reply;
	}
	private void open() {
		if (showDialog() != JOptionPane.OK_OPTION) {
			return;
		}
		try {
			ObjectInputStream inputStream;
			inputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
			drawingPanel.setShapeManagers((Vector<GShape>)inputStream.readObject());
			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}	
	}
	private int showDialog() {
		JFileChooser chooser = new JFileChooser(".");
	    FileNameExtensionFilter filter = new FileNameExtensionFilter( "Graphics Editor", "gps");
	    chooser.setFileFilter(filter);
	    int returnVal = chooser.showSaveDialog(this);
//	    if(returnVal == JFileChooser.APPROVE_OPTION) {
//	    	return chooser.getSelectedFile().getName();
//	    }
//	    return null;		
		File renameFile = chooser.getSelectedFile();
		String extension = ".gps";
		if(renameFile != null) {
			if(renameFile.getName().contains(extension))
				file = new File(renameFile.getName());
			else
				file = new File(renameFile.getName() + extension);
		}
		return returnVal;
	}
	private void save() {
		
		if (showDialog() != JOptionPane.OK_OPTION) {
			return;
		}
		try {
			ObjectOutputStream outputStream;
			outputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
			outputStream.writeObject(drawingPanel.getShapeVector());
			outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void close(){
		if(!(drawingPanel.isShapeexist())){
			System.exit(1);
			System.out.println("tessss");
		}
		else{
			int returnVal=JOptionPane.showConfirmDialog(null,"변경사항을 저장하시겠습니까?");
			if(returnVal==JOptionPane.OK_OPTION){
				save();
				System.exit(1);

			}else{
				System.exit(1);
			}
		}
	}
	public void newfile(){
		if(!(drawingPanel.isShapeexist())){
			drawingPanel.intitpanel();
		}else{
			int returnVal=JOptionPane.showConfirmDialog(null,"변경사항을 저장하시겠습니까?");
			if(returnVal==JOptionPane.OK_OPTION){
				save();
				drawingPanel.intitpanel();
			}else{
				drawingPanel.intitpanel();
			}
		}
	}
	private class ActionHandler implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (event.getActionCommand().equals(EFileMenuItem.open.name())) {
				open();
			} else if (event.getActionCommand().equals(EFileMenuItem.save.name())) {
				save();
				
			} else if(event.getActionCommand().equals(EFileMenuItem.close.name())){
				close();
			} else if(event.getActionCommand().equals(EFileMenuItem.newItem.name())){
				newfile();
			}

		}		
	}

}
