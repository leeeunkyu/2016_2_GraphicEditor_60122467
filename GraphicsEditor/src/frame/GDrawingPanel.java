package frame;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import contant.GConstants;
import contant.GConstants.EAnchors;
import contant.GConstants.EDrawingType;
import shapes.GCurSor;
import shapes.Anchors;
import shapes.GShape;

public class GDrawingPanel extends JPanel {
   // attributes
   private static final long serialVersionUID = 1L;
   // object states
   private enum EState {idleTP,idleNP,drawingTP,drawingNP,transforming};
   private enum Anchorstate {anchorready,anchorpush};
   //components;
   public Vector<GShape> shapeVector;
   public Vector<GShape> anchorVector;
   
   Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
	Cursor hourglass = new Cursor(Cursor.DEFAULT_CURSOR);
   // working variables
   
   private GShape currentShape;
   private GShape currentanchor;
   // associative attributes
   private GShape selectedShape;
   private Anchorstate anchor;
   private EState eState=EState.idleTP;
   public void setSelectedShape(GShape selectedShape) {
      this.selectedShape = selectedShape;
      anchor = Anchorstate.anchorready;
    switch (this.selectedShape.geteDrawingType()) {
		case TP:
			eState=EState.idleTP; break;
		case NP:
			eState=EState.idleNP; break;
		default:
			break;
	}
   }
   public void setSelectedSkill(){
	   	anchor = Anchorstate.anchorpush;
   } 
   
   
   public GDrawingPanel() {
      MouseEventHandler mouseEventHandler = new MouseEventHandler();
      this.addMouseListener(mouseEventHandler);
      this.addMouseMotionListener(mouseEventHandler);
      shapeVector = new Vector<GShape>();
      anchorVector = new Vector<GShape>();
   }
   public void initialize() {
      // TODO Auto-generated method stub
      
   }
   public void paint(Graphics g) {
	  super.paint(g);
      for(GShape shape : this.shapeVector){
         shape.draw((Graphics2D)g);
      }
      for(GShape anc : this.anchorVector){
    	  anc.draw((Graphics2D)g);
      }
   } 
	private void resetSelected() {
		for (GShape shape: this.shapeVector) {
			shape.setSelected(false);
		}
		this.repaint();
	}
   private void initDrawing(int x, int y){
	  this.resetSelected();
      this.currentShape = this.selectedShape.clone();
      Graphics2D g2D = (Graphics2D) this.getGraphics();
      g2D.setXORMode(this.getBackground());
      this.currentShape.initDrawing(x,y,g2D);
   }
   private void keepDrawing(int x, int y){
      Graphics2D g2D = (Graphics2D) this.getGraphics();
      g2D.setXORMode(this.getBackground());
      this.currentShape.keepDrawing(x,y,g2D);
   }
   private void continueDrawing(int x, int y){
      Graphics2D g2D = (Graphics2D) this.getGraphics();
      g2D.setXORMode(this.getBackground());
      this.currentShape.continueDrawing(x,y,g2D);
   }
   private void finishDrawing(int x, int y){
      Graphics2D g2D = (Graphics2D) this.getGraphics();
      g2D.setXORMode(this.getBackground());
      this.currentShape.finishDrawing(x,y,g2D);
      this.shapeVector.add(this.currentShape);
   }
	private void initTransforming(int x, int y) {
		this.resetSelected();
		Graphics2D g2D = (Graphics2D)this.getGraphics();
		g2D.setXORMode(this.getBackground());
		switch (this.currentShape.getCurrentEAnchor()) {
		case NN:
			this.currentShape.initResizing(x, y, g2D);
			break;
		case NE:
			this.currentShape.initResizing(x, y, g2D);
			break;
		case NW:
			this.currentShape.initResizing(x, y, g2D);
			break;
		case SS:
			this.currentShape.initResizing(x, y, g2D);
			break;
		case SE:
			this.currentShape.initResizing(x, y, g2D);
			break;
		case SW:
			this.currentShape.initResizing(x, y, g2D);
			break;
		case EE:
			this.currentShape.initResizing(x, y, g2D);
			break;
		case WW:
			this.currentShape.initResizing(x, y, g2D);
			break;
		case RR:
			break;
		case MM:
			this.currentShape.initTransforming(x, y, g2D);
			break;
		}
	}
	private void keepTransforming(int x, int y) {
		Graphics2D g2D = (Graphics2D)this.getGraphics();
		g2D.setXORMode(this.getBackground());
		
		switch (this.currentShape.getCurrentEAnchor()) {
		case NN:
			this.currentShape.keepResizing(x, y, g2D);
			break;
		case NE:
			this.currentShape.keepResizing(x, y, g2D);
			break;
		case NW:
			this.currentShape.keepResizing(x, y, g2D);
			break;
		case SS:
			this.currentShape.keepResizing(x, y, g2D);
			break;
		case SE:
			this.currentShape.keepResizing(x, y, g2D);
			break;
		case SW:
			this.currentShape.keepResizing(x, y, g2D);
			break;
		case EE:
			this.currentShape.keepResizing(x, y, g2D);
			break;
		case WW:
			this.currentShape.keepResizing(x, y, g2D);
			break;
		case RR:
			break;
		case MM:
			this.currentShape.keepTransforming(x, y, g2D);
			break;
		}
	}
	private void finishTransforming(int x, int y) {
		Graphics2D g2D = (Graphics2D)this.getGraphics();
		g2D.setXORMode(this.getBackground());
		switch (this.currentShape.getCurrentEAnchor()) {
		case NN:
			this.currentShape.finishResizing(x, y, g2D);
			break;
		case NE:
			this.currentShape.finishResizing(x, y, g2D);
			break;
		case NW:
			this.currentShape.finishResizing(x, y, g2D);
			break;
		case SS:
			this.currentShape.finishResizing(x, y, g2D);
			break;
		case SE:
			this.currentShape.finishResizing(x, y, g2D);
			break;
		case SW:
			this.currentShape.finishResizing(x, y, g2D);
			break;
		case EE:
			this.currentShape.finishResizing(x, y, g2D);
			break;
		case WW:
			this.currentShape.finishResizing(x, y, g2D);
			break;
		case RR:
			break;
		case MM:
			this.currentShape.finishTransforming(x, y, g2D);
			break;
		}
		this.currentShape.setSelected(true);
		this.repaint();
	}
	private GShape onShape(int x, int y) {
		for (GShape shape: this.shapeVector) {
			GConstants.EAnchors eAnchor = shape.contanins(x, y);
			if (eAnchor != null)
				return shape;
		}
		return null;
	}
   private void changeAnchors(int x, int y){
	   for (int i=0;i<shapeVector.size();i++) {
		   if(anchor == Anchorstate.anchorpush && shapeVector.get(i).contains2(x, y)){
			   Graphics2D g2D = (Graphics2D) this.getGraphics();
			   shapeVector.get(i).drawAnchors(g2D);
			   //this.currentShape = this.selectedShape.clone();
			   this.shapeVector.add(this.currentShape);
			   break;  
		}  
	   }
   }
   
   private void changeCursor(int x, int y) {
		for (GShape shape: this.shapeVector) {
			EAnchors eAnchor = shape.contanins(x, y);
			if(eAnchor != null){
				switch(eAnchor){
					case NN: this.setCursor(GCurSor.N_RESIZE()); return;
					case NE: this.setCursor(GCurSor.NE_RESIZE()); return;
					case NW: this.setCursor(GCurSor.NW_RESIZE()); return;
					case SS: this.setCursor(GCurSor.S_RESIZE()); return;
					case SW: this.setCursor(GCurSor.SW_RESIZE());return;
					case SE: this.setCursor(GCurSor.SE_RESIZE()); return;
					case EE: this.setCursor(GCurSor.E_RESIZE()); return;
					case WW: this.setCursor(GCurSor.W_RESIZE()); return;
					case RR: this.setCursor(GCurSor.RR()); return;
					case MM: this.setCursor(GCurSor.MOVE()); return;
				}
			}
	
		}
			this.setCursor(GCurSor.defaultcursor());
	}
   
   class MouseEventHandler implements MouseInputListener, MouseMotionListener {

      @Override
      public void mouseClicked(MouseEvent e) {
         if(e.getClickCount() == 1){
            mouse1Clicked(e);
         }else if(e.getClickCount() == 2){
            mouse2Clicked(e);
         }
      }
      private void mouse1Clicked(MouseEvent e) {
         if (eState == EState.idleNP && anchor == Anchorstate.anchorready ) {
               initDrawing(e.getX(), e.getY());
               eState = EState.drawingNP;
         }else if(eState == EState.drawingNP){
            continueDrawing(e.getX(), e.getY());
         }else if(eState == EState.idleTP || eState == EState.idleNP && anchor == Anchorstate.anchorpush){
        	 //¿¨Ä¿
        	 changeAnchors(e.getX(), e.getY());
         }
      }
      private void mouse2Clicked(MouseEvent e) {
         if (eState == EState.drawingNP) {      
            finishDrawing(e.getX(), e.getY());
            eState = EState.idleNP;
         }
      }
      @Override
      public void mousePressed(MouseEvent e) {
    	if (eState == EState.idleTP) {
    		currentShape = onShape(e.getX(), e.getY());
    	  if (currentShape == null && anchor == Anchorstate.anchorready) {
            initDrawing(e.getX(), e.getY());
            eState = EState.drawingTP;
           
         }
    	  else {
				initTransforming(e.getX(), e.getY());
				eState = EState.transforming;
			}
    	}
      }
      @Override
      public void mouseReleased(MouseEvent e) {
         if (eState == EState.drawingTP && anchor == Anchorstate.anchorready) {      
            finishDrawing(e.getX(), e.getY());
            eState = EState.idleTP;
         }else if (eState == EState.transforming) {
				finishTransforming(e.getX(), e.getY());
				eState = EState.idleTP;
			} 
      }
      @Override
      public void mouseMoved(MouseEvent e) {
         if (eState == EState.drawingNP) {      
            keepDrawing(e.getX(), e.getY());
         }else if (eState == EState.idleTP || eState == EState.idleNP) {
        	 changeCursor(e.getX(), e.getY());
			}
      }      
      @Override
      public void mouseDragged(MouseEvent e) {
         if (eState == EState.drawingTP) {      
            keepDrawing(e.getX(), e.getY());
         }else if (eState == EState.transforming) {
				keepTransforming(e.getX(), e.getY());				
			}
      }
      @Override
      public void mouseEntered(MouseEvent arg0) {
      }
      @Override
      public void mouseExited(MouseEvent arg0) {
      }
   }
}



   



   
