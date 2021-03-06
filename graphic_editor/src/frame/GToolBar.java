package frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

import contant.GConstants.EToolBarButton;
import frame.GDrawingPanel;

public class GToolBar extends JToolBar {
	private static final long serialVersionUID = 1L;
	// association	
	private GDrawingPanel drawingPanel;
	public GToolBar() {
		ButtonGroup buttonGroup = new ButtonGroup();
		ActionHandler actionHandler = new ActionHandler();
		for (EToolBarButton eToolBarButton: EToolBarButton.values()) {
			JRadioButton button = new JRadioButton();
			//attributes of components
			button.setIcon(new ImageIcon(eToolBarButton.getIconName()));
			button.setSelectedIcon(new ImageIcon(eToolBarButton.getSelectedIconName()));
			this.add(button);
			buttonGroup.add(button);
			button.addActionListener(actionHandler);
			button.setActionCommand(eToolBarButton.toString());
			System.out.println(eToolBarButton.toString());
		}
		JRadioButton button = new JRadioButton();
		button.setIcon(new ImageIcon("rsc/anchor.gif"));
		button.setSelectedIcon(new ImageIcon("rsc/anchorSLT.gif"));
		this.add(button);
		JRadioButton button2 = new JRadioButton();
		button2.setIcon(new ImageIcon("rsc/text.gif"));
		button2.setSelectedIcon(new ImageIcon("rsc/textSLT.gif"));
		this.add(button2);
		button2.addActionListener(actionHandler);
		button2.setActionCommand("text");
		buttonGroup.add(button);
		buttonGroup.add(button2);
		button.addActionListener(actionHandler);
		button.setActionCommand("anchor");
	}
	public void initialize(GDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
		JRadioButton button = (JRadioButton) this.getComponentAtIndex(EToolBarButton.rectangle.ordinal());
		button.doClick();
	}
	public class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("text")){
				drawingPanel.setSelectedSkill(e.getActionCommand());
			}
			else if(e.getActionCommand().equals("anchor")){
				drawingPanel.setSelectedSkill(e.getActionCommand());
			}else{
				drawingPanel.setSelectedShape(
						EToolBarButton.valueOf(e.getActionCommand()).getShape());
				drawingPanel.textstate=drawingPanel.textstate.textready;
				
			}
		}
	}
	
}
